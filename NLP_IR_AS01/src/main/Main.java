package main;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
	
	public static void main(String args[]) 
	{
		Script[] scripts = new Script[10];
		
		scripts[0] = ScriptReader.read("src/script/Ragnarok.txt", "Ragnarok");
		scripts[1] = ScriptReader.read("src/script/Coco.txt", "Coco");
		scripts[2] = ScriptReader.read("src/script/Get Out.txt", "Get Out");
		scripts[3] = ScriptReader.read("src/script/Hurt Locker.txt", "Hurt Locker");
		scripts[4] = ScriptReader.read("src/script/La La Land.txt", "La La Land");
		scripts[5] = ScriptReader.read("src/script/Ragnarok.txt", "Ragnarok");
		scripts[6] = ScriptReader.read("src/script/The Big Sick.txt", "The Bick Sick");
		scripts[7] = ScriptReader.read("src/script/Beauty And The Beast.txt", "Beauty And The Beast");
		scripts[8] = ScriptReader.read("src/script/Theory of Everything.txt", "Theory of Everything");
		scripts[9] = ScriptReader.read("src/script/Batman.txt", "Batman");
		
		for(int i = 0; i < 10; i++)
			CsvConverter.convert(scripts[i]);
		
		VectorSpaceModel model = new VectorSpaceModel(scripts, 300);
		
		for(int i = 0 ; i < 300; i ++)
		{
			System.out.println(i + " : " + model.termList.get(i));
		}
		
		model.build();
		
		ArrayList<String> positive = new ArrayList<String>();
		ArrayList<String> negative = new ArrayList<String>();
		
		positive.add("vicki");
		
		model.Query(positive, negative);
		
		/*Script ragnarok = ScriptReader.read("src/script/Ragnarok.txt", "Ragnarok");
		CsvConverter.convert(ragnarok);
		
		Script coco = ScriptReader.read("src/script/Coco.txt", "Coco");
		CsvConverter.convert(coco);
		
		Script getout = ScriptReader.read("src/script/Get Out.txt", "Get Out");
		CsvConverter.convert(getout);
		
		Script hurtlocker = ScriptReader.read("src/script/Hurt Locker.txt", "Hurt Locker");
		CsvConverter.convert(hurtlocker);
		
		Script lalaland = ScriptReader.read("src/script/La La Land.txt", "La La Land");
		CsvConverter.convert(lalaland);
		
		Script logan = ScriptReader.read("src/script/Ragnarok.txt", "Ragnarok");
		CsvConverter.convert(logan);
		
		Script thebigsick = ScriptReader.read("src/script/The Big Sick.txt", "The Bick Sick");
		CsvConverter.convert(thebigsick);
		 
		Script beautyandthebeast = ScriptReader.read("src/script/Beauty And The Beast.txt", "Beauty And The Beast");
		CsvConverter.convert(beautyandthebeast);
		
		Script toe = ScriptReader.read("src/script/Theory of Everything.txt", "Theory of Everything");
		CsvConverter.convert(toe);
		
		Script batman = ScriptReader.read("src/script/Batman.txt", "Batman");
		CsvConverter.convert(batman);*/
	}
}
