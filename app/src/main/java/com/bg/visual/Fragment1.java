package com.bg.visual;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;

/**
 * A simple {@link Fragment1} subclass.
 */
public class Fragment1 extends Fragment {

    ArrayList<String> names = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment, container, false);

        ListView lvMain = (ListView) view.findViewById(R.id.lvMain);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),R.layout.my_list_item, names);
        lvMain.setAdapter(adapter);

        final EditText editText = (EditText) view.findViewById(R.id.TextList);

        // Button button = (Button) view.findViewById(R.id.button2);
        //final TextView textView = (TextView) view.findViewById(R.id.textView);
        Button button = (Button) view.findViewById(R.id.btnAddList);
        editText.setText(" ");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names.add(0,editText.getText().toString());
                adapter.notifyDataSetChanged();

            }
        });

        return view;

    }
}
