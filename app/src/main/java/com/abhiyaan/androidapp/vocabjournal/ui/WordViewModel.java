package com.abhiyaan.androidapp.vocabjournal.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.abhiyaan.androidapp.vocabjournal.db.Sentence;
import com.abhiyaan.androidapp.vocabjournal.db.Word;
import com.abhiyaan.androidapp.vocabjournal.db.WordWithSentences;

import java.util.List;

/**
 * Created by Binaya Bhattarai on 3/27/2018.
 */

public class WordViewModel extends AndroidViewModel{

    private LiveData<Word> currentWord;

    private LiveData<List<Sentence>> sentences;

    public void init(WordWithSentences wordWithSentences){
        //TODO
    }

    public WordViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Word> getCurrentWord() {
        return currentWord;
    }

    public LiveData<List<Sentence>> getSentences() {
        return sentences;
    }
}
