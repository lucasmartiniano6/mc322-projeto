public class Main {


    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro();
        new MenuPrincipal(tabuleiro);
        
        // Tabuleiro tabuleiro = new Tabuleiro();
        // Relogio relogio_brancas = new Relogio(10, "branca");
        // Relogio relogio_pretas = new Relogio(10, "preta");
        // tabuleiro.setRelogio_brancas(relogio_brancas);
        // tabuleiro.setRelogio_pretas(relogio_pretas);
        // JanelaChess janela = new JanelaChess(tabuleiro);
        // janela.getWindow().repaint();
        


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
