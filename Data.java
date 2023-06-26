public interface Data {
    // Interface para carregar e salvar dados
    public static final int NUM_COLUNAS = 8;
    public static final int NUM_LINHAS = 8;

    public String load(String filename);
    public boolean save(String filename, Tabuleiro data);
}
