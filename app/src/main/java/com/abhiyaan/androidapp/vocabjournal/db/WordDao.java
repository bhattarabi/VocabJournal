package com.abhiyaan.androidapp.vocabjournal.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by Binaya Bhattarai on 3/26/2018.
 */

@Dao
@TypeConverters(DateConverter.class)
public interface WordDao {

    @Query("select * from word")
    LiveData<List<Word>> getAllWords();

    @Query("select * from word where title = :title")
    LiveData<WordWithSentences> getWordWithSentences(String title);

    @Query("select * from word where title = :title")
    LiveData<Word> getWord(String title);

    @Query("select * from word limit 1")
    Word getFirstWord();

    @Insert(onConflict = IGNORE)
    void insert(Word word);


    //TODO
//    @Query("select * from word where created_on < :date")
//    LiveData<List<Word>> getWordsSince(Date date);

    //TODO
//    @Query("select * from word where title like '['+:start+'-'+:end+']%' ")
//    LiveData<List<Word>> getWordsInRange(char start, char end);

    //TODO
//    LiveData<List<Word>> getSentenceLessWords();
}
