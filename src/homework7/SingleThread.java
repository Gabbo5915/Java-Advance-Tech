package homework7;

import java.util.List;
import java.util.Random;

public class SingleThread {
	public static void run(List<String> target,char[] dna) {
		Random random=new Random();
		for(int i=0;i<100;i++) {
			StringBuilder str=new StringBuilder();
			for(int j=0;j<10;j++) {
				str.append(dna[random.nextInt(4)]);
			}
			target.add(str.toString());
		}
	}
}
