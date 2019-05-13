package dictionary;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Dictionary{
    private ArrayList<String> dictionary = new ArrayList<>();
    
    public Dictionary (){
        createDictionary();
    }

	// read the dictionary file
    private void createDictionary(){
        BufferedReader reader;
		try {
			Path currentRelativePath = Paths.get("");
			reader = new BufferedReader(new FileReader(currentRelativePath.toAbsolutePath().toString() + "\\data\\dictionary.txt"));
			String line = reader.readLine();
			while (line != null) {
				this.dictionary.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public ArrayList<String> getDictionary(){
        return this.dictionary;
    }
}