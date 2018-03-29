package com.abhiyaan.androidapp.vocabjournal.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiyaan.androidapp.vocabjournal.R;
import com.abhiyaan.androidapp.vocabjournal.databinding.WordDefinitionFragmentBinding;
import com.abhiyaan.androidapp.vocabjournal.db.WordWithSentences;


/**
 * Created by Binaya Bhattarai on 3/25/2018.
 */

public class WordDefinitionFragment extends Fragment {

    WordDefinitionFragmentBinding wordDefinitionFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        wordDefinitionFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.word_definition_fragment, container, false);

        return wordDefinitionFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewModelProviders.of(getActivity())
            .get(SingleWordViewModel.class)
            .getCurrentWord().observe(this, new Observer<WordWithSentences>() {
            @Override
            public void onChanged(@Nullable WordWithSentences wordWithSentences) {
                wordDefinitionFragmentBinding.
                        setCurrentWord(wordWithSentences.getWord());

//                wordDefinitionFragmentBinding.executePendingBindings();
            }
        });
    }
}