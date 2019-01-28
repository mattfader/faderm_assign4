package sda.oscail.edu.actiontabs;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import static sda.oscail.edu.actiontabs.R.*;

public class CollectionFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(layout.collectionfragment, container, false);

        /*
         * Copyright (C) 2016 The Android Open Source Project
         *
         * Licensed under the Apache License, Version 2.0 (the "License");
         * you may not use this file except in compliance with the License.
         * You may obtain a copy of the License at
         *
         *      http://www.apache.org/licenses/LICENSE-2.0
         *
         * Unless required by applicable law or agreed to in writing, software
         * distributed under the License is distributed on an "AS IS" BASIS,
         * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
         * See the License for the specific language governing permissions and
         * limitations under the License.
         */


/**
 * {@link FlavorActivity} shows a list of Android platform releases.
 * For each release, display the name, version number, and image.
 * @version 1.1 this has since been modified to display wearable merchandise like t-shirts, and caps
 * the titles for the orginial array are still intact but values have been altered.
 */


        AdapterView listview;
        ArrayAdapter<String>[] listAdapter;
        AdapterView.OnItemClickListener onItemClickListener;
        //@Override
        //protected void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(layout.productsfragment);


        // Create an ArrayList of AndroidFlavor objects
        final ArrayList<Stores> stores = new ArrayList<Stores>();
        stores.add(new Stores("Castleknock", "01-822-0001", "1 Castleknock rd, Dublin 15"));
        stores.add(new Stores("Swords", "01-822-0001", "1 Castleknock rd, Dublin 15"));
        stores.add(new Stores("Rathcool", "01-822-0001", "1 Castleknock rd, Dublin 15"));
        stores.add(new Stores("Dun Laoghaire", "01-822-0001", "1 Castleknock rd, Dublin 15"));
        stores.add(new Stores("Lucan", "01-822-0001", "1 Castleknock rd, Dublin 15"));


        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        final CollectionAdapter storeAdapter = new CollectionAdapter(getActivity(), stores);

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) root.findViewById(id.listview_collection);
        listView.setAdapter(storeAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Below we have the toast method and we are trying to parse the array into the new variable item.
             * Store(String vName, String vNumber, int imageResourceId)
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             **/
            @Override

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                TextView textView = (TextView)root.findViewById(R.id.store);
                 //Display a Toast message indicting the selected item
                        Toast.makeText(getActivity(),textView.getText(),
                        Toast.LENGTH_LONG)
                        .show();
            }


        });

        return root;}
}





