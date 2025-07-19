import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SentinelNodeTest {

    @Test
    public void testConstructor() {
        SentinelNode node = new SentinelNode();
        assertNull(node.next);
        assertNull(node.right);
        assertNull(node.down);  
    }

    @Test 
    public void testNextLink() {
        SentinelNode node1 = new SentinelNode();
        SentinelNode node2 = new SentinelNode();
        node1.next = node2;
        assertEquals(node2, node1.next);
    }

    @Test
    public void testRightAndDownLinks() {
        SentinelNode sentinel = new SentinelNode();
        DataNode rightNode = new DataNode(0, 1, 5);
        DataNode downNode = new DataNode(1, 0, 10);

        sentinel.right = rightNode;
        sentinel.down = downNode;

        assertEquals(rightNode, sentinel.right);
        assertEquals(downNode, sentinel.down);
    }

    @Test
    public void testScenarioMultipleSentinels() {
        SentinelNode s0 = new SentinelNode();
        SentinelNode s1 = new SentinelNode();
        s0.next = s1;
        DataNode d0 = new DataNode(0, 0, 1);
        DataNode d1 = new DataNode(1, 0, 2);
        s0.right = d0;
        s1.down = d1;

        assertEquals(s1, s0.next);
        assertEquals(d0, s0.right);
        assertEquals(d1, s1.down);
    }
}