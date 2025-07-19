import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataNodeTest {

    @Test
    public void testConstructor() {
        DataNode node = new DataNode(1, 2, 10);
        assertEquals(1, node.row);
        assertEquals(2, node.col);
        assertEquals(10, node.value);
        assertNull(node.right);
        assertNull(node.down);
    }

    @Test
    public void testLinkingRightAndDown() {
        DataNode node1 = new DataNode(0, 0, 5);
        DataNode node2 = new DataNode(0, 1, 8);
        DataNode node3 = new DataNode(1, 0, 3);

        node1.right = node2;
        node1.down = node3;

        assertEquals(node2, node1.right);
        assertEquals(node3, node1.down);
    }

    @Test
    public void testScenarioMultipleLinks() {
        DataNode node1 = new DataNode(0, 0, 1);
        DataNode node2 = new DataNode(0, 1, 2);
        DataNode node3 = new DataNode(1, 0, 3);

        node1.right = node2;
        node2.down = node3;

        assertEquals(2, node1.right.value);
        assertEquals(3, node1.right.down.value);
    }
}  