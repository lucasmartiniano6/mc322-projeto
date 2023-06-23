public class Peao extends Peca{
    public boolean isReachable(String destino){
        // Implementação do movimento do Peão
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(destino) - getPosY(this.getPosicao())); // deltaY > 0 se o peão está indo para frente

        if(this.getCorDono().equals("branca") && getPosY(destino) <= getPosY(this.getPosicao())) // Peão branco não pode ir para trás
            return false;
        if(this.getCorDono().equals("preta") && getPosY(destino) >= getPosY(this.getPosicao())) // Peão preto não pode ir para trás
            return false;

        if(deltaX > 1 || (deltaY != 1 && deltaY != 2)) // Fora do alcance do Peão
            return false;

        // Aqui, com certeza deltaY = (1 ou 2) e deltaX = (0 ou 1)
        int nx = getPosX(destino);
        int ny = getPosY(destino);
        if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8) // Posição do grid inválida
            return false;

        if (deltaY == 2){
            if(this.getMovimentos() > 0 || deltaX != 0) // Já se movimentou ou está tentando se mover para o lado
                return false;
            if(ny < getPosY(this.getPosicao())){
                if(this.getTabuleiro().getPeca(nx, ny) != null || this.getTabuleiro().getPeca(nx, ny + 1) != null) // Caso a posição não esteja vazia
                    return false;                                                                                  //ou exista uma peça no caminho
            } else if (ny > getPosY(this.getPosicao()))
                if(this.getTabuleiro().getPeca(nx, ny) != null || this.getTabuleiro().getPeca(nx, ny - 1) != null) // Caso a posição não esteja vazia
                    return false;                                                                                  //ou exista uma peça no caminho
            return true;
        }        
        else if (deltaY == 1){
            if(this.getTabuleiro().getPeca(nx, ny) != null){ // Caso a posição não esteja vazia
                if(!this.getTabuleiro().getPeca(nx, ny).getCorDono().equals(this.getCorDono())){ // Peça inimiga
                    if(deltaX == 0) 
                        return false;
                    return true;
                }
            }
            else if(deltaX == 0){ // Caso a posição esteja vazia
                if(this.getCorDono().equals("branca") && ny == 8) {
                    // Promoção de um peão das brancas
                }
                else if(this.getCorDono().equals("preta") && ny == 1) {
                    // Promoção de um peão das pretas
                }
                else {
                    return true;
                }   
            }
    
        }
        return false;
   }
    
    public Peao(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
        if(corDono.equals("branca"))
            setLabel("P");
        else
            setLabel("p");
    }
}
