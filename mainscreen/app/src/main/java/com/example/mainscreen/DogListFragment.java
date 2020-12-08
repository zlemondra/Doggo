package com.example.mainscreen;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.Objects;

//import com.example.mainscreen.dummy.DummyContent;

/**
 * A fragment representing a list of Dogs.
 */
public class DogListFragment extends ListFragment {
    //Class Variables
    ListView viewDogs;
    ItemSelected activity;
    Intent intent;
    SQLiteDatabase ggdDatabase;
    Cursor cursor;
    ArrayList<String> dogNames;

    public interface ItemSelected   {
        void onItemSelected(int index);
    }//End of interface itemSelected

    public DogListFragment() {
        // Required empty public constructor
    }//End of default constructor method

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (ItemSelected) context;
    }//End of method onAttach

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        dogNames = getArguments().getStringArrayList("names");

        //Add values to the list
        setListAdapter(new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, dogNames));
    }//End of method onActivityCreated

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        activity.onItemSelected(position);
    }//End of method onListItemClick

}//End of fragment DogListFragment