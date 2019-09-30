package android.bignerdranch.criminalintent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    // Method to instantiate fragment instances
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // Gets fragment manager from support library
        FragmentManager fm = getSupportFragmentManager();
        // Retrieves CrimeFragment by calling its container view ID
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // Checks if fragment is already in list
        if (fragment == null) {
            // Creates fragment
            fragment = createFragment();
            // Creates fragment transaction
            fm.beginTransaction()
                    // Adds crime fragment view to list of fragments by referencing its FrameLayout ID
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
