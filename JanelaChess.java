import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

public class JanelaChess extends Window {
    private int size;

    public JanelaChess(Tabuleiro tabuleiro){
        super(tabuleiro);
        
        
    }


    // cria uma janela
    public void gerarJanela(){
        setWindow(new JFrame());
        getWindow().setSize(900, 900);
        getWindow().setPreferredSize(new Dimension(900, 900));
        getWindow().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getWindow().setLayout(null);
        getWindow().setLocationRelativeTo(null);
        getWindow().setVisible(true);
        getWindow().getContentPane().setBounds(0, 0, 800, 900);
        getWindow().getContentPane().setBackground(Color.WHITE);
        getWindow().repaint();
        getTabuleiro().getRelogio_brancas().startRelogio();

    }

    // cria o background e as pecas
    public void makeBackground(){
        this.setPecas(getWindow().getContentPane());

        // setLinhas
        for(int y=225; y<625; y+=200){ // Linhas impares
            for(int x=0; x<800; x+=200){
                new Square(x, y, "dark", getWindow().getContentPane(), getTabuleiro());
                new Square(x+100, y, "light", getWindow().getContentPane(),getTabuleiro());
            }
        }
        for(int y=325; y<625; y+=200){ // Linhas pares
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
        // PanelRelogioBrancas.setBackground(Color.BLUE);
        // PanelRelogioBrancas.setBounds(getWindow().getWidth()/2,getWindow().getHeight() - 5, 50, 100);
        PanelRelogioBrancas.setBounds(100,10, 50, 50);;
        JLabel LabelBrancas = getTabuleiro().getRelogio_brancas().getLabelRelogio();
        // LabelBrancas.setBounds(getWindow().getWidth()/2,getWindow().getHeight() - 5, 50, 50);
        PanelRelogioBrancas.add(LabelBrancas);

        JPanel PanelRelogioPretas = new JPanel();
        // PanelRelogioPretas.setBackground(Color.BLUE);
        PanelRelogioPretas.setBounds(25,10, 50, 50);
        JLabel LabelPretas = getTabuleiro().getRelogio_pretas().getLabelRelogio();
        // LabelBrancas.setBounds(getWindow().getWidth()/2,getWindow().getHeight() - 5, 50, 50);
        
        PanelRelogioPretas.add(LabelPretas);
        // PanelRelogioPretas.setBackground(Color.BLUE);
        getWindow().getContentPane().add(PanelRelogioPretas);
        getWindow().getContentPane().add(PanelRelogioBrancas);

        
        
    }


    // cria as pecas
    public void setPecas(Container panel){
        for(Peca peca : getTabuleiro().getBrancas()){
            int x = Peca.getPosX(peca.getPosicao()) * 100;
            int y = (7 - Peca.getPosY(peca.getPosicao())) * 100;
            new Square(x, y +25, peca.getLabel(), getWindow().getContentPane(), getTabuleiro());
        }
        for(Peca peca : getTabuleiro().getPretas()){
            int x = Peca.getPosX(peca.getPosicao()) * 100;
            int y = (7 - Peca.getPosY(peca.getPosicao())) * 100;
            new Square(x, y +25, peca.getLabel(), getWindow().getContentPane(), getTabuleiro());
        }
    }

}

