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
		scripts[5] = ScriptReader.read("src/script/Logan.txt", "Logan");
		scripts[6] = ScriptReader.read("src/script/The Big Sick.txt", "The Bick Sick");
		scripts[7] = ScriptReader.read("src/script/Beauty And The Beast.txt", "Beauty And The Beast");
		scripts[8] = ScriptReader.read("src/script/Theory of Everything.txt", "Theory of Everything");
		scripts[9] = ScriptReader.read("src/script/Batman.txt", "Batman");
		
		for(int i = 0; i < 10; i++)
			CsvConverter.convert(scripts[i]);
	
		long startTime = System.currentTimeMillis(); 
		
		/* Step 1 */
		/* Select N, dimension of vectorspace */ 
		VectorSpaceModel model = new VectorSpaceModel(scripts, 30);	
		model.build();
		
		ArrayList<String> positive = new ArrayList<String>();
		ArrayList<String> negative = new ArrayList<String>();
		
		/* Step 2 */
		/* You can add your positive query words */ 
		positive.add("logan");
		
		model.printRank(model.Query(positive, negative));
		
		SVD svd = new SVD(model.weight_vector, 10);
		
		//svd.print();
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Actual Execution Time : " + (endTime - startTime) + "ms");
	}
}
