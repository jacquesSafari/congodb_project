package mysgbdfxml;

public class Champs {
    String nom;
    String type;

    public Champs(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }
    public Champs(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
