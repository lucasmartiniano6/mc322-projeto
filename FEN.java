import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FEN implements Data{
    // Forsyth–Edwards Notation
    // https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
    // Usado para representar o estado do tabuleiro
    // Exemplo de início: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR

    public String load(String filename){
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            String data = "";
            while (scanner.hasNextLine()) {
                data += scanner.nextLine();
            }
            scanner.close();
            return data;
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return null;
        }
    }

    // verifica se a peca na lista de pecas é a mesma peca sendo analisada na listaUsadas
    public boolean verificaPeca(String posicao, Peca peca){
        if(posicao.equals(peca.getPosicao())){
            return true;
        }
        return false;
    }


    public boolean _setBoard(String data, Tabuleiro tabuleiro){
        // Internamente chamado por setBoardFromFEN para colocar as peças no tabuleiro
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                tabuleiro.getGrid()[i][j] = null;

        String[] fields = data.split(" ");
        String[] linhas = fields[0].split("/");
        ArrayList<Peca> brancaJaUsada = new ArrayList<>();
        ArrayList<Peca> pretaJaUsada = new ArrayList<>();
        
        for(int i=0; i<linhas.length; i++){
            String linha = linhas[i];
            int posX = 0;
            for(int j = 0; j < linha.length(); j++){
                char c = linha.charAt(j);
                if(Character.isDigit(c)){
                    int n = Character.getNumericValue(c);
                    for(int k = 0; k < n-1; k++){
                        posX++;
                    }
                } else {
                    String posicao = Posicao.values()[posX].toString() + (8-i);
                    Peca peca = null;
                    if(Character.isUpperCase(c)){
                        for(int idx = 0; idx < tabuleiro.getBrancas().size(); idx++){
                            if(tabuleiro.getBrancas().get(idx).getLabel().equals(Character.toString(c))){
                                peca = tabuleiro.getBrancas().get(idx);
                                for(Peca p: brancaJaUsada){
                                    if(p.equals(peca))
                                        break;
                                }
                                brancaJaUsada.add(peca);
                                break;
                            }
                        }
                    } else {
                        for(int idx = 0; idx < tabuleiro.getPretas().size(); idx++){
                            if(tabuleiro.getPretas().get(idx).getLabel().equals(Character.toString(c))){
                                peca = tabuleiro.getPretas().get(idx);
                                for(Peca p: pretaJaUsada){
                                    if(p.equals(peca))
                                        break;
                                }
                                pretaJaUsada.add(peca);
                                break;
                            }
                        }
                    }
                    if(peca != null){
                        int x = Peca.getPosX(posicao);
                        int y = Peca.getPosY(posicao);
                        tabuleiro.getGrid()[x][y] = peca;
                        peca.setPosicao(posicao);
                    }
                }
                posX++;
            }
        }
        return true;
    }

    public static String generateFen(Tabuleiro tabuleiro) {
        String data = "";
        for(int j=7; j>=0; j--){
            String[] patterns = new String[8];
            for(int i=0; i<8; i++){
                Peca peca = tabuleiro.getPeca(i, j);
                if(peca == null) patterns[i] = "";
                else patterns[i] = peca.getLabel();
            }
            for(int i=0; i<8; i++){
                int cnt = 0;
                while(i< 8 && patterns[i] == ""){
                    i++;
                    cnt++;
                }
                if(cnt != 0) data += Integer.toString(cnt);
                if(i >= 8) continue;
                else data += patterns[i];
            }
            if(j>0) data += '/';
        }
        return data;
    }

    public File criarArquivoFen(){
        String filename = "tabuleiro";
        
        File fen = new File("fen/lastFens/" + filename  + ".fen");
        // Get the file
        try{   
            if(!fen.exists()){
                fen.createNewFile();
                return fen;
            } else {
                int num = 1;
                fen = new File("fen/lastFens/" + filename + Integer.toString(num) + ".fen" );
                while(fen.exists()){
                    num++;
                    fen = new File("fen/lastFens/" + filename + Integer.toString(num) + ".fen" );
                }
                fen.createNewFile();
                return fen;
            }
            
        }catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
        
       


    public boolean save(String filename, Tabuleiro tabuleiro){
        String data = generateFen(tabuleiro);
        File fen = criarArquivoFen();
        try {
            FileWriter writer = new FileWriter(fen);
            writer.write(data);
            writer.close();
            return true;
         } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo");
            return false;
        }

    }
}
