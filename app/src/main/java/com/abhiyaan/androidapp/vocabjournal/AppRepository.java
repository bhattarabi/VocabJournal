package com.abhiyaan.androidapp.vocabjournal;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.abhiyaan.androidapp.vocabjournal.db.Word;

/**
 * Created by Binaya Bhattarai on 3/27/2018.
 */

public class AppRepository {

    private AppWebService appWebService;

    public LiveData<Word> getWord(String wordTitle){
        return appWebService.getDefinition(wordTitle);
    }
}
