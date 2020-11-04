package com.example.mainscreen;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.Objects;
//import com.example.mainscreen.dummy.DummyContent;
import java.util.ArrayList;
import java.util.Objects;

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
        setListAdapter(new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, dogNames));
    }//End of method onActivityCreated

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        activity.onItemSelected(position);
    }//End of method onListItemClick

}//End of fragment DogListFragment