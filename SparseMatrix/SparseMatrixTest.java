import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SparseMatrixTest {

    @Test
    public void testEmptyMatrix() {
        SparseMatrix matrix = new SparseMatrix(3, 3);
        for (int i = 0; i < 3; i++) {
            assertNull(matrix.sentinels[i].right);
            assertNull(matrix.sentinels[i].down);
        }
    }

    @Test
    public void testInsertAndRetrieve() {
        SparseMatrix matrix = new SparseMatrix(4, 4);
        matrix.insert(1, 2, 10);
        DataNode node = matrix.sentinels[1].right;
        assertNotNull(node);
        assertEquals(1, node.row);
        assertEquals(2, node.col);
        assertEquals(10, node.value);
    }

    @Test
    public void testInsertZeroDoesNothing() {
        SparseMatrix matrix = new SparseMatrix(2, 2);
        matrix.insert(0, 1, 0);
        assertNull(matrix.sentinels[0].right);
        assertNull(matrix.sentinels[1].down);
    }

    @Test
    public void testAddMatrices() {
        SparseMatrix m1 = new SparseMatrix(2, 2);
        m1.insert(0, 0, 1);
        m1.insert(1, 1, 2);

        SparseMatrix m2 = new SparseMatrix(2, 2);
        m2.insert(0, 0, 3);
        m2.insert(1, 0, 4);

        SparseMatrix sum = m1.add(m2);

        // (0,0) = 1+3 = 4
        assertEquals(4, sum.sentinels[0].right.value);
        // (1,0) = 4
        assertEquals(4, sum.sentinels[1].right.value);
        // (1,1) = 2
        assertEquals(1, sum.sentinels[1].right.col);
        assertEquals(2, sum.sentinels[1].right.value);
    }

    @Test
    public void testMultiplyByScalar() {
        SparseMatrix matrix = new SparseMatrix(2, 2);
        matrix.insert(0, 1, 5);
        matrix.insert(1, 0, -2);

        SparseMatrix result = matrix.multiplyByScalar(3);

        assertEquals(15, result.sentinels[0].right.value);
        assertEquals(-6, result.sentinels[1].right.value);
    }

    @Test
    public void testTranspose() {
        SparseMatrix matrix = new SparseMatrix(2, 3);
        matrix.insert(0, 1, 7);
        matrix.insert(1, 2, 9);

        SparseMatrix transposed = matrix.transpose();

        // (1,0) = 7
        assertEquals(7, transposed.sentinels[1].right.value);
        assertEquals(1, transposed.sentinels[1].right.row);
        assertEquals(0, transposed.sentinels[1].right.col);

        // (2,1) = 9
        assertEquals(9, transposed.sentinels[2].right.value);
        assertEquals(2, transposed.sentinels[2].right.row);
        assertEquals(1, transposed.sentinels[2].right.col);
    }
} 