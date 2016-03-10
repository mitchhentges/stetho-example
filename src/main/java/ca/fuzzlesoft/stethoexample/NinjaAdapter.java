package ca.fuzzlesoft.stethoexample;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import ca.fuzzlesoft.stethoexample.NinjaAdapter.ViewHolder;

/**
 * @author mitch
 * @since 3/10/16.
 */
public class NinjaAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final List<Ninja> ninjas = Arrays.asList(
            new Ninja("Batman McBatmannerson", "bruce@wayne.hooli.xyz"),
            new Ninja("Agron Kabashi", "agron.kabashi@tretton37.com"),
            new Ninja("Alexander Klintström", "alexander.klintstrom@tretton37.com"),
            new Ninja("Alexandra Andersson", "alexandra.andersson@tretton37.com"),
            new Ninja("Anastasiia Anastasis", "anastasiia.anastasis@tretton37.com"),
            new Ninja("Anders Ringqvist", "anders.ringqvist@tretton37.com"),
            new Ninja("Andreas Håkansson", "andreas.hakansson@tretton37.com"),
            new Ninja("Andreas Nilsson", "andreas.nilsson@tretton37.com"),
            new Ninja("Andreas Voigt", "andreas.voigt@tretton37.com"),
            new Ninja("Artem Konovalenkov", "artem.konovalenkov@tretton37.com"),
            new Ninja("Balazs Gobel", "balazs.gobel@tretton37.com"),
            new Ninja("Balazs Suhajda", "balazs.suhajda@tretton37.com")
    );

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
