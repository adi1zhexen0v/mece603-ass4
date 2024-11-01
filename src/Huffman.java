import java.util.Map;

public class Huffman {
    public static void generateCodes(Node node, String prefix, Map<Character, String> codes) {
        if (node == null) {
            return;
        }

        if (node.nodeIsLeaf()) {
            codes.put(node.character, prefix);
        } else {
            generateCodes(node.left, prefix + "0", codes);
            generateCodes(node.right, prefix + "1", codes);
        }
    }
}
