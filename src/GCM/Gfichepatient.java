package GCM;

//import java.time.LocalDateTime;

public class Gfichepatient {
    private Gpatient patient;
    private Gdossiermedical dossier;
    
    

    public Gfichepatient(Gpatient patient) {
        this.patient = patient;
        this.dossier = new Gdossiermedical(patient, 0.0, 0.0);
    }

    public Gpatient getPatient() {
        return patient;
    }

    public Gdossiermedical getDossier() {
        return dossier;
    }
    public String getGdossiermedical() {
        return dossier.toString();
    }

    @Override
    public String toString() {
        return "Fiche patient : " + patient.getNom() + " " + patient.getPrenom() + "\n" + dossier.toString();
    }

    public static void add(Gfichepatient gfichepatient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    
    
}
