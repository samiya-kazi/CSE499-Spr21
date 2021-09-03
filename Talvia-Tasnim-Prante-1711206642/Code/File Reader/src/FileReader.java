
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

	public static void main(String[] args) throws IOException {

		

		File file = new File("/Users/xeonc/Desktop/page 1.txt"); //reading the file
		Scanner scan = new Scanner(file);

		String fileContent = "";  // storing the file into a string 
		while (scan.hasNextLine()) {         //reads the whole file content line by line
			fileContent = fileContent.concat(scan.nextLine() + "\n");
		}

		FileWriter writer = new FileWriter("/Users/xeonc/Desktop/newfile.csv"); // converting into csv file
		writer.write(fileContent);
		writer.close();

	}

}
