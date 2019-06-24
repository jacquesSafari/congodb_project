package sgdb.relationsJava.engine.persistance;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Table implements Comparable<Table> {
    private String nom;
    private Set<Attribut> attributs = new TreeSet<>();

    public Table(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void addAttribut(Attribut attribut) {
        attributs.add(attribut);
    }

    public void removeAttribut(Attribut attribut) {
        attributs.remove(attribut);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(nom, table.nom) &&
                Objects.equals(attributs, table.attributs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, attributs);
    }

    @Override
    public int compareTo(Table o) {
        return 0;
    }
}
