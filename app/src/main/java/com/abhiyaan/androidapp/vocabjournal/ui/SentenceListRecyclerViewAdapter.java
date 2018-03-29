package com.abhiyaan.androidapp.vocabjournal.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.abhiyaan.androidapp.vocabjournal.R;
import com.abhiyaan.androidapp.vocabjournal.databinding.RecyclerItemSentenceBinding;
import com.abhiyaan.androidapp.vocabjournal.db.Sentence;

import java.util.List;

/**
 * Created by Binaya Bhattarai on 3/24/2018.
 */

public class SentenceListRecyclerViewAdapter extends
        RecyclerView.Adapter<SentenceListRecyclerViewAdapter.RecyclerViewHolder>{

    public List<Sentence> allSentences;
    private View.OnClickListener onClickListener;

    private RecyclerItemSentenceBinding recyclerItemSentenceBinding;

    public SentenceListRecyclerViewAdapter(List<Sentence> sentences,
                                           View.OnClickListener onClickListener){
        this.allSentences = sentences;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        recyclerItemSentenceBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.recycler_item_sentence, parent, false);
        return new RecyclerViewHolder(recyclerItemSentenceBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.recyclerItemSentenceBinding.setSentence(allSentences.get(position));
        holder.itemView.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return allSentences.size();
    }

    public void updateSentences(List<Sentence> newSentences){
        this.allSentences = newSentences;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        RecyclerItemSentenceBinding recyclerItemSentenceBinding;

        public RecyclerViewHolder(RecyclerItemSentenceBinding binding) {
            super(binding.getRoot());
            recyclerItemSentenceBinding = binding;
        }
    }
}
