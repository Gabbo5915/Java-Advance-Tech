package homework2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DirToTxt {
	/**
	 * Copy all the content from target Directory
	 * @param dir The direction you want to extract content to file
	 * @param des The target file you want to write content from direction
	 */
	public static void dirToTxt(File dir,File des) {
		BufferedWriter output=null;
		BufferedReader input=null;
		try {
			output=new BufferedWriter(new FileWriter(des,true));
			File[] files=dir.listFiles();
			for(File file:files) {	//List all the files inside the target folder
				if(!file.isDirectory()) {	//file is a file
					input=new BufferedReader(new FileReader(file));
					String st=null;
					while((st=input.readLine())!=null) {
						output.append(st).append("\n");
					}
					input.close();
				}else {	//use the same function apply to the sub-folder until you find the file which is not folder
					dirToTxt(file,des);
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
