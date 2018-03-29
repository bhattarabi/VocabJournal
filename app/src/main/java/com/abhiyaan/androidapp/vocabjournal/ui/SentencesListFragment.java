package com.abhiyaan.androidapp.vocabjournal.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiyaan.androidapp.vocabjournal.R;
import com.abhiyaan.androidapp.vocabjournal.databinding.SentencesListFragmentBinding;
import com.abhiyaan.androidapp.vocabjournal.db.Sentence;
import com.abhiyaan.androidapp.vocabjournal.db.WordWithSentences;

import java.util.ArrayList;

/**
 * Created by Binaya Bhattarai on 3/24/2018.
 */

public class SentencesListFragment extends Fragment {

    private SentenceListRecyclerViewAdapter sentenceListRecyclerViewAdapter;
    private SentencesListFragmentBinding sentencesListFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        sentencesListFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.sentences_list_fragment, container, false);

        sentenceListRecyclerViewAdapter = new SentenceListRecyclerViewAdapter(
                new ArrayList<Sentence>(), (View.OnClickListener)getActivity());

        sentencesListFragmentBinding.sentencesRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));
        sentencesListFragmentBinding.sentencesRecyclerView.
                setAdapter(sentenceListRecyclerViewAdapter);

        sentencesListFragmentBinding.fabAddSentence.setOnClickListener(
                (View.OnClickListener)getActivity());

        return sentencesListFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SingleWordViewModel viewModel =
                ViewModelProviders.of(getActivity()).get(SingleWordViewModel.class);

        viewModel.getCurrentWord().observe(this, new Observer<WordWithSentences>() {
            @Override
            public void onChanged(@Nullable WordWithSentences wordWithSentences) {
                sentenceListRecyclerViewAdapter.updateSentences(
                        wordWithSentences.getSentences());
                sentenceListRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }
}