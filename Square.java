import javax.lang.model.util.ElementScanner6;
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
    public Square(int x, int y, String peca, JPanel panel, Tabuleiro tabuleiro){
        selected = new ArrayList<Square>();
        this.tabuleiro = tabuleiro;

        icon = peca;
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

        Square origem = selected.get(0);
        Square destino = selected.get(1);

        if(tabuleiro.mover(s1, s2)){
            destino.icon = origem.icon;
            destino.button.setIcon(new ImageIcon("imgs/" + destino.icon + ".png"));

            origem.icon = "light"; 
            origem.button.setIcon(new ImageIcon("imgs/" + origem.icon + ".png"));

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
                else 
                    s.icon = s.icon.substring(0, s.icon.length() - 9);
                s.button.setIcon(new ImageIcon("imgs/" + s.icon + ".png"));
            }
            selected.add(this);
            parSelected();
            selected.clear();
        }
        else {
            // if p is selected then unselect it
            if(icon.equals("dark"))
                icon = "dark_selected";
            else if(icon.equals("light"))
                icon = "light_selected";
            else if(icon.equals("dark_selected"))
                icon = "dark";
            else if(icon.equals("light_selected"))
                icon = "light";
            else if(icon.length() == 1)
                icon = icon + "_selected";
            else if(icon.substring(icon.length() - 9, icon.length()).equals("selected"))
                icon = icon.substring(0, icon.length() - 9);

            button.setIcon(new ImageIcon("imgs/" + icon + ".png"));    
            selected.add(this);
        }
   }
}

