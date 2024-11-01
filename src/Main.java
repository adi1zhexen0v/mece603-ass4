// MECE 603
// ID No. 202484848
// Description: Assignment 4 solution

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Character, Integer> frequencies = HuffmanReading.calculateFrequencies("Input.txt");

        Node huffmanTree = Tree.buildHuffmanTree(frequencies);

        Map<Character, String> codes = new HashMap<>();
        Huffman.generateCodes(huffmanTree, "", codes);

        // (a) Actual number of bits needed to save the file (each character needs 8 bits)
        int originalSize = frequencies.values().stream().mapToInt(f -> f * 8).sum();
        System.out.println("(a) Original file size: " + originalSize + " bits\n");

        // (b) The fixed-length codes for each character in the file
        System.out.println("(b) Fixed-length codes:");
        System.out.printf("\t%-12s %-12s %-24s %-6s%n", "Character", "Frequency", "Fixed-length Code", "Bits");
        System.out.println("\t-------------------------------------------------------------");
        int fixedLengthBits = (int) Math.ceil(Math.log(frequencies.size()) / Math.log(2));
        frequencies.forEach((character, frequency) -> {
            String fixedCode = String.format("%" + fixedLengthBits + "s", Integer.toBinaryString(character)).replace(' ', '0');
            System.out.printf("\t%-12s %-12d %-24s %-12d%n", character, frequency, fixedCode, fixedCode.length());
        });

        // (c) The size of the compressed file after encoding using fixed-length coding
        int compressedSizeFixedLength = frequencies.values().stream().mapToInt(f -> f * fixedLengthBits).sum();
        System.out.println("\n(c) Compressed file size (after variable-length coding): " + compressedSizeFixedLength + " bits\n");

        // (d) The optimal variable-length codes for each character in the file
        System.out.println("(d) Variable-length codes:");
        System.out.printf("\t%-12s %-12s %-24s %-6s%n", "Character", "Frequency", "Variable-length Code", "Bits");
        System.out.println("\t-------------------------------------------------------------");
        frequencies.forEach((character, frequency) -> {
            String variableCode = codes.get(character);
            System.out.printf("\t%-12s %-12d %-24s %-6d%n", character, frequency, variableCode, variableCode.length());
        });

        // (e) The size of the compressed file after encoding using variable-length coding
        int compressedSizeVariableLength = frequencies.entrySet().stream()
                .mapToInt(entry -> entry.getValue() * codes.get(entry.getKey()).length())
                .sum();
        System.out.println("\n(e) Compressed file size (after variable-length coding): " + compressedSizeVariableLength + " bits");
    }
}
