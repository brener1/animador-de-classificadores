package br.unifil.dc.lab2;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Write a description of class Gravador here.
 *
 * @author Ricardo Inacio
 * @version 20200409
 */
public class Gravador
{
    public Gravador() {
        this.seqGravacoes = new ArrayList<Transparencia>();
    }

    /** Método que grava a disposição atual da lista(como ela esta no momento).
     *
     * @param lista Recebe a lista a ser usada no método
     * @param nome  Registra oque esta sendo feito no momento
     */
    public void gravarLista(List<Integer> lista, String nome) {
        List<Color> cores = novaListaColors(lista.size(), Color.BLUE);
        ListaGravada gravacao = new ListaGravada(List.copyOf(lista), cores, nome);
        seqGravacoes.add(gravacao);
    }

    /** Método que destaca o indice passado e a disposição atual da lista
     *
     * @param lista Recebe a lista a ser usada no método
     * @param i Indice a ser destacado pelo método
     * @param nome Registra oque esta sendo feito no momento
     */
    public void gravarIndiceDestacado(List<Integer> lista, int i, String nome) {
        List<Color> cores = novaListaColors(lista.size(), Color.BLUE);
        cores.set(i, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(List.copyOf(lista), cores, nome);
        seqGravacoes.add(gravacao);
    }

    /** Método que destaca os dois indices que estão sendo comparados e grava a disposição atual da lista
     *
     * @param lista Recebe a lista a ser usada no método
     * @param i Indice do primeiro elemento à ser comparado
     * @param j Indice do segundo elemento à ser comparado
     */
    public void gravarComparacaoSimples(List<Integer> lista, int i, int j) {
        List<Color> cores = novaListaColors(lista.size(), Color.BLUE);
        cores.set(i, Color.GRAY);
        cores.set(j, Color.GRAY);
        ListaGravada gravacao = new ListaGravada(List.copyOf(lista), cores, "Comparação");
        seqGravacoes.add(gravacao);
    }

    public void gravarComparacaoBinaria(List<Integer> lista, int i, int j, int k) {
        List<Color> cores = novaListaColors(lista.size(), Color.BLUE);
        cores.set(i, Color.GRAY);
        cores.set(j, Color.GRAY);
        cores.set(k, Color.RED);
        ListaGravada gravacao = new ListaGravada(lista, cores, "Pesquisa binária");
        seqGravacoes.add(gravacao);
    }

    /** Método que destaca os dois indices que tiveram seus valores trocados(com uma cor diferente do método anterior)
     * e grava a disposição atual da lista
     *
     * @param lista Recebe a lista a ser usada no método
     * @param i Indice do primeiro elemento à ser comparado
     * @param j Indice do segundo elemento à ser comparado
     */
    public void gravarPosTrocas(List<Integer> lista, int i, int j) {
        List<Color> cores = novaListaColors(lista.size(), Color.BLUE);
        cores.set(i, Color.YELLOW);
        cores.set(j, Color.YELLOW);
        ListaGravada gravacao = new ListaGravada(List.copyOf(lista), cores, "Pós-troca");
        seqGravacoes.add(gravacao);
    }

    /**Método que retorna a sequencia de gravações feitas como um "filme" de tapes, que rodam cena por cena
     *
     * @return Retorna a sequência de gravações
     */
    public ListIterator<Transparencia> getFilme() {
        return seqGravacoes.listIterator();
    }

    /** Método que define as cores a serem usadas
     *
     * @param numElems O número de elementos da lista para saber quantas cores sera necessário
     * @param cor Objeto que grava a cor a ser usada
     * @return Retorna um ArrayList do Color com as cores a serem usadas
     */
    private static List<Color> novaListaColors(int numElems, Color cor) {
        ArrayList<Color> lista = new ArrayList<>(numElems);
        for (; numElems > 0; numElems--) lista.add(cor);
        return lista;
    }

    private List<Transparencia> seqGravacoes;
}
