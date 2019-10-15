package homework4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// initialize the client object
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		// get the 'test' dataset
		MongoDatabase dbObj = mongoClient.getDatabase("CS622");
		
		
		// from this line, transfer date from txt to MongoDB
		dbObj.createCollection("homework4");
		System.out.println("Collection created successfully");
		// list its collections
		for (String name : dbObj.listCollectionNames()) {
			System.out.println("Collections inside this db:" + name);
		}
		
		
		MongoCollection<Document> collection = dbObj.getCollection("homework4");
		
		File source=new File("./src/homework4/summary.txt");
		System.out.println("Transfer data from "+source.getName()+"...");
		//Read the content from summary.txt of homework1
		try {
			BufferedReader input=new BufferedReader(new FileReader(source));
			String st=null;
			while((st=input.readLine())!=null) {
				Document doc=Document.parse(st);
				collection.insertOne(doc);
			}
			input.close();
			System.out.println("DONE!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/***
		 * From this line, start to search
		 */
		//Search the HeartBeats
		Scanner scan=new Scanner(System.in);
		System.out.println("INPUT THE MONTH AND DAY YOU WANT TO SEARCH FOR TOTAL HEARTBEATS:(e.g.Mar 6)");
		String date=scan.nextLine();
		System.out.println("INPUT THE YEAR:(e.g.2017)");
		String year=scan.nextLine();
		System.out.println("THE TOTAL HEARTBEATS OF "+date+" "+year+" IS: "+SEARCH.HeartBeats(date,year,collection));
		//Search the running event
		System.out.println("INPUT THE MONTH AND DAY YOU WANT TO SEARCH IF YOU HAS RUNNING EVENT:(e.g.Mar 6)");
		date=scan.nextLine();
		System.out.println("INPUT THE YEAR:(e.g.2017)");
		year=scan.nextLine();
		SEARCH.isActivity(date, year, collection);
		//Search the Steps
		System.out.println("INPUT THE MONTH AND DAY YOU WANT TO SEARCH FOR TOTAL STEPS:(e.g.Mar 6)");
		date=scan.nextLine();
		System.out.println("INPUT THE YEAR:(e.g.2017)");
		year=scan.nextLine();
		System.out.println("THE TOTAL STEPS OF "+date+" "+year+" IS: "+SEARCH.countSteps(date,year,collection));
		scan.close();
		mongoClient.close();
	}

}
