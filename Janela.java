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
        boolean dark = true;
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 800);
        panel.setBackground(Color.BLUE);
 
         for(int y=0; y<800; y+=100){
            for(int x=0; x<800; x+=200){
                new Square(x, y, dark, panel, tabuleiro);
            }
            for(int x=100; x<800; x+=200){
                new Square(x, y, !dark, panel, tabuleiro);
            }
            dark = !dark;
        }


        window.add(panel);
    }

    public JFrame getWindow(){
        return this.window;
    }
}

