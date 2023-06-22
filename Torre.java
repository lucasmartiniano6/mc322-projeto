public class Torre extends Peca{
    public boolean isReachable(String destino, boolean test) {
        // Implementação do movimento da Torre
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(this.getPosicao()) - getPosY(destino));

        if(deltaX > 0 && deltaY > 0) // Fora do alcance da Torre
            return false;

        int nx = getPosX(destino);
        int ny = getPosY(destino);
        if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8) // Posição do grid inválida
            return false;

        if(deltaX == 0){
            if(ny > getPosY(this.getPosicao())) { // Caso a Torre esteja se movendo para cima
                for(int i = getPosY(this.getPosicao()) + 1; i < ny; i++) {
                    if(this.getTabuleiro().getPeca(getPosX(this.getPosicao()), i) != null) { // Caso haja alguma peça entre a Torre e o destino
                        return false;
                    }
                }
            }
            else { // Caso a Torre esteja se movendo para baixo
                for(int i = getPosY(this.getPosicao()) - 1; i > ny; i--) {
                    if(this.getTabuleiro().getPeca(getPosX(this.getPosicao()), i) != null) { // Caso haja alguma peça entre a Torre e o destino
                        return false;
                    }
                }
            }
        }
        if(deltaY == 0) {
            if (nx > getPosX(this.getPosicao())) { // Caso a Torre esteja se movendo para a direita
                for(int i = getPosX(this.getPosicao()) + 1; i < nx; i++) {
                    if(this.getTabuleiro().getPeca(i, getPosY(this.getPosicao())) != null) // Caso haja alguma peça entre a Torre e o destino
                        return false;
                }
            }
            else { // Caso a Torre esteja se movendo para a esquerda
                for(int i = getPosX(this.getPosicao()) - 1; i > nx; i--) {
                    if(this.getTabuleiro().getPeca(i, getPosY(this.getPosicao())) != null) // Caso haja alguma peça entre a Torre e o destino
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

    public Torre(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
        if(corDono.equals("branca"))
            setLabel("R");
        else
            setLabel("r");
    }
}
