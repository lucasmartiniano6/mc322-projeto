public class Rei extends Peca{
    public boolean moverPeca(String destino){
        // Implementação do movimento do Rei
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(this.getPosicao()) - getPosY(destino));

        if(deltaX > 1 || deltaY > 1) // Fora do alcance do Rei
            return false;

        int nx = getPosX(destino);
        int ny = getPosY(destino);
        if(nx >= 0 && nx < 8 && ny >= 0 && ny < 8){ // Verifica se a posição no grid é válida
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
        return false;
    }

    public Rei(String corDono, Tabuleiro tabuleiro){
        super(corDono, tabuleiro);
        if(corDono == "branco")
            this.setPosicao("E1");
        else if(corDono == "preto")
            this.setPosicao("E8");
    }
}
