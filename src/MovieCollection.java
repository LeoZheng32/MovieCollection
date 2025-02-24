import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class MovieCollection {
    try {
        File myFile = new File("src//movies_data.csv");
        Scanner fileScanner = new Scanner(myFile);
        while (fileScanner.hasNext()) {
            String data = fileScanner.nextLine();

        }
    } catch (IOException exception) {
        System.out.println(exception.getMessage());
    }
}
