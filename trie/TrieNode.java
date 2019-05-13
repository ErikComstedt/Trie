package trie;

/* Class for representation of a TrieNode, which is a node within the Trie (the trie is built up of trienodes)*/
class TrieNode {

    private TrieNode[] arr;
    private boolean isEnd;

    // Initialize your data structure here.
    public TrieNode() {
        // the number of unique characters in the alphabet
        this.arr = new TrieNode[61];
    }

    public TrieNode getNode(int i){
        return this.arr[i];
    }

    public void setNode(int i, TrieNode node){
        this.arr[i] = node;
    }

    public boolean isEndNode(){
        return this.isEnd;
    }

    public void setEnd(){
        this.isEnd = true;
    }
}
