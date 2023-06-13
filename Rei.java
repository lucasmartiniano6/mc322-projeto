public class Rei extends Peca{
    public boolean moverPeca(String destino){
        // Implementação do movimento do Rei
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(this.getPosicao()) - getPosY(destino));

        if(deltaX > 1 || deltaY > 1) // Fora do alcance do Rei
            return false;

        int nx = getPosX(destino);
        int ny = getPosY(destino);
        if(nx < 0 && nx >= 8 && ny < 0 && ny >= 8) // Posição do grid inválida
            return false;

        if(this.getTabuleiro().getPeca(nx, ny) != null){ // Caso a posição não esteja vazia
            if(this.getTabuleiro().getPeca(nx, ny).getCorDono() != this.getCorDono()){ // Peca inimiga
                this.getTabuleiro().setEmpty(getPosicao());
                this.getTabuleiro().setPeca(destino, this);
                return true;
            }
            else return false;
        }
        else { // Caso a posição esteja vazia
            this.getTabuleiro().setEmpty(getPosicao());
            this.getTabuleiro().setPeca(destino, this);
            return true;
        }
    }

    public Rei(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
    }
}
