package domain;

public abstract class Curs {
    private int id;
    private String nume;
    private double pret;

    public Curs(int id, String nume, double pret) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "domain.Curs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                '}';
    }
}

