package model;

import org.json.JSONObject;
import persistence.Writable;

// An anime class that has name, status, and date.
public class Anime implements Writable {
    private String name;
    private String status;
    private String date;

    public static final String statusA = "Not Yet Started";
    public static final String statusB = "Currently Watching";
    public static final String statusC = "Finished Watching";

    // MODIFIES: this
    // EFFECTS: construct the anime information with anime name, current watching status, and date.
    public Anime(String name, String status, String date) {
        this.name = name;
        this.date = date;
        if (status.equalsIgnoreCase("a") || status.equalsIgnoreCase(statusA)) {
            this.status = statusA;
        } else if (status.equalsIgnoreCase("b") || status.equalsIgnoreCase(statusB)) {
            this.status = statusB;
        } else if (status.equalsIgnoreCase("c") || status.equalsIgnoreCase(statusC)) {
            this.status = statusC;
        }
    }

    // EFFECTS: return the name of the anime.
    public String getName() {
        return name;
    }

    // EFFECTS: return the watching status of the anime.
    public String getStatus() {
        return status;
    }

    // EFFECTS: return the date of the anime.
    public String getDate() {
        return date;
    }

    // EFFECTS: change the status of the anime.
    public void changeStatus() {
        if (status.equals(statusA)) {
            status = statusB;
        } else if (status.equals(statusB)) {
            status = statusC;
        }
    }

    // MODIFIES: a Json object with anime information
    // EFFECTS: return the Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Anime Name", name);
        json.put("Anime Status", status);
        json.put("Anime Date", date);
        return json;
    }
}
