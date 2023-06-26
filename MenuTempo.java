import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuTempo extends Window {
    private JButton opcao3;
    private JButton opcao5;
    private JButton opcao10;
    private JLabel label;
   


    public MenuTempo(Tabuleiro tabuleiro){
        super(tabuleiro);
    }

    // exlcui os arquivos fen da pasta lastFens;
    public void apagarPasta(){
        File pasta = new File("fen/lastFens/" );
        File[] listaFens = pasta.listFiles();  
        for(int i = 0; i < listaFens.length; i ++){
            listaFens[i].delete();
        }
    }



    public void makeBackground() {
        this.label = new JLabel("Escolha o tempo de jogo:");
        this.opcao3 = new JButton("3 minutos");
        this.opcao5 = new JButton("5 minutos");
        this.opcao10 = new JButton("10 minutos");
        JPanel panel = new JPanel();
        getWindow().add(panel);
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(opcao3);
        panel.add(opcao5);
        panel.add(opcao10);
        
        opcao3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                apagarPasta();
                Tabuleiro tabuleiro = new Tabuleiro();
                Relogio relogio_brancas = new Relogio(3, "branca");
                Relogio relogio_pretas = new Relogio(3, "preta");
                tabuleiro.setRelogio_brancas(relogio_brancas);
                tabuleiro.setRelogio_pretas(relogio_pretas);
                new JanelaChess(tabuleiro);
                
                
            }
        }); 
        opcao5.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                apagarPasta();
                Tabuleiro tabuleiro = new Tabuleiro();
                Relogio relogio_brancas = new Relogio(5, "branca");
                Relogio relogio_pretas = new Relogio(5, "preta");
                tabuleiro.setRelogio_brancas(relogio_brancas);
                tabuleiro.setRelogio_pretas(relogio_pretas);
                new JanelaChess(tabuleiro);
            }
        }); 
        opcao10.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                apagarPasta();
                Tabuleiro tabuleiro = new Tabuleiro();
                Relogio relogio_brancas = new Relogio(10, "branca");
                Relogio relogio_pretas = new Relogio(10, "preta");
                tabuleiro.setRelogio_brancas(relogio_brancas);
                tabuleiro.setRelogio_pretas(relogio_pretas);
                new JanelaChess(tabuleiro);
            }
        });
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

        
        

        

    
