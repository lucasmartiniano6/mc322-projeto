public class Bispo extends Peca{
    // Construtor
    public Bispo(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
        if(corDono.equals("branca"))
            setLabel("B");
        else
            setLabel("b");
    }

    public boolean isReachable(String destino, boolean test) {
        // Implementação do movimento do Bispo
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(this.getPosicao()) - getPosY(destino));

        if(deltaX != deltaY) // Fora do alcance do Bispo
            return false;

        int nx = getPosX(destino);
        int ny = getPosY(destino);
        if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8) // Posição do grid inválida
            return false;
        
        if(nx > getPosX(this.getPosicao()) && ny > getPosY(this.getPosicao())) { // Movimento na diagonal superior direita
            for(int i = 1; i < deltaX; i++) {
                if(this.getTabuleiro().getPeca(getPosX(this.getPosicao()) + i, getPosY(this.getPosicao()) + i) != null) { // Caso haja alguma peça entre o Bispo e o destino
                    return false;
                }
            }
        }
        else if(nx > getPosX(this.getPosicao()) && ny < getPosY(this.getPosicao())) { // Movimento na diagonal inferior direita
            for(int i = 1; i < deltaX; i++) {
                if(this.getTabuleiro().getPeca(getPosX(this.getPosicao()) + i, getPosY(this.getPosicao()) - i) != null) { // Caso haja alguma peça entre o Bispo e o destino
                    return false;
                }
            }
        }
        else if(nx < getPosX(this.getPosicao()) && ny > getPosY(this.getPosicao())) { // Movimento na diagonal superior esquerda
            for(int i = 1; i < deltaX; i++) {
                if(this.getTabuleiro().getPeca(getPosX(this.getPosicao()) - i, getPosY(this.getPosicao()) + i) != null) { // Caso haja alguma peça entre o Bispo e o destino
                    return false;
                }
            }
        }
        else if(nx < getPosX(this.getPosicao()) && ny < getPosY(this.getPosicao())) { // Movimento na diagonal inferior esquerda
            for(int i = 1; i < deltaX; i++) {
                if(this.getTabuleiro().getPeca(getPosX(this.getPosicao()) - i, getPosY(this.getPosicao()) - i) != null) { // Caso haja alguma peça entre o Bispo e o destino
                    return false;
                }
            }
        }

        if(this.getTabuleiro().getPeca(nx, ny) != null) { // Caso a posição de destino não esteja vazia
            if(!this.getTabuleiro().getPeca(nx, ny).getCorDono().equals(this.getCorDono())) { // Peça inimiga
                return true;
            }
            else
                return false;
        }
        else { // Caso a posição de destino esteja vazia
            return true;
        }
    }
}
