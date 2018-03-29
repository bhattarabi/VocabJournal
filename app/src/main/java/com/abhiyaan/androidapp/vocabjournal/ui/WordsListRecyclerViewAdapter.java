package com.abhiyaan.androidapp.vocabjournal.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiyaan.androidapp.vocabjournal.R;
import com.abhiyaan.androidapp.vocabjournal.databinding.RecyclerItemWordBinding;
import com.abhiyaan.androidapp.vocabjournal.db.Word;

import java.util.List;

/**
 * Created by Binaya Bhattarai on 3/23/2018.
 */

public class WordsListRecyclerViewAdapter extends
        RecyclerView.Adapter<WordsListRecyclerViewAdapter.RecyclerViewHolder> {

    private List<Word> allWords;
    private WordClickCallback onClickListener;

    private RecyclerItemWordBinding recyclerItemWordBinding;

    private List<Word> currentWords;

    public WordsListRecyclerViewAdapter(
            List<Word> words, WordClickCallback onClickListener){
        this.allWords = words;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        recyclerItemWordBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.recycler_item_word, parent, false);
        return new RecyclerViewHolder(recyclerItemWordBinding);
    }

    @Override
    public void onBindViewHolder(
            RecyclerViewHolder holder, int position) {
        holder.recyclerItemWordBinding.setWord(allWords.get(position));
        holder.recyclerItemWordBinding.setCallback(onClickListener);
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    public void updateWords(List<Word> newWords){
        this.allWords = newWords;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private RecyclerItemWordBinding recyclerItemWordBinding;

        RecyclerViewHolder(RecyclerItemWordBinding binding){
            super(binding.getRoot());
            recyclerItemWordBinding = binding;
        }
    }
}
