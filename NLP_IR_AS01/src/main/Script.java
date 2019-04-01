package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Script {

	
	public String scriptName;
	public String scriptPath;
	public ArrayList<String> contents;
	public Map<String, Integer> wordMap;
	
	public List<String> stopwords;
	
	public int numofWords = 0;
	
	public Script(String scriptPath, String scriptName) 
	{
		this.scriptName =  scriptName;
		this.scriptPath = scriptPath;
		this.contents = new ArrayList<String>();
		this.wordMap = new HashMap<String, Integer>();
		
		stopwords = gen_stopwords();
	}
	
	public void addScript(String line) 
	{
		if(!line.equals("")) {
			
			//Store Original Sentence
			contents.add(line.trim());
			
			//Build wordMap
			/*StringTokenizer line_st = new StringTokenizer(line, " ");*/
			String[] line_split = line.split("[ ,?.\"()!_:-]+");
			
			for(int i = 0; i < line_split.length; i++) 
			{
				String temp = preIntercept(line_split[i]);
				
				if(temp.equals("%useless%"))
					continue;
				if(wordMap.containsKey(temp)) {
					wordMap.replace(temp, wordMap.get(temp) + 1);
					numofWords++;
				}else {
					wordMap.put(temp, 1);
					numofWords++;
				}
			}
		}
	}
	
	public void printScript() 
	{
		for(String elem : contents) 
		{
			System.out.println(elem);
		}
	}
	
	public void printWordMap() 
	{
		for(Map.Entry<String, Integer> entry : wordMap.entrySet()) 
		{
			String Key = entry.getKey();
			Integer Value = entry.getValue();
			
			System.out.println(Key + " : " + Value);
		}
	}
	/**
	 * 
	 * @param word
	 * @return result
	 * 
	 * Remove Stopwords and symbols.
	 */
	private String preIntercept(String word) 
	{
		String result = word.toLowerCase();
		result = result.replaceAll("'s", "");
		
		if(this.stopwords.contains(result)
				|| result.length() < 2
				|| result.matches("[ ,?.\"()!_:-]+"))
			return "%useless%";
		
		return result;
	}
	
	private ArrayList<String> gen_stopwords()
	{
		ArrayList<String> res = new ArrayList<String>();
		
		String plain_stopwords = "a,able,about,across,after,all,almost,also,am,among,an,and,any,are,as,at,be,because,been,but,by,can,cannot,could,dear,did,do,does,either,else,ever,every,for,from,get,got,had,has,have,he,her,hers,him,his,how,however,i,if,in,into,is,it,its,just,least,let,like,likely,may,me,might,most,must,my,neither,no,nor,not,of,off,often,on,only,or,other,our,own,rather,said,say,says,she,should,since,so,some,than,that,the,their,them,then,there,these,they,this,tis,to,too,twas,us,wants,was,we,were,what,when,where,which,while,who,whom,why,will,with,would,yet,you,your";
		
		StringTokenizer st = new StringTokenizer(plain_stopwords, ",");
		
		while(st.hasMoreTokens()) 
		{
			res.add(st.nextToken());
		}
		
		return res;
	}
	
}
