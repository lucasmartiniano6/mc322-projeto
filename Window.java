import javax.swing.*;

public abstract class Window {
    private JFrame window;
    private JPanel panel;
    private final Tabuleiro tabuleiro;

    public Window(Tabuleiro tabuleiro){
        this.tabuleiro = tabuleiro;
        gerarJanela();
        makeBackground();
    }

    
    public abstract void makeBackground();
     
    public  abstract void gerarJanela();

    
    public void setWindow(JFrame window) {
        this.window = window;
    }

    public JFrame getWindow(){
        return window;
    }
    
    public JPanel getPanel() {
        return panel;
    }
    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

}
