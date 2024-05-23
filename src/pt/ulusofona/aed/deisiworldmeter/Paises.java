package pt.ulusofona.aed.deisiworldmeter;

public class Paises {

    int id;
    String alfa2;
    String alfa3;
    String nome;
    int numOcorrenciasPopulacao;

    public Paises(int id, String alfa2, String alfa3, String nome) {
        this.id = id;
        this.alfa2 = alfa2;
        this.alfa3 = alfa3;
        this.nome = nome;
        this.numOcorrenciasPopulacao = 0;
    }

    @Override
    public String toString() {
        if (this.id < 700) {
            return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase();
        } else {
            return nome + " | " + id + " | " + alfa2.toUpperCase() + " | " + alfa3.toUpperCase() + " | " + numOcorrenciasPopulacao;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlfa2() {
        return alfa2;
    }

    public void setAlfa2(String alfa2) {
        this.alfa2 = alfa2;
    }

    public String getAlfa3() {
        return alfa3;
    }

    public void setAlfa3(String alfa3) {
        this.alfa3 = alfa3;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumOcorrenciasPopulacao() {
        return numOcorrenciasPopulacao;
    }

    public void setNumOcorrenciasPopulacao(int numOcorrenciasPopulacao) {
        this.numOcorrenciasPopulacao = numOcorrenciasPopulacao;
    }
}