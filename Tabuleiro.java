import java.util.ArrayList;

public class Tabuleiro {
    private Peca[][] grid = new Peca[8][8];
    private ArrayList<Peca> brancas = new ArrayList<>();
    private ArrayList<Peca> pretas = new ArrayList<>();
    private ArrayList<Pair> listaFensJogo = new ArrayList<>();

    public Tabuleiro(){
        // Inicializar o grid vazio
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                grid[i][j] = null;
        
        
        // Inicializar as peças brancas
        brancas.add(new Rei("branca", this, "E1")); // Rei
        brancas.add(new Rainha("branca", this, "D1")); // Rainha
        brancas.add(new Cavalo("branca", this, "B1")); // Cavalos
        brancas.add(new Cavalo("branca", this, "G1")); 
        brancas.add(new Torre("branca", this, "A1")); // Torres
        brancas.add(new Torre("branca", this, "H1"));
        brancas.add(new Bispo("branca", this, "C1")); // Bispos
        brancas.add(new Bispo("branca", this, "F1")); // Bispos
        for(int i=0; i<8; i++) // Peões
            brancas.add(new Peao("branca", this, Posicao.values()[i].toString() + "2"));
        
        // Inicializar as peças pretas
        pretas.add(new Rei("preta", this, "E8")); // Rei
        pretas.add(new Rainha("preta", this, "D8")); // Rainha
        pretas.add(new Cavalo("preta", this, "B8")); // Cavalos
        pretas.add(new Cavalo("preta", this, "G8")); 
        pretas.add(new Torre("preta", this, "A8")); // Torres
        pretas.add(new Torre("preta", this, "H8"));
        pretas.add(new Bispo("preta", this, "C8")); // Bispos
        pretas.add(new Bispo("preta", this, "F8")); // Bispos
         for(int i=0; i<8; i++) // Peões
            pretas.add(new Peao("preta", this, Posicao.values()[i].toString() + "7"));

        // Colocar as peças brancas e pretas no tabuleiro
        try{
            for(int i=0; i<brancas.size(); i++){
                Peca branca = brancas.get(i);
                this.setPeca(branca.getPosicao(), branca);

                Peca preta = pretas.get(i);
                this.setPeca(preta.getPosicao(), preta);
            }
        } catch (NullPointerException e) {
            System.out.println("Brancas e pretas devem ter o mesmo tamanho: " + e);
            throw new NullPointerException();
        }
    }

    public boolean setBoardFromFEN(String filename){
        String data = FEN.load(filename);
        if(data == null) return false;
        return FEN._setBoard(data, this);
    }

    public boolean saveBoard(String filename){
        return FEN.save(filename, this);
    }

    public void adicionarTabuleiro(String fen) {
        for(Pair par : listaFensJogo) {
            if(par.getFen().equals(fen)) {
                par.increaseTimes();
                if(par.getTimes() == 3) {
                    repetitionEnd();
                }
                return;
            }
        }
        listaFensJogo.add(new Pair(fen));
    }

    public boolean mover(String origem, String destino){
        // Movimenta a peça da origem para o destino
        try {
            Peca peca = this.getPeca(origem);
            return peca.moverPeca(destino);
        } catch (NullPointerException e) {
            System.out.println("Erro ao mover peça: " + e);
            return false;
        }
    }

    public void setEmpty(String posicao){
        int x = Peca.getPosX(posicao);
        int y = Peca.getPosY(posicao);
        grid[x][y] = null;
    }
    
    public void setPeca(String posicao, Peca peca){
        int x = Peca.getPosX(posicao);
        int y = Peca.getPosY(posicao);
        if(this.grid[x][y] != null){
            if(this.grid[x][y].getCorDono().equals("branca"))
                brancas.remove(this.grid[x][y]);
            else
                pretas.remove(this.grid[x][y]);
        }
        this.grid[x][y] = peca;
        peca.setPosicao(posicao);
    }

    public Peca getPeca(String posicao){
        int x = Peca.getPosX(posicao);
        int y = Peca.getPosY(posicao);
        return grid[x][y];
    }

    public Peca getPeca(int x, int y){
        return grid[x][y];
    }

    public Peca[][] getGrid() {
        return grid;
    }

    public void setGrid(Peca[][] grid) {
        this.grid = grid;
    }

    public ArrayList<Pair> getListaFensJogo() {
        return listaFensJogo;
    }

    public ArrayList<Peca> getBrancas() {
        return brancas;
    }

    public void setBrancas(ArrayList<Peca> brancas) {
        this.brancas = brancas;
    }
    public void addBrancas(Peca peca){
        for(Peca p: brancas)
            if(p.equals(peca))
                return;
        this.brancas.add(peca);
    }

    public ArrayList<Peca> getPretas() {
        return pretas;
    }

    public void setPretas(ArrayList<Peca> pretas) {
        this.pretas = pretas;
    }
    public void addPretas(Peca peca){
        for(Peca p: brancas)
        if(p.equals(peca))
            return;
        this.pretas.add(peca);
    }

    public boolean isChecked(String corDono){
        String reiPos = null;
        if(corDono.equals("branca")) {
            for(Peca peca : brancas) {
                if(peca instanceof Rei) {
                    reiPos = peca.getPosicao();
                }
            }
            for(Peca peca : pretas) {
                if(peca.isReachable(reiPos)) {
                    return true;
                }
            }
        }
        else {
            for(Peca peca : pretas) {
                if(peca instanceof Rei) {
                    reiPos = peca.getPosicao();
                }
            }
            for(Peca peca : brancas) {
                if(peca.isReachable(reiPos)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean noMoves(String corDono){
        ArrayList<Peca> pecas = corDono.equals("branca") ? getBrancas() : getPretas(); 
        for(Peca peca: pecas){
            for(Posicao letra: Posicao.values()){
                for(int num = 1; num <= 8; num++){
                    String posicao = letra.name() + Integer.toString(num);
                    if(peca.legalMove(posicao)) { // existe ao menos 1 movimento que salva a partida
                        peca._undo_move();
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void repetitionEnd() {
        System.out.println("O jogo acabou por empate por repetição");
    }
}
