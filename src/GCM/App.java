package GCM;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Gcabinetmedical cabinet = new Gcabinetmedical();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            try {
                System.out.println("\n--- Menu Principal ---");
                System.out.println("1. Ajouter un patient");
                System.out.println("2. Lister les patients");
                System.out.println("3. Ajouter un rendez-vous");
                System.out.println("4. Lister les rendez-vous");
                System.out.println("5. Supprimer un rendez-vous");
                System.out.println("6. Afficher les horaires disponibles");
                System.out.println("7. Créer une ordonnance");
                System.out.println("8. Afficher les ordonnances");
                System.out.println("9. Remplir un dossier médical");
                System.out.println("10. Afficher dossier et historique");
                System.out.println("11. Rechercher un patient");
                System.out.println("12. Créer certificat médical");
                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        scanner.nextLine();
                        System.out.print("Nom : ");
                        String nom = scanner.nextLine();
                        System.out.print("Prénom : ");
                        String prenom = scanner.nextLine();
                        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                        String dateNaissance = scanner.nextLine();
                        System.out.print("Téléphone : ");
                        String telephone = scanner.nextLine();
                        cabinet.ajouterpatient(new Gpatient(0, nom, prenom, telephone, dateNaissance));
                        break;

                    case 2:
                        cabinet.listerPatients();
                        break;

                    case 3:
                        cabinet.listerPatients();
                        System.out.print("Nom du patient : ");
                        scanner.nextLine();
                        String nomPatient = scanner.nextLine();
                        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                        String dateNaissPatient = scanner.nextLine();
                        Gpatient patient = cabinet.rechercherPatient(nomPatient, dateNaissPatient);

                        if (patient != null) {
                            System.out.print("Date du rendez-vous (AAAA-MM-JJ) : ");
                            LocalDate date = LocalDate.parse(scanner.nextLine());
                            System.out.print("Heure du rendez-vous (HH:MM) : ");
                            LocalTime heure = LocalTime.parse(scanner.nextLine());
                            cabinet.ajouterRendezVous(patient, date, heure);
                        } else {
                            System.out.println("Patient non trouvé.");
                        }
                        break;

                    case 4:
                        cabinet.listerRendezVous();
                        break;

                    case 5:
                        cabinet.listerPatients();
                        cabinet.listerRendezVous();
                        System.out.print("Numéro du rendez-vous à supprimer : ");
                        int idxRdv = scanner.nextInt();
                        cabinet.supprimerRendezVous(idxRdv - 1);
                        break;

                    case 6:
                        LocalDate date = null;
                        while (date == null) {
                            try {
                                System.out.print("Date à vérifier (AAAA-MM-JJ) : ");
                                date = LocalDate.parse(scanner.nextLine());
                            } catch (DateTimeParseException e) {
                                System.out.println("Format de date invalide. Veuillez réessayer.");
                            }
                        }
                        cabinet.afficherHorairesDisponibles(date);
                        break;

                    case 7:
                        scanner.nextLine();
                        System.out.print("Nom du patient : ");
                        String nomOrd = scanner.nextLine();
                        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                        String dateNaissOrd = scanner.nextLine();
                        Gpatient patientOrd = cabinet.rechercherPatient(nomOrd, dateNaissOrd);

                        if (patientOrd != null) {
                            System.out.print("Nom du médecin : ");
                            String medecin = scanner.nextLine();
                            ArrayList<String> medicaments = new ArrayList<>();
                            ArrayList<String> doses = new ArrayList<>();
                            ArrayList<String> durees = new ArrayList<>();
                            ArrayList<String> instructions = new ArrayList<>();

                            System.out.println("Entrez les médicaments (fin pour terminer) :");
                            while (true) {
                                System.out.print("Nom du médicament : ");
                                String medicament = scanner.nextLine();
                                if (medicament.equalsIgnoreCase("fin"))
                                    break;
                                System.out.print("Dose : ");
                                String dose = scanner.nextLine();
                                System.out.print("Durée : ");
                                String duree = scanner.nextLine();
                                System.out.print("Instruction : ");
                                String instruction = scanner.nextLine();
                                medicaments.add(medicament);
                                doses.add(dose);
                                durees.add(duree);
                                instructions.add(instruction);
                            }
                            cabinet.creerOrdonnance(patientOrd, medecin, medicaments, doses, durees, instructions);
                        } else {
                            System.out.println("Patient non trouvé.");
                        }
                        break;

                    case 8:
                        System.out.print("Nom du patient : ");
                        scanner.nextLine();
                        String nomOrdAff = scanner.nextLine();
                        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                        String dateNaissOrdAff = scanner.nextLine();
                        cabinet.afficherOrdonnances();
                        break;

                    case 9:
                        scanner.nextLine(); // Clear the buffer
                        System.out.print("Nom du patient : ");
                        String nomDos = scanner.nextLine();
                        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                        String dateNaissDos = scanner.nextLine();
                        Gpatient patientDos = cabinet.rechercherPatient(nomDos, dateNaissDos);

                        if (patientDos != null) {
                            System.out.print("Poids (kg) : ");
                            double poids = scanner.nextDouble();
                            System.out.print("Taille (m) : ");
                            double taille = scanner.nextDouble();
                            scanner.nextLine(); // Clear the buffer

                            // Saisie des antécédents médicaux
                            ArrayList<String> antMed = new ArrayList<>();
                            System.out.println("Entrez les antécédents médicaux (fin pour terminer) :");
                            while (true) {
                                String ant = scanner.nextLine();
                                if (ant.equalsIgnoreCase("fin"))
                                    break; // Fin de la saisie
                                antMed.add(ant); // Ajout des antécédents
                            }

                            // Saisie des antécédents chirurgicaux
                            ArrayList<String> antChir = new ArrayList<>();
                            System.out.println("Entrez les antécédents chirurgicaux (fin pour terminer) :");
                            while (true) {
                                String ant = scanner.nextLine();
                                if (ant.equalsIgnoreCase("fin"))
                                    break; // Fin de la saisie
                                antChir.add(ant); // Ajout des antécédents
                            }

                            // Appel de la méthode pour remplir le dossier
                            cabinet.remplirDossier(patientDos, poids, taille, antMed, antChir);
                            System.out.println("Dossier médical rempli pour " + patientDos.getNom() + " "
                                    + patientDos.getPrenom());
                        } else {
                            System.out.println("Patient non trouvé.");
                        }
                        break;
                    /*
                     * scanner.nextLine();
                     * System.out.print("Nom du patient : ");
                     * String nomDos = scanner.nextLine();
                     * System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                     * String dateNaissDos = scanner.nextLine();
                     * Gpatient patientDos = cabinet.rechercherPatient(nomDos, dateNaissDos);
                     * 
                     * if (patientDos != null) {
                     * System.out.print("Poids (kg) : ");
                     * double poids = scanner.nextDouble();
                     * System.out.print("Taille (m) : ");
                     * double taille = scanner.nextDouble();
                     * scanner.nextLine();
                     * 
                     * ArrayList<String> antMed = new ArrayList<>();
                     * System.out.println("Antécédents médicaux (fin pour terminer) :");
                     * while (true) {
                     * String ant = scanner.nextLine();
                     * if (ant.equalsIgnoreCase("fin")) break;
                     * antMed.add(ant);
                     * }
                     * 
                     * ArrayList<String> antChir = new ArrayList<>();
                     * System.out.println("Antécédents chirurgicaux (fin pour terminer) :");
                     * while (true) {
                     * String ant = scanner.nextLine();
                     * if (ant.equalsIgnoreCase("fin")) break;
                     * antChir.add(ant);
                     * }
                     * cabinet.remplirDossier(patientDos, poids, taille, antMed, antChir);
                     * } else {
                     * System.out.println("Patient non trouvé.");
                     * }
                     * break;
                     */

                    case 10:
                        scanner.nextLine();
                        System.out.print("Nom : ");
                        String nomHist = scanner.nextLine();
                        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                        String dateHist = scanner.nextLine();
                        cabinet.afficherHistoriquePatient(nomHist, dateHist);
                        break;

                    case 11:
                        scanner.nextLine();
                        System.out.print("Nom : ");
                        String nomRech = scanner.nextLine();
                        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                        String dateNaissRech = scanner.nextLine();
                        Gpatient patientTrouve = cabinet.rechercherPatient(nomRech, dateNaissRech);
                        if (patientTrouve != null) {
                            System.out.println(patientTrouve);
                        } else {
                            System.out.println("Patient non trouvé.");
                        }
                        break;

                    case 12:
                        scanner.nextLine();
                        System.out.print("Nom du patient : ");
                        String nomCert = scanner.nextLine();
                        System.out.print("Date de naissance (AAAA-MM-JJ) : ");
                        String dateNaissCert = scanner.nextLine();
                        Gpatient patientCert = cabinet.rechercherPatient(nomCert, dateNaissCert);

                        if (patientCert != null) {
                            System.out.print("Motif : ");
                            String motif = scanner.nextLine();
                            System.out.print("Durée (jours) : ");
                            int duree = scanner.nextInt();
                            cabinet.creerCertificatMedical(patientCert, motif, duree);
                        } else {
                            System.out.println("Patient non trouvé.");
                        }
                        break;

                    case 0:
                        System.out.println("Au revoir !");
                        break;

                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine();
                choix = -1;
            } catch (DateTimeParseException e) {
                System.out.println("Format de date ou d'heure invalide.");
                choix = -1;
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                choix = -1;
            }
        } while (choix != 0);
        scanner.close();
    }
}
/*
 * import java.time.LocalDate;
 * import java.time.LocalTime;
 * import java.time.format.DateTimeParseException;
 * import java.util.InputMismatchException;
 * import java.util.Scanner;
 * 
 * public class App {
 * public static void main(String[] args) {
 * Gcabinetmedical cabinet = new Gcabinetmedical();
 * Scanner scanner = new Scanner(System.in);
 * int choix;
 * 
 * do {
 * try {
 * System.out.println("\n--- Menu Principal ---");
 * System.out.println("1. Ajouter un patient");
 * System.out.println("2. Lister les patients");
 * System.out.println("3. Ajouter un rendez-vous");
 * System.out.println("4. Lister les rendez-vous");
 * System.out.println("5. Afficher le dossier médical d'un patient");
 * System.out.println("0. Quitter");
 * System.out.print("Votre choix : ");
 * choix = scanner.nextInt();
 * 
 * switch (choix) {
 * case 1:
 * scanner.nextLine(); // Consume the newline
 * System.out.print("Nom : ");
 * String nom = scanner.nextLine();
 * System.out.print("Prénom : ");
 * String prenom = scanner.nextLine();
 * System.out.print("Date de naissance (AAAA-MM-JJ) : ");
 * String dateNaissance = scanner.nextLine();
 * System.out.print("Téléphone : ");
 * String telephone = scanner.nextLine();
 * System.out.print("Poids : ");
 * String poids= scanner.nextLine();
 * System.out.print("Taille : ");
 * String taille= scanner.nextLine();
 * // Create new patient with auto-generated ID
 * cabinet.ajouterpatient(new Gpatient(0, nom, prenom, telephone,
 * dateNaissance),new Gdossiermedical(patient, poids, taille));
 * break;
 * 
 * case 2:
 * cabinet.listerPatients();
 * break;
 * 
 * case 3:
 * cabinet.listerPatients();
 * System.out.print("Sélectionnez un patient (numéro) : ");
 * int indexPatient = scanner.nextInt() - 1;
 * scanner.nextLine(); // Consume the newline
 * 
 * if (indexPatient >= 0 && indexPatient < cabinet.getFiches().size()) {
 * System.out.print("Date du rendez-vous (AAAA-MM-JJ) : ");
 * LocalDate date = LocalDate.parse(scanner.nextLine());
 * System.out.print("Heure du rendez-vous (HH:MM) : ");
 * LocalTime heure = LocalTime.parse(scanner.nextLine());
 * 
 * Gpatient patient = cabinet.getFiches().get(indexPatient).getPatient();
 * cabinet.ajouterRendezVous(patient, date, heure);
 * } else {
 * System.out.println("Patient invalide.");
 * }
 * break;
 * 
 * case 4:
 * cabinet.listerRendezVous();
 * break;
 * 
 * case 5:
 * cabinet.listerPatients();
 * System.out.print("Sélectionnez un patient (numéro) : ");
 * int indexDossier = scanner.nextInt() - 1;
 * cabinet.afficherDossierMedical(indexDossier);
 * break;
 * 
 * case 0:
 * System.out.println("Au revoir !");
 * break;
 * 
 * default:
 * System.out.println("Choix invalide.");
 * }
 * } catch (InputMismatchException e) {
 * System.out.println("Entrée invalide. Veuillez entrer un nombre.");
 * scanner.nextLine(); // Clear the invalid input
 * choix = -1; // To continue the loop
 * } catch (DateTimeParseException e) {
 * System.out.println("Format de date ou d'heure invalide. Veuillez réessayer."
 * );
 * choix = -1; // To continue the loop
 * } catch (IndexOutOfBoundsException e) {
 * System.out.println("Sélection invalide. Veuillez choisir un patient valide."
 * );
 * choix = -1; // To continue the loop
 * } catch (Exception e) {
 * System.out.println("Une erreur est survenue : " + e.getMessage());
 * choix = -1; // To continue the loop
 * }
 * } while (choix != 0);
 * 
 * scanner.close();
 * }
 * }
 */