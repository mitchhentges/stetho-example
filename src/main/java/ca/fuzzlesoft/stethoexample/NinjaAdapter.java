package ca.fuzzlesoft.stethoexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import ca.fuzzlesoft.stethoexample.NinjaAdapter.ViewHolder;

/**
 * @author mitch
 * @since 3/10/16.
 */
public class NinjaAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<String> ninjas = Arrays.asList("BOrk", "You are doing me a scare", "neat");

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ninja_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(ninjas.get(position));
    }

    @Override
    public int getItemCount() {
        return ninjas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}
