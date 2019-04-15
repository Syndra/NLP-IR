package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import javax.print.attribute.standard.NumberOfDocuments;

public class VectorSpaceModel {

	public Script[] scripts;
	
	public int numOfScript = 0;
	public int N = 0; // Dimension of Vector space
	 
	public ArrayList<String> termList;
	 
	public float[][] tf_vector;
	public float[] idf_vector;
	
	public double[][] weight_vector;
	
	public VectorSpaceModel(Script[] scripts, int N) {
		
		this.numOfScript = scripts.length;
		this.termList = new ArrayList<String>();
		this.scripts = scripts;
		this.N = N;
		
		Iterator[] it = new Iterator[numOfScript];
		
		int loop = N;
		
		/**
		 * TermList Generating
		 */
		for(int term = 0; term < loop; term++) 
		{	
			if(it[term%numOfScript] == null)
				it[term%numOfScript] = CsvConverter.sortByValue(scripts[term%numOfScript].wordMap).iterator();
			
			String temp = (String)it[term%numOfScript].next();
			
			if(termList.contains(temp))
			{
				loop++;
				continue;
			}
			termList.add(temp);
		}
	}
	
	public void build() {
		
		/**
		 * TF & DF
		 */
		tf_vector = new float[numOfScript][N];
		idf_vector = new float[N];
		weight_vector = new double[numOfScript][N];
		
		for(int i = 0 ; i < numOfScript; i ++) 
		{
			for(int index = 0 ; index < N; index ++) 
			{
				if(scripts[i].wordMap.containsKey(this.termList.get(index))) {
					tf_vector[i][index] = (float)scripts[i].wordMap.get(this.termList.get(index));
					idf_vector[index] += (float)1/(float)numOfScript;
				}else {
					tf_vector[i][index] = 0;
				}
			
				tf_vector[i][index] /= scripts[i].numofWords;
			}
		}
		
		/**
		 * IDF
		 */
		for(int i = 0; i < N && idf_vector[i] != 0; i ++) {
			idf_vector[i] = (float) Math.log((float)numOfScript / idf_vector[i]) / (float)Math.log(2);
		}
		
		/**
		 * TF*IDF
		 */
		
		for(int i = 0 ; i < numOfScript; i++) {
			for(int index = 0 ; index < N ; index++) {
				weight_vector[i][index] = tf_vector[i][index] * idf_vector[index];
			}
		}
	}
	
	public int[] Query(ArrayList<String> positive, ArrayList<String> negative) {
		
		boolean hasToRebuild = false;
		
		
		  for(String elem : positive) { if(!this.termList.contains(elem)) {
		  this.addTerm(elem); hasToRebuild = true; } }
		  
		  if(hasToRebuild) build();
		 
		
		int [] res = new int[this.numOfScript];
		
		float[] query_vector = new float[this.N];
		
		float[] angle = new float[this.numOfScript];
		
		for(int i = 0; i < N; i++) {
			if(positive.contains(this.termList.get(i))) {
				query_vector[i] = 1;
			}
			else if(negative.contains(this.termList.get(i))){
				query_vector[i] = -1;
			}else {
				query_vector[i] = 0;
			}	
		}
		
		HashMap<Integer, Float> sortMap = new HashMap<Integer, Float>();
		
		for(int i = 0 ; i < numOfScript; i ++) 
		{
			angle[i] = getAngle(query_vector, this.weight_vector[i]);
			sortMap.put(i, angle[i]);
		}
		
		Iterator it = CsvConverter.sortByValueInverse(sortMap).iterator();
		
		int i = 0 ;
		while(it.hasNext()) {
			int index = (int) it.next();
			res[i] = index;
			i++;
		}
		
		return res;
	}
	
	public void printRank(int[] queryResult) 
	{
		for(int i=0; i < numOfScript; i ++) 
		{
			System.out.println("Rank " + (i+1) + " : " + this.scripts[queryResult[i]].scriptName);
		}
	}
	
	private float getAngle(float[] source, double[] target) {
		float result = 0;
		float temp = 0;
		float cos = 0;
		
		float sourceSize = vectorSize(source);
		double targetSize = vectorSize(target);
		
		for(int i = 0; i < N; i ++) {
			temp += source[i] * target[i];
		}
		
		temp /= sourceSize*targetSize;
		
		cos = temp;
		
		result = (float) Math.acos(cos);
		
		return result;
	}
	
	private float vectorSize(float[] vector) {
		float res = 0;
		
		for(int i = 0; i<vector.length; i++) {
			res += vector[i]*vector[i];
		}
		
		return (float) Math.sqrt(res);
	}
	
	private double vectorSize(double[] vector) {
		float res = 0;
		
		for(int i = 0; i<vector.length; i++) {
			res += vector[i]*vector[i];
		}
		
		return (double) Math.sqrt(res);
	}
	
	private float[] reverse(float[] array) {
		float[] res = array;
		
		for(int i = 0; i < array.length/2; i ++) {
			float temp = res[i];
			res[i] = res[array.length - 1 - i];
			res[array.length - 1 - i] = temp;
		}
		
		return res;
	}
	
	private void addTerm(String terms) {
		
		/*
		 * Add into termList
		 **/
		
		this.N += 1;
		this.termList.add(terms);
	}
}
