public class Peao extends Peca{
    public boolean isReachable(String destino, boolean test){
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
                    return false;   
            if(getCorDono().equals("preta")) {
                getTabuleiro().setEnPassantNext(String.format("%c%d", destino.charAt(0), getPosY(getPosicao())));
            } else if(getCorDono().equals("branca")) {
                getTabuleiro().setEnPassantNext(String.format("%c%d", destino.charAt(0), getPosY(getPosicao())+2));
            }                                                                         //ou exista uma peça no caminho
            return true;
        }        
        else if (deltaY == 1){
            if(this.getTabuleiro().getPeca(nx, ny) != null){ // Caso a posição não esteja vazia
                if(!this.getTabuleiro().getPeca(nx, ny).getCorDono().equals(this.getCorDono())){ // Peça inimiga
                    if(deltaX == 0) 
                        return false;
                    if(this.getCorDono().equals("branca") && ny == 7) {
                        promocao(test);
                    }
                    else if(this.getCorDono().equals("preta") && ny == 0) {
                        promocao(test);
                    }
                    return true;
                }
            } else if(getTabuleiro().getEnPassantNow() != null && getTabuleiro().getEnPassantNow().equals(destino)) {
                enPassant(destino);
                return true;
            }
            else if(deltaX == 0){ // Caso a posição esteja vazia
                if(this.getCorDono().equals("branca") && ny == 7) {
                    promocao(test);
                }
                else if(this.getCorDono().equals("preta") && ny == 0) {
                    promocao(test);
                }
                else {
                    return true;
                }   
            }
    
        }
        return false;
    }

    public void enPassant(String destino) {
        int yval;
        if(getCorDono().equals("preta")) {
            yval = getPosY(destino);
        } else {
            yval = getPosY(destino)-1;
        }
        Peca comido = getTabuleiro().getPeca(getPosX(destino), yval);
        getTabuleiro().setEmpty(getPosX(destino), yval);
        getTabuleiro().setPeca(destino, comido);
    }

    public void callPromocao(boolean test) {
        // Não usada
        if(test) {
            return;
        }
        MenuPromocao menu = new MenuPromocao(getTabuleiro());
        String type = null;
        while(type == null) {
            type = menu.getValor();
        }
    }

    public void promocao(boolean test) {
        if(test) {
            return;
        }
        Peca newPeca = null;
        String type = "rainha";
        if(type.equals("cavalo")) {
            newPeca = new Cavalo(getCorDono(), getTabuleiro(), getPosicao());
        } else if(type.equals("bispo")) {
            newPeca = new Bispo(getCorDono(), getTabuleiro(), getPosicao());
        } else if(type.equals("rainha")) {
            newPeca = new Rainha(getCorDono(), getTabuleiro(), getPosicao());
        } else if(type.equals("torre")) {
            newPeca = new Torre(getCorDono(), getTabuleiro(), getPosicao());
        } else {
            return;
        }
        setPeca(newPeca);
    }
    

    public Peao(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
        if(corDono.equals("branca"))
            setLabel("P");
        else
            setLabel("p");
    }
}
