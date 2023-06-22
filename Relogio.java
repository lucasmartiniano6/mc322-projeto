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
    public Relogio(int tempo, String corDono, Janela janela){
        this.segundos = 0;
        this.minutos = tempo;
        this.window = janela.getWindow();
        setWindowRelogio();  
    }

    // cria uma janela para o relogio
    public void setWindowRelogio(){
        label = new JLabel(minutos + ":" + segundos);
        label.setHorizontalAlignment(1);
        label.setVerticalAlignment(JLabel.CENTER);
        window.add(label);
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
        timer.start();
    }
}

