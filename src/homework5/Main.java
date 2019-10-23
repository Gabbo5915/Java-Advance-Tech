package homework5;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import
org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import
org.apache.lucene.queryparser.classic.QueryParser
;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			 // 0. Specify the analyzer for tokenizing text.
			 // The same analyzer should be used for indexing and searching
			 StandardAnalyzer analyzer = new StandardAnalyzer();
			 // 1. create the index
			 Directory index = new ByteBuffersDirectory();
			 IndexWriterConfig config = new IndexWriterConfig(analyzer);
			 IndexWriter w = new IndexWriter(index, config);
			 File target=new File("src/homework5/summary.txt");
			 BufferedReader input=new BufferedReader(new FileReader(target));
			 String st=null;
			 int x=1;
			 while((st=input.readLine())!=null) {
				 addDoc(w,st,Integer.toString(x++));
			 }
			 w.close();
			 input.close();
			 // 2. query
			 Scanner scan=new Scanner(System.in);
			 System.out.println("Please input the string you want to search: ");
			 String querystr =scan.nextLine();
			 // the "title" arg specifies the default field to use when no field is explicitly specified in the query.
			 Query q = new QueryParser("keyword", analyzer).parse(querystr);
			 // 3. search
			 System.out.println("Input the number of records you want to show: ");
			 int hitsPerPage = scan.nextInt();
			 scan.close();
			 IndexReader reader = DirectoryReader.open(index);
			 IndexSearcher searcher = new IndexSearcher(reader);
			 long start=System.nanoTime();
			 TopDocs docs = searcher.search(q, hitsPerPage);
			 long end=System.nanoTime();
			 ScoreDoc[] hits = docs.scoreDocs;
			 // 4. display results
			 System.out.println("Found " + hits.length + " hits.");
			 for(int i=0;i<hits.length;++i) {
			 int docId = hits[i].doc;
			 System.out.println("The line number is: "+docId);
			 Document d = searcher.doc(docId);
			 System.out.println((i + 1) + ". " + d.get("content") + "\t" + d.get("keyword"));
			 }

			 System.out.println("Total lucene search time is: "+(end-start));
			 // reader can only be closed when there
			 // is no need to access the documents any more.
			 reader.close();
			 System.out.println("******************************Day 1******************************");
			 SearchTime.Search(new File("src/homework5/Day1.txt"), querystr);
			 System.out.println("******************************Day 5******************************");
			 SearchTime.Search(new File("src/homework5/Day5.txt"), querystr);
			 System.out.println("******************************Day 10******************************");
			 SearchTime.Search(new File("src/homework5/Day10.txt"), querystr);
			 System.out.println("******************************Day 20******************************");
			 SearchTime.Search(new File("src/homework5/Day20.txt"), querystr);
			 System.out.println("******************************Day 30******************************");
			 SearchTime.Search(new File("src/homework5/Day30.txt"), querystr);
			 } catch(IOException ex){
			 ex.printStackTrace();
			 } catch (ParseException ex2) {
			 ex2.printStackTrace();
			 } 
	}

	private static void addDoc(IndexWriter w, String keyword, String content) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("keyword", keyword, Field.Store.YES));
		// use a string field for isbn because we don't want it tokenized
		doc.add(new StringField("content", content, Field.Store.YES));
		w.addDocument(doc);
	}

}
