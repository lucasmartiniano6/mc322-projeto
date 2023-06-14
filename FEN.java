import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FEN implements Data{
    // Forsyth–Edwards Notation
    // https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation

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

    public boolean _setBoard(String data, Tabuleiro tabuleiro){
        // Internamente chamado por setBoardFromFEN para colocar as peças no tabuleiro
        for(int i=0; i<8; i++)
            for(int j=0; j<8; j++)
                tabuleiro.getGrid()[i][j] = null;

        String[] fields = data.split(" ");
        String[] linhas = fields[0].split("/");
        boolean[] brancaJaUsada = new boolean[16];
        boolean[] pretaJaUsada = new boolean[16];

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
                            if(tabuleiro.getBrancas().get(idx).getLabel().equals(Character.toString(c)) && !brancaJaUsada[idx]){
                                peca = tabuleiro.getBrancas().get(idx);
                                brancaJaUsada[idx] = true;
                                break;
                            }
                        }
                    } else {
                        for(int idx = 0; idx < tabuleiro.getPretas().size(); idx++){
                            if(tabuleiro.getPretas().get(idx).getLabel().equals(Character.toString(c)) && !pretaJaUsada[idx]){
                                peca = tabuleiro.getPretas().get(idx);
                                pretaJaUsada[idx] = true;
                                break;
                            }
                        }
                    }
                    if(peca != null)
                        tabuleiro.setPeca(posicao, peca);
                }
                posX++;
            }
        }
        return true;
    }

    public boolean save(String filename, String data){
        return false;
    }
}
