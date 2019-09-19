package homework3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Search {
	private Map<String,Integer> counts=null;	//to store the count number of sensor name search
	private Map<String,List<String>> results=null;	//to store the past result of search like "bpm":100
	public Search() {	//Constructor
		this.counts=new HashMap<>();
		this.results=new HashMap<>();
	}
	public Search(Map<String, Integer> a,Map<String, List<String>> b) {	////Constructor
		this.counts=a;
		this.results=b;
	}
	/**
	 * 
	 * @param str The content you want to search
	 * @param file	The file path that you want to search the content from
	 */
	public void search(String str,String filePath) {
		addCount(str);	//add 1 to search times of sensor name
		File file=new File(filePath);
		BufferedReader input=null;
		try {
			input=new BufferedReader(new FileReader(file));
			String cur=null;
			while((cur=input.readLine())!=null) {	//if rend the end of the file
				if(cur.contains(str)) {
					List<String> temp=results.getOrDefault(str, new LinkedList<String>());
					temp.add(cur);
					results.put(str, temp);
					System.out.println(cur);
				}
			}
			input.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Using this method to add count number of different sensor search history
	 * @param cur The current string people input to search
	 */
	private void addCount(String cur) {
		if(cur.contains("HeartRate")) {
			int x=counts.getOrDefault("HeartRate", 0)+1;
			counts.put("HeartRate", x);
		}
		else if(cur.contains("Activity")) {
			int x=counts.getOrDefault("Activity", 0)+1;
			counts.put("Activity", x);
		}
		else if(cur.contains("Light")) {
			int x=counts.getOrDefault("Light", 0)+1;
			counts.put("Light", x);
		}
		else if(cur.contains("Battery")) {
			int x=counts.getOrDefault("Battery", 0)+1;
			counts.put("Battery", x);
		}
		else if(cur.contains("BT")) {
			int x=counts.getOrDefault("BT", 0)+1;
			counts.put("BT", x);
		}
	}
	/**
	 * If no specific sensor name, output all the search history of sensor name
	 */
	public void getCount() {
		System.out.println("HeartRate:"+this.counts.get("HeartRate"));
		System.out.println("Activity:"+this.counts.get("Activity"));
		System.out.println("Light:"+this.counts.get("Light"));
		System.out.println("Battery:"+this.counts.get("Battery"));
		System.out.println("BT:"+this.counts.get("BT"));
	}
	/**
	 * Overloading
	 * @param target The sensor name you want to search
	 */
	public void getCount(String target) {
		System.out.println(target+this.counts.get(target));
	}
	/**
	 * 
	 * @param target Get the stored search result of a content
	 */
	public void getResult(String target) {
		System.out.print("The result of "+target+" is: ");
		List<String> list=this.results.get(target);
		if(list==null) {
			System.out.println("NULL");
		}else {
			System.out.println();
			Iterator<String> ite=list.iterator();
			while(ite.hasNext()) {
				System.out.println(ite.next());
			}
		}
	}
}
