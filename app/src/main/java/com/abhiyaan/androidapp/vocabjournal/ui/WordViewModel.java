package com.abhiyaan.androidapp.vocabjournal.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.abhiyaan.androidapp.vocabjournal.AppRepository;
import com.abhiyaan.androidapp.vocabjournal.db.Sentence;
import com.abhiyaan.androidapp.vocabjournal.db.Word;
import com.abhiyaan.androidapp.vocabjournal.db.WordWithSentences;

import java.util.List;

/**
 * Created by Binaya Bhattarai on 3/27/2018.
 */

public class WordViewModel extends AndroidViewModel{

    private LiveData<WordWithSentences> currentWord;

    private AppRepository appRepository;

    public void init(String wordTitle){
        //setup currentWord
        currentWord = appRepository.getWord(wordTitle);
    }

    public WordViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
    }

    public LiveData<WordWithSentences> getCurrentWord() {
        return currentWord;
    }
}
