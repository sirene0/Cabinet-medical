
package GCM;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Gcabinetmedical {
    private ArrayList<Gordonnance> ordonnances = new ArrayList<>();
    private ArrayList<Gfichepatient> fiches = new ArrayList<>();
    private ArrayList<Grendezvous> rdvLIST = new ArrayList<>();
    private final LocalTime HEURE_DEBUT = LocalTime.of(8, 0); // 08:00
    private final LocalTime HEURE_FIN = LocalTime.of(18, 0); // 18:00

    public void ajouterpatient(Gpatient patient) {
        fiches.add(new Gfichepatient(patient));
        System.out.println("Patient ajoute avec succes !");
    }

    public void listerPatients() {
        if (fiches.isEmpty()) {
            System.out.println("Aucun patient enregistre !");
        } else {
            for (var i = 0; i < fiches.size(); i++) {
                System.out.println((i + 1) + "." + fiches.get(i).getPatient().getNom() + " "
                        + fiches.get(i).getPatient().getPrenom() + " "
                        + fiches.get(i).getPatient().getDatedenaissannce()
                        + " " + fiches.get(i).getPatient().getTelephone());
            }
        }
    }

    public Gfichepatient rechercherFichePatient(int idPatient) {
        for (Gfichepatient fiche : fiches) {
            if (fiche.getPatient().getId() == idPatient) {
                return fiche;
            }
        }
        return null;
    }

    public void ajouterRendezVous(Gpatient patient, LocalDate date, LocalTime heure) {
        for (Grendezvous rdv : rdvLIST) {
            if (rdv.getdate() != null && rdv.gettime() != null) {
                if (rdv.getdate().equals(date) && rdv.gettime().equals(heure)) {
                    System.out.println("ERREUR: Un rendez-vous existe déjà à cette date et heure (" +
                            rdv.getpatient().getNom() + " " + rdv.getpatient().getPrenom() + ")");
                    return;
                }
            }
        }

        // Si aucun conflit, créer le nouveau rendez-vous
        int newId = rdvLIST.size() + 1;
        Grendezvous newRdv = new Grendezvous(newId, patient, date, heure);
        rdvLIST.add(newRdv);
        System.out.println("Rendez-vous ajouté avec succès pour " + patient.getNom() + " " +
                patient.getPrenom() + " le " + date + " à " + heure);
    }

    public void listerRendezVous() {
        if (rdvLIST.isEmpty()) {
            System.out.println("Aucun rendez-vous enregistré.");
        } else {
            System.out.println("\nListe des rendez-vous :");
            for (Grendezvous rdv : rdvLIST) {
                if (rdv.getpatient() != null && rdv.getdate() != null && rdv.gettime() != null) {
                    System.out.println("RDV #" + rdv.getId() + " - Patient: " +
                            rdv.getpatient().getNom() + " " + rdv.getpatient().getPrenom() +
                            " - Date: " + rdv.getdate() + " - Heure: " + rdv.gettime());
                }
            }
        }
    }

    public void afficherDossier(int idPatient) {
        Gfichepatient fiche = rechercherFichePatient(idPatient);
        if (fiche != null) {
            System.out.println(fiche.getDossier());
        } else {
            System.out.println("Aucun dossier trouvé pour l'ID : " + idPatient);
        }
    }

    public void remplirDossier(Gpatient patient, double poids, double taille,
            ArrayList<String> antecedentsMedicaux, ArrayList<String> antecedentsChirurgicaux) {

        // Rechercher la fiche du patient
        Gfichepatient fiche = rechercherFichePatient(patient.getId());
        if (fiche != null) {
            Gdossiermedical dossier = fiche.getDossier();

            // Mettre à jour les informations de poids et de taille
            dossier.setPoids(poids);
            dossier.setTaille(taille);

            // Ajouter les antécédents médicaux
            for (String antecedent : antecedentsMedicaux) {
                dossier.ajouteranteciedentmedicaux(antecedent); // Correction du nom de la méthode
            }

            // Ajouter les antécédents chirurgicaux
            for (String antecedent : antecedentsChirurgicaux) {
                dossier.ajouteranteciedentchirurgicaux(antecedent); // Correction du nom de la méthode
            }

            patient.setDossier(dossier);

            System.out.println("Dossier médical mis à jour avec succès.");
        } else {
            System.out.println("Patient non trouvé : " + patient.getNom() + " " + patient.getPrenom());
        }

    }

    public void afficherHorairesDisponibles(LocalDate date) {
        System.out.println("\nHoraires disponibles pour le " + date + " :");
        ArrayList<LocalTime> heuresPrises = new ArrayList<>();

        for (Grendezvous rdv : rdvLIST) {
            if (rdv.getdate().equals(date)) {
                heuresPrises.add(rdv.gettime());
            }
        }

        LocalTime heure = HEURE_DEBUT;
        while (!heure.isAfter(HEURE_FIN)) {
            if (!heuresPrises.contains(heure)) {
                System.out.println(" - " + heure);
            }
            heure = heure.plusHours(1); // Incrémenter d'une heure
        }
    }

    public Gpatient rechercherPatient(String nom, String dateNaissance) {
        for (Gfichepatient fiche : fiches) {
            Gpatient patient = fiche.getPatient();
            if (patient.getNom().equalsIgnoreCase(nom) &&
                    patient.getDatedenaissannce().equalsIgnoreCase(dateNaissance)) {
                return patient;
            }
        }
        return null;
    }

    // Méthode pour supprimer un rendez-vous
    public void supprimerRendezVous(int idxRendezVous) {
        if (idxRendezVous >= 0 && idxRendezVous < rdvLIST.size()) {
            rdvLIST.remove(idxRendezVous);
            System.out.println("Rendez-vous supprimé avec succès.");
        } else {
            System.out.println("Index de rendez-vous invalide.");
        }
    }

    // Méthode pour afficher historique complet
    public void afficherHistoriquePatient(String nom, String dateNaissance) {
        Gpatient patient = rechercherPatient(nom, dateNaissance);
        if (patient != null) {
            System.out.println("\n=== Historique du patient ===");
            System.out.println("Dossier médical :");
            if (patient.getDossier() != null) {
                System.out.println(patient.getDossier().toString());
            }

            System.out.println("\nOrdonnances :");
            for (Gordonnance ord : ordonnances) {
                if (ord.getpatient().equals(patient)) {
                    ord.afficherOrdonnance();
                }
            }

            System.out.println("\nRendez-vous :");
            for (int i = 0; i < rdvLIST.size(); i++) {
                if (rdvLIST.get(i).getpatient().equals(patient)) {
                    System.out.println(i + ". " + rdvLIST.get(i));
                }
            }
        }
    }

    public void creerCertificatMedical(Gpatient patient, String motif, int duree) {
        System.out.println("\n=== CERTIFICAT MEDICAL ===");
        System.out.println("Je soussigné, Dr. [Nom du médecin]");
        System.out.println("Certifie avoir examiné :");
        System.out.println(patient.getNom() + " " + patient.getPrenom());
        System.out.println("Né(e) le : " + patient.getDatedenaissannce());
        System.out.println("\n" + motif);
        System.out.println("Durée : " + duree + " jours");
        System.out.println("\nFait le : " + LocalDate.now());
        System.out.println("Signature : ____________");
    }

    // RE
    public ArrayList<Gfichepatient> getFiches() {
        return fiches;
    }

    public void setFiches(ArrayList<Gfichepatient> fiches) {
        this.fiches = fiches;
    }

    public void creerOrdonnance(Gpatient patient, String medecin, ArrayList<String> medicaments,
            ArrayList<String> doses, ArrayList<String> durees, ArrayList<String> instructions) {
        Gordonnance ordonnance = new Gordonnance(patient, medecin);
        for (int i = 0; i < medicaments.size(); i++) {
            ordonnance.ajouterMedicament(medicaments.get(i), doses.get(i), durees.get(i), instructions.get(i));
        }
        ordonnances.add(ordonnance);
        System.out.println(
                "Ordonnance créée avec succès pour le patient : " + patient.getNom() + " " + patient.getPrenom());
    }

    // Afficher toutes les ordonnances
    public void afficherOrdonnances() {
        if (ordonnances.isEmpty()) {
            System.out.println("Aucune ordonnance enregistrée.");
        } else {
            System.out.println("\nListe des ordonnances :");
            for (Gordonnance ordonnance : ordonnances) {
                System.out.println(ordonnance);
            }
        }

    }

}
/*
 * package GCM;
 * 
 * import java.util.ArrayList;
 * import java.time.LocalDate;
 * import java.time.LocalTime;
 * 
 * public class Gcabinetmedical {
 * private ArrayList<Gfichepatient> fiches = new ArrayList<>();
 * private ArrayList<Grendezvous> rdvLIST = new ArrayList<>();
 * 
 * public void ajouterpatient(Gpatient patient,Gdossiermedical dossier) {
 * fiches.add(new Gfichepatient(patient));
 * fiches.add(new Gfichepatient(dossier));
 * System.out.println("Patient ajoute avec succes !");
 * }
 * public void listerPatients() {
 * if (fiches.isEmpty()) {
 * System.out.println("Aucun patient enregistre !");
 * } else {
 * for (var i = 0; i < fiches.size(); i++) {
 * System.out.println((i + 1) + "." + fiches.get(i).getPatient().getNom() + " "
 * + fiches.get(i).getPatient().getPrenom() + " "
 * + fiches.get(i).getPatient().getDatedenaissannce()
 * + " " + fiches.get(i).getPatient().getTelephone());
 * }
 * }
 * }
 * 
 * public void ajouterRendezVous(Gpatient patient, LocalDate date, LocalTime
 * heure) {
 * for (Grendezvous rdv : rdvLIST) {
 * if (rdv.getdate() != null && rdv.gettime() != null) {
 * if (rdv.getdate().equals(date) && rdv.gettime().equals(heure)) {
 * System.out.
 * println("ERREUR: Un rendez-vous existe déjà à cette date et heure (" +
 * rdv.getpatient().getNom() + " " + rdv.getpatient().getPrenom() + ")");
 * return;
 * }
 * }
 * }
 * 
 * // Si aucun conflit, créer le nouveau rendez-vous
 * int newId = rdvLIST.size() + 1;
 * Grendezvous newRdv = new Grendezvous(newId, patient, date, heure);
 * rdvLIST.add(newRdv);
 * System.out.println("Rendez-vous ajouté avec succès pour " + patient.getNom()
 * + " " +
 * patient.getPrenom() + " le " + date + " à " + heure);
 * }
 * public void listerRendezVous() {
 * if (rdvLIST.isEmpty()) {
 * System.out.println("Aucun rendez-vous enregistré.");
 * } else {
 * System.out.println("\nListe des rendez-vous :");
 * for (Grendezvous rdv : rdvLIST) {
 * if (rdv.getpatient() != null && rdv.getdate() != null && rdv.gettime() !=
 * null) {
 * System.out.println("RDV #" + rdv.getId() + " - Patient: " +
 * rdv.getpatient().getNom() + " " + rdv.getpatient().getPrenom() +
 * " - Date: " + rdv.getdate() + " - Heure: " + rdv.gettime());
 * }
 * }
 * }
 * }
 * 
 * public void afficherDossierMedical(int indexPatient) {
 * if (indexPatient >= 0 && indexPatient < fiches.size()) {
 * System.out.println(fiches.get(indexPatient).getGdossiermedical());
 * } else {
 * System.out.println("Patient invalide.");
 * }
 * }
 * // RE
 * public ArrayList<Gfichepatient> getFiches() {
 * return fiches;
 * }
 * 
 * public void setFiches(ArrayList<Gfichepatient> fiches) {
 * this.fiches = fiches;
 * }
 * }
 */