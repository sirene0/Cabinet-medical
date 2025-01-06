package GCM;

import java.time.LocalDate;
import java.util.ArrayList;

public class Gordonnance {
    private int id;
    private Gpatient patient;
    private String medecin;    // Médecin qui prescrit l'ordonnance
    private LocalDate date;    // Date de l'ordonnance
    private ArrayList<String> medicaments; // Liste des noms de médicaments
    private ArrayList<String> doses;       // Liste des doses des médicaments
    private ArrayList<String> durees;      // Liste des durées de traitement
    private ArrayList<String> instructions; // Liste des instructions de traitement

    public Gordonnance(Gpatient patient, String medecin) {
        this.id = IDGenerator.generateId(); // Génération d'ID unique
        this.patient = patient;
        this.medecin = medecin;
        this.date = LocalDate.now(); // Date de création de l'ordonnance
        this.medicaments = new ArrayList<>();
        this.doses = new ArrayList<>();
        this.durees = new ArrayList<>();
        this.instructions = new ArrayList<>();
    }

    // Ajouter un médicament avec sa dose, durée et instructions
    public void ajouterMedicament(String medicament, String dose, String duree, String instruction) {
        medicaments.add(medicament);
        doses.add(dose);
        durees.add(duree);
        instructions.add(instruction);
    }

    // Affichage de l'ordonnance
    public void afficherOrdonnance() {
        System.out.println("===== Ordonnance Médicale =====");
        System.out.println("ID Ordonnance : " + id);
        System.out.println("Date : " + date);
        System.out.println("Médecin : " + medecin);
        System.out.println("Patient : " + patient.getNom() + " " + patient.getPrenom());
        System.out.println("\nMédicaments prescrits :");
        
        if (medicaments.isEmpty()) {
            System.out.println("  Aucun médicament prescrit.");
        } else {
            for (int i = 0; i < medicaments.size(); i++) {
                System.out.println("  - Médicament : " + medicaments.get(i));
                System.out.println("    Dose : " + doses.get(i));
                System.out.println("    Durée : " + durees.get(i));
                System.out.println("    Instructions : " + instructions.get(i));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ordonnance ID: ").append(id).append("\n");
        sb.append("Patient: ").append(patient.getNom()).append(" ").append(patient.getPrenom()).append("\n");
        sb.append("Médecin: ").append(medecin).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Médicaments prescrits :\n");
        
        if (medicaments.isEmpty()) {
            sb.append("  Aucun médicament prescrit.\n");
        } else {
            for (int i = 0; i < medicaments.size(); i++) {
                sb.append("  - Médicament : ").append(medicaments.get(i)).append("\n");
                sb.append("    Dose : ").append(doses.get(i)).append("\n");
                sb.append("    Durée : ").append(durees.get(i)).append("\n");
                sb.append("    Instructions : ").append(instructions.get(i)).append("\n");
            }
        }
        return sb.toString();
    }

    public Object getpatient() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getpatient'");
    }
}