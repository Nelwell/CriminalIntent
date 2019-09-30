package android.bignerdranch.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Singleton class to store data in memory throughout lifecycle changes only
public class CrimeLab {
    private static CrimeLab sCrimeLab;

    // Initialize Crime list
    private List<Crime> mCrimes;

    // Creates Context object
    public static CrimeLab get(Context context) {
        // If doesn't exist, calls constructor to create new sCrimeLab object
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        // If already exists, returns the instance
        return sCrimeLab;
    }

    // Creates empty Crime list
    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();
        // Creates 100 generic crime objects
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Every other one
            mCrimes.add(crime);
        }
    }

    // Returns Crime list
    public List<Crime> getCrimes() {
        return mCrimes;
    }

    // Returns Crime with given ID
    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimes) {
            if (crime.getId().equals(id)) {
                return crime;
            }
        }
        return null;
    }
}
