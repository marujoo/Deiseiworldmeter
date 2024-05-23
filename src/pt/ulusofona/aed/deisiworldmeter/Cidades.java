package pt.ulusofona.aed.deisiworldmeter;

public class Cidades {

    String alfa2;
    String cidade;
    String regiao;
    double populacao;
    double latitude;
    double longitude;


    public Cidades(String alfa2,
                   String cidade,
                   String regiao,
                   double populacao,
                   double latitude,
                   double longitude) {

        this.alfa2 = alfa2;
        this.cidade = cidade;
        this.regiao = regiao;
        this.populacao = populacao;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return cidade + " | " + alfa2.toUpperCase() + " | " + regiao + " | " + (int)populacao + " | ("
                + latitude + "," + longitude + ")";
    }

    public String getAlfa2() {
        return alfa2;
    }

    public void setAlfa2(String alfa2) {
        this.alfa2 = alfa2;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public double getPopulacao() {
        return populacao;
    }

    public void setPopulacao(double populacao) {
        this.populacao = populacao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

