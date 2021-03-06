package com.developer.gdgvit.leaveapp;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.test.suitebuilder.annotation.Suppress;
import android.util.Log;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import com.afollestad.materialdialogs.MaterialDialog;
import com.developer.gdgvit.leaveapp.syncAdaptors.LeaveAppSyncAdapter;


public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceClickListener{

    private static final boolean ALWAYS_SIMPLE_PREFS = false;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Intent i;
    int j;
    String pass;
    SQLiteOpenHelper mOpenHelper;
    public static final String table = "leave";
    SQLiteDatabase db;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.login_prefs);

        bindPreferenceSummaryToValue(findPreference(getString(R.string.reg_no_key)));

        Preference logOutBtn =  findPreference("logOutBtn");
        logOutBtn.setOnPreferenceClickListener(this);

        Preference license =  findPreference("license");
        license.setOnPreferenceClickListener(this);

        Preference feedback =  findPreference("feedback");
        feedback.setOnPreferenceClickListener(this);




        setupActionBar();


    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar();
            // Show the Up button in the action bar.

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            // TODO: If Settings has multiple levels, Up should navigate up
            // that hierarchy.
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        setupSimplePreferencesScreen();
    }

    /**
     * Shows the simplified settings UI if the device configuration if the
     * device configuration dictates that a simplified, single-pane UI should be
     * shown.
     */
    private void setupSimplePreferencesScreen() {
        if (!isSimplePreferences(this)) {
            return;
        }


    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this) && !isSimplePreferences(this);
    }

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Determines whether the simplified settings UI should be shown. This is
     * true if this is forced via {@link #ALWAYS_SIMPLE_PREFS}, or the device
     * doesn't have newer APIs like {@link PreferenceFragment}, or the device
     * doesn't have an extra-large screen. In these cases, a single-pane
     * "simplified" settings UI should be shown.
     */
    private static boolean isSimplePreferences(Context context) {
        return ALWAYS_SIMPLE_PREFS
                || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB
                || !isXLargeTablet(context);
    }


@Override
@SuppressWarnings("deprecation")
    public boolean onPreferenceClick(Preference preference) {
        Log.i(LeaveAppClass.Log_Tag,"onPreferenceClick called");
        if(preference.getKey().equals("logOutBtn")){
            Log.i(LeaveAppClass.Log_Tag,"if called");
            pref = PreferenceManager.getDefaultSharedPreferences(this);
            editor = pref.edit();
            editor.clear();
            editor.commit();
            dropTable();
            restart();
        }
    else if(preference.getKey().equals("license")){
            showBasicLongContent();
        }
    else if(preference.getKey().equals("feedback")){
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_EMAIL,"hello@gdgvitvellore.com");
            sendIntent.putExtra(Intent.EXTRA_SUBJECT,"LeaveApp Feedback");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Feedback/Bug");
            startActivity(Intent.createChooser(sendIntent, "Feedback"));
        }
        return true;
    }

    private void dropTable() {
        db = mOpenHelper.getWritableDatabase();
        final String sql = "drop table " + table;
        Log.i(LeaveAppClass.Log_Tag,"drop table called");
        try {
            Log.e(LeaveAppClass.Log_Tag,"drop table called");
            db.execSQL(sql);
            setTitle(sql);
        } catch (SQLException e) {
            Log.e(LeaveAppClass.Log_Tag,"drop table called in catch block");
            setTitle("exception");
        }catch (NullPointerException npe){
            Log.e(LeaveAppClass.Log_Tag,"null pointer exception npe");
        }
    }

    private void showBasicLongContent() {
        new MaterialDialog.Builder(this)
                .title(R.string.license_name)
                .content(R.string.mit_license)
                .positiveText(R.string.done)
                .show();
    }

    public void restart(){
        i = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage( getBaseContext().getPackageName() );
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }


    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();



            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            }else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };






    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */

}