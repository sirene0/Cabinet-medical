package GCM;
import java.time.LocalDateTime;

public class Gfichepatient {
    private Gpatient patient;
    private Gdossiermedical dossier;
    
    

    public Gfichepatient(Gpatient patient) {
        this.patient = patient;
        this.dossier = new Gdossiermedical(patient, 0, 0);
    }

    public Gpatient getPatient() {
        return patient;
    }

    public Gdossiermedical getDossier() {
        return dossier;
    }

    @Override
    public String toString() {
        return "Gfichepatient [patient=" + patient + ", dossier=" + dossier + "]";
    }
    
}
