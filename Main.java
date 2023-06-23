public class Main {


    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro();
        new MenuPrincipal(tabuleiro);
        
        // Relogio relogio1 = new Relogio(1, "brancas", janela);
        // relogio1.startRelogio();

        
        // Scanner entrada = new Scanner(System.in);

        // printarTabuleiro(tabuleiro); // Debugging do tabuleiro
        // tabuleiro.saveBoard("fen/board.fen");

        // // Debugging movimentos
        // tabuleiro.setBoardFromFEN("fen/board.fen");
        // while(true) {
        //    String s1 = entrada.nextLine();
        //    String s2 = entrada.nextLine();
        //    tabuleiro.mover(s1, s2);
           
        //    printarTabuleiro(tabuleiro);
    }
}
