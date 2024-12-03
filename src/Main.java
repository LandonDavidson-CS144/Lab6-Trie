import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = {"lab", "labor", "lack", "lack", "lad", "ladder", "lady", "lake", "fred"};
        for (String word: words) {
            trie.insert(word);
        }
        System.out.println("Done");
    }
}

class Node {
    private final ArrayList<Node> children;
    private final char letter;
    public Node(char letter) {
        this.letter = letter;
        children = new ArrayList<>();
    }
    public Node addChild(char element) {
        Node node = new Node(element);
        children.add(node);
        return node;
    }
    public Node getChild(char target) {
        for (Node node: children) {
            if (node.letter == target) {
                return node;
            }
        }
        return null;
    }
    public boolean hasChild(char key) {
        for (Node node: children) {
            if (node.letter == key) {
                return true;
            }
        }
        return false;
    }
    public void removeChild(Node target) {
        children.remove(target);
    }
    public boolean hasChildren() {
        return !children.isEmpty();
    }
}

class Trie {
    Node root;
    public Trie() {
        root = new Node('1');
    }
    public void insert(String word) {
        word += '0';
        Node curNode = root;
        for (char c: word.toCharArray()) {
            if (!curNode.hasChild(c)) curNode = curNode.addChild(c);
            else curNode = curNode.getChild(c);
        }
    }
    public boolean search(String key) {
        key += '0';
        Node curNode = root;
        int charIndex = 0;
        while (curNode.hasChildren()) {
            char curChar = key.charAt(charIndex);
            if (!curNode.hasChild(curChar)) return false;
            curNode = curNode.getChild(curChar);
            charIndex++;
        }
        return true;
    }
    public void delete(String target) {
        deleteHelper(target + '0', root);
    }
    private void deleteHelper(String target, Node curNode) {
        char curChar = target.charAt(0);
        if (curNode.hasChild(curChar) && curChar != '0') {
            deleteHelper(target.substring(1), curNode.getChild(curChar));
        }
        if (curNode.hasChild(curChar) && !curNode.getChild(curChar).hasChildren()) {
            curNode.removeChild(curNode.getChild(curChar));
        }
    }
}
