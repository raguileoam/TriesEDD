import java.util.HashMap;

public class TrieNode {
    private TrieNode parent;
    private boolean isWord;
    private String text;
    private HashMap<Character,TrieNode> children;

    @Override
    public String toString() {
        return "TrieNode{" +
                "parent=" + parent +
                ", isWord=" + isWord +
                ", children=" + children.keySet() +
                '}';
    }

    public TrieNode(TrieNode parent){
        this.parent = parent;
        this.isWord = false;
        this.children = new HashMap<>();
    }

    public TrieNode getParent() {
        return parent;
    }

    public boolean isWord() {
        return isWord;
    }

    public String getText() {
        return text;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }
}
