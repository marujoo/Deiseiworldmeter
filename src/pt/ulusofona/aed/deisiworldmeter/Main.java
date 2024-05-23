package pt.ulusofona.aed.deisiworldmeter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    static ArrayList<Paises> dataPaises;
    static ArrayList<Cidades> dataCidades;
    static ArrayList<Populacao> dataPopulacao;
    static int linhasCorretasPaises;
    static int linhasIncorretasPaises;
    static int primeiraLinhaIncorretaPaises;
    static int linhasCorretasCidades;
    static int linhasIncorretasCidades;
    static int primeiraLinhaIncorretaCidades;
    static int linhasCorretasPopulacao;
    static int linhasIncorretasPopulacao;
    static int primeiraLinhaIncorretaPopulacao;


    public static boolean idDuplicadoPais (Paises pais) {

        for (Paises p : dataPaises) {
            if (p.id == pais.id) {
                return true;
            }
        }
        return false;
    }

    public static boolean idPopulacaoNoPais (int id) {

        for (Paises p : dataPaises) {
            if (p.id == id) {
                return true;
            }
        }
        return false;
    }

    public static int calcularIndicadoresPorPais(int idPais) { // com hashmap era mais eficiente, mudar na 2 parte
        int numIndicadores = 0;
        for (Populacao populacao : dataPopulacao) {
            if (populacao.getId() == idPais) {
                numIndicadores++;
            }
        }
        return numIndicadores;
    }

    public static boolean cidadeNoPais (String alfa2) {
        for (Paises p : dataPaises) {
            if (p.alfa2.equals(alfa2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean parseFiles(File pasta) {

        dataPaises = new ArrayList<>();
        dataCidades = new ArrayList<>();
        dataPopulacao = new ArrayList<>();
        linhasCorretasPaises = 0;
        linhasIncorretasPaises = 0;
        primeiraLinhaIncorretaPaises = -1;
        linhasCorretasCidades = 0;
        linhasIncorretasCidades = 0;
        primeiraLinhaIncorretaCidades = -1;
        linhasCorretasPopulacao = 0;
        linhasIncorretasPopulacao = 0;
        primeiraLinhaIncorretaPopulacao = -1;


        File ficheiroPaises = new File(pasta, "paises.csv");
        File ficheiroCidades = new File(pasta, "cidades.csv");
        File ficheiroPopulacao = new File(pasta, "populacao.csv");

        try {
            // Leitura do arquivo paises.csv
            Scanner scannerPaises = new Scanner(ficheiroPaises);
            // Ignora a primeira linha
            if (scannerPaises.hasNextLine()) {
                scannerPaises.nextLine();
            }
            while (scannerPaises.hasNextLine()) {
                String linha = scannerPaises.nextLine();
                String[] partes = linha.split(",");
                if (partes.length == 4) { // Verifica se a linha tem o numero correto de campos
                    int id = Integer.parseInt(partes[0]);
                    String alfa2 = partes[1];
                    String alfa3 = partes[2];
                    String nome = partes[3];
                    Paises pais = new Paises(id, alfa2, alfa3, nome);
                    if (dataPaises.contains(pais) || idDuplicadoPais(pais) || (partes[1].isEmpty() || partes[2].isEmpty() || partes[3].isEmpty())) {
                        linhasIncorretasPaises++;
                        if (primeiraLinhaIncorretaPaises == -1) {
                            primeiraLinhaIncorretaPaises = linhasCorretasPaises + linhasIncorretasPaises + 1;
                        }
                    } else {
                        dataPaises.add(pais);
                        linhasCorretasPaises++;
                    }
                } else {
                    linhasIncorretasPaises++;
                    if (primeiraLinhaIncorretaPaises == -1) {
                        primeiraLinhaIncorretaPaises = linhasCorretasPaises + linhasIncorretasPaises + 1;
                    }
                }
            }
            scannerPaises.close();

            // Leitura do arquivo cidades.csv
            Scanner scannerCidades = new Scanner(ficheiroCidades);
            // Ignora a primeira linha
            if (scannerCidades.hasNextLine()) {
                scannerCidades.nextLine();
            }
            while (scannerCidades.hasNextLine()) {
                String linha = scannerCidades.nextLine();
                String[] partes = linha.split(",");
                if (partes.length == 6 && cidadeNoPais(partes[0]) && !partes[3].isEmpty() &&
                        (!partes[0].isEmpty() && !partes[2].isEmpty())) {// Verifica se a linha tem o numero correto de campos
                    double populacao = Double.parseDouble((partes[3]));
                    if (populacao == 0) { // dps tiro sqe
                        linhasIncorretasCidades++;
                        continue;
                    }
                    String alfa2 = partes[0];
                    String cidade = partes[1];
                    String regiao = partes[2];
                    double latitude = Double.parseDouble(partes[4]);
                    double longitude = Double.parseDouble(partes[5]);
                    Cidades cidades = new Cidades(alfa2, cidade, regiao, populacao, latitude, longitude);
                    if (dataCidades.contains(cidades)) {
                        linhasIncorretasCidades++;
                        if (primeiraLinhaIncorretaCidades == -1) {
                            primeiraLinhaIncorretaCidades = linhasCorretasCidades + linhasIncorretasCidades + 1;
                        }
                    } else {
                        dataCidades.add(cidades);
                        linhasCorretasCidades++;
                    }
                } else {
                    linhasIncorretasCidades++;
                    if (primeiraLinhaIncorretaCidades == -1) {
                        primeiraLinhaIncorretaCidades = linhasCorretasCidades + linhasIncorretasCidades + 1;
                    }
                }
            }
            scannerCidades.close();

            // Leitura do arquivo populacao.csv
            Scanner scannerPopulacao = new Scanner(ficheiroPopulacao);
            // Ignora a primeira linha
            if (scannerPopulacao.hasNextLine()) {
                scannerPopulacao.nextLine();
            }
            while (scannerPopulacao.hasNextLine()) {
                String linha = scannerPopulacao.nextLine();
                String[] partes = linha.split(",");
                if (partes.length == 5 || partes[1].isEmpty()) { // Verifica se a linha tem o numero correto de campos
                    int id = Integer.parseInt(partes[0]);
                    String ano = partes[1];
                    int popMacho = Integer.parseInt(partes[2]);
                    int popFemea = Integer.parseInt(partes[3]);
                    double densidade = Double.parseDouble(partes[4]);
                    Populacao populacao = new Populacao(id, ano, popMacho, popFemea, densidade);
                    if (dataPopulacao.contains(populacao) || !idPopulacaoNoPais(id)) {
                        linhasIncorretasPopulacao++;
                        if (primeiraLinhaIncorretaPopulacao == -1) {
                            primeiraLinhaIncorretaPopulacao = linhasCorretasPopulacao + linhasIncorretasPopulacao + 1;
                        }
                    } else {
                        dataPopulacao.add(populacao);
                        linhasCorretasPopulacao++;
                    }
                } else {
                    linhasIncorretasPopulacao++;
                    if (primeiraLinhaIncorretaPopulacao == -1) {
                        primeiraLinhaIncorretaPopulacao = linhasCorretasPopulacao + linhasIncorretasPopulacao + 1;
                    }
                }
            }
            scannerPopulacao.close();

            return true; // Retorna true apos a leitura bem sucedida dos ficheirs
        } catch(FileNotFoundException e){
            e.printStackTrace();
            return false; // Retorna false se houver erro ao ler algum ficheiro
        }

    }

    public static ArrayList<String> getObjects(TipoEntidade tipo) {
        ArrayList<String> objetos = new ArrayList<>();

        switch (tipo) {
            case PAIS:
                for (Paises pais : dataPaises) {

                    if (pais.id >= 700) {
                        pais.setNumOcorrenciasPopulacao(calcularIndicadoresPorPais(pais.id));
                    }
                    objetos.add(pais.toString());
                }
                break;

            case CIDADE:
                for (Cidades cidade : dataCidades) {
                    objetos.add(cidade.toString());
                }
                break;

            case INPUT_INVALIDO:
                objetos.add("paises.csv | " + linhasCorretasPaises + " | " + linhasIncorretasPaises + " | " + primeiraLinhaIncorretaPaises);
                objetos.add("cidades.csv | " + linhasCorretasCidades + " | " + linhasIncorretasCidades + " | " + primeiraLinhaIncorretaCidades);
                objetos.add("populacao.csv | " + linhasCorretasPopulacao + " | " + linhasIncorretasPopulacao + " | " + primeiraLinhaIncorretaPopulacao);
                break;
            default:
                break;
        }
        return objetos;
    }

//   public static void main(String[] args) {
//
//        boolean parseOk = parseFiles(new File("dados"));
//        ArrayList paises = getObjects(TipoEntidade.PAIS);
//        System.out.println(paises.get(0).toString());
//        System.out.println(paises.get(1).toString());
//        System.out.println(paises.get(2).toString());
//    }
}