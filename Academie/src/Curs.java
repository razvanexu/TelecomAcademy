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

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public double getPret() {
        return pret;
    }

    public void setPret(double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Curs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", pret=" + pret +
                '}';
    }
}
