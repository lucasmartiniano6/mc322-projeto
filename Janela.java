import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class Janela {
    JFrame window;
    int size = 900;
    Tabuleiro tabuleiro;

    // constructor
    public Janela(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        setWindow();  
        setBackground();
    }

    // cria uma janela para o relogio
    public void setWindow(){
        this.window = new JFrame();
        window.setSize(size, size);
        window.setPreferredSize(new Dimension(size, size));
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
    }

    public void setBackground(){
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 800);
        panel.setBackground(Color.BLUE);
 
        // setPecas
        this.setPecas(panel);

        // setLinhas
        for(int y=200; y<600; y+=200){ // Linhas impares
            for(int x=0; x<800; x+=200){
                new Square(x, y, "dark", panel, tabuleiro);
                new Square(x+100, y, "light", panel, tabuleiro);
            }
        }
        for(int y=300; y<600; y+=200){ // Linhas pares
            for(int x=0; x<800; x+=200){
                new Square(x, y, "light", panel, tabuleiro);
                new Square(x+100, y, "dark", panel, tabuleiro);
            }
        }

        window.add(panel);
    }

    public void setPecas(JPanel panel){
        for(Peca peca : tabuleiro.getBrancas()){
            int x = Peca.getPosX(peca.getPosicao()) * 100;
            int y = (7 - Peca.getPosY(peca.getPosicao())) * 100;
            new Square(x, y, peca.getLabel(), panel, tabuleiro);
        }
        for(Peca peca : tabuleiro.getPretas()){
            int x = Peca.getPosX(peca.getPosicao()) * 100;
            int y = (7 - Peca.getPosY(peca.getPosicao())) * 100;
            new Square(x, y, peca.getLabel(), panel, tabuleiro);
        }
    }


    public JFrame getWindow(){
        return this.window;
    }
}

