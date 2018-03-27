package com.abhiyaan.androidapp.vocabjournal.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Binaya Bhattarai on 3/26/2018.
 */

public class Word {

    @PrimaryKey
    @NonNull
    private String title;

    @NonNull
    private String definition;

    @NonNull
    @TypeConverters(DateConverter.class)
    @ColumnInfo(name = "created_on")
    private Date createdOn;

    public Word(){}

    @Ignore
    public Word(String title, String definition){
        this.title = title;
        this.definition = definition;
    }

    @Ignore
    public Word(String title, String definition, Date createdOn){
        this(title, definition);
        this.createdOn = createdOn;
    }
}
