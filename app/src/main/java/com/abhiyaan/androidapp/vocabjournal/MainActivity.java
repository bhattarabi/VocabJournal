package com.abhiyaan.androidapp.vocabjournal;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.abhiyaan.androidapp.vocabjournal.databinding.ActivityMainBinding;
import com.abhiyaan.androidapp.vocabjournal.ui.WordViewModel;
import com.abhiyaan.androidapp.vocabjournal.ui.WordViewPagerAdapter;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    WordViewModel wordViewModel;
    ActivityMainBinding activityMainBinding;

    WordViewPagerAdapter wordViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        activityMainBinding.setSubmitClickListener(this);

        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        if (wordViewModel.isLoaded())
            createFragments();
    }

    private void hideSoftKeys(){
        InputMethodManager imm =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("resuming", String.valueOf(wordViewModel.isSoftKeyHidden()));
        if (wordViewModel.isSoftKeyHidden()){
            hideSoftKeys();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.et_query:
                wordViewModel.setSoftKeyHidden(false);
                break;

            case R.id.btn_submit_query:
                new GetWordTask().execute(activityMainBinding.etQuery.getText().toString());

            default:
                wordViewModel.setSoftKeyHidden(true);
                hideSoftKeys();
        }
    }

    private class GetWordTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            wordViewModel.getWord(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            if (!wordViewModel.isLoaded()) {
                createFragments();
                wordViewModel.setLoaded(true);
            }
            wordViewPagerAdapter.notifyDataSetChanged();
//            wordViewPagerAdapter.fin
        }
    }

    private void createFragments() {
        wordViewPagerAdapter = new WordViewPagerAdapter(getSupportFragmentManager());
        activityMainBinding.viewpager.setAdapter(wordViewPagerAdapter);
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewpager);
    }
}
