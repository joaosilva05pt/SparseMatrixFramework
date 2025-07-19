public class DataNode {
    int row, col, value;
    DataNode right, down; // Referência para o próximo nó na mesma linha à direita e em baixo

    // Construtor da classe que inicializa os campos com os valores fornecidos
    public DataNode(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.right = null;
        this.down = null;
    }
}