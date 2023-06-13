public class Tabuleiro {
    private Peca[][] grid = new Peca[8][8];

    public Tabuleiro(){
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                grid[i][j] = null;
        
        Rei K1 = new Rei("branco", this, "E1");
        this.setPeca(K1.getPosicao(), K1);
 
        K1.moverPeca("E2");
    }

    public void setEmpty(String posicao){
        int x = Peca.getPosX(posicao);
        int y = Peca.getPosY(posicao);
        grid[x][y] = null;
    }
    
    public void setPeca(String posicao, Peca peca){
        int x = Peca.getPosX(posicao);
        int y = Peca.getPosY(posicao);
        this.grid[x][y] = peca;
        peca.setPosicao(posicao);
    }

    public Peca getPeca(int x, int y){
        return grid[x][y];
    }
}
