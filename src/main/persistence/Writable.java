package persistence;

import org.json.JSONObject;


// Represents an interface that returns its input as a Json object
public interface Writable {


    // EFFECTS: returns this as JSON object
    JSONObject toJson();


}
