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


public class ProductsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.productsfragment, container, false);

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
        final ArrayList<Product> AndroidFlavors = new ArrayList<Product>();
        AndroidFlavors.add(new Product("T- Shirt  €5.00 +VAT", "XS,S,M,L,XL,XXL", drawable.tshirt));
        AndroidFlavors.add(new Product("Hoodie    €10.00 +VAT", "XS,S,M,L,XL,XXL", drawable.hoodie));
        AndroidFlavors.add(new Product("Polo  €6.50 +VAT", "XS,S,M,L,XL,XXL", drawable.polo));
        AndroidFlavors.add(new Product("Vest/Tank  €5.75 +VAT", "XS,S,M,L,XL,XXL", drawable.vest));
        AndroidFlavors.add(new Product("Ball Cap   €12.00 +VAT", "Adjustable", drawable.ballcap));


        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        final ProductAdapter flavorAdapter = new ProductAdapter(getActivity(), AndroidFlavors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        final ListView listView = (ListView) root.findViewById(id.listview_product);
        listView.setAdapter(flavorAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Below we have the toast method and we are trying to parse the array into the new variable item.
             * AndroidFlavor(String vName, String vNumber, int imageResourceId)
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             **/
            @Override

            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                TextView textView = (TextView) getActivity().findViewById(R.id.version_name);
                //Display a Toast message indicting the selected item
                Toast.makeText(getActivity(),textView.getText(),
                        Toast.LENGTH_LONG)
                        .show();
            }


        });

    return root;}
}


