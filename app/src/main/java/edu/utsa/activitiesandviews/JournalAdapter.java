package edu.utsa.activitiesandviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class JournalAdapter extends ArrayAdapter<Journal> {
    public JournalAdapter(Context context, List<Journal> journals) { super(context, 0, journals); }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Journal journal = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.journal_cell, parent,false);

        TextView title = convertView.findViewById(R.id.cellTitle);
        TextView desc = convertView.findViewById(R.id.cellDesc);

        title.setText(journal.getTitle());
        desc.setText(journal.getDescription());

        return convertView;
    }
}