import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class Relogio {
    private String corDono;
    private boolean iniciado;
    private JLabel label;
    private int minutos;
    private int segundos;
    private Timer timer;
    // constructor
    // tempo dado indica os minutos

    // public void setTimer(Timer timer) {
    //     this.timer = timer;
    //     iniciado = false;
    // }


    public Relogio(int tempo, String corDono){
        this.segundos = 0;
        this.corDono =corDono;
        this.minutos = tempo;
        this.iniciado = false;
        setLabelRelogio();
    }


    public boolean isStarted(){
        return iniciado;
    }

    public String getCorDono() {
        return corDono;
    }

    public Timer getTimer(){
        return timer;
    }

    
    // cria uma janela para o relogio
    public void setLabelRelogio(){
        this.label = new JLabel(minutos + ":" + segundos);
        label.setBackground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        // window.add(label);
    }

    public JLabel getLabelRelogio(){
        return label;
    }

    public void startRelogio(){
        iniciado = true;
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
                    if(getCorDono().equals("branca"))
                        Tabuleiro.endGame("O tempo do jogador acabou", "preta");
                    else
                        Tabuleiro.endGame("O tempo do jogador acabou", "branca");
                }
                label.setText(minutos + ":" + segundos);
            }
        });
        timer.start();
    }

    public void pausaRelogio(Timer timer){
        timer.stop();
    }

    public void despausaRelogio(Timer timer){
        timer.start();
    }
}

