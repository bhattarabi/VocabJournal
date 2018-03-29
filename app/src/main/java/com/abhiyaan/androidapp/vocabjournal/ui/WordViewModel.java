package com.abhiyaan.androidapp.vocabjournal.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.abhiyaan.androidapp.vocabjournal.AppRepository;
import com.abhiyaan.androidapp.vocabjournal.db.Sentence;
import com.abhiyaan.androidapp.vocabjournal.db.WordWithSentences;

/**
 * Created by Binaya Bhattarai on 3/27/2018.
 */

public class WordViewModel extends AndroidViewModel{

    private LiveData<WordWithSentences> currentWord;

    private AppRepository appRepository;

    private boolean isLoaded;
    private boolean isSoftKeyHidden;


    public void getWord(String wordTitle){
        //setup currentWord
        currentWord = appRepository.getWord(wordTitle);
        if (!isSoftKeyHidden){
            isSoftKeyHidden = true;
        }
    }

    public void createSentence(String content) {
        appRepository.createSentence(
                new Sentence(content, getCurrentWord().getValue().getWord().getTitle())
        );
    }

    public WordViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        isLoaded = false;
        isSoftKeyHidden = false;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setLoaded(boolean loaded) {
        isLoaded = loaded;
    }

    public LiveData<WordWithSentences> getCurrentWord() {
        return currentWord;
    }

    public void setSoftKeyHidden(Boolean isSoftKeyHidden){
        this.isSoftKeyHidden = isSoftKeyHidden;
    }

    public boolean isSoftKeyHidden(){
        return isSoftKeyHidden;
    }


}