package homework2;

import java.io.File;

public class Run {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String zipFilePath="src/homework2/SampleUserSmartwatch.zip";
		String destDirectory="src/homework2";
		UnzipUtility unzipper = new UnzipUtility();
        try {
            unzipper.unzip(zipFilePath, destDirectory);//unzip the original zip file
        } catch (Exception ex) {
            // some errors occurred
            ex.printStackTrace();
        }
        File targetDir=new File("src/homework2/SampleUserSmartwatch");
        File[] files=targetDir.listFiles();
        for(File file:files) {
        	try {
        		unzipper.unzip(file.getPath(), "src/homework2/temp");//unzip each sub-zip file
        	}catch(Exception ex) {
        		ex.printStackTrace();
        	}
        }*/
        File finalDir=new File("src/homework2/temp");// for store all the txt file
        File summary=new File("src/homework2/summary.txt");// summary file
        for(File file:finalDir.listFiles()) {
        	if(file.isDirectory()) {
        		System.out.println(file.getName());
        		DirToTxt.dirToTxt(file, summary);
        	}
        }
	}

}
