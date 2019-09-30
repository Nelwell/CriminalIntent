package android.bignerdranch.criminalintent;

import androidx.fragment.app.Fragment;

// Main Activity
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
