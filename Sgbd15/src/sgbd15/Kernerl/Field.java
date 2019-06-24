package sample.Kernerl;

public class Field{
    private String nom;
    private String type;

    public Field(){

    }

    //getters and setters
    public String getNom(){
        return this.nom;
    }
    public String getType(){
        return this.type;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public void setType(String type){
        this.type = type;
    }
}