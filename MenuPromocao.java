import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Toolkit;


public class MenuPromocao {
    private JFrame window;
    private JPanel panel;
    private final Tabuleiro tabuleiro;
    private JButton opcaoCavalo;
    private JButton opcaoBispo;
    private JButton opcaoTorre;
    private JButton opcaoRainha;
    private JLabel label;
    private String type = null;


    public MenuPromocao(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        gerarJanela();
        makeBackground();
    }


    public void makeBackground() {
        label = new JLabel("Escolha a peça da promoção:");
        opcaoCavalo = new JButton("Cavalo");
        opcaoBispo = new JButton("Bispo");
        opcaoTorre = new JButton("Torre");
        opcaoRainha = new JButton("Rainha");

        JPanel panel = new JPanel();
        getWindow().add(panel);
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(opcaoCavalo);
        panel.add(opcaoBispo);
        panel.add(opcaoTorre);
        panel.add(opcaoRainha);
        panel.setVisible(true);
        

        opcaoCavalo.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                setValor("cavalo");
                getWindow().dispose();
            }
        }); 
        opcaoBispo.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                setValor("bispo");
                getWindow().dispose();
            }
        }); 
        opcaoTorre.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                setValor("torre");
                getWindow().dispose();
            }
        }); 
        opcaoRainha.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                setValor("raiha");
                getWindow().dispose();
            }
        }); 
        getWindow().revalidate();
        } 

    public String getValor() {
        return type;
    }

    public void setValor(String type) {
        this.type = type;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }

    public JFrame getWindow(){
        return window;
    }
    
    public JPanel getPanel() {
        return panel;
    }
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public Dimension getResolucao(){
        Toolkit tool = Toolkit.getDefaultToolkit();
        return tool.getScreenSize();
      
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
        
    public void gerarJanela(){
        setWindow((new JFrame()));
        getWindow().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getWindow().setSize(200, 175);
        getWindow().setLocationRelativeTo(null);
        getWindow().setVisible(true);
    }

    }
    //   