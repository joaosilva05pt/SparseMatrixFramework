import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
             System.out.println("Bem vindo ao programa: \n");
            int r = sc.nextInt();
            int c = sc.nextInt();
            int n = sc.nextInt();

            if (r <= 0 || c <= 0) {
                System.out.println("Dimensões inválidas.");
                return;
            }

            SparseMatrix matrix = new SparseMatrix(r, c);
            for (int i = 0; i < n; i++) {
                int row = sc.nextInt();
                int col = sc.nextInt();
                int value = sc.nextInt();

                if (row < 0 || row >= r || col < 0 || col >= c) {
                    System.out.println("Índices fora dos limites.");
                    return;
                }
                matrix.insert(row, col, value);
            }

            String op = sc.next();

            switch (op) {
                case "+":
                    int r2 = sc.nextInt();
                    int c2 = sc.nextInt();
                    int n2 = sc.nextInt();
                    

                    if (r2 != r || c2 != c) {
                        System.out.println("As dimensões das matrizes devem ser iguais para soma.");
                        return;
                    }

                    SparseMatrix m2 = new SparseMatrix(r2, c2);
                    for (int i = 0; i < n2; i++) {
                        
                        int row = sc.nextInt();
                        int col = sc.nextInt();
                        int value = sc.nextInt();

                        if (row < 0 || row >= r2 || col < 0 || col >= c2) {
                            System.out.println("Índices fora dos limites.");
                            return;
                        }
                        m2.insert(row, col, value);
                    }

                    SparseMatrix sum = matrix.add(m2);
                    System.out.println("\nResultado: "); 
                    sum.print();
                    break;

                case "*":
                    int scalar = sc.nextInt();
                    SparseMatrix scaled = matrix.multiplyByScalar(scalar);
                    System.out.println("\nResultado: "); 
                    scaled.print();
                    break;

                case "t":
                    SparseMatrix transposed = matrix.transpose();
                    System.out.println("\nResultado: "); 
                    transposed.print();
                    break;

                default:
                    System.out.println("Operação inválida.");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}