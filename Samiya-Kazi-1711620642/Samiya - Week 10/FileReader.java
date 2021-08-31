
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.lang.Math;


public class FileReader {

	public static void main(String[] args) throws IOException {

		File file = new File("/Users/Basic/Documents/NSU/CSE499B/oracle.txt");
		Scanner scan = new Scanner(file);
		
		try (PrintWriter writer = new PrintWriter(new File("/Users/Basic/Documents/NSU/CSE499B/oracle.csv"))) {
			writer.write("CVE ID,Category,Date,Time Gap (Days)\n");
			scan.nextLine();
			String prevDate = null;
			
			while (scan.hasNextLine()) {
				StringBuilder sb = new StringBuilder();
				String entry = scan.nextLine();
				
				int firstTab = entry.indexOf("\t");
				String newEntry = entry.substring(firstTab + 1);
				
				int idEnd = newEntry.indexOf("\t");
				String cveid = newEntry.substring(0, idEnd);
				
				
				int categoryStart = newEntry.indexOf("\t", newEntry.indexOf("\t", newEntry.indexOf("\t") + 1) + 1) + 1;
				int categoryEnd = newEntry.indexOf("\t", newEntry.indexOf("\t", newEntry.indexOf("\t", newEntry.indexOf("\t") + 1) + 1) + 1);
				String category = newEntry.substring(categoryStart, categoryEnd);
				
				int dateStart = newEntry.indexOf("\t", newEntry.indexOf("\t", newEntry.indexOf("\t", newEntry.indexOf("\t") + 1) + 1) + 1) + 1;
				int dateEnd = dateStart + 10;
				String date = newEntry.substring(dateStart, dateEnd);
				
				
				long timeGap;
				if(prevDate == null)
					timeGap = 0;
				else {
					LocalDate prev = LocalDate.parse(prevDate);
					LocalDate current = LocalDate.parse(date);
					timeGap = ChronoUnit.DAYS.between(prev, current);
					timeGap = Math.abs(timeGap);
				}
				
				
				sb.append(cveid);
				sb.append(",");
				sb.append(category);
				sb.append(",");
				sb.append(date);
				sb.append(",");
				sb.append(timeGap);
				sb.append(",");
				sb.append("\n");
				
				prevDate = date;
				writer.write(sb.toString());
			}
			
			writer.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}

