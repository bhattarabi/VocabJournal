package com.abhiyaan.androidapp.vocabjournal.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiyaan.androidapp.vocabjournal.R;
import com.abhiyaan.androidapp.vocabjournal.databinding.WordsListFragmentBinding;
import com.abhiyaan.androidapp.vocabjournal.db.Word;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Binaya Bhattarai on 3/24/2018.
 */

public class WordsListFragment extends Fragment{
    private WordsListViewModel wordsListViewModel;
    private WordsListRecyclerViewAdapter wordsListRecyclerViewAdapter;

    private WordsListFragmentBinding wordsListFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        wordsListFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.words_list_fragment, container,false);

        wordsListRecyclerViewAdapter = new WordsListRecyclerViewAdapter(new ArrayList<Word>(),
                (WordClickCallback)getActivity());
        wordsListFragmentBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        wordsListFragmentBinding.recyclerView.setAdapter(wordsListRecyclerViewAdapter);

        return wordsListFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        wordsListViewModel = ViewModelProviders.of(getActivity())
                .get(WordsListViewModel.class);

        wordsListViewModel.getMyWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                wordsListRecyclerViewAdapter.updateWords(words);
            }
        });
    }
}
