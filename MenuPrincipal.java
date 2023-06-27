import javax.swing.*;
import java.awt.Color;
import java.util.Timer;
import java.awt.event.*;
import java.io.File;
import java.util.TimerTask;

public class MenuPrincipal extends Window  {
    private static int num = 0 ;


    public MenuPrincipal(Tabuleiro tabuleiro){
        super(tabuleiro);
    }

    public void gerarJanela(){
        setWindow((new JFrame()));
        getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getWindow().setSize(getResolucao());
        getWindow().setLayout(null);
        getWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);
        getWindow().setVisible(true);
        getWindow().repaint();
    }


    public void makeBackground(){
        setPanel(new JPanel());
        getPanel().setSize(getResolucao());
        
        getPanel().setLayout(null);
        getPanel().setBackground(Color.GRAY);

        JButton play_button = new JButton();
        JButton end_button = new JButton();
        JButton watch_button = new JButton();

        play_button.setText("JOGAR");
        end_button.setText("SAIR");
        watch_button.setText("ASSISTIR JOGO");
        play_button.setBounds( (int) ((getResolucao().getWidth()/2) -100), (int) getResolucao().getHeight()/2 - 100, 200, 50);
        watch_button.setBounds( (int) ((getResolucao().getWidth()/2) -100), (int) getResolucao().getHeight()/2 - 30, 200, 50);
        end_button.setBounds((int) ((getResolucao().getWidth()/2) -100), (int) getResolucao().getHeight()/2 + 40, 200, 50);
        
        getPanel().add(play_button);
        getPanel().add(end_button);
        getPanel().add(watch_button);
        play_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                new MenuTempo(getTabuleiro());
            }
        });  
        end_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });  
        watch_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                Tabuleiro tabuleiro = new Tabuleiro();
                int delay = 3000;  // indica que a primeira chamada ocorre apos 3 segundos  
                int period = 2000; // 3 segundos de intervalo
                Timer timer = new Timer();
                JanelaAssistir JanelaWatch  = new JanelaAssistir(tabuleiro);
                
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        File fen = new File("fen/lastFens/");
                        if(num == 0){
                            JanelaWatch.getTabuleiro().setBoardFromFEN("tabuleiro.fen");
                            JanelaAssistir.atualizaMov(tabuleiro);
                            Square.printarTabuleiro(tabuleiro);
                            num++;
                        }else if (num > 0 && num < fen.listFiles().length){
                            JanelaWatch.getTabuleiro().setBoardFromFEN("tabuleiro" + Integer.toString(num) + ".fen");
                            JanelaAssistir.atualizaMov(tabuleiro);
                            Square.printarTabuleiro(tabuleiro);
                            num++;
                        if(num >= fen.listFiles().length){
                            num = 0;
                            timer.cancel();
                            this.cancel();
                            
                        }
                        }
                }
            }, delay, period);
            }
        });
        getWindow().add(getPanel());
    }
}
