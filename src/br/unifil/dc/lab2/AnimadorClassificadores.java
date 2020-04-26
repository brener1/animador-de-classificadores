package br.unifil.dc.lab2;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Write a description of class AnimadorClassificadores here.
 *
 * @author Ricardo Inacio
 * @version 20200409
 */
public class AnimadorClassificadores extends JFrame {

    /**
     * O construtor do aplicativo AnimadorClassificadores. É aqui que todos os elementos da interface gráfica são
     * construídos, configurados e posicionados. Por enquanto, aqui também é feito o tratamento de
     * eventos (input de usuário através de mouse e teclado), utilizando métodos anônimos (lambda).
     *
     * @see javax.swing.JFrame
     */
    public AnimadorClassificadores() {

        // Cria e configura botões
        btnCarregar = new JButton("Carregar");
        btnCarregar.addActionListener((e) -> onBtnCarregarPressionado());

        btnProx = new JButton("Prox");
        btnProx.setEnabled(false);
        btnProx.addActionListener((e) -> onBtnProxPressionado());

        btnAnt = new JButton("Ant");
        btnAnt.setEnabled(false);
        btnAnt.addActionListener((e) -> onBtnAntPressionado());

        // Cria e configura o campo de seleção de algoritmos animados
        OpcaoAlgoritmo[] algsAnimados = {
                OpcaoAlgoritmo.LISTA_ESTATICA,
                OpcaoAlgoritmo.PESQUISADOR_BINARIO, OpcaoAlgoritmo.PESQUISADOR_SEQUENCIAL,
                OpcaoAlgoritmo.CLASSIFICADOR_BOLHA, OpcaoAlgoritmo.CLASSIFICADOR_SELECAO,
                OpcaoAlgoritmo.CLASSIFICADOR_INSERCAO, OpcaoAlgoritmo.CLASSIFICADOR_MERGESORT,
                OpcaoAlgoritmo.CLASSIFICADOR_QUICKSORT};
        boxListaAlgoritmos = new JComboBox<OpcaoAlgoritmo>(algsAnimados);
        boxListaAlgoritmos.addItemListener((ItemEvent e) -> onSlctOpcaoAlgoritmo((OpcaoAlgoritmo) e.getItem()));

        final int COMPRIMENTO_ENTRADA_LISTA = 20;
        txfEntradaValores = new JTextField("", COMPRIMENTO_ENTRADA_LISTA);

        final int COMPRIMENTO_ENTRADA_CHAVE_PESQUISA = 3;
        txfEntradaChavePesquisa = new JTextField("", COMPRIMENTO_ENTRADA_CHAVE_PESQUISA);

        // Campo para abrigar e organizar os botões e campos de entrada
        JPanel pnlBotoes = new JPanel(new FlowLayout());
        pnlBotoes.add(new JLabel("Valores:"));
        pnlBotoes.add(txfEntradaValores);
        pnlBotoes.add(new JLabel("Chave:"));
        pnlBotoes.add(txfEntradaChavePesquisa);
        pnlBotoes.add(boxListaAlgoritmos);
        pnlBotoes.add(btnCarregar);
        pnlBotoes.add(btnAnt);
        pnlBotoes.add(btnProx);

        // Cria e configura a tela do desenhista
        tela = new Tocador();
        tela.setPreferredSize(new Dimension(800, 600));

        // Container que organiza o posicionamento dos botões e da tela
        Container organizacao = getContentPane();
        organizacao.setLayout(new BorderLayout());
        organizacao.add(tela, BorderLayout.CENTER);
        organizacao.add(pnlBotoes, BorderLayout.SOUTH);

        // Configurações de comportamento do aplicativo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AnimadorClassificadores - Lab. Comp. II");
        pack();
        setVisible(true);
        requestFocus();
    }

    /**
     * Event listeners a partir daqui, tratadores de ações do usuário.
     */

    public void onBtnCarregarPressionado() {
        // Verifica o desenho escolhido no ComboBox e repassa à tela para pintura
        List<Integer> valores = textoParaLista(txfEntradaValores.getText());
        OpcaoAlgoritmo algoritmo = (OpcaoAlgoritmo) boxListaAlgoritmos.getSelectedItem();

        Gravador novoFilme = null;
        switch (algoritmo) {
            case LISTA_ESTATICA:
                novoFilme = AlgoritmosAnimados.listaEstatica(valores);
                break;
            case PESQUISADOR_SEQUENCIAL:
                int chave = Integer.parseInt(txfEntradaChavePesquisa.getText());
                novoFilme = AlgoritmosAnimados.pesquisaSequencial(valores, chave);
                break;

            case CLASSIFICADOR_BOLHA:
                novoFilme = AlgoritmosAnimados.classificarPorBolha(valores);
                break;

            case CLASSIFICADOR_SELECAO:
                novoFilme = AlgoritmosAnimados.classificarPorSelecao(valores);
                break;

            case PESQUISADOR_BINARIO:
                Integer chave2 = Integer.parseInt(txfEntradaChavePesquisa.getText());
                novoFilme = AlgoritmosAnimados.pesquisaBinaria(valores, chave2);
                break;

            case CLASSIFICADOR_INSERCAO:
                novoFilme = AlgoritmosAnimados.classificarInsercao(valores);
                break;

            default:
                throw new RuntimeException("Funcionalidade ainda não implementada pelo aluno");
        }

        if (novoFilme != null) {
            tela.carregarFilme(novoFilme.getFilme());
            btnProx.setEnabled(true);
            btnAnt.setEnabled(true);
        }

        onBtnProxPressionado();
    }

    public void onBtnProxPressionado() {
        tela.avancarFilme();
        tela.repaint();
        requestFocusInWindow();
    }

    public void onBtnAntPressionado() {
        tela.voltarFilme();
        tela.repaint();
        requestFocusInWindow();
    }

    public void onSlctOpcaoAlgoritmo(OpcaoAlgoritmo alg) {
        if (alg == OpcaoAlgoritmo.PESQUISADOR_SEQUENCIAL || alg == OpcaoAlgoritmo.PESQUISADOR_BINARIO) {
            txfEntradaChavePesquisa.setEnabled(true);
        } else {
            txfEntradaChavePesquisa.setEnabled(false);
        }
    }


    /**
     * Ponto de início do programa. Simplesmente informa que é um aplicativo gráfico e passa o
     * controle para o construtor do método. Utiliza uma segunda thread para isso.
     *
     * @param args Argumentos recebidos da linha de comando.
     */
    public static void main(String[] args) {
        // Código que inicializa o aplicativo gráfico
        SwingUtilities.invokeLater(AnimadorClassificadores::new);
    }

    private enum OpcaoAlgoritmo {
        LISTA_ESTATICA("Lista estática"),

        PESQUISADOR_SEQUENCIAL("Pesquisa sequencial"),
        PESQUISADOR_BINARIO("Pesquisa binária"),

        CLASSIFICADOR_BOLHA("Bolha"),
        CLASSIFICADOR_SELECAO("Seleção"),
        CLASSIFICADOR_INSERCAO("Inserção"),
        CLASSIFICADOR_MERGESORT("Mergesort"),
        CLASSIFICADOR_QUICKSORT("Quicksort");

        OpcaoAlgoritmo(String repr) {
            this.repr = repr;
        }

        @Override
        public String toString() {
            return this.repr;
        }

        private final String repr;
    }

    /**
     * Método auxiliar para a classe que converte para uma lista de Integers uma sequencia de
     * valores em String, separados por ",". Valores não numéricos são considerados como 0.
     *
     * @param textoValores String com valores separados por ",".
     * @return Lista de inteiros de acordo com a String de entrada.
     */
    private static List<Integer> textoParaLista(String textoValores) {
        String[] numerosTxt = textoValores.split(",");
        List<Integer> lista = new ArrayList<>(numerosTxt.length);

        for (String numTxt : numerosTxt) {
            try {
                lista.add(Integer.valueOf(numTxt));
            } catch (NumberFormatException nfe) {
                String entradaErrada = nfe.getMessage();
                int posIni = entradaErrada.indexOf("\"") + 1;
                int posFim = entradaErrada.lastIndexOf("\"");
                entradaErrada = entradaErrada.substring(posIni, posFim);

                System.err.println("Ignorando entrada '" + entradaErrada + "'. Utilizado valor 0.");
                lista.add(0);
            }
        }

        return lista;
    }

    // Elementos (widgets) da nossa interface
    private Tocador tela;
    private JButton btnCarregar, btnProx, btnAnt;
    private JComboBox<OpcaoAlgoritmo> boxListaAlgoritmos;
    private JTextField txfEntradaValores;
    private JTextField txfEntradaChavePesquisa;
}