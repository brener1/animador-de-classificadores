package br.unifil.dc.lab2;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ListIterator;

/**
 * Write a description of class Tocador here.
 * 
 * @author Ricardo Inacio
 * @version 20200409
 */
public class Tocador extends JPanel {

    public Tocador(ListIterator<Transparencia> quadrosFilme) {
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        carregarFilme(quadrosFilme);
    }
    public Tocador() {
        this(null);
    }
    
    public void carregarFilme(ListIterator<Transparencia> quadrosFilme) {
        this.quadrosFilme = quadrosFilme;
        this.quadroAtual = null;
        numQuadro = 0;
    }
    
    public void avancarFilme() {
        if (quadrosFilme.hasNext()) {
            quadroAtual = quadrosFilme.next();
            numQuadro++;
        }
    }
    
    public void voltarFilme() {
        if (quadrosFilme.hasPrevious()) {
            quadroAtual = quadrosFilme.previous();
            numQuadro--;
        }
    }
    
    public void rebobinarFilme() {
        while (quadrosFilme.hasPrevious()) {
            quadroAtual = quadrosFilme.previous();
            numQuadro--;
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D pincel = (Graphics2D) g;
        
        if (quadroAtual != null) {
            quadroAtual.pintar(pincel, this);
        } else {
            // ESCREVER NO MEIO DA TELA "O Filme ainda não iniciou."
            //throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
            pincel.drawString("O Filme ainda não iniciou", getWidth()/2-65, getHeight()/2);
        }
        
        // ESCREVER NO CANTO INFERIOR DIREITO DA TELA "Quadro 'numQuadro'"
        //throw new RuntimeException("O aluno ainda não implementou essa funcionalidade.");
        pincel.drawString("Quadro "+numQuadro, getWidth()-100, getHeight()-20);
    }
    
    private int numQuadro = 0;
    private Transparencia quadroAtual = null;
    private ListIterator<Transparencia> quadrosFilme = null;
}
