import javax.swing.*;
import java.awt.event.*;

public class Relogio {
    String corDono;
    int tempo;
    JFrame window;
    JLabel label;
    int minutos;
    int segundos;
    Timer timer;

    // constructor
    // tempo dado indica os minutos
    public Relogio(int tempo, String corDono){
        this.segundos = 0;
        this.minutos = tempo;
        setWindowRelogio();  
    }

    // cria uma janela para o relogio
    public void setWindowRelogio(){
        this.window = new JFrame();
        window.setSize(100, 100);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        label = new JLabel();
        label= new JLabel(minutos + ":" + segundos);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        window.add(label);
        window.setVisible(true);
    }

    public void startRelogio(){
        this.timer = new Timer(1000, new ActionListener(){   
            public void actionPerformed(ActionEvent e){
                if (segundos !=0){
                    segundos--;
                } else if(segundos == 0){
                    segundos = 59;
                    minutos--;
                }
                if (minutos  == 0 && segundos == 0){
                    timer.stop();
                }
                label.setText(minutos + ":" + segundos);
            }
        });
    }
}

