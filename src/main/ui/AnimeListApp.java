package ui;

import model.Anime;
import model.AnimeList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// An App That Display the Anime List for the user to interact with
public class AnimeListApp {
    private static final String JSON_STORE = "./data/animeListApp.json";
    private Anime newAnime;
    private AnimeList animeList = new AnimeList();
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: run the anime list application
    public AnimeListApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runAnimeListApp();
    }

    // MODIFIES: this
    // EFFECTS: display the anime list and interact with the user inputs
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void runAnimeListApp() {
        boolean running = true;
        String selection;

        initialize();
        displayList();
        displayOptions();

        while (running) {
            selection = input.next();
            selection = selection.toLowerCase();

            if (selection.equals("q")) {
                running = false;
            } else if (selection.equals("a") || selection.equals("b") || selection.equals("c")) {
                runSelection(selection);
                displayList();
                displayOptions();
            } else if (selection.equals("l")) {
                loadAnimeList();
                displayList();
                displayOptions();
            } else if (selection.equals("s")) {
                saveAnimeList();
                displayOptions();
            } else {
                System.out.println("    Invalid! Please try again.");
                System.out.println();
                displayOptions();
            }
        }
        System.out.println("        Have a good day and Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: run the user choice of selection with the user input
    private void runSelection(String letter) {
        if (letter.equals("a")) {
            this.add();
        } else if (letter.equals("b")) {
            this.remove();
        } else if (letter.equals("c")) {
            this.mark();
        } else {
            System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECTS: add function for the display option choice for the user
    private void add() {
        String name;
        System.out.println("    Please enter the name of the anime");
        name = input.next();
        System.out.println("    Please enter the status of the anime");
        System.out.println("    If you have not yet started watching, please enter A");
        System.out.println("    If you are currently watching, please enter B");
        System.out.println("    If you have finished watching, please enter C");
        String status = input.next();
        System.out.println("    Please enter the date of today");
        String date = input.next();
        newAnime = new Anime(name, status, date);
        animeList.addAnime(newAnime);
    }

    // MODIFIES: this
    // EFFECTS: remove function for the display option choice for the user
    private void remove() {
        String name;
        System.out.println("    Please enter the name of the anime that you want to remove");
        name = input.next();
        animeList.remove(name);
    }

    // MODIFIES: this
    // EFFECTS: mark function for the display option choice for the user
    private void mark() {
        String name;
        System.out.println("    Enter the name of the anime that you want to mark");
        name = input.next();
        animeList.mark(name);
    }

    // MODIFIES: this
    // EFFECTS: initialize the anime list with some anime
    private void initialize() {
        newAnime = new Anime("Kazuha", "a", "11/21");
        animeList.addAnime(newAnime);
        newAnime = new Anime("Ayaka", "b", "10/28");
        animeList.addAnime(newAnime);
        newAnime = new Anime("Yoimiya", "c", "06/20");
        animeList.addAnime(newAnime);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: display the anime list to the user
    private void displayList() {
        System.out.println();
        System.out.println("    A List of Anime For You!");
        System.out.println();
        printInfo();
    }

    // EFFECTS: display menu of options to the user
    private void displayOptions() {
        System.out.println("    Would you like to load your saved anime list from last time?");
        System.out.println("        l -> load anime list from the file\n");
        System.out.println("    You can also select the following options: ");
        System.out.println("        a -> add a new anime to the anime list");
        System.out.println("        b -> remove a anime from the anime list");
        System.out.println("        c -> mark the status of a anime as marked");
        System.out.println("        s -> save current anime list to the file");
        System.out.println("        q -> done for the day and quit the app");
        System.out.println();
    }

    // EFFECTS: print all the info of anime list to the user
    private void printInfo() {
        for (Anime anime : animeList.getAnimeList()) {
            System.out.println("        Anime Name: " + anime.getName());
            System.out.println("        Anime Status: " + anime.getStatus());
            System.out.println("        Anime Date: " + anime.getDate());
            System.out.println();
        }
    }

    // MODIFIES: this
    // EFFECTS: save the anime list info given by the user to files
    private void saveAnimeList() {
        try {
            jsonWriter.open();
            jsonWriter.write(animeList);
            jsonWriter.close();
            System.out.println("        Successfully saved Anime List to " + JSON_STORE + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the anime list saved by the user last time from files
    private void loadAnimeList() {
        try {
            animeList = jsonReader.read();
            System.out.println("        Successfully loaded Anime List from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
