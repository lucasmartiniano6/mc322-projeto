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
    }
}
