import java.util.Map;
import java.util.PriorityQueue;

public class Tree {
    public static Node buildHuffmanTree(Map<Character, Integer> frequencies) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            priorityQueue.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            Node merged = new Node('\0', left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;

            priorityQueue.add(merged);
        }

        return priorityQueue.poll();
    }
}