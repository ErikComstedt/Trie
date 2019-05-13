package trie;
import java.util.ArrayList;


// class for trie
public class Trie {
  private TrieNode root;
  // the number of unique charaters in the alphabet
  private int ALPHABETLENGTH = 61;
  // the height of the trie
  private int treeHeight;


  /* Variables used when searching */
  // ArrayList which tracks the words which matches the searchstring
  private ArrayList<String> matchingWords = new ArrayList<>();
  // A prefix equal to the searchstring which will be added to the beginning of found words
  private ArrayList<Character> prefixarr = new ArrayList<>();

  public Trie(ArrayList<String> dictionary) {
      root = new TrieNode();
      initializeDictionary(dictionary);
  }

  // get method for matchingWords
  public ArrayList<String> getMatchingWords(){
    return matchingWords;
  }

  // Invokes clear on both prefixarr and matchingWords
  public void flush(){
    prefixarr.clear();
    matchingWords.clear();
  }

  // Builds a word
  private String makeword (){
    StringBuilder build = new StringBuilder(1);
    for (int i = 0; i < prefixarr.size(); i++){
      build.append(prefixarr.get(i));
    }
    return build.toString();
  }

  // creates the dictionary tree
  private void initializeDictionary(ArrayList<String> dictionary){
    treeHeight = 0;
    for (int i = 0; i < dictionary.size(); i++){
      insert(dictionary.get(i));
      if (treeHeight < dictionary.get(i).length()){
        treeHeight = dictionary.get(i).length();
      }
    }
  }

  // Inserts a word into the trie.
  private void insert(String word) {
      TrieNode p = root;
      for(int i=0; i<word.length(); i++){
        char c = word.charAt(i);
        int index = new CharConvert().getCharID(c);
        if (p.getNode(index)==null){
          TrieNode temp = new TrieNode();
          p.setNode(index, temp);
          p = temp;
        }
        else{
          p = p.getNode(index);
        }
      }
      p.setEnd();
  }

  private boolean validateInputString(String s){
    char[] charaters = s.toCharArray();
    for (char character : charaters){
      if (!new CharConvert().validateInputChar(character)){
        flush();
        return false;
      }
    }
    return true;
  }

  // searches for a string s in the tree
  public TrieNode searchNode(String s){
    if (!validateInputString(s)){
      return null;
    }
    ArrayList<Integer> charIDs = new ArrayList<>();
    TrieNode p = root;
    CharConvert charConverter = new CharConvert();
    int index=0;
    for(int i=0; i<s.length(); i++){
      char c = s.charAt(i);
      index = new CharConvert().getCharID(c);
      if(p.getNode(index) != null){
          p = p.getNode(index);
          charIDs.add(index);
      }else{
        return null;
      }
    }
    if(p==root){
        return null;
    }
    for (int i = 0; i < charIDs.size(); i++){
      prefixarr.add(new CharConvert().intToChar(charIDs.get(i)));
    }

    char [] str = new char[treeHeight - (s.length() - 1)];
    // searches for substrings which begins with s
    subStringSearch(p, str, 0, charConverter);
    return p;
  }

  // searches for nodes located directly below the final node in the string s
  // once isEnd is true, we have found a word.
  // recursive until all nodes have been visited
  private void subStringSearch(TrieNode root, char str[], int level, CharConvert charConverter) {
    if (root == null){
        return;
    }
    if (root.isEndNode()){
        matchingWords.add(makeword()+charConverter.getCharArrayString(str, level));
    }
    int i;
    for (i = 0; i < ALPHABETLENGTH; i++)  {
      // if this node has any connections, we search from that connection
      if(root.getNode(i) != null){
        str[level] = charConverter.intToChar(i);
        subStringSearch(root.getNode(i), str, level + 1, charConverter);
      }
    }
  }
}
