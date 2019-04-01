package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ScriptReader {

	public static Script read(String filePath, String scriptName) {
		
		Script script = new Script(filePath, scriptName);
		
		try {
			File file = new File(filePath);
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			
			while((line = bufReader.readLine()) != null) 
			{
				script.addScript(line);
			}
			
			return script;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
            System.out.println(e);
        }
		
		return null;
	}
}
