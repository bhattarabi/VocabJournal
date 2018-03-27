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

    HttpUtils httpUtils = new HttpUtils();

    public Word getDefinition(String word){
//        final MutableLiveData<Word> newWord = new MutableLiveData<>();
//        newWord.setValue(getDefinitionFromWeb(word));
        return getDefinitionFromWeb(word);
    }


    private Word getDefinitionFromWeb(String wordTitle) {

        HttpsURLConnection urlConn = httpUtils.makeUrlConnection(wordTitle);

        String jsonResponse = httpUtils.readFromUrlConnection(
                httpUtils.makeUrlConnection(wordTitle));

        ArrayList<LexicalEntry> defs =
                httpUtils.getDictionaryEntriesFromJson(jsonResponse);

        String definition = "";
        for (LexicalEntry l: defs)
            definition += l+"\n\n";

        return new Word(wordTitle, definition, new Date());
    }
}
