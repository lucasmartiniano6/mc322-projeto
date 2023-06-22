import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Square {
    JButton button;
    String icon;
    static ArrayList<Square> selected;
    Tabuleiro tabuleiro;

    // constructor
    // tempo dado indica os minutos
    public Square(int x, int y, boolean dark, JPanel panel, Tabuleiro tabuleiro){
        selected = new ArrayList<Square>();
        this.tabuleiro = tabuleiro;

        if(dark)
            icon = "dark";
        else
            icon = "light";
        button = new JButton(new ImageIcon("imgs/" + icon + ".png"));    

        button.setBounds(x, y, 100, 100);  
        button.addActionListener(new ActionListener(){  
        public void actionPerformed(ActionEvent e){action();}});  
        panel.add(button);    
    }         

    public static void printarTabuleiro(Tabuleiro tabuleiro){
        System.out.println("**************************");
        for(int j = 7; j >= 0; j--){
            System.out.print(j+1 + " ");
            for(int i = 0; i < 8; i++){
                Peca peca = tabuleiro.getPeca(i, j);
                if(peca == null) System.out.print(" . ");
                else System.out.print(" " + peca.getLabel() + " ");
            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H");
        System.out.println("**************************");
    }

    public void parSelected(){
        // Caso tenha dois selecionados
        int x1 = (selected.get(0).button.getX() / 100) + 1;
        int y1 = 8 - (selected.get(0).button.getY() / 100);
        int x2 = (selected.get(1).button.getX() / 100) + 1;
        int y2 = 8 - (selected.get(1).button.getY() / 100);

        String letra = "ABCDEFGH";
        String s1 = letra.substring(x1-1, x1) + y1;
        String s2 = letra.substring(x2-1, x2) + y2;

        if(tabuleiro.mover(s1, s2)){
            System.out.println(s1 + " " + s2);        
            printarTabuleiro(tabuleiro);
        }
        else
            System.out.println("Movimento inválido");
        
    }

    public void action(){
        if(selected.size() == 1){ // Caso tenha dois selecionados
            for(Square s : selected){
                if(s.icon.equals("dark_selected"))
                    s.icon = "dark";
                else if(s.icon.equals("light_selected"))
                    s.icon = "light";
                s.button.setIcon(new ImageIcon("imgs/" + s.icon + ".png"));
            }
            selected.add(this);
            parSelected();
            selected.clear();
        }
        else {
            if(icon.equals("dark"))
                icon = "dark_selected";
            else if(icon.equals("dark_selected"))
                icon = "dark";
            else if(icon.equals("light"))
                icon = "light_selected";
            else if(icon.equals("light_selected"))
                icon = "light";
            button.setIcon(new ImageIcon("imgs/" + icon + ".png"));    
            selected.add(this);
        }
   }
}

