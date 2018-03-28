package com.abhiyaan.androidapp.vocabjournal;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.abhiyaan.androidapp.vocabjournal.databinding.ActivityMainBinding;
import com.abhiyaan.androidapp.vocabjournal.db.WordDao;
import com.abhiyaan.androidapp.vocabjournal.db.WordWithSentences;
import com.abhiyaan.androidapp.vocabjournal.ui.WordViewModel;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    WordViewModel wordViewModel;
    ActivityMainBinding activityMainBinding;

    private boolean isObservingViewModel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        activityMainBinding.setSubmitClickListener(this);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
    }

    private void observeViewModel(){
    wordViewModel.getCurrentWord().observe( this,
            new Observer<WordWithSentences>() {
            @Override
            public void onChanged(@Nullable WordWithSentences wordWithSentences) {
                Log.i("word:", wordWithSentences.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        new GetWordTask(wordViewModel).execute(
                activityMainBinding.etQuery.getText().toString()
        );
    }

    private class GetWordTask extends AsyncTask<String, Void, String>{

        WordViewModel v;

        public GetWordTask (WordViewModel vm){
            v = vm;
        }

        @Override
        protected String doInBackground(String... strings) {
            v.init(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!isObservingViewModel) {
                observeViewModel();
                isObservingViewModel = true;
            }
        }
    }
}
