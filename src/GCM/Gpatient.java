package GCM;

public class Gpatient {

    // private static int compteurid;
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String datedenaissannce;
    private Gdossiermedical dossier;

    public Gpatient(int id, String nom, String prenom, String telephone, String datedenaissannce) {
        this.id = IDGenerator.generateId();
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.datedenaissannce = datedenaissannce;

    }

    public String getDatedenaissannce() {
        return datedenaissannce;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public Gdossiermedical getDossier() {
        return dossier;
    }

    public void setDossier(Gdossiermedical dossier) {
        this.dossier = dossier;
    }

    public void afficherPatients() {
        System.out.println("ID :" + id + " Nom :" + nom + " Prenom :" + prenom + " Date de naissance  :"
                + datedenaissannce + "Telephone :" + telephone);
        dossier.toString();
    }
}
