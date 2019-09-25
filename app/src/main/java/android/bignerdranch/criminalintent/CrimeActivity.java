package android.bignerdranch.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class CrimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        // Gets fragment manager from support library
        FragmentManager fm = getSupportFragmentManager();
        // Retrieves CrimeFragment by calling its container view ID
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // Checks if fragment is already in list
        if (fragment == null) {
            // New CrimeFragment created
            fragment = new CrimeFragment();
            // Creates fragment transaction
            fm.beginTransaction()
                    // Adds crime fragment view to list of fragments by referencing its FrameLayout ID
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
