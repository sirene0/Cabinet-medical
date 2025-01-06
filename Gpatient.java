package GCM;
public class Gpatient {

        private static int compteurid = 1;
        private int id;
        private String nom;
        private String prenom;
        private String telephone;
        private String datedenaissannce;
        
    
        public Gpatient(int id, String nom, String prenom, String telephone,String datedenaissannce) {
            this.id = compteurid++;
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
        
    
        

        public void afficherPatients() {
            System.out.println("ID :" + id + " Nom :" + nom + " Prenom :" + prenom + "Telephone :" + telephone);
        }
    }
