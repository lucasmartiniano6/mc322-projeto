public class Cavalo extends Peca {
    
    public boolean moverPeca(String destino){
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(this.getPosicao()) - getPosY(destino));
        
        
        if(deltaX + deltaY !=3) {  // verifica se o movimento está no alcance do cavalo
            return false;
        }

        //  semelhante ao rei
        int nx = getPosX(destino);
        int ny = getPosY(destino);
        if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8) // Posição do grid inválida
            return false;
       
        if(this.getTabuleiro().getPeca(nx, ny) == null){ // Caso o destino esteja vazio
            return this._make_move(destino);

        } else { // Caso o destino contenha alguma peça
           if(this.getTabuleiro().getPeca(nx, ny).getCorDono() != this.getCorDono())// Verifica se o destino possui uma peca inimiga
                return this._make_move(destino);
            else 
                return false;
        }
    }



    // Constructor
    public Cavalo(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
        if(corDono.equals("branca")){
            setLabel("C");
        }else
            setLabel("c");
    }
}
