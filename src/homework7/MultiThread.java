package homework7;

import java.util.List;
import java.util.Random;

public class MultiThread {
	public static void run(List<String> target,char[] dna) {
		Random random=new Random();
		new Thread(()->{
			for(int i=0;i<20;i++) {
				System.out.println(Thread.currentThread().getName()+"excuteing...");
				StringBuilder str=new StringBuilder();
				for(int j=0;j<10;j++) {
					str.append(dna[random.nextInt(4)]);
				}
				target.add(str.toString());
			}
		},"first").start();
		new Thread(()->{
			for(int i=0;i<20;i++) {
				System.out.println(Thread.currentThread().getName()+"excuteing...");
				StringBuilder str=new StringBuilder();
				for(int j=0;j<10;j++) {
					str.append(dna[random.nextInt(4)]);
				}
				target.add(str.toString());
			}
		},"second").start();
		new Thread(()->{
			for(int i=0;i<20;i++) {
				System.out.println(Thread.currentThread().getName()+"excuteing...");
				StringBuilder str=new StringBuilder();
				for(int j=0;j<10;j++) {
					str.append(dna[random.nextInt(4)]);
				}
				target.add(str.toString());
			}
		},"third").start();
		new Thread(()->{
			for(int i=0;i<20;i++) {
				System.out.println(Thread.currentThread().getName()+"excuteing...");
				StringBuilder str=new StringBuilder();
				for(int j=0;j<10;j++) {
					str.append(dna[random.nextInt(4)]);
				}
				target.add(str.toString());
			}
		},"forth").start();
		new Thread(()->{
			for(int i=0;i<20;i++) {
				System.out.println(Thread.currentThread().getName()+"excuteing...");
				StringBuilder str=new StringBuilder();
				for(int j=0;j<10;j++) {
					str.append(dna[random.nextInt(4)]);
				}
				target.add(str.toString());
			}
		},"fifth").start();
	}
}
