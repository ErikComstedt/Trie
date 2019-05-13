package trie;

public class CharConvert{

  public CharConvert(){}

    // recives the int ID (0-60), which each char is mapped towards
  public int getCharID(int character){
    // a-z
    if (character > 64 && character < 123){
      character -= 'A';
      if (character > 25){
        character -= ' ';
      }
      return character;
    }
    // Å-ÿ
    else if (character > 191 && character < 256){
      character -= 167;
      if (character > 31){
        character -= ' ';
      }
      return character;
    }
    // '
    else {
      if (character == 39){
        return character + 19;
      }
      // . and -
      else{
        return character + 14;
      }
    }
  }


  public char intToChar(int value){
    if (value < 26){
      return (char) (value+65);
    }
    else if (value >=26 && value < 58){
      return (char) (value + 167);
    }
    else {
      if (value == 58){
        return '\'';
      }
      else {
        return (char) (value - 14);
      }
    }
  }

  public String getCharArrayString(char [] arr, int length){
    String str = "";
    for (int i = 0; i < length; i++){
      str += arr[i];
    }
    return str;
  }

  // verifies that each char is an allowed input character
  public boolean validateInputChar(char character){
    if ((character > 64 && character < 91) || (character > 96 && character < 123)){
     return true;
    }
    else if (character > 191 && character < 256){
      return true;
    }
    else if (character == 39 || character == 45 || character == 46){
      return true;
    }
    return false;
  }

}
