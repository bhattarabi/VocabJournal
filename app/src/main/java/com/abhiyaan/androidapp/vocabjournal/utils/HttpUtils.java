package com.abhiyaan.androidapp.vocabjournal.utils;

import com.abhiyaan.androidapp.vocabjournal.Definition;
import com.abhiyaan.androidapp.vocabjournal.LexicalEntry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Binaya Bhattarai on 3/27/2018.
 */

public class HttpUtils {

    final String APP_ID_TAG = "APP_ID";
    final String APP_ID = "ba638121";
    final String APP_KEY_TAG = "APP_KEY";
    final String APP_KEY = "95b4ddf78d2cdc182f5b01aa877095ba";

    final String ACCEPT_TAG = "Accept";
    final String ACCEPT_JSON = "application/json";

    final String BASE_URL = "https://od-api.oxforddictionaries.com/api/v1/entries/en";
    final String BASE_URL_LEMMATRON =
            "https://od-api.oxforddictionaries.com/api/v1/inflections/en";

    public HttpsURLConnection makeUrlConnection(String query){
        String fullUrl = BASE_URL + "/" + query;
        HttpsURLConnection urlConnection = null;

        try {
            urlConnection = (HttpsURLConnection) new URL(fullUrl).openConnection();
            urlConnection.setRequestProperty(ACCEPT_TAG, ACCEPT_JSON);
            urlConnection.setRequestProperty(APP_ID_TAG, APP_ID);
            urlConnection.setRequestProperty(APP_KEY_TAG, APP_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlConnection;
    }

    // read the output from the server
    public String readFromUrlConnection(HttpsURLConnection urlConn){

        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public ArrayList<LexicalEntry> getDictionaryEntriesFromJson(String dicEntriesJson) {
        ArrayList<LexicalEntry> lexicalEntryArrayList = new ArrayList<>();
        try {
            JSONObject results = new JSONObject(dicEntriesJson)
                    .getJSONArray("results")
                    .getJSONObject(0);

            String title = results.getString("id");
            String language = results.getString("language");

            JSONArray lexicalEntries = results.getJSONArray("lexicalEntries");


            for (int i=0; i<lexicalEntries.length(); i++){
                JSONObject lexEntry = lexicalEntries.getJSONObject(i);
                JSONArray senses = lexEntry
                        .getJSONArray("entries")
                        .getJSONObject(0)
                        .getJSONArray("senses");

                //grab all definitions for this sense of the word
                ArrayList<Definition> definitions = new ArrayList<>();
                for (int j=0; j<senses.length(); j++){

                    String example = null;
                    JSONObject obj = senses.getJSONObject(j);
                    if (obj.has("examples")){
                        example = obj
                                .getJSONArray("examples")
                                .getJSONObject(0)
                                .getString("text");
                    }

                    String definition = senses
                            .getJSONObject(j)
                            .getJSONArray("definitions")
                            .getString(0);

                    Definition def = new Definition(definition, example);
                    definitions.add(def);
                }

                String category = lexEntry.getString("lexicalCategory");
                JSONObject pronunciation = lexEntry
                        .getJSONArray("pronunciations")
                        .getJSONObject(0);

                String pronunciationUrl = pronunciation.getString("audioFile");
                String phonetic = pronunciation.getString("phoneticSpelling");

                lexicalEntryArrayList.add(new LexicalEntry(phonetic, pronunciationUrl, category,
                        definitions));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return lexicalEntryArrayList;
        }
    }
}
