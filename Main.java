import java.util.Scanner;

public class Main {
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
    
    public static void main(String[] args){
        
        Scanner entrada = new Scanner(System.in);
        Tabuleiro tabuleiro = new Tabuleiro();

        // teste relogio
        Relogio relogio1 = new Relogio(1, "brancas");
        relogio1.startRelogio();
        relogio1.timer.start();


        printarTabuleiro(tabuleiro); // Debugging do tabuleiro
        tabuleiro.saveBoard("fen/board.fen");

        // Debugging movimentos
        tabuleiro.setBoardFromFEN("fen/board.fen");
        while(true) {
           String s1 = entrada.nextLine();
           String s2 = entrada.nextLine();
           tabuleiro.mover(s1, s2);
           printarTabuleiro(tabuleiro);
        }
    }
}
