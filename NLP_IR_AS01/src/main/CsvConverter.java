package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CsvConverter {
	
	public static void convert(Script script) 
	{
		File file = new File("src/csv/"+script.scriptName+".csv");
		
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(file, false));
			
			/*for(Map.Entry<String, Integer> entry : script.wordMap.entrySet()) 
			{
				String Key = entry.getKey();
				Integer Value = entry.getValue();
				
				fw.write(Key+","+Value);
				fw.newLine();
			}*/
			
			Iterator it = sortByValue(script.wordMap).iterator();
			
			while(it.hasNext()) {
				String temp = (String)it.next();
				fw.write(temp+","+script.wordMap.get(temp));
				fw.newLine();
			}
			
			fw.flush();
			fw.close();
			
			System.out.println("Converted Successfully!!!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List sortByValue(final Map map) {
		
        List<String> list = new ArrayList();
        list.addAll(map.keySet());
        Collections.sort(list,new Comparator() {
        	
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable) v2).compareTo(v1);
            }
        });
       /* Collections.reverse(list);*/
        return list;
    }
	
	public static List sortByValueInverse(final Map map) {
		
        List<String> list = new ArrayList();
        list.addAll(map.keySet());
        Collections.sort(list,new Comparator() {
        	
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable) v2).compareTo(v1);
            }
        });
        Collections.reverse(list);
        return list;
    }
}