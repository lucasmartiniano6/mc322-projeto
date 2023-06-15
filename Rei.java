public class Rei extends Peca{
    public boolean moverPeca(String destino){
        // Implementação do movimento do Rei
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(this.getPosicao()) - getPosY(destino));

        int nx = getPosX(destino);
        int ny = getPosY(destino);
        if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8) // Posição do grid inválida
            return false;

        if(this.getTabuleiro().getPeca(nx, ny) != null){ // Caso a posição não esteja vazia
            if(!this.getTabuleiro().getPeca(nx, ny).getCorDono().equals(this.getCorDono())){ // Peça inimiga
                return this._make_move(destino);
            }
            else 
                return false;
        }
        else { // Caso a posição esteja vazia
            if(!this.getMovimentou() && deltaX == 2 && deltaY == 0) { // Implementação do roque
                if(this.getCorDono().equals("branca")) {
                    if(destino.equals("G1")) { // Roque menor do Rei das brancas
                        if(this.getTabuleiro().getPeca("F1") == null && this.getTabuleiro().getPeca("G1") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("H1").getClass() == Torre.class && !this.getTabuleiro().getPeca("H1").getMovimentou()) {
                                this.getTabuleiro().getPeca("H1").moverPeca("F1");
                                return this._make_move(destino);
                            }
                        }
                    }
                    else if(destino.equals("C1")) { // Roque maior do Rei das brancas
                        if(this.getTabuleiro().getPeca("B1") == null && this.getTabuleiro().getPeca("C1") == null && this.getTabuleiro().getPeca("D1") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("A1").getClass() == Torre.class && !this.getTabuleiro().getPeca("A1").getMovimentou()) {
                                this.getTabuleiro().getPeca("A1").moverPeca("D1");
                                return this._make_move(destino);
                            }
                        }
                    }
                }
                else if(this.getCorDono().equals("preta")) {
                    if(destino.equals("G8")) { // Roque menor do Rei das pretas
                        if(this.getTabuleiro().getPeca("F8") == null && this.getTabuleiro().getPeca("G8") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("H8").getClass() == Torre.class && !this.getTabuleiro().getPeca("H8").getMovimentou()) {
                                this.getTabuleiro().getPeca("H8").moverPeca("F8");
                                return this._make_move(destino);
                            }
                        }
                    }
                    else if(destino.equals("C8")){ // Roque maior do Rei das pretas
                        if(this.getTabuleiro().getPeca("B8") == null && this.getTabuleiro().getPeca("C8") == null && this.getTabuleiro().getPeca("D8") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("A8").getClass() == Torre.class && !this.getTabuleiro().getPeca("A8").getMovimentou()) {
                                this.getTabuleiro().getPeca("A8").moverPeca("D8");
                                return this._make_move(destino);
                            }
                        }
                    }
                }
                return false;
            }
            else if(deltaX > 1 || deltaY > 1) // Fora do alcance do Rei
                return false;
            return this._make_move(destino);
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
