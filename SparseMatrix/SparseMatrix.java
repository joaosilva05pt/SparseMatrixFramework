import java.util.*;

public class SparseMatrix {
    int rows, cols; // Número de linhas e colunas da matriz
    SentinelNode[] sentinels;

    public SparseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        int max = Math.max(rows, cols);
        sentinels = new SentinelNode[max];
        for (int i = 0; i < max; i++) {
            sentinels[i] = new SentinelNode();
        }
        for (int i = 0; i < max; i++) {
            sentinels[i].next = sentinels[(i + 1) % max];
        }
    }

    // Método para inserir um valor na matriz  
    public void insert(int row, int col, int value) {
        if (value == 0) return;
        DataNode newNode = new DataNode(row, col, value);

        // Inserir na linha 
        DataNode currentRow = sentinels[row].right;
        DataNode prevRow = null;
        while (currentRow != null && currentRow.col < col) {
            prevRow = currentRow;
            currentRow = currentRow.right;
        }
        if (prevRow == null) {
            newNode.right = sentinels[row].right;
            sentinels[row].right = newNode;
        } else {
            newNode.right = prevRow.right;
            prevRow.right = newNode;
        }

        // Inserir na coluna 
        DataNode currentCol = sentinels[col].down;
        DataNode prevCol = null;
        while (currentCol != null && currentCol.row < row) {
            prevCol = currentCol;
            currentCol = currentCol.down;
        }
        if (prevCol == null) {
            newNode.down = sentinels[col].down;
            sentinels[col].down = newNode;
        } else {
            newNode.down = prevCol.down;
            prevCol.down = newNode;
        }
    }

    // Método que retorna a transposta da matriz 
    public SparseMatrix transpose() {
        SparseMatrix result = new SparseMatrix(cols, rows);
        for (int i = 0; i < rows; i++) {
            DataNode node = sentinels[i].right;
            while (node != null) {
                result.insert(node.col, node.row, node.value);
                node = node.right;
            }
        }
        return result;
    }

    // Multiplica todos os elementos da matriz por um escalar
    public SparseMatrix multiplyByScalar(int scalar) {
        SparseMatrix result = new SparseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            DataNode node = sentinels[i].right;
            while (node != null) {
                result.insert(i, node.col, node.value * scalar);
                node = node.right;
            }
        }
        return result;
    }

    // Soma a matriz atual com outra matriz esparsa
    public SparseMatrix add(SparseMatrix other) {
        SparseMatrix result = new SparseMatrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            DataNode a = this.sentinels[i].right;
            DataNode b = other.sentinels[i].right;
            while (a != null || b != null) {
                if (b == null || (a != null && a.col < b.col)) {
                    result.insert(i, a.col, a.value);
                    a = a.right;
                } else if (a == null || (b != null && b.col < a.col)) {
                    result.insert(i, b.col, b.value);
                    b = b.right;
                } else {
                    int sum = a.value + b.value;
                    if (sum != 0) result.insert(i, a.col, sum);
                    a = a.right;
                    b = b.right;
                }
            }
        }
        return result;
    }

    // Imprime a matriz no formato de matriz esparsa (linha coluna valor)
    public void print() {
        List<String> entries = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < rows; i++) {
            DataNode node = sentinels[i].right;
            while (node != null) {
                entries.add(node.row + " " + node.col + " " + node.value);
                count++;
                node = node.right;
            }
        }
        System.out.println(rows + " " + cols + " " + count);
        for (String entry : entries) {
            System.out.println(entry);
        }
    }
}