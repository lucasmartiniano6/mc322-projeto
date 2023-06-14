public abstract class Peca{
    private final String corDono; // "branca" ou "preta"
    private final Tabuleiro tabuleiro;
    private String posicao; // Ex: "A1", "A8", "H1", "H8"
    private boolean movimentou;

    public Peca(String corDono, Tabuleiro tabuleiro, String posicao){
        this.corDono = corDono;
        this.tabuleiro = tabuleiro;
        this.posicao = posicao;
        this.movimentou = false;
        if(corDono != "branca" && corDono != "preta")
            throw new IllegalArgumentException("Cor inválida");
    }

    public abstract boolean moverPeca(String destino);

    public boolean _make_move(String destino){
        // Internamente chamado por moverPeca quando o movimento é válido
        this.setMovimentou(true);
        this.getTabuleiro().setEmpty(getPosicao());
        this.getTabuleiro().setPeca(destino, this);
        return true;
    }
   

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

    public void setMovimentou(boolean movimentou) {
        this.movimentou = movimentou;
    }

    public boolean getMovimentou() {
        return movimentou;
    }
};