public class Rei extends Peca{
    public boolean isReachable(String destino) {
        // Implementação do movimento do Rei
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(this.getPosicao()) - getPosY(destino));

        int nx = getPosX(destino);
        int ny = getPosY(destino);
        
        if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8) // Posição do grid inválida
            return false;

        if(this.getTabuleiro().getPeca(nx, ny) != null){ // Caso a posição não esteja vazia
            if(!this.getTabuleiro().getPeca(nx, ny).getCorDono().equals(this.getCorDono()) && deltaX <= 1 && deltaY <= 1){ // Peça inimiga
                return true;
            }
            else 
                return false;
        }
        else { // Caso a posição esteja vazia
            if(this.getMovimentos() == 0 && deltaX == 2 && deltaY == 0) { // Implementação do roque
                if(this.getCorDono().equals("branca")) {
                    if(destino.equals("G1")) { // Roque menor do Rei das brancas
                        if(this.getTabuleiro().getPeca("F1") == null && this.getTabuleiro().getPeca("G1") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("H1") instanceof Torre && this.getTabuleiro().getPeca("H1").getMovimentos() == 0) {
                                this.getTabuleiro().getPeca("H1").moverPeca("F1");
                                return true;
                            }
                        }
                    }
                    else if(destino.equals("C1")) { // Roque maior do Rei das brancas
                        if(this.getTabuleiro().getPeca("B1") == null && this.getTabuleiro().getPeca("C1") == null && this.getTabuleiro().getPeca("D1") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("A1") instanceof Torre && this.getTabuleiro().getPeca("A1").getMovimentos() == 0) {
                                this.getTabuleiro().getPeca("A1").moverPeca("D1");
                                return true;
                            }
                        }
                    }
                }
                else if(this.getCorDono().equals("preta")) {
                    if(destino.equals("G8")) { // Roque menor do Rei das pretas
                        if(this.getTabuleiro().getPeca("F8") == null && this.getTabuleiro().getPeca("G8") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("H8") instanceof Torre && this.getTabuleiro().getPeca("H8").getMovimentos() == 0) {
                                this.getTabuleiro().getPeca("H8").moverPeca("F8");
                                return true;
                            }
                        }
                    }
                    else if(destino.equals("C8")){ // Roque maior do Rei das pretas
                        if(this.getTabuleiro().getPeca("B8") == null && this.getTabuleiro().getPeca("C8") == null && this.getTabuleiro().getPeca("D8") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("A8") instanceof Torre && this.getTabuleiro().getPeca("A8").getMovimentos() == 0) {
                                this.getTabuleiro().getPeca("A8").moverPeca("D8");
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            else if(deltaX > 1 || deltaY > 1) // Fora do alcance do Rei
                return false;
            return true;
        }
    }

    public Rei(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
        if(corDono.equals("branca"))
            this.setLabel("K");
        else
            this.setLabel("k");
    }
}
