package com.abhiyaan.androidapp.vocabjournal.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Binaya Bhattarai on 3/23/2018.
 */
@Database(entities = {Word.class, Sentence.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract WordDao wordDao();

    public abstract SentenceDao sentenceDao();

    public static AppDatabase getInMemoryDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context, AppDatabase.class, "vocab_journal.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
