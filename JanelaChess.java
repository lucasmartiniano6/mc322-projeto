import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.*;

public class JanelaChess extends Window {
    int x_proporcional = (int) (getResolucao().getWidth() * 0.45);
    int y_proporcional = (int) (getResolucao().getHeight() * 0.85);
    
    
    public JanelaChess(Tabuleiro tabuleiro){
        super(tabuleiro);   
        
        getTabuleiro().setJanela(this);

    }

    public JanelaChess(Tabuleiro tabuleiro, int delay){
        super(tabuleiro);
        getTabuleiro().setJanela(this);
        watchGame();
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
        getWindow().getContentPane().repaint();
        // getWindow().add(panel);
    }

     public void watchGame(){
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
        PanelRelogioBrancas.repaint();
        PanelRelogioPretas.repaint();
        
        
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
     


    public void getEndingWindow(String motivo, String corGanhador){
        JFrame janela_fim = new JFrame();
        janela_fim.setLayout(null);
        janela_fim.setSize(400, 100);
        janela_fim.setVisible(true);
        janela_fim.setResizable(false);
        janela_fim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela_fim.setLocationRelativeTo(null);
        janela_fim.getContentPane().setBackground(Color.WHITE);
        
        JPanel panel_ganhador = new JPanel();
        panel_ganhador.setBackground(Color.WHITE);
        panel_ganhador.setBounds(0 , 0, 400,30);
        JLabel ganhador = new JLabel(corGanhador + "s vencem!");
        ganhador.setVerticalAlignment(JLabel.TOP);
        ganhador.setHorizontalAlignment(JLabel.CENTER);
        ganhador.setForeground(Color.BLACK);

        JPanel panel_motivo = new JPanel();
        panel_motivo.setBackground(Color.WHITE);
        panel_motivo.setBounds(0, janela_fim.getHeight()/4, 400,30);
        JLabel label_motivo = new JLabel(motivo);
        label_motivo.setVerticalAlignment(JLabel.CENTER);
        label_motivo.setHorizontalAlignment(JLabel.CENTER);
        ganhador.setForeground(Color.BLACK);
        
        panel_ganhador.add(ganhador);
        panel_motivo.add(label_motivo);
        janela_fim.add(panel_ganhador);
        janela_fim.add(panel_motivo);
        
    }

    public void getEndingWindow(String motivo){
        JFrame janela_fim = new JFrame();
        janela_fim.setLayout(null);
        janela_fim.setSize(400, 100);
        janela_fim.setVisible(true);
        janela_fim.setResizable(false);
        janela_fim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela_fim.setLocationRelativeTo(null);
        janela_fim.getContentPane().setBackground(Color.WHITE);
        
        JPanel panel_empate = new JPanel();
        panel_empate.setBackground(Color.WHITE);
        panel_empate.setBounds(0 , 0, 400,30);
        JLabel empate = new JLabel("EMPATE!");
        empate.setVerticalAlignment(JLabel.TOP);
        empate.setHorizontalAlignment(JLabel.CENTER);
        empate.setForeground(Color.BLACK);

        JPanel panel_motivo = new JPanel();
        panel_motivo.setBackground(Color.WHITE);
        panel_motivo.setBounds(0, janela_fim.getHeight()/4, 400,30);
        JLabel label_motivo = new JLabel("Motivo: " + motivo);
        label_motivo.setVerticalAlignment(JLabel.CENTER);
        label_motivo.setHorizontalAlignment(JLabel.CENTER);
        empate.setForeground(Color.BLACK);
        
        panel_empate.add(empate);
        panel_motivo.add(label_motivo);
        janela_fim.add(panel_empate);
        janela_fim.add(panel_motivo);
    }


    public void close(String motivo, String corGanhador){
        getTabuleiro().getRelogio_brancas().pausaRelogio( getTabuleiro().getRelogio_brancas().getTimer());
        getTabuleiro().getRelogio_pretas().pausaRelogio( getTabuleiro().getRelogio_pretas().getTimer());
        getEndingWindow(motivo, corGanhador);
        getWindow().dispose();
    }

    public void close(String motivo){
        getTabuleiro().getRelogio_brancas().pausaRelogio( getTabuleiro().getRelogio_brancas().getTimer());
        getTabuleiro().getRelogio_pretas().pausaRelogio( getTabuleiro().getRelogio_pretas().getTimer());
        getEndingWindow(motivo);
        getWindow().dispose();
    }



}

