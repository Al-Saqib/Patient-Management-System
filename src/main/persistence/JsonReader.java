package persistence;


import model.PatientRecords;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;



// Represents a reader that reads patient records from JSON data stored in file
public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file

    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads patient records from file and returns it;
    // throws IOException if an error occurs reading data from file

    public PatientRecords read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecords(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses patient records from JSON object and returns it

    private PatientRecords parseRecords(JSONObject jsonObject) {
        PatientRecords pr = new PatientRecords();
        addPatients(pr, jsonObject);
        return pr;
    }


    // MODIFIES: pr
    // EFFECTS: parses patients from JSON object and adds them to patient records

    private void addPatients(PatientRecords pr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("patients");
        for (Object json : jsonArray) {
            JSONObject nextPatient = (JSONObject) json;
            addPatient(pr, nextPatient);
//            String fullName = jsonObject.getString("name");
//            int publicHealthNumber = jsonObject.getInt("num");
//            pr.addPatient(publicHealthNumber, fullName);
        }
    }


    // MODIFIES: pr
    // EFFECTS: parses patient from JSON object and adds it to patient records

    private void addPatient(PatientRecords pr, JSONObject jsonObject) {
        String fullName = jsonObject.getString("name");
        int publicHealthNumber = jsonObject.getInt("num");
        pr.addPatient(publicHealthNumber, fullName);
    }
}

