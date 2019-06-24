package sgdb.relationsJava.engine.persistance;

import java.util.Objects;

public class Attribut implements Comparable<Attribut> {
    private String nom;
    private Type type;
    private Constraint constraint;

    @Override
    public int compareTo(Attribut o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribut attribut = (Attribut) o;
        return Objects.equals(nom, attribut.nom) &&
                type == attribut.type &&
                constraint == attribut.constraint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, type, constraint);
    }

    public enum Type {
        CHAINE, CAHACTERE, ENTIER
    }

    public enum Constraint {
        UNIQUE, FOREIGN, PRIMARY
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public Attribut(String nom, Type type, Constraint constraint) {
        this.nom = nom;
        this.type = type;
        this.constraint = constraint;
    }
}
