package com.abhiyaan.androidapp.vocabjournal;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.abhiyaan.androidapp.vocabjournal.db.Word;
import com.abhiyaan.androidapp.vocabjournal.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Binaya Bhattarai on 3/27/2018.
 */

public class AppWebService {

    public static final String NO_DEFINITION =
            "No entries found in dictionary! Please modify your search and try again.";
    public static final String BAD_WORD = "bad_word_no_definition";

    HttpUtils httpUtils = new HttpUtils();

    public Word getDefinition(String word){
//        final MutableLiveData<Word> newWord = new MutableLiveData<>();
//        newWord.setValue(getDefinitionFromWeb(word));
        return getDefinitionFromWeb(word);
    }


    private Word getDefinitionFromWeb(String wordTitle) {

        HttpsURLConnection urlConn = httpUtils.makeUrlConnection(wordTitle);
        String definition = NO_DEFINITION;
        String title = BAD_WORD;

        if (httpUtils.getsValidResponse(urlConn)){
            title = wordTitle;
            definition = "";
            String jsonResponse = httpUtils.readFromUrlConnection(
                    httpUtils.makeUrlConnection(wordTitle));

            ArrayList<LexicalEntry> defs =
                    httpUtils.getDictionaryEntriesFromJson(jsonResponse);

            for (LexicalEntry l: defs)
                definition += l+"\n\n";
        }
        return new Word(title, definition);
    }
}
