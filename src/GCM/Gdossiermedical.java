package GCM;

import java.util.ArrayList;
//import java.util.List;

public class Gdossiermedical {
    private Gpatient patient;
    private double poids;
    private double taille;
    private ArrayList<String> anteciedentmedicaux;
    private ArrayList<String> anteciedentchirurgicaux;

    public Gdossiermedical(Gpatient patient, double poids, double taille) {
        this.anteciedentmedicaux = new ArrayList<>();
        this.anteciedentchirurgicaux = new ArrayList<>();
        this.patient = patient;
        this.poids = poids;
        this.taille = taille;
    }

    
    public double getPoids() {
        return poids;
    }


    public double getTaille() {
        return taille;
    }


    public void setPoids(double poids) {
        this.poids = poids;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public void ajouteranteciedentchirurgicaux(String ant) {
        anteciedentchirurgicaux.add(ant);
    }

    public void ajouteranteciedentmedicaux(String ant) {
        anteciedentmedicaux.add(ant);
    }

    public ArrayList<String> getAnteciedentmedicaux() {
        return anteciedentmedicaux;
    }

    public ArrayList<String> getAnteciedentchirurgicaux() {
        return anteciedentchirurgicaux;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nDossier médical du patient : ").append(patient.getNom()).append(" ").append(patient.getPrenom()).append(" ").append(patient.getDatedenaissannce()).append(" ").append(patient.getTelephone());
        sb.append("\nPoids : ").append(poids).append(" kg");
        sb.append("\nTaille : ").append(taille).append(" m");
        sb.append("\nAntécédents médicaux : ");
        if (anteciedentmedicaux.isEmpty()) {
            sb.append("Aucun");
        } else {
            for (String ant : anteciedentmedicaux) {
                sb.append("\n  - ").append(ant);
            }
        }
        sb.append("\nAntécédents chirurgicaux : ");
        if (anteciedentchirurgicaux.isEmpty()) {
            sb.append("Aucun");
        } else {
            for (String ant : anteciedentchirurgicaux) {
                sb.append("\n  - ").append(ant);
            }
        }
        return sb.toString();
    }
}