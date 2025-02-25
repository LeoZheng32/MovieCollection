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
            String data = fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                data = fileScanner.nextLine();
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
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void searchTitles() {
        ArrayList<String> searchedMovie = new ArrayList<>();
        System.out.print("Enter a title search term: ");
        String term = scanner.nextLine().toLowerCase();
        for (Movie movie : movieArrayList) {
            if (movie.getTitle().toLowerCase().contains(term)) {
                searchedMovie.add(movie.getTitle());
            }
        }

        insertionSortWordList(searchedMovie);

        int i = 1;
        for (String movie : searchedMovie) {
            System.out.println(i + ". " + movie);
            i++;
        }

        if (!searchedMovie.isEmpty()) {
            System.out.println("Which movie would you like to learn more about? ");
            System.out.print("Enter number: ");
            int movieNum = scanner.nextInt();
            scanner.nextLine();
            for (Movie movie : movieArrayList) {
                if (movie.getTitle().equals(searchedMovie.get(movieNum - 1))) {
                    System.out.println(movie.info());
                    break;
                }
            }
        } else {
            System.out.println("No movie titles match that search term!");
        }
        System.out.println("** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void searchCast() {
        ArrayList<String> findCast = new ArrayList<>();
        System.out.print("Enter to search for a cast: ");
        String term = scanner.nextLine().toLowerCase();
        for (Movie movie : movieArrayList) {
            String[] splitData = movie.getCast().split("\\|");
            for (String data : splitData) {
                if (data.toLowerCase().contains(term) && !findCast.contains(data)) {
                    findCast.add(data);
                }
            }
        }
        insertionSortWordList(findCast);
        int i = 1;
        for (String movie : findCast) {
            System.out.println(i + ". " + movie);
            i++;
        }

        if (!findCast.isEmpty()) {
            System.out.println("Which cast member would you like see all movies for? ");
            System.out.print("Enter number: ");
            String name = findCast.get(scanner.nextInt()-1);
            scanner.nextLine();

            ArrayList<String> castMovie = new ArrayList<>();
            for (Movie movie : movieArrayList) {
                String[] splitData = movie.getCast().split("\\|");
                for (int j = 0; j < splitData.length; j++) {
                    if (splitData[j].contains(name)) {
                        castMovie.add(movie.getTitle());
                        break;
                    }
                }
            }
            insertionSortWordList(castMovie);
            int interation = 1;
            for (String movie : castMovie) {
                System.out.println(interation + ". " + movie);
                interation++;
            }
        }
        System.out.print("** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void insertionSortWordList(ArrayList<String> words) {
        for (int i = 0; i < words.size(); i++) {
            int m = i;
            while (m-1 >= 0 && words.get(m).compareTo(words.get(m-1)) < 0) {
                String temp = words.get(m-1);
                words.set(m-1, words.get(m));
                words.set(m, temp);
                m--;
            }
        }
    }
}
