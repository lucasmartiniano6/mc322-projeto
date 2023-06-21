public class Pair {
    String fen;
    int times;

    public Pair(String fen) {
        this.fen = fen;
        this.times = 1;
    }

    public String getFen() {
        return fen;
    }

    public void setFen(String fen) {
        this.fen = fen;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public void increaseTimes() {
        times++;
    }
}
