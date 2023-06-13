public class Main {
    public static void printarTabuleiro(Tabuleiro tabuleiro){
        System.out.println("*******************");
        for(int j = 0; j < 8; j++){
            for(int i = 0; i < 8; i++){
                Peca peca = tabuleiro.getPeca(i, j);
                if(peca == null) System.out.print(" . ");
                else System.out.print(peca.getCorDono().substring(0,1) + peca.getClass().getSimpleName().substring(0,1) + " ");
            }
            System.out.println();
        }
        System.out.println("*******************");
    }
    
    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro();

        // Debugging do tabuleiro
        printarTabuleiro(tabuleiro);

        // Debugging movimentos
        tabuleiro.getBrancas().get("K").moverPeca("E2"); // tentar mover o Rei branco para E2 
        printarTabuleiro(tabuleiro);
        tabuleiro.getBrancas().get("P1").moverPeca("B3"); // tentar mover o Peao 1 branco para B3 
        printarTabuleiro(tabuleiro);
 
    }
}
