public class Peao extends Peca{
    public boolean moverPeca(String destino){
        // Implementação do movimento do Peão
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = getPosY(destino) - getPosY(this.getPosicao()); // deltaY > 0 se o peão está indo para frente

        if(deltaX > 1 || (deltaY != 1 && deltaY != 2)) // Fora do alcance do Peão
            return false;

        // Aqui, com certeza deltaY = (1 ou 2) e deltaX = (0 ou 1)
        int nx = getPosX(destino);
        int ny = getPosY(destino);
        if(nx < 0 && nx >= 8 && ny < 0 && ny >= 8) // Posição do grid inválida
            return false;

        
        if (deltaY == 2){
            if(getMovimentou() == true || deltaX != 0) // Já se movimentou ou está tentando se mover para o lado
                return false;
            if(this.getTabuleiro().getPeca(nx, ny) != null) // Caso a posição não esteja vazia
                return false;
            
            return this._make_move(destino);
        }        
        else if (deltaY == 1){
            if(this.getTabuleiro().getPeca(nx, ny) != null){ // Caso a posição não esteja vazia
                if(this.getTabuleiro().getPeca(nx, ny).getCorDono() != this.getCorDono()){ // Peca inimiga
                    if(deltaX == 0) return false;
                    return this._make_move(destino);
                }
            }
            else if(deltaX == 0){ // Caso a posição esteja vazia
                return this._make_move(destino);
            }
    
        }
        return false;
   }
    
    public Peao(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
    }
}
