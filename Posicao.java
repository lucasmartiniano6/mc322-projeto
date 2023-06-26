public enum Posicao {
    // Enum para representar as posições do tabuleiro no grid[8][8]
    // As posições são representadas por letras de A até H e números de 1 até 8, mapeados para inteiros de 0 até 7

    A(0),
    B(1),
    C(2),
    D(3),
    E(4),
    F(5),
    G(6),
    H(7);

    public final int valor;

    Posicao(int valor) {
        this.valor = valor;
    }
}
