package com.abhiyaan.androidapp.vocabjournal.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.abhiyaan.androidapp.vocabjournal.AppRepository;
import com.abhiyaan.androidapp.vocabjournal.db.Word;

import java.util.List;

/**
 * Created by Binaya Bhattarai on 3/29/2018.
 */

public class WordsListViewModel extends AndroidViewModel {

    private LiveData<List<Word>> myWords;
    private AppRepository appRepository;

    public WordsListViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        myWords = appRepository.getAllWords();
    }

    public LiveData<List<Word>> getMyWords() {
        return myWords;
    }
}
