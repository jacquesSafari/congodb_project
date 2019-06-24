package mysgbdfxml;

public class Table {
    String id;
    String nom;
    String postonom;
    String prenom;
    String titre;
    String adresse ;
    String telephone;
    String matricule;

    public Table(String id) {
        this.id = id;
    }
    public Table(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public Table(String id, String nom, String postonom) {
        this.id = id;
        this.nom = nom;
        this.postonom = postonom;
    }
    public Table(String id, String nom, String postonom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.postonom = postonom;
        this.prenom = prenom;
    }
    public Table(String id, String nom, String postonom, String prenom, String titre) {
        this.id = id;
        this.nom = nom;
        this.postonom = postonom;
        this.prenom = prenom;
        this.titre = titre;
    }
    public Table(String id, String nom, String postonom, String prenom, String titre, String adresse) {
        this.id = id;
        this.nom = nom;
        this.postonom = postonom;
        this.prenom = prenom;
        this.titre = titre;
        this.adresse = adresse;
    }
    public Table(String id, String nom, String postonom, String prenom, String titre, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.postonom = postonom;
        this.prenom = prenom;
        this.titre = titre;
        this.adresse = adresse;
        this.telephone = telephone;
    }
    public Table(String id, String nom, String postonom, String prenom, String titre, String adresse, String telephone, String matricule) {
        this.id = id;
        this.nom = nom;
        this.postonom = postonom;
        this.prenom = prenom;
        this.titre = titre;
        this.adresse = adresse;
        this.telephone = telephone;
        this.matricule = matricule;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPostonom() {
        return postonom;
    }

    public void setPostonom(String postonom) {
        this.postonom = postonom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
}
