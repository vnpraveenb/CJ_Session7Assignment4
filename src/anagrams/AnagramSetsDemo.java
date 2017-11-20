package anagrams;

import java.util.*;


	
public class AnagramSetsDemo {
	
	static int[] twentySixPrimes = { 2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101 };
	static char[] alphabet = { 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
 
 	int[] wordCountArray;
 	int[] wordLengthArray;
 	String[] wordArray;
	ArrayList<ArrayList<String>> outputList = new ArrayList<ArrayList<String>>();

 	
 	public void setWordArray(String[] wordArr)
 	{
		wordArray =  new String[wordArr.length];
		for(int i = 0; i<wordArr.length;i++)
		{
			wordArray[i] = wordArr[i];
		}	

 	}
 	
	public void calculateWordLength(String[] wordArr)
	{		
		wordLengthArray =  new int[wordArr.length];
 
 		for(int i = 0; i<wordArr.length;i++)
		{
			wordLengthArray[i] = wordArr[i].length();
		}	
	}
  	
	public void calculateWordCount(String[] wordArr)
	{
		wordCountArray =  new int[wordArr.length];
		
		for(int i = 0; i<wordArr.length;i++)
		{
			int wordCount = 0;
			for(int j =0; j< wordArr[i].length() ;j++)
			{
				for(int k = 0; k<26; k++)
				{
					if(wordArr[i].charAt(j) == alphabet[k])
					{
						wordCount = wordCount + twentySixPrimes[k];
					}
				}
			}
			wordCountArray[i] = wordCount;
		}
	}
	
	public void listAnagramsTogether(String[] wordArr)
	{
 		for(int i = 0; i<wordLengthArray.length;i++)
		{ 
			ArrayList<String> tempArrayList = new ArrayList<String>();

			for (int j = i; j<wordCountArray.length; j++)
			{
				
				if(wordLengthArray[i] == wordLengthArray[j] && (wordCountArray[i] == wordCountArray[j]) && (i!=j))
				{
					tempArrayList.add(wordArray[i]);
					tempArrayList.add(wordArray[j]);
					
				}
			}	 
			
			tempArrayList = new ArrayList<String>(new LinkedHashSet<String>(tempArrayList));
			outputList.add(tempArrayList);
			for(int k = 0; k<outputList.size(); k++)
			{
				if (outputList.get(k).isEmpty())
				{
					outputList.remove(k);
				}
			}
			outputList = new ArrayList<ArrayList<String>>(new LinkedHashSet<ArrayList<String>>(outputList));

			
 
		}
 		
		sanitizedResult();

	}
	
	public void sanitizedResult()
	{
		ArrayList<Integer> delIndices = new ArrayList<Integer>();
		for(int i = 0; i<outputList.size(); i++)
		{
			for (int j = i; j<outputList.size();j++)
			{
				for(int k =0; k<outputList.get(j).size(); k++)
				{
					if(outputList.get(i).contains(outputList.get(j).get(k)) && i!=j)
					{
						delIndices.add(j);
					}
				}
			}
		}
		delIndices = new ArrayList<Integer>(new LinkedHashSet<Integer>(delIndices));

		
		for(int m =0; m<delIndices.size();m++)
		{
 			outputList.remove(outputList.get(delIndices.get(m)-m));

		}
		
		System.out.println("Listing the Grouped Anagrams\n");
 		for(int n =0; n<outputList.size();n++)
		{
 			System.out.print(outputList.get(n) + " ");	
		}

	}
		
	public static void main(String args[])//Start of Main Class
	{
		
 	   String wordArr[] = {"listen","pot","part","opt","trap","silent","top","this","hello","hits","what","shit"};
 	  
 	   AnagramSetsDemo obj1 = new AnagramSetsDemo();
 	   
 	   obj1.setWordArray(wordArr);
  	   System.out.println();
  	   
  	   obj1.calculateWordCount(wordArr);
  	   obj1.calculateWordLength(wordArr);

  	   obj1.listAnagramsTogether(wordArr);
	} 
}
