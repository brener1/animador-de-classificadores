package br.unifil.dc.lab2;

import javax.swing.JPanel;
import java.awt.*;
import java.util.List;

/**
 * Write a description of class ListaGravada here.
 * 
 * @author Ricardo Inacio
 * @version 20200409
 */
public class ListaGravada implements Transparencia
{
    /**
     * Constructor for objects of class ListaGravada
     */
    public ListaGravada(List<Integer> lista, List<Color> coresIndices, String nome) {
        this.lista = lista;
        this.nome = nome;
        this.coresIndices = coresIndices;
    }
    
    
    public void pintar(Graphics2D pincel, JPanel contexto) {
        Dimension dim = contexto.getSize();

        //descobrir maior elemento da lista
        int maiorElemento = 0;
        for(int i = 0; i<lista.size(); i++){
            if(lista.get(i)>maiorElemento){
                maiorElemento = lista.get(i);
            }
        }
        //descobrir altura de cada coluna
        int base = (500*contexto.getHeight())/600;
        double[] alturaColunas = new double[lista.size()];
        double propAltura = (double) 300/maiorElemento;
        double auxAltura;
        for(int i = 0; i< lista.size(); i++){
            auxAltura= (double) lista.get(i)*propAltura;
            alturaColunas[i] = base-auxAltura;
        }

        //proporção
        int larguraProp = 72;
        int distanciaProp = 50;
        int margemProp = 20;

        int x = contexto.getWidth();
        int y = contexto.getHeight();

        //tamanho margem
        int margem = 15;

        //valor da largura das colunas
        double largura;
        int numeroColunas = lista.size();
        int numeroEspacos = numeroColunas-1;
        largura = (x-(2*margem))/(1.694*numeroEspacos+1);

        //valor da distancia entre colunas
        double distancia;
        distancia = largura*0.694;
        BasicStroke larguraPincel = new BasicStroke(3);
        pincel.setStroke(larguraPincel);
        //linha da base
        pincel.setColor(Color.BLACK);
        pincel.drawLine(0,base,x,base);

        int posiRect = margem;
        for (int i = 0; i<lista.size(); i++){
            //preenchimento do retangulo
            if (coresIndices.get(i) == null){
                pincel.setColor(Color.BLUE);
            }else{
                pincel.setColor(coresIndices.get(i));
            }
            pincel.fillRect(posiRect, (int)alturaColunas[i], (int)largura, base-(int)alturaColunas[i]);

            //contorno do retangulo
            pincel.setColor(Color.BLACK);
            pincel.drawRect(posiRect, (int)alturaColunas[i], (int)largura, base-(int)alturaColunas[i]);


            /*Desenha os retângulos linha por linha
            pincel.drawLine(posiRect, base, posiRect+(int)largura, base);//baixo
            pincel.drawLine(posiRect+(int)largura, base, posiRect+(int)largura, (int)alturaColunas[i]);//direita
            pincel.drawLine(posiRect+(int)largura, (int)alturaColunas[i], posiRect,(int)alturaColunas[i]);//cima
            pincel.drawLine(posiRect, (int)alturaColunas[i], posiRect, base);//esquerda
            */

            //desenha nome
            String nome = lista.get(i).toString();
            int posiNome = margem+(int)largura/2;
            pincel.drawString(nome, posiRect+((int)largura/3),base+30);

            //define a posição do proximo retangulo e proximo nome
            posiNome += distancia+largura;
            posiRect += largura+distancia;
        }

    }
    
    
    private List<Integer> lista;
    private List<Color> coresIndices;
    private String nome;
}
