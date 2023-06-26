import javax.swing.*;
import java.util.Timer;
import java.awt.Container;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class Square {
    private static ArrayList<Square> selected = new ArrayList<>(); // Guarda os dois squares selecionados (em verde)
    private static Square[][] squares = new Square[8][8]; // Grid dos squares (mesma ordem do tabuleiro)
    private Tabuleiro tabuleiro;
    private JButton button;
   
    private String icon; // imagem do square (mesma label da peca)

    public Square(int x, int y, String peca, Container panel, Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;

        this.icon = peca;
        int x_map = x/100;
        int y_map = 7-(y/100);
        if((x_map+y_map)%2 != 0){
            // light background
            if(Character.isUpperCase(peca.charAt(0)))
                this.icon = "white/" + peca + "_light"; // peca branca 
            else
                this.icon = "black/" + peca + "_light"; // peca preta
        } 
        else{
            // dark background
            if(Character.isUpperCase(peca.charAt(0)))
                this.icon = "white/" + peca + "_dark"; // peca branca 
            else
                this.icon = "black/" + peca + "_dark"; // peca preta
        }
        
        if(peca.equals("dark"))
            this.icon = "light";
        else if(peca.equals("light"))
            this.icon = "dark";
        
        this.button = new JButton(new ImageIcon("imgs/" + icon + ".png"));    

        this.button.setBounds(x, y+25, 100, 100);  
        this.button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){action();}
        });  
        panel.add(button);    
        
        // map squares in a grid
        squares[x_map][y_map] = this;
    }         

    public Square(int x, int y, String peca, Container panel){
    
        this.icon = peca;
        int x_map = x/100;
        int y_map = 7-(y/100);
        if((x_map+y_map)%2 == 0){
            // light background
            if(Character.isUpperCase(peca.charAt(0)))
                this.icon = "white/" + peca + "_light"; // peca branca 
            else
                this.icon = "black/" + peca + "_light"; // peca preta
        } 
        else{
            // dark background
            if(Character.isUpperCase(peca.charAt(0)))
                this.icon = "white/" + peca + "_dark"; // peca branca 
            else
                this.icon = "black/" + peca + "_dark"; // peca preta
        }
        
        if(peca.equals("dark"))
            this.icon = "dark";
        else if(peca.equals("light"))
            this.icon = "light";
        
        this.button = new JButton(new ImageIcon("imgs/" + icon + ".png"));    

        this.button.setBounds(x, y+25, 100, 100);  
        panel.add(button);    
        // map squares in a grid
        squares[x_map][y_map] = this;
        // int delay = 6000;   // delay de 5 seg.
        // int interval = 1000;  // intervalo de 1 seg.
        // Timer timer = new Timer();
        // timer.scheduleAtFixedRate(new TimerTask() {
        // public void run() {
        //     // File fen = new File("fen/lastFens/" + "tabuleiro" + ".fen");
            
        //     }
        // }, delay, delay);

    }         


    public static void printarTabuleiro(Tabuleiro tabuleiro){
        // Printar o tabuleiro no terminal
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


    public static void setMovimento(Tabuleiro tabuleiro){
        for(int x=0; x<8; x++){
            for(int y=0; y<8; y++){
                Peca peca = tabuleiro.getPeca(x, y);
                Square square = squares[x][y];
                if(peca == null){
                    // Caso não tenha peca no tabuleiro (icon = "dark" ou "light" de fundo)
                    // linha par
                        // coluna par -> light
                        // coluna impar -> dark
                    // linha impar
                        // coluna par -> dark
                        // coluna impar -> light
                    if((x+y)%2 == 0)
                        square.icon = "light";
                    else
                        square.icon = "dark";
                    square.button.setIcon(new ImageIcon("imgs/" + square.icon + ".png"));
                }
                else if(!peca.getLabel().equals(square.icon)){
                    // Caso tenha peca no tabuleiro e o icon do square não seja o mesmo da peca
                    square.icon = peca.getLabel();
                    if((x+y)%2 == 0){
                        if(Character.isUpperCase(peca.getLabel().charAt(0)))
                            square.icon = "white/" + square.icon + "_light";
                        else
                            square.icon = "black/" + square.icon + "_light";
                    }
                    else{
                        if(Character.isUpperCase(peca.getLabel().charAt(0)))
                            square.icon = "white/" + square.icon + "_dark";
                        else
                            square.icon = "black/" + square.icon + "_dark";
                    }
                    square.button.setIcon(new ImageIcon("imgs/" + square.icon + ".png"));
                }
            }
            }
    }

    // private static  void atualizaMov(Tabuleiro tabuleiro){
    //     int delay = 6000;   // delay de 5 seg.
        
    //     Timer timer = new Timer();
    //     timer.scheduleAtFixedRate(new TimerTask() {
    //     public void run() {
    //         // File fen = new File("fen/lastFens/" + "tabuleiro" + ".fen");
    //         setMovimento(tabuleiro);
    //         }
    //     }, delay, delay);
    // }


    public void parSelected(){
        // Caso tenha dois selecionados (em verde)
        int x1 = (selected.get(0).button.getX() / 100) + 1;
        int y1 = 8 - ((selected.get(0).button.getY() / 100));
        int x2 = (selected.get(1).button.getX() / 100) + 1;
        int y2 = 8 - ((selected.get(1).button.getY() / 100));

        String letra = "ABCDEFGH";
        String s1 = letra.substring(x1-1, x1) + y1;
        String s2 = letra.substring(x2-1, x2) + y2;

        
        if(tabuleiro.mover(s1, s2)){
            // Movimento válido
            // Iterar por todas as pecas do grid (Tabuleiro) e atualizar o icon dos squares (interface) de acordo
            for(int x=0; x<8; x++){
                for(int y=0; y<8; y++){
                    Peca peca = tabuleiro.getPeca(x, y);
                    Square square = squares[x][y];
                    if(peca == null){
                        // Caso não tenha peca no tabuleiro (icon = "dark" ou "light" de fundo)
                        // linha par
                            // coluna par -> light
                            // coluna impar -> dark
                        // linha impar
                            // coluna par -> dark
                            // coluna impar -> light
                        if((x+y)%2 == 0)
                            square.icon = "dark";
                        else
                            square.icon = "light";
                        square.button.setIcon(new ImageIcon("imgs/" + square.icon + ".png"));
                    }
                    else if(!peca.getLabel().equals(square.icon)){
                        // Caso tenha peca no tabuleiro e o icon do square não seja o mesmo da peca
                        square.icon = peca.getLabel();
                        if((x+y)%2 != 0){
                            if(Character.isUpperCase(peca.getLabel().charAt(0)))
                                square.icon = "white/" + square.icon + "_light";
                            else
                                square.icon = "black/" + square.icon + "_light";
                        }
                        else{
                            if(Character.isUpperCase(peca.getLabel().charAt(0)))
                                square.icon = "white/" + square.icon + "_dark";
                            else
                                square.icon = "black/" + square.icon + "_dark";
                        }
                        square.button.setIcon(new ImageIcon("imgs/" + square.icon + ".png"));
                    }
                }
            }

            System.out.println(s1 + " " + s2);        
            printarTabuleiro(tabuleiro);
        }
        else
            System.out.println("Movimento inválido");
        
    }

    public void action(){
        if(selected.size() == 1){ // Caso tenha dois selecionados
            for(Square s : selected){
                // Desselecionar o square
                if(s.icon.equals("dark_selected"))
                    s.icon = "dark";
                else if(s.icon.equals("light_selected"))
                    s.icon = "light";
                else 
                    s.icon = s.icon.substring(0, s.icon.length() - 9);
                s.button.setIcon(new ImageIcon("imgs/" + s.icon + ".png"));
            }
            selected.add(this);
            parSelected(); // Verificar se o movimento é válido
            selected.clear();
        }
        else {
            // Selecionar o square (em verde) ou desselecionar
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
            else if(icon.substring(icon.length() - 9, icon.length()).equals("_selected"))
                icon = icon.substring(0, icon.length() - 9);
            else
                icon = icon + "_selected";


            button.setIcon(new ImageIcon("imgs/" + icon + ".png"));    
            selected.add(this);
        }
   }
}

