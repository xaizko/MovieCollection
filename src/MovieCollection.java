import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


public class MovieCollection {
    private ArrayList<Movie> collection;
    private Scanner scanner;
    public MovieCollection() {
        collection = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void insertionSortWordList(ArrayList<String> words) {
        for (int i = 1; i < words.size(); i++) {
            int idx = i-1;
            String temp = words.get(i);
            while (idx >= 0 && words.get(idx).compareTo(temp) > 0) {
                words.set(idx+1, words.get(idx));
                idx--;
            }
            words.set(idx +1, temp);
        }
    }

    private void readData() {
        ArrayList<String> movie = new ArrayList<>();
        try {
            File myFile = new File("src\\movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String title = splitData[0];
                String cast = splitData[1];
                String director = splitData[2];
                String overview = splitData[3];
                int runtime = Integer.parseInt(splitData[4]);
                double rating = Double.parseDouble(splitData[5]);
                Movie movieTemp = new Movie(title, cast, director, overview, runtime, rating);
                collection.add(movieTemp);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void start() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";
        readData();

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

    public void searchTitles() {
        System.out.print("Enter a title search term: ");
        String searchMovie = scanner.nextLine();
        ArrayList<String> temp = new ArrayList<>();
        int count = 1;
        for (Movie name : collection) {
            if (name.getTitle().toLowerCase().contains(searchMovie.toLowerCase())) {
                temp.add(name.getTitle());
            }
        }
        insertionSortWordList(temp);
        for (String name : temp) {
            System.out.println( count + ". " + name);
            count++;
        }
    }



}
