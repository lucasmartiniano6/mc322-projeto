public class Rei extends Peca{
    public boolean isReachable(String destino) {
        // Implementação do movimento do Rei
        int deltaX = Math.abs(getPosX(this.getPosicao()) - getPosX(destino));
        int deltaY = Math.abs(getPosY(this.getPosicao()) - getPosY(destino));

        int nx = getPosX(destino);
        int ny = getPosY(destino);
        
        if(nx < 0 || nx >= 8 || ny < 0 || ny >= 8) // Posição do grid inválida
            return false;

        if(this.getTabuleiro().getPeca(nx, ny) != null){ // Caso a posição não esteja vazia
            if(!this.getTabuleiro().getPeca(nx, ny).getCorDono().equals(this.getCorDono()) && deltaX <= 1 && deltaY <= 1){ // Peça inimiga
                return true;
            }
            else 
                return false;
        }
        else { // Caso a posição esteja vazia
            if(this.getMovimentos() == 0 && deltaX == 2 && deltaY == 0) { // Implementação do roque
                if(this.getCorDono().equals("branca")) {
                    if(destino.equals("G1")) { // Roque menor do Rei das brancas
                        if(this.getTabuleiro().getPeca("F1") == null && this.getTabuleiro().getPeca("G1") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("H1") instanceof Torre && this.getTabuleiro().getPeca("H1").getMovimentos() == 0) {
                                this.getTabuleiro().getPeca("H1").moverPeca("F1");
                                return true;
                            }
                        }
                    }
                    else if(destino.equals("C1")) { // Roque maior do Rei das brancas
                        if(this.getTabuleiro().getPeca("B1") == null && this.getTabuleiro().getPeca("C1") == null && this.getTabuleiro().getPeca("D1") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("A1") instanceof Torre && this.getTabuleiro().getPeca("A1").getMovimentos() == 0) {
                                this.getTabuleiro().getPeca("A1").moverPeca("D1");
                                return true;
                            }
                        }
                    }
                }
                else if(this.getCorDono().equals("preta")) {
                    if(destino.equals("G8")) { // Roque menor do Rei das pretas
                        if(this.getTabuleiro().getPeca("F8") == null && this.getTabuleiro().getPeca("G8") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("H8") instanceof Torre && this.getTabuleiro().getPeca("H8").getMovimentos() == 0) {
                                this.getTabuleiro().getPeca("H8").moverPeca("F8");
                                return true;
                            }
                        }
                    }
                    else if(destino.equals("C8")){ // Roque maior do Rei das pretas
                        if(this.getTabuleiro().getPeca("B8") == null && this.getTabuleiro().getPeca("C8") == null && this.getTabuleiro().getPeca("D8") == null) { //Checa se não há nenhuma peça entre o Rei e a Torre
                            if(this.getTabuleiro().getPeca("A8") instanceof Torre && this.getTabuleiro().getPeca("A8").getMovimentos() == 0) {
                                this.getTabuleiro().getPeca("A8").moverPeca("D8");
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            else if(deltaX > 1 || deltaY > 1) // Fora do alcance do Rei
                return false;
            return true;
        }
    }

    public void _undo_move(String lastPos, String destino, boolean comeu){
        // Internamente chamado por moverPeca quando o movimento é inválido
        // Coloca a peça em lastPos
        setMovimentos(getMovimentos()-1);
        if(Math.abs(getPosX(lastPos) - getPosX(destino)) > 1) {
            String pos = "";
            String font = "";
            if(getPosY(lastPos) == 0) {
                if(getPosX(destino) == 2) {
                    pos = "A1";
                    font = "D1";
                } else if(getPosX(destino) == 6) {
                    pos = "H1";
                    font = "F1";
                }
                getTabuleiro().setPeca(pos, getTabuleiro().getPeca(font));
                getTabuleiro().setPeca("E1", this);
            } else if(getPosY(lastPos) == 7) {
                if(getPosX(destino) == 2) {
                    pos = "A8";
                    font = "D8";
                } else if(getPosX(destino) == 6) {
                    pos = "H8";
                    font = "F8";
                }
                getTabuleiro().setPeca(pos, getTabuleiro().getPeca(font));
                getTabuleiro().setPeca("E8", this);
            }
        } else {
             if(comeu) {
                Peca pecaRemov = getTabuleiro().getPecasComidas().get(getTabuleiro().getPecasComidas().size()-1);
                if(pecaRemov.getCorDono().equals("branca")) {
                    getTabuleiro().addBrancas(pecaRemov);
                } else if(pecaRemov.getCorDono().equals("preta")) {
                    getTabuleiro().addPretas(pecaRemov);
                }
                getTabuleiro().setPeca(lastPos, this);
                getTabuleiro().setPeca(destino, pecaRemov);
                getTabuleiro().getPecasComidas().remove(pecaRemov);
                } else {
                    getTabuleiro().setEmpty(destino);
                    getTabuleiro().setPeca(lastPos, this);
                }
        }
    }


    public Rei(String corDono, Tabuleiro tabuleiro, String posicao){
        super(corDono, tabuleiro, posicao);
        if(corDono.equals("branca"))
            this.setLabel("K");
        else
            this.setLabel("k");
    }
}
