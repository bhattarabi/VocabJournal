package com.abhiyaan.androidapp.vocabjournal.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abhiyaan.androidapp.vocabjournal.R;
import com.abhiyaan.androidapp.vocabjournal.databinding.RecyclerItemWordBinding;
import com.abhiyaan.androidapp.vocabjournal.db.Word;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Binaya Bhattarai on 3/23/2018.
 */

public class WordsListRecyclerViewAdapter extends
        RecyclerView.Adapter<WordsListRecyclerViewAdapter.RecyclerViewHolder> {

    private List<Word> allWords;
    private WordClickCallback onClickListener;

    private RecyclerItemWordBinding recyclerItemWordBinding;

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
        this.allWords  = null;
        this.allWords = newWords;
        notifyDataSetChanged();
    }

    private void sortWordsByTitle(){
        Collections.sort(allWords, new Comparator<Word>(){
            public int compare(Word w1, Word w2){
                return w1.getTitle().compareTo(w2.getTitle());
            }
        });
    }

    public void sortWords(int sortBy){
        switch(sortBy){
            case 0:
                Collections.sort(allWords, new Comparator<Word>(){
                    public int compare(Word w1, Word w2){
                        return w1.getCreatedOn().compareTo(w2.getCreatedOn());
                    }
                });
                break;
            case 1:
                sortWordsByTitle();
                break;
            case 2:
                sortWordsByTitle();
                Collections.reverse(allWords);
                break;
            default:
        }
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private RecyclerItemWordBinding recyclerItemWordBinding;

        RecyclerViewHolder(RecyclerItemWordBinding binding){
            super(binding.getRoot());
            recyclerItemWordBinding = binding;
        }
    }
}
