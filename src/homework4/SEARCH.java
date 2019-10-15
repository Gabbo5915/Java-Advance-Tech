package homework4;
import static com.mongodb.client.model.Filters.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class SEARCH {
	/***
	 * 
	 * @param monthAndDay The Month and Day like Mar 6
	 * @param year	The year like 2016
	 * @param collection	target MongoDB collection
	 * @return
	 */
	public static int HeartBeats(String monthAndDay,String year,MongoCollection<Document> collection) {
		int res=0;
		MongoCursor<Document> pointer=collection.find(eq("sensor_name","HeartRate")).iterator();
		while(pointer.hasNext()) {
			Document cur=pointer.next();
			//System.out.println(cur.toJson());
			if(cur.getString("timestamp").contains(monthAndDay)&&cur.getString("timestamp").contains(year)) {
				Document num=(Document)cur.get("sensor_data");
				//System.out.println(num.toJson());
				res+=num.getInteger("bpm");
			}
		}
		return res;
	}
	
	/***
	 * 
	 * @param monthAndDay The Month and Day like Mar 6
	 * @param year	The year like 2016
	 * @param collection	target MongoDB collection
	 * @return
	 */
	public static int countSteps(String monthAndDay,String year,MongoCollection<Document> collection) {
		int res=0;
		MongoCursor<Document> pointer=collection.find(eq("sensor_name","Activity")).iterator();
		while(pointer.hasNext()) {
			Document activity=pointer.next();
			String temp=activity.toJson();
			if(!temp.contains("step_counts")) continue;
			if(activity.getString("timestamp").contains(monthAndDay)&&activity.getString("timestamp").contains(year)) {
				Document data=(Document)activity.get("sensor_data");
				res+=data.getInteger("step_counts");
			}
		}
		return res;
	}
	
	/***
	 * 
	 * @param monthAndDay The Month and Day like Mar 6
	 * @param year	The year like 2016
	 * @param collection	target MongoDB collection
	 * @return
	 */
	public static void isActivity(String monthAndDay,String year,MongoCollection<Document> collection) {
		MongoCursor<Document> pointer=collection.find(eq("sensor_name","Activity")).iterator();
		boolean act=false;
		while(pointer.hasNext()) {
			Document activity=pointer.next();
			String temp=activity.toJson();
			//System.out.println(temp);
			if(!temp.contains("start_time")) continue;
			Document cur=(Document) activity.get("timestamp");
			//System.out.println(cur.toJson());
			if(cur.getString("start_time").contains(monthAndDay)&&cur.getString("start_time").contains(year)) {
				act=true;
				System.out.println("Yes, you ran from "+cur.getString("start_time")+" to "+cur.getString("end_time"));
			}
		}
		if(!act) {
			System.out.println("NO, you did't have any running event at "+monthAndDay+" "+year);
		}
	}
}
