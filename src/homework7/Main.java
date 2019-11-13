package homework7;

import java.util.ArrayList;
import java.util.List;
public class Main {
	static List<String> singleThread=new ArrayList<>();
	static List<String> multiThread=new ArrayList<>();
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		char[] dna=new char[] {'A','T','C','G'};
		long start=System.nanoTime();
		SingleThread.run(singleThread, dna);
		long end=System.nanoTime();
		System.out.println("The nano time of Single Thread runtime is: "+(end-start)+". The size of the String list is: "+singleThread.size());
		start=System.nanoTime();
		MultiThread.run(multiThread, dna);
		end=System.nanoTime();
		Thread.sleep(1);
		System.out.println("The nano time of Multi Thread runtime is: "+(end-start)+". The size of the String list is: "+multiThread.size());
	}

}
