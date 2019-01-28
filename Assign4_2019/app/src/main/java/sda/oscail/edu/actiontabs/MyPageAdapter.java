package sda.oscail.edu.actiontabs;
/**
 * Created by ckirwan on 18/06/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPageAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;
    public static final int HOME = 0;
    public static final int PRODUCTS = 1;
    public static final int ORDERS = 2;
    public static final int COLLECTION = 3;
    public static final String UI_TAB_HOME = "HOME";
    public static final String UI_TAB_PRODUCTS = "PRODUCTS";
    public static final String UI_TAB_ORDERS = "ORDERS";
    public static final String UI_TAB_COLLECTION= "COLLECTION";


    public MyPageAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position)
        {
            case HOME:
                HomeFragment chatTab = new HomeFragment();
                return chatTab;
            case PRODUCTS:
                ProductsFragment findTab = new ProductsFragment();
                return findTab;
            case ORDERS:
                OrderFragment meetTab = new OrderFragment();
                return meetTab;
            case COLLECTION:
                CollectionFragment partyTab = new CollectionFragment();
                return partyTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case HOME:
                return UI_TAB_HOME;
            case PRODUCTS:
                return UI_TAB_PRODUCTS;
            case ORDERS:
                return UI_TAB_ORDERS;
            case COLLECTION:
                return UI_TAB_COLLECTION;
            default:
                break;
        }
        return null;
    }



}