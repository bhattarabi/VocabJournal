package com.abhiyaan.androidapp.vocabjournal.ui;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.abhiyaan.androidapp.vocabjournal.R;
import com.abhiyaan.androidapp.vocabjournal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{

    public static final String WORD_ARG = "word_arg";

    SingleWordViewModel singleWordViewModel;
    ActivityMainBinding activityMainBinding;

    WordViewPagerAdapter wordViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        activityMainBinding.setSubmitClickListener(this);

        singleWordViewModel = ViewModelProviders.of(this).get(SingleWordViewModel.class);

        String wordArg = getIntent().getStringExtra(WORD_ARG);

        if (wordArg != null){
            new GetWordTask().execute(wordArg);
        }

        if (singleWordViewModel.isLoaded())
            createFragments();
    }

    private void hideSoftKeys(){
        InputMethodManager imm =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.et_query:
                singleWordViewModel.setSoftKeyHidden(false);
                break;

            case R.id.fab_add_sentence:
                getSentenceInput();
                break;

            case R.id.btn_update_sentence:
                break;

            case R.id.btn_delete_sentence:
                break;

            case R.id.btn_submit_query:
                new GetWordTask().execute(
                        activityMainBinding.etQuery.getText().toString().toLowerCase());

            default:
                singleWordViewModel.setSoftKeyHidden(true);
                hideSoftKeys();
        }
    }

    private void getSentenceInput(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Usage Example");

        final EditText input = new EditText(getApplicationContext());
        input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        input.setHint(R.string.enter_sentence_hint);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new CreateSentenceTask().execute(input.getText().toString());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private class CreateSentenceTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            singleWordViewModel.createSentence(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            wordViewPagerAdapter.notifyDataSetChanged();
        }
    }

    private class GetWordTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {
            singleWordViewModel.getWord(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void v){
            if (!singleWordViewModel.isLoaded()) {
                createFragments();
                singleWordViewModel.setLoaded(true);
            }
            wordViewPagerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menuitem_mywords:
                Intent wordActivityIntent = new Intent(
                        this, MyWordsActivity.class);
                startActivity(wordActivityIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createFragments() {
        wordViewPagerAdapter = new WordViewPagerAdapter(getSupportFragmentManager());
        activityMainBinding.viewpager.setAdapter(wordViewPagerAdapter);
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewpager);
    }
}
