
import java.awt.Color;
import java.awt.Container;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

public class JanelaAssistir extends Window {
    public JanelaAssistir(Tabuleiro tabuleiro){
        super(tabuleiro);
        getTabuleiro().setJanela(this);
        // atualizaMov();
        
    }

    // cria uma janela
    public void gerarJanela(){
        setWindow(new JFrame());
         int x_proporcional = (int) (getResolucao().getWidth() * 0.45);
        int y_proporcional = (int) (getResolucao().getHeight() * 0.85);
        getWindow().setBounds(x_proporcional, y_proporcional, 815, y_proporcional);
        getWindow().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getWindow().setLayout(null);
        getWindow().setLocationRelativeTo(null);
        getWindow().setResizable(false);
        getWindow().setVisible(true);
        getWindow().getContentPane().setBounds(x_proporcional, y_proporcional, 815, y_proporcional);
        getWindow().getContentPane().setBackground(Color.WHITE);
        getWindow().repaint();

    }

     public void makeBackground(){
        this.setPecas(getWindow().getContentPane());
        // setLinhas
        for(int y=214; y<614; y+=200){ // Linhas impares
            for(int x=0; x<800; x+=200){
                new Square(x, y, "dark", getWindow().getContentPane());
                new Square(x+100, y, "light", getWindow().getContentPane());
            }
        }
        for(int y=314; y<614; y+=200){ // Linhas pares
            for(int x=0; x<800; x+=200){
                new Square(x, y, "light", getWindow().getContentPane());
                new Square(x+100, y, "dark",getWindow().getContentPane());
            }
        }
        

    }

    public static void atualizaMov(Tabuleiro tabuleiro){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
        public void run() {
            // File fen = new File("fen/lastFens/" + "tabuleiro" + ".fen");
            Square.setMovimento(tabuleiro);
            }
        }, 0 ,2000);
    }

    


    // cria as pecas
    public void setPecas(Container panel){
        for(Peca peca : getTabuleiro().getBrancas()){
            int x = Peca.getPosX(peca.getPosicao()) * 100;
            int y = (7 - Peca.getPosY(peca.getPosicao())) * 100;
            new Square(x, y +14, peca.getLabel(), getWindow().getContentPane());
        }
        for(Peca peca : getTabuleiro().getPretas()){
            int x = Peca.getPosX(peca.getPosicao()) * 100;
            int y = (7 - Peca.getPosY(peca.getPosicao())) * 100;
            new Square(x, y +14, peca.getLabel(), getWindow().getContentPane());
        }
    }
     

}
