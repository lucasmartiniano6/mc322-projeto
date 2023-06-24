import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

public class JanelaChess extends Window {
    private int size;
    int x_proporcional = (int) (getResolucao().getWidth() * 0.45);
    int y_proporcional = (int) (getResolucao().getHeight() * 0.85);
    
    
    public JanelaChess(Tabuleiro tabuleiro){
        super(tabuleiro);   
    }

    // cria uma janela
    public void gerarJanela(){
        setWindow(new JFrame());
         int x_proporcional = (int) (getResolucao().getWidth() * 0.45);
        int y_proporcional = (int) (getResolucao().getHeight() * 0.85);
        getWindow().setBounds(x_proporcional, y_proporcional, 816, y_proporcional);
        getWindow().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getWindow().setLayout(null);
        getWindow().setLocationRelativeTo(null);
        getWindow().setResizable(false);
        getWindow().setVisible(true);
        getWindow().getContentPane().setBounds(x_proporcional, y_proporcional, 815, y_proporcional);
        getWindow().getContentPane().setBackground(Color.WHITE);
        getWindow().repaint();
        getTabuleiro().getRelogio_brancas().startRelogio();

    }

    // cria o background e as pecas
    public void makeBackground(){
        this.setPecas(getWindow().getContentPane());

        // setLinhas
        for(int y=214; y<614; y+=200){ // Linhas impares
            for(int x=0; x<800; x+=200){
                new Square(x, y, "dark", getWindow().getContentPane(), getTabuleiro());
                new Square(x+100, y, "light", getWindow().getContentPane(),getTabuleiro());
            }
        }
        for(int y=314; y<614; y+=200){ // Linhas pares
            for(int x=0; x<800; x+=200){
                new Square(x, y, "light", getWindow().getContentPane(), getTabuleiro());
                new Square(x+100, y, "dark",getWindow().getContentPane(), getTabuleiro());
            }
        }
        gerarRelogios();
        // getWindow().add(panel);
    }



    public void gerarRelogios(){
        JPanel PanelRelogioBrancas = new JPanel();
        JPanel PanelRelogioPretas = new JPanel();
        getWindow().getContentPane().add(PanelRelogioBrancas);
        getWindow().getContentPane().add(PanelRelogioPretas);
        PanelRelogioBrancas.setBounds(0,y_proporcional+845, 800, 40);;
        PanelRelogioBrancas.setBackground(Color.WHITE);
        JLabel LabelBrancas = getTabuleiro().getRelogio_brancas().getLabelRelogio();
        LabelBrancas.setForeground(Color.BLACK);
        PanelRelogioBrancas.add(LabelBrancas);
        PanelRelogioPretas.setBounds(x_proporcional,y_proporcional + 5, 800, 40);
        JLabel LabelPretas = getTabuleiro().getRelogio_pretas().getLabelRelogio();
        LabelPretas.setForeground(Color.BLACK);
        // LabelBrancas.setBounds(getWindow().getWidth()/2,getWindow().getHeight() - 5, 50, 50);
        PanelRelogioPretas.setBackground(Color.WHITE);
        PanelRelogioPretas.add(LabelPretas);
        LabelPretas.setHorizontalAlignment(JLabel.CENTER);
        LabelPretas.setVerticalAlignment(JLabel.CENTER);
        
        
        // getWindow().getContentPane().add(PanelRelogioBrancas);

        
        
    }


    // cria as pecas
    public void setPecas(Container panel){
        for(Peca peca : getTabuleiro().getBrancas()){
            int x = Peca.getPosX(peca.getPosicao()) * 100;
            int y = (7 - Peca.getPosY(peca.getPosicao())) * 100;
            new Square(x, y +14, peca.getLabel(), getWindow().getContentPane(), getTabuleiro());
        }
        for(Peca peca : getTabuleiro().getPretas()){
            int x = Peca.getPosX(peca.getPosicao()) * 100;
            int y = (7 - Peca.getPosY(peca.getPosicao())) * 100;
            new Square(x, y +14, peca.getLabel(), getWindow().getContentPane(), getTabuleiro());
        }
    }
     
    public void close(){
        getWindow().dispose();
    }



}

