package Trie;

import java.util.HashMap;

public class TrieNode {
    private TrieNode parent; //Nodo padre
    private boolean isWord; //Si es la ultima letra de alguna palabra
    private HashMap<Character,TrieNode> children; //Nodos hijos

    @Override
    public String toString() {
        return "Trie.TrieNode{" +
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
