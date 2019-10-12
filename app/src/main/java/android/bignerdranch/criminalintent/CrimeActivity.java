package android.bignerdranch.criminalintent;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

// Superclass changed to SingleFragmentActivity
public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "android.bignerdranch.criminalintent.crime_id";

    // Passes crime ID as an Intent extra when this activity is started
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        // Passes in a String key and Serializable value
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        // Retrieves extra from intent, passes into CrimeFragment new instance
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);

    }
}
