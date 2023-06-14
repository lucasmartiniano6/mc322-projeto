public class Main {
    public static void printarTabuleiro(Tabuleiro tabuleiro){
        System.out.println("**************************");
        for(int j = 7; j >= 0; j--){
            System.out.print(j+1 + " ");
            for(int i = 0; i < 8; i++){
                Peca peca = tabuleiro.getPeca(i, j);
                if(peca == null) System.out.print(" . ");
                else System.out.print(peca.getCorDono().substring(0,1) + peca.getClass().getSimpleName().substring(0,1) + " ");
            }
            System.out.println();
        }
        System.out.println("   A  B  C  D  E  F  G  H");
        System.out.println("**************************");
    }
    
    public static void main(String[] args){
        Tabuleiro tabuleiro = new Tabuleiro();

        printarTabuleiro(tabuleiro); // Debugging do tabuleiro

        // Debugging movimentos
        tabuleiro.mover("C2", "C4");
        tabuleiro.mover("C7", "C5");
        printarTabuleiro(tabuleiro);
    }
}
