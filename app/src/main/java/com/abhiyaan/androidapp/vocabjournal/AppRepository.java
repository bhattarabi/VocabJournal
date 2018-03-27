package com.abhiyaan.androidapp.vocabjournal;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.abhiyaan.androidapp.vocabjournal.db.AppDatabase;
import com.abhiyaan.androidapp.vocabjournal.db.Word;
import com.abhiyaan.androidapp.vocabjournal.db.WordDao;
import com.abhiyaan.androidapp.vocabjournal.db.WordWithSentences;

/**
 * Created by Binaya Bhattarai on 3/27/2018.
 */

public class AppRepository {

    private AppWebService appWebService;
    private AppDatabase appDatabase;

    public AppRepository(Application application){
        appDatabase = AppDatabase.getInMemoryDatabase(application);
        appWebService = new AppWebService();
    }

    public LiveData<WordWithSentences> getWord(String wordTitle){
        refreshWord(wordTitle);
        return appDatabase.wordDao().getWordWithSentences(wordTitle);
    }

    private void refreshWord(String wordTitle){
        String log;
        if (appDatabase.wordDao().getWordSync(wordTitle) == null){
            log = "from the Web";
            appDatabase.wordDao().insert(appWebService.getDefinition(wordTitle));
        }
        else{
            log = "from local DB";
        }
        Log.i("fetchWord:", log);
    }
}
