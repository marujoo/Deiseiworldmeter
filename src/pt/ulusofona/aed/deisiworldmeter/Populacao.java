package pt.ulusofona.aed.deisiworldmeter;

public class Populacao {

    int id;
    String ano;
    int popMacho;
    int popFemea;
    double densidade;

    public Populacao(int id, String ano, int popMacho, int popFemea, double densidade) {
        this.id = id;
        this.ano = ano;
        this.popMacho = popMacho;
        this.popFemea = popFemea;
        this.densidade = densidade;
    }

    @Override
    public String toString() {
        return "Populacao{" +
                "id=" + id +
                ", ano=" + ano +
                ", popMacho=" + popMacho +
                ", popFemea=" + popFemea +
                ", densidade=" + densidade +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getPopMacho() {
        return popMacho;
    }

    public void setPopMacho(int popMacho) {
        this.popMacho = popMacho;
    }

    public int getPopFemea() {
        return popFemea;
    }

    public void setPopFemea(int popFemea) {
        this.popFemea = popFemea;
    }

    public double getDensidade() {
        return densidade;
    }

    public void setDensidade(double densidade) {
        this.densidade = densidade;
    }
}
