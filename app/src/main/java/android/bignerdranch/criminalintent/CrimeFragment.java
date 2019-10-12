package android.bignerdranch.criminalintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";

    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    // Argument Bundle created. Is called when needed to create a CrimeFragment
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        // Creates fragment instance and attaches arguments to the fragment
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieves CrimeId from fragment arguments
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        // Fetches Crime from CrimeLab and stores in variable
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        // Gets reference to string resource
        mTitleField = v.findViewById(R.id.crime_title);
        // Displays Crime's title in crimeFragment
        mTitleField.setText(mCrime.getTitle());
        // Listener for EditText TitleField
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            // Initiates upon text being edited
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        // Gets reference to button's string resource
        mDateButton = v.findViewById(R.id.crime_date);
        // Converts date to string and sets text to button
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false); // Disables button, for now

        // Gets reference to checkbox's string resource
        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        // Displays Crime's solved status
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        // CheckBox listener to determine if checked or not
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked); // Sets checked status to new crime
            }
        });

        return v;

    }
}
