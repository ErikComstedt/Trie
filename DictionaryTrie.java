import trie.*;
import dictionary.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryTrie {


  public static void printResults(ArrayList<String> list, String s){
    System.out.println("There are " + list.size() + " words in the dictionary trie which begin with " + s + " \n");
    for (int i = 0; i< list.size(); i++){
      System.out.println(list.get(i));
    }
  }

  // searches for the string s in the trie
  public static void search(Trie trie, String s){
    System.out.println("\nSearching for \"" + s + "\" in the Trie: \n");
    trie.searchNode(s);
    ArrayList<String> results = trie.getMatchingWords();
    printResults(results, s);
    trie.flush();
  }


  public static void main(String[] args) throws Exception {
    Trie trieRoot = new Trie(new Dictionary().getDictionary());
    Scanner stdin = new Scanner(System.in, "Cp850");
    System.out.println("\nEnter the word you wish to search for in the dictionary trie: ");
    while(stdin.hasNext()){
      search(trieRoot, stdin.next());
      System.out.println("\nEnter the word you wish to search for in the dictionary trie: ");
    }
    stdin.close();
  }
}
