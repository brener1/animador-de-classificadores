package br.unifil.dc.lab2;

import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

/**
 * Write a description of class AlgoritmosAnimados here.
 * 
 * @author Ricardo Inacio
 * @version 20200408
 */
public class AlgoritmosAnimados
{
    public static Gravador listaEstatica(List<Integer> valores) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Valores da lista imutável");
        return anim;
    }

    public static Gravador pesquisaSequencial(List<Integer> valores, int chave) {
        //instancia a classe gravador que grava as comparações e operações
        Gravador anim = new Gravador();
        //adiciona a lista na sequencia de gravações a serem feitas durante a pesquisa
        anim.gravarLista(valores, "Inicio de pesquisa sequencial");

        //indice do elemento a ser verificado
        int i = 0;
        //adiciona a gravação da lista passada no gravador e destaca de amarelo o indice verificado
        anim.gravarIndiceDestacado(valores, i, "Pesquisa sequencial");
        //verifica se o indice atual não é o numero procurado(chave), caso seja ele interrompe o loop while
        while (i < valores.size() && valores.get(i) != chave) {
            //destaca de amarelo o indice verificado e grava
            anim.gravarIndiceDestacado(valores, i, "Pesquisa sequencial");
            //incrementa o indice para a proxima verificação
            i++;
        }
        //verifica se o indice atual(que supostamente seria a chave) existe na List
        if (i < valores.size()) {
            //caso o indice verificado exista na List, ele interrompe a busca deixando o indice destacado de amarelo e gravando
            //a disposição atual da lista
            anim.gravarIndiceDestacado(valores, i, "Chave encontrada");
        } else {
            //caso o indice verificado NÃO exista na List, ele finaliza a busca pois a chave não existe na lista passada e adiciona
            //a lista na sequencia de gravações
            anim.gravarLista(valores, "Chave não encontrada");
        }
        //retorna todas as gravações feita no método
        return anim;
    }
    
    
    public static Gravador classificarPorBolha(List<Integer> valores) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Disposição inicial");

        boolean houvePermuta;
        do {
            houvePermuta = false;

            // Sobe a bolha
            for (int i = 1; i < valores.size(); i++) {
                anim.gravarComparacaoSimples(valores, i-1, i);
                if (valores.get(i-1) > valores.get(i)) {
                    permutar(valores, i - 1, i);
                    anim.gravarPosTrocas(valores, i-1, i);
                    houvePermuta = true;
                }
            }
        } while (houvePermuta);


        anim.gravarLista(valores, "Disposição final");
        return anim;
    }

    public static Gravador classificarPorSelecao(List<Integer> valores){
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Disposição inicial");

        for (int i = 0; i < valores.size(); i++) {
            int menorIdx = encontrarIndiceMenorElem(valores, i);
            permutar(valores, menorIdx, i);
            anim.gravarIndiceDestacado(valores, i, "Trocando valores");
        }

        anim.gravarLista(valores, "Disposição final");
        return anim;
    }

    //terminar
    public static Gravador pesquisaBinaria(List<Integer> valores, Integer chave) {
        Gravador anim = new Gravador();
        anim.gravarLista(valores, "Disposição inicial");
        assert isOrdenada(valores) : "Esse método só funciona com listas ordenadas.";

        int acumulaMeio = 0;
        do {
            int meio = valores.size() / 2;

            anim.gravarComparacaoBinaria(valores, 0, valores.size()-1, meio);
            if (valores.get(meio) == chave) {
                anim.gravarIndiceDestacado(valores, meio + acumulaMeio, "Chave encontrada");
                return anim;
            }

            if (valores.get(meio) > chave) {
                valores = valores.subList(0, meio);
            }
            anim.gravarComparacaoSimples(valores, 0, valores.size());
            if (valores.get(meio) < chave) {
                acumulaMeio += meio + 1;
                valores = valores.subList(meio + 1, valores.size());
            }
            anim.gravarComparacaoSimples(valores, 0, valores.size());
        } while(valores.size() > 0);

        anim.gravarLista(valores, "Disposição Final");
        return anim;

    }
    
    public static Gravador classificarInsercao(List<Integer> lista) {
        Gravador anim = new Gravador();
        anim.gravarLista(lista, "Disposição inicial");

        for (int i = 1; i < lista.size(); i++) {
            Integer elem = lista.get(i);

            int j = i;
            anim.gravarComparacaoSimples(lista, j, j-1);
            while (j > 0 && lista.get(j-1) > elem) {
                anim.gravarPosTrocas(lista, j, j-1);
                lista.set(j, lista.get(j-1)); // Deslocamento

                j--;
                anim.gravarComparacaoSimples(lista, j, i);
            }

            lista.set(j, elem);
        }
        anim.gravarLista(lista, "Disposição final");
        return anim;
    }


    private static void permutar(List<Integer> lista, int a, int b) {
        Integer permutador = lista.get(a); // permutador = lista[a]
        lista.set(a, lista.get(b)); // lista[a] = lista[b]
        lista.set(b, permutador); // lista[b] = permutador
    }

    private static int encontrarIndiceMenorElem(List<Integer> lista, int idxInicio) {
        int menor = idxInicio;
        for (int i = idxInicio+1; i < lista.size(); i++) {
            if (lista.get(menor) > lista.get(i))
                menor = i;
        }
        return menor;
    }

    private static boolean isOrdenada(List<Integer> lista) {
        for (int i = 1; i < lista.size(); i++)
            if (lista.get(i-1) > lista.get(i))
                return false;

        return true;
    }

}