package com.abhiyaan.androidapp.vocabjournal.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.abhiyaan.androidapp.vocabjournal.R;
import com.abhiyaan.androidapp.vocabjournal.databinding.ActivityWordBinding;
import com.abhiyaan.androidapp.vocabjournal.db.Word;

/**
 * Created by Binaya Bhattarai on 3/25/2018.
 */

public class MyWordsActivity extends AppCompatActivity implements WordClickCallback {

    ActivityWordBinding activityWordBinding;
    WordsListFragment wordsListFragment;
    WordsListViewModel wordsListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityWordBinding = DataBindingUtil.setContentView(this, R.layout.activity_word);

        wordsListViewModel = ViewModelProviders.of(this)
                .get(WordsListViewModel.class);

        if (savedInstanceState == null){
            wordsListFragment = new WordsListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, wordsListFragment, null).commit();
        }
    }

    @Override
    public void onClick(Word word) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra(MainActivity.WORD_ARG, word.getTitle());
        startActivity(mainIntent);
    }
}
