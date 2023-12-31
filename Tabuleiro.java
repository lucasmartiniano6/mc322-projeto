import java.util.ArrayList;

public class Tabuleiro {
    // Tabuleiro de xadrez, contém as peças e suas posições
    // O tabuleiro é representado por um grid 8x8 de peças
    // As posições são representadas por letras de A até H e números de 1 até 8, mapeados para inteiros de 0 até 7

    private Peca[][] grid = new Peca[8][8];
    private ArrayList<Peca> brancas = new ArrayList<>();
    private ArrayList<Peca> pretas = new ArrayList<>();
    private ArrayList<Pair> listaFensJogo = new ArrayList<>();
    private ArrayList<Peca> pecasComidas = new ArrayList<>();
    private static JanelaChess janela;
    private static JanelaAssistir janelaExibir;
    private Relogio relogioBrancas;
    private Relogio relogioPretas;
    private String lastPlay = "preta";
    private String enPassantNow = null;
    private String enPassantNext = null;
    private int coldMoves = 0;

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
        adicionarTabuleiro(FEN.generateFen(this));
    }

    // Getters e Setters
    public ArrayList<Peca> getPecasComidas() {
        return pecasComidas;
    }

    public static JanelaAssistir getJanela_a() {
        return janelaExibir;
    }

    public void setJanela(JanelaAssistir janelaExibir) {
        Tabuleiro.janelaExibir = janelaExibir;
    }

    public JanelaChess getJanela() {
        return janela;
    }

    public void setJanela(JanelaChess janela) {
        Tabuleiro.janela = janela;
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

    public void setEnPassantNow(String pos) {
        enPassantNow = pos;
    }

    public String getEnPassantNow() {
        return enPassantNow;
    }

    public void setColdMoves(int coldMoves) {
        this.coldMoves = coldMoves;
    }

    public int getColdMoves() {
        return coldMoves;
    }

    public void setEnPassantNext(String pos) {
        enPassantNext = pos;
    }

    public String getEnPassantNext() {
        return enPassantNext;
    }

     public void setLastPlay(String lastPlay) {
        this.lastPlay = lastPlay;
    }

    public String getLastPlay() {
        return lastPlay;
    }

    public Relogio getRelogio_brancas() {
        return relogioBrancas;
    }


    public void setRelogio_brancas(Relogio relogioBrancas) {
        this.relogioBrancas = relogioBrancas;
    }

    public Relogio getRelogio_pretas() {
        return relogioPretas;
    }

    public void setRelogio_pretas(Relogio relogioPretas) {
        this.relogioPretas = relogioPretas;
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


    public void setEmpty(String posicao){
        int x = Peca.getPosX(posicao);
        int y = Peca.getPosY(posicao);
        grid[x][y] = null;
    }
    
    
    public void setEmpty(int x, int y){
        grid[x][y] = null;
    }


    public boolean setPeca(String posicao, Peca peca){
        // true se houve captura, false cc
        boolean returnFlag = false;
        int x = Peca.getPosX(posicao);
        int y = Peca.getPosY(posicao);
        setEmpty(peca.getPosicao());
        if(this.grid[x][y] != null){
            if(this.grid[x][y].getCorDono().equals("branca"))
                brancas.remove(this.grid[x][y]);
            else
                pretas.remove(this.grid[x][y]);
            pecasComidas.add(grid[x][y]);
            returnFlag = true;
        }
        this.grid[x][y] = peca;
        peca.setPosicao(posicao);
        return returnFlag;
    }


    public Peca getPeca(String posicao){
        int x = Peca.getPosX(posicao);
        int y = Peca.getPosY(posicao);
        return grid[x][y];
    }


    public boolean setBoardFromFEN(String filename){
        // Lê um arquivo FEN e coloca as peças no tabuleiro de acordo
        FEN fen = new FEN();
        
        String data = fen.load("fen/lastFens/" +filename);
        if(data == null) return false;
        return fen.setBoard(data, this);
    } 

    public boolean saveBoard(String filename){
        FEN fen = new FEN();
        return fen.save(filename, this);
    }


    public void fischerInitializer(){
        // Inicializa o tabuleiro com uma posição Fischer aleatória
        // https://en.wikipedia.org/wiki/Fischer_random_chess
        FEN fischer = new FEN();
        String data = fischer.load("fen/fischer.fen");
        String fens[] = data.split(" ");
        // Seleciona uma posição aleatória entre as 960 posições possíveis
        int random = (int)(Math.random() * 960);
        System.out.println(random);
        fischer.setBoard(fens[random], this);
    }


    private void adicionarTabuleiro(String fen) {
        for(Pair par : listaFensJogo) {
            if(par.getFen().equals(fen)) {
                par.increaseTimes();
                if(par.getTimes() == 3) {
                    endGame("repetição"); //jogo acabou empatado (por repetição)
                }
                return;
            }
        }
        listaFensJogo.add(new Pair(fen));
    }

   
    public void playRelogio(String corJogador){
        if(!relogioBrancas.getCorDono().equals(corJogador)){
            relogioBrancas.despausaRelogio(relogioBrancas.getTimer());
        } else{
            if (!relogioPretas.isStarted()){   
                relogioPretas.startRelogio();
            }else{
                relogioPretas.despausaRelogio(relogioPretas.getTimer());
            }
        }
    }


    public void pauseRelogio(String corJogador){
        if(relogioBrancas.getCorDono().equals(corJogador)){
            relogioBrancas.pausaRelogio(relogioBrancas.getTimer());
        } else {
            relogioPretas.pausaRelogio(relogioPretas.getTimer());
        }
    }
    

    public boolean mover(String origem, String destino){
        // Movimenta a peça da origem para o destino
        Peca peca = getPeca(origem);
        if(peca == null) {
            return false;   
        }
        if(peca.getCorDono().equals(getLastPlay())) {
            return false;
        }
        // playRelogio(peca);
        try {
            boolean madeMove = peca.moverPeca(destino);
            String fen = FEN.generateFen(this);
            
            // playRelogio(peca);
            if(madeMove) {
                saveBoard(fen);
                adicionarTabuleiro(fen);
                setLastPlay(peca.getCorDono());
                pauseRelogio(peca.getCorDono());
                playRelogio(peca.getCorDono());
                
            }
            return madeMove;
        } catch (NullPointerException e) {
            System.out.println("Erro ao mover peça: " + e);
            return false;
        }
    }
    

    public boolean isChecked(String corDono){
        String reiPos = null;
        if(corDono.equals("branca")) {
            for(Peca peca : brancas) {
                if(peca instanceof Rei) {
                    reiPos = peca.getPosicao();
                    break;
                }
            }
            for(Peca peca : pretas) {
                if(peca.isReachable(reiPos, true)) {
                    return true;
                }
            }
        }
        else {
            for(Peca peca : pretas) {
                if(peca instanceof Rei) {
                    reiPos = peca.getPosicao();
                    break;
                }
            }
            for(Peca peca : brancas) {
                if(peca.isReachable(reiPos, true)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean noMoves(String corDono){
        ArrayList<Peca> pecas = corDono.equals("branca") ? getBrancas() : getPretas(); 
        ArrayList<Peca> pecasCopy = new ArrayList<Peca>(pecas);
        for(Peca peca: pecasCopy){
            for(Posicao letra: Posicao.values()){
                for(int num = 1; num <= 8; num++){
                    String posicao = letra.name() + Integer.toString(num);
                    if(peca.legalMove(posicao)) { // existe ao menos 1 movimento que salva a partida
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public static void endGame(String motivo, String corGanhador) {
        System.out.println(corGanhador + "s vencem!");
        System.out.println("Motivo: " + motivo);
        
        janela.close(motivo, corGanhador);
        
    }


    public void endGame(String motivo) {
        System.out.println("Empate!");
        System.out.println("Motivo: " + motivo);
        janela.close(motivo);
    }
}
