import java.util.HashMap;
import java.util.Map;

public class Tabuleiro {
    private Peca[][] grid = new Peca[8][8];
    private Map<String, Peca> brancas = new HashMap<String, Peca>();
    private Map<String, Peca> pretas = new HashMap<String, Peca>();


    public Tabuleiro(){
        // Inicializar o grid vazio
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                grid[i][j] = null;
        
        
        // Inicializar as peças brancas
        brancas.put("P1", new Peao("branca", this, "A1")); // Pawn 1
        brancas.put("K", new Rei("branca", this, "E1")); // King
        
        // Inicializar as peças pretas
        pretas.put("P1", new Peao("preta", this, "A7"));
        pretas.put("K", new Rei("preta", this, "E8"));

        // Colocar as peças brancas e pretas no tabuleiro
        for(String key : brancas.keySet()){
            Peca branca = brancas.get(key);
            Peca preta = pretas.get(key);
            this.setPeca(branca.getPosicao(), branca);
            this.setPeca(preta.getPosicao(), preta);
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
        this.grid[x][y] = peca;
        peca.setPosicao(posicao);
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

    public Map<String, Peca> getBrancas() {
        return brancas;
    }

    public void setBrancas(Map<String, Peca> brancas) {
        this.brancas = brancas;
    }

    public Map<String, Peca> getPretas() {
        return pretas;
    }

    public void setPretas(Map<String, Peca> pretas) {
        this.pretas = pretas;
    }

}
