public abstract class Peca{
    private final String corDono; // "branca" ou "preta"
    private final Tabuleiro tabuleiro;
    private String posicao; // Ex: "A1", "A8", "H1", "H8"
    private int movimentos;
    private String label; // Ex: "P", "B", "R", "Q", "K" (notação FEN)

    public Peca(String corDono, Tabuleiro tabuleiro, String posicao){
        this.corDono = corDono;
        this.tabuleiro = tabuleiro;
        this.posicao = posicao;
        this.movimentos = 0;
        if(corDono != "branca" && corDono != "preta")
            throw new IllegalArgumentException("Cor inválida");
    }

    public boolean moverPeca(String destino) {
        String lastPos = this.getPosicao();
        String corDono = this.getCorDono();
        boolean comeu = false;
        String corAdversario = corDono.equals("branca") ? "preta" : "branca";
        if(this.isReachable(destino)) {
            comeu = _make_move(destino);
            if(!this.getTabuleiro().isChecked(corDono)) {
                // movimento válido do jogador atual (não me deixa checked)
                // se é checked (cor inimiga)
                //  -> se é mate --> END GAME (checkmate)
                //  -> senão -> continue
                // senão é checked (cor inimiga)
                //  -> se é mate --> END GAME (afogamento)
                if(this.getTabuleiro().isChecked(corAdversario)) {
                    if(this.getTabuleiro().noMoves(corAdversario)) {
                        System.out.println("Xeque-mate. " + corDono + "s vencem!");
                        System.exit(0); //end game
                    }
                }
                else {
                    if(this.getTabuleiro().noMoves(corAdversario)) {
                        System.out.println("Afogamento. Partida empatada!");
                        System.exit(0); //end game
                    }
                }
                return true;
            }
            else{
                // movimento inválido
                // deixa o jogador atual checked
                this._undo_move(lastPos, destino, comeu);
                return false;
            }
        }
        return false;
    }

    public boolean legalMove(String destino) {
        boolean comeu = false;
        String lastPos = this.getPosicao();
        if(this.isReachable(destino)) {
           comeu = _make_move(destino);
            if(!this.getTabuleiro().isChecked(this.getCorDono())) {
                this._undo_move(lastPos, destino, comeu);
                return true;
            }
            this._undo_move(lastPos, destino, comeu); 
        }
        return false;
    }

    protected abstract boolean isReachable(String destino);

    private boolean _make_move(String destino){
        // Internamente chamado por moverPeca quando o movimento é válido
        // Coloca a peça em destino
        this.setMovimentos(++movimentos);
        this.getTabuleiro().setEmpty(getPosicao());
        return(getTabuleiro().setPeca(destino, this));
    }

    
    public void _undo_move(String lastPos, String destino, boolean comeu){
        // Internamente chamado por moverPeca quando o movimento é inválido
        // Coloca a peça em lastPos
        this.setMovimentos(--movimentos);
        if(comeu) {
            Peca pecaRemov = tabuleiro.getPecasComidas().get(tabuleiro.getPecasComidas().size()-1);
            if(pecaRemov.getCorDono().equals("branca")) {
                tabuleiro.addBrancas(pecaRemov);
            } else if(pecaRemov.getCorDono().equals("preta")) {
                tabuleiro.addPretas(pecaRemov);
            }
            getTabuleiro().setPeca(lastPos, this);
            getTabuleiro().setPeca(destino, pecaRemov);
            tabuleiro.getPecasComidas().remove(pecaRemov);
        } else {
            getTabuleiro().setEmpty(destino);
            getTabuleiro().setPeca(lastPos, this);
        }
    }

    public static int getPosX(String pos){
        try{
            return Posicao.valueOf(pos.substring(0,1)).valor;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return 0;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }

    public int getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(int movimentos) {
        this.movimentos = movimentos;
    }
}