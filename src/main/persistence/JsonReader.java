package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads anime from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads anime list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AnimeList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAnimeList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses anime list from JSON object and returns it
    private AnimeList parseAnimeList(JSONObject jsonObject) {
        AnimeList animeList = new AnimeList();
        addAnimes(animeList, jsonObject);
        return animeList;
    }

    // MODIFIES: animeList
    // EFFECTS: parses anime from JSON object and adds them to anime list
    private void addAnimes(AnimeList animeList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("animeList");
        for (Object json : jsonArray) {
            JSONObject nextAnime = (JSONObject) json;
            addAnime(animeList, nextAnime);
        }
    }

    // MODIFIES: animeList
    // EFFECTS: parses anime from JSON object and adds it to anime list
    private void addAnime(AnimeList animeList, JSONObject jsonObject) {
        String name = jsonObject.getString("Anime Name");
        String status = jsonObject.getString("Anime Status");
        String date = jsonObject.getString("Anime Date");
        Anime newAnime = new Anime(name, status, date);
        animeList.addAnime(newAnime);
    }
}
