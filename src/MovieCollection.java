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

    public void insertionSortWordList2(ArrayList<Movie> movies) {
        for (int i = 1; i < movies.size(); i++) {
            int idx = i-1;
            String temp = movies.get(i).getTitle();
            Movie temp2 = movies.get(i);
            while (idx >= 0 && movies.get(idx).getTitle().compareTo(temp) > 0) {
                movies.set(idx+1, movies.get(idx));
                idx--;
            }
            movies.set(idx +1, temp2);
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
                searchCast();
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
        ArrayList<Movie> temp2 = new ArrayList<>();
        int count = 1;
        for (Movie name : collection) {
            if (name.getTitle().toLowerCase().contains(searchMovie.toLowerCase())) {
                temp.add(name.getTitle());
                temp2.add(name);
            }
        }
        insertionSortWordList(temp);
        insertionSortWordList2(temp2);
        if (temp.size() == 0) {
            System.out.println("No movie titles match that search term!");
        } else {
            for (String name : temp) {
                System.out.println(count + ". " + name);
                count++;
            }
        }

        //movie info
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int movieNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        //print info
        System.out.println("Title: " + temp2.get(movieNum-1).getTitle());
        System.out.println("Runtime: " + temp2.get(movieNum-1).getRuntime());
        System.out.println("Directed by: " + temp2.get(movieNum-1).getDirector());
        System.out.println("Cast: " + temp2.get(movieNum-1).getCast());
        System.out.println("Overview: " + temp2.get(movieNum-1).getOverview());
        System.out.println("User rating: " + temp2.get(movieNum-1).getRating());
    }

    public void searchCast() {
        System.out.print("Enter a person to search for (first or last name): ");
        String searchCast = scanner.nextLine();
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Movie> temp2 = new ArrayList<>();
        int count = 1;
        for (Movie name : collection) {
            String[] splitData = name.getCast().split("\\|");
            for (int i = 0; i < splitData.length; i++) {
                if (splitData[i].toLowerCase().contains(searchCast.toLowerCase())) {
                    for (String name2 : temp) {
                        if (!name2.equals(splitData[i])) {
                            temp.add(splitData[i]);
                            temp2.add(name);
                        }
                    }
                }
            }
        }
        //sort
        insertionSortWordList(temp);
        insertionSortWordList2(temp2);

        //dislpay size
        if (temp.size() == 0) {
            System.out.println("No actors match that search term!");
        } else {
            for (String name : temp) {
                System.out.println(count + ". " + name);
                count++;
            }
        }
    }



}
