package homework3;

import java.util.Scanner;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Search search=new Search();
		Scanner scan=new Scanner(System.in);
		String exit="NO";
		while(exit.equals("NO")) {
			System.out.println("Start new search?(YES/NO)");
			String newSearch=scan.nextLine();
			if(newSearch.equals("YES")) {
				System.out.println("Please input the file's path you want to search from(No input will set to default file):");
				String filePath=scan.nextLine();	//e.g:"src/homework3/summary.txt"
		        System.out.println("Please input what you want to search:");
		        String str=scan.nextLine();
		        if(filePath==null||filePath.length()==0) {
		        	search.search(str, "src/homework3/summary.txt");
		        }else {
		        	search.search(str, filePath);
		        }
			}else {
				System.out.println("Please input the key words you want to search:");
				String target=scan.nextLine();
				search.getResult(target);
			}
			System.out.println("Check search history?(YES/NO)");
			String checkHistory=scan.nextLine();
			if(checkHistory.equals("YES")) {
				System.out.println("Input the sensor name:(No input will output all the records)");
				String sensor=scan.nextLine();
				if(sensor==null||sensor.length()==0) {
					search.getCount();
				}else {
					search.getCount(sensor);
				}
			}
			System.out.println("Exit?(YES/NO)");
			exit=scan.nextLine();
		}
		scan.close();
	}

}
