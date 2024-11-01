public class Node {
    char character;
    int frequency;
    Node left;
    Node right;

    Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    boolean nodeIsLeaf() {
        return left == null && right == null;
    }
}
