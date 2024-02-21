package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// An anime list class that constructs a list with anime, and it's information.
public class AnimeList implements Writable {
    private ArrayList<Anime> animeList;

    // MODIFIES: this
    // EFFECTS: construct the anime list as a new arraylist.
    public AnimeList() {
        animeList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a new anime to the anime list.
    public void addAnime(Anime anime) {
        animeList.add(anime);
        EventLog.getInstance().logEvent(new Event(
                "A New Anime [" + anime.getName()
                        + "] Has Been Added With An Anime Date Of " + anime.getDate() + "."));
    }

    // MODIFIES: this
    // EFFECTS: remove an anime from the anime list by using the name.
    // do nothing if the anime name is not on the anime list.
    public void remove(String name) {
        for (int i = 0; i < animeList.size(); i++) {
            if (name.equalsIgnoreCase(animeList.get(i).getName())) {
                EventLog.getInstance().logEvent(new Event(
                        "Anime [" + animeList.get(i).getName() + "] Has Been Removed."));
                animeList.remove(i);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: mark an anime's status from the anime list by using the name.
    // do nothing if the anime name is not on the anime list.
    public void mark(String name) {
        for (Anime anime : animeList) {
            if (name.equalsIgnoreCase(anime.getName())) {
                anime.changeStatus();
                EventLog.getInstance().logEvent(new Event(
                        "Anime ["  + anime.getName() + "]'s Status Has Changed."));
            }
        }
    }

    // EFFECTS: return the anime list.
    public ArrayList<Anime> getAnimeList() {
        return animeList;
    }

    // MODIFIES: a Json object with anime list information
    // EFFECTS: return the Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("animeList", animeListToJson());
        return json;
    }

    // MODIFIES: a Json Array with all the anime contained
    // EFFECTS: returns all the anime in this anime list as a JSON array
    private JSONArray animeListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Anime a : animeList) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

}
