package persistence;

import org.json.JSONObject;

// An interface named Writable that has JSON Object for implementations
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
