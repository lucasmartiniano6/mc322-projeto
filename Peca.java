public abstract class Peca{
    private final String corDono; // "branco" ou "preto"
    private final Tabuleiro tabuleiro;
    private String posicao; // Ex: "A1", "A8", "H1", "H8"

    public Peca(String corDono, Tabuleiro tabuleiro, String posicao){
        this.corDono = corDono;
        this.tabuleiro = tabuleiro;
        this.posicao = posicao;
    }

    public abstract boolean moverPeca(String destino);

    public static int getPosX(String pos){
        return Posicao.valueOf(pos.substring(0,1)).valor;
    }
    
    public static int getPosY(String pos){
        return Integer.parseInt(pos.substring(1,2)) - 1;
    }
    
    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getCorDono() {
        return corDono;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
};