package pt.ulusofona.aed.deisiworldmeter;

public class Stats {
    int linhasCorretas;
    int linhasIncorretas;
    int primeiraLinhaIncorreta;

    public Stats(int linhasCorretas, int linhasIncorretas, int primeiraLinhaIncorreta) {
        this.linhasCorretas = linhasCorretas;
        this.linhasIncorretas = linhasIncorretas;
        this.primeiraLinhaIncorreta = primeiraLinhaIncorreta;
    }

}
