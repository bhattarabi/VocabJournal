package com.abhiyaan.androidapp.vocabjournal.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Binaya Bhattarai on 3/26/2018.
 */

@Dao
public interface SentenceDao{

    @Insert(onConflict = IGNORE)
    void insert(Sentence sentence);

    @Query("select * from sentence where word_id = :wordId ")
    LiveData<List<Sentence>> getSentencesForWord(String wordId);

    @Insert(onConflict = REPLACE)
    void update(Sentence sentence);

    @Delete
    void delete(Sentence sentence);
}
