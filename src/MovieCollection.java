import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class MovieCollection {
    ArrayList<Movie> movieArrayList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public MovieCollection() {
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                movieArrayList.add(new Movie(splitData[0], splitData[1], splitData[2], splitData[3], Integer.parseInt(splitData[4]), Double.parseDouble(splitData[5])));
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        start();
    }

    private void start() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                //searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void searchTitles() {
        int num = 1;
        ArrayList<Movie> searchedMovie = new ArrayList<>();
        System.out.println("Enter a title search term: ");
        String term = scanner.nextLine().toLowerCase();
        for (Movie movie : movieArrayList) {
            if (movie.getTitle().toLowerCase().contains(term)) {
                System.out.println(num + ". " + movie.getTitle());
                num++;
                searchedMovie.add(movie);
            }
        }
        if (!searchedMovie.isEmpty()) {
            System.out.println("Which movie would you like to learn more about? ");
            System.out.println("Enter number: ");
            int movieNum = scanner.nextInt();
            scanner.nextLine();
            searchedMovie.get(movieNum - 1).info();
        } else {
            System.out.println("No movie titles match that search term!");
        }
        System.out.println("** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    //private void searchCast {

    //}
}
