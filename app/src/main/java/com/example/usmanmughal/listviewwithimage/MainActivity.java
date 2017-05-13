package com.example.usmanmughal.listviewwithimage;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String[] titles;
    String[] descriptions;
    int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10, R.drawable.image11, R.drawable.image12};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        Resources res = getResources();
        titles = res.getStringArray(R.array.names);
        descriptions = res.getStringArray(R.array.descriptions);

        CustomAdapter adapter = new CustomAdapter(this, titles, descriptions, images);
        listView.setAdapter(adapter);
    }
}

class MyHolder {
    ImageView imageView;
    TextView title;
    TextView desc;

    MyHolder(View v) {
        imageView = (ImageView) v.findViewById(R.id.imageView);
        title = (TextView) v.findViewById(R.id.textViewTitle);
        desc = (TextView) v.findViewById(R.id.textViewDescription);
    }
}

class CustomAdapter extends ArrayAdapter<String> {

    Context context;
    String[] titles;
    String[] descs;
    int[] images;


    CustomAdapter(Context c, String[] titles, String[] descs, int[] images) {
        super(c, R.layout.simple_row, R.id.textViewTitle, titles);
        context = c;
        this.titles = titles;
        this.descs = descs;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        MyHolder holder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.simple_row, parent, false);
            holder = new MyHolder(row);
            row.setTag(holder);
        } else {
            holder = (MyHolder) row.getTag();
        }

        holder.imageView.setImageResource(images[position]);
        holder.title.setText(titles[position]);
        holder.desc.setText(descs[position]);

        return row;
    }
}
