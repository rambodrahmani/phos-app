package com.phos.rambodrahmani.phos;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;

public class MainActivity extends ActionBarActivity
        implements Home.OnFragmentInteractionListener,
        TheProject.OnFragmentInteractionListener,
        Places.OnFragmentInteractionListener,
        TheTeam.OnFragmentInteractionListener,
        Companies.OnFragmentInteractionListener,
        People.OnFragmentInteractionListener,
        EventStream.OnFragmentInteractionListener,
        SplashScreen.OnFragmentInteractionListener,
        NavigationDrawerFragment.NavigationDrawerCallbacks {

    SharedPreferences prefs = null;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            String mData = mBundle.getString("com.parse.Data");
            System.out.println("DATA : xxxxx : " + mData);
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        // update the main content by replacing fragments
        android.app.Fragment fragment = fragment = new SplashScreen();

        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        prefs = getSharedPreferences("com.rambodrahmani.phos", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            Log.d("RR", "Inserting event into user calendar");

            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        android.app.Fragment fragment = null;

        switch (position) {
            case 0:
                return;
            case 1:
                mTitle = getString(R.string.title_section1);
                fragment = new Home();
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                fragment = new TheProject();
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                fragment = new Places();
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                fragment = new TheTeam();
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                fragment = new Companies();
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                fragment = new People();
                break;
            case 7:
                Intent intent = new Intent(this, GameActivity.class);
                startActivity(intent);
                return;
                /*mTitle = getString(R.string.title_section7);
                getSupportActionBar().hide();
                fragment = new TheGame();
                break;*/
            case 8:
                mTitle = getString(R.string.title_section8);
                fragment = new EventStream();
                break;
        }

        getFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

        restoreActionBar();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                break;
            case 8:
                mTitle = getString(R.string.title_section8);
                break;
        }

        restoreActionBar();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
