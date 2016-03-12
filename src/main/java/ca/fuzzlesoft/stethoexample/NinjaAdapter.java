package ca.fuzzlesoft.stethoexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.fuzzlesoft.stethoexample.NinjaAdapter.ViewHolder;

/**
 * @author mitch
 * @since 3/10/16.
 */
public class NinjaAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<Ninja> ninjas;
    private final OnNinjaTapped ninjaTapped;

    public NinjaAdapter(List<Ninja> ninjas, OnNinjaTapped ninjaTapped) {
        this.ninjas = ninjas;
        this.ninjaTapped = ninjaTapped;
    }

    public void addNinjas(List<Ninja> newNinjas) {
        ninjas.addAll(newNinjas);
        notifyItemRangeInserted(ninjas.size(), newNinjas.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ninja_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ninja ninja = ninjas.get(position);
        holder.name.setText(ninja.getName());
        holder.email.setText(ninja.getEmail());
    }

    @Override
    public int getItemCount() {
        return ninjas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView email;

        public ViewHolder(RelativeLayout layout) {
            super(layout);
            name = (TextView) layout.findViewById(R.id.name);
            email = (TextView) layout.findViewById(R.id.email);
        }
    }
}
