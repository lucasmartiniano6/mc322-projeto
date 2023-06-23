import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class MenuPrincipal extends Window  {
    
    public MenuPrincipal(Tabuleiro tabuleiro){
        super(tabuleiro);
    }

    public void gerarJanela(){
        setWindow((new JFrame()));
        getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getWindow().setLayout(null);
        getWindow().setExtendedState(JFrame.MAXIMIZED_BOTH);
        getWindow().setVisible(true);
        getWindow().repaint();
    }

    public void makeBackground(){
        setPanel(new JPanel());
        setPanel(new JPanel());
        getPanel().setBounds(0, 0, getWindow().getWidth(), getWindow().getHeight());
        getPanel().setLayout(null);
        getPanel().setBackground(Color.GRAY);
        JButton play_button = new JButton();
        JButton end_button = new JButton();
        play_button.setText("JOGAR");
        end_button.setText("SAIR");
        play_button.setBounds((getWindow().getWidth()/2) -100, getWindow().getHeight()/2 - 100, 200, 50);
        end_button.setBounds(getWindow().getWidth()/2 - 100, getWindow().getHeight()/2 -15, 200, 50);
        getPanel().add(play_button);
        getPanel().add(end_button);
        play_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                new menu_tempo(getTabuleiro());
            }
        });  
        // botoesmenu.setSize(200, 20);
        end_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });  
        getWindow().add(getPanel());
        
    }


    // public static void main(String[] args){
    //     JFrame Menu = new MenuPrincipal();
        
    //     Menu.setVisible(true);
        
        //  public static void main(String[] args){
        // // Tabuleiro tabuleiro = new Tabuleiro();
        // // Janela janela = new Janela(tabuleiro);
        //   MenuPrincipal menu = new MenuPrincipal();
        // //  Tabuleiro tabuleiro = new Tabuleiro();
        // // Janela janela = new Janela(tabuleiro);
        
        //  }
        
    // }
}
