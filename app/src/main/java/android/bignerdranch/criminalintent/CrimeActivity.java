package android.bignerdranch.criminalintent;

import androidx.fragment.app.Fragment;

// Superclass changed to SingleFragmentActivity
public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}
