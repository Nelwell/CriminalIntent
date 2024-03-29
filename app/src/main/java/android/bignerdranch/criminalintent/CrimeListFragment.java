package android.bignerdranch.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        // Gets reference to crime_list_fragment attribute ID for recycler view in layout file
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        // Calls layout manager object to linearly display items in list
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    // Overrides onResume() and instead calls updateUI method to reload list with newest data
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    // Connects Adapter to RecyclerView
    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        // Checks if CrimeAdapter is already set up
        if (mAdapter == null) {
            // new CrimeAdapter
            mAdapter = new CrimeAdapter(crimes);
            // Sets Adapter on RecyclerView
            mCrimeRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    // ViewHolder that will inflate list_item_crime layout, and pull out TextViews to be bound
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Crime mCrime;

        // Initialized member variables
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;

        // CrimeHolder constructor, passes list_item_crime into super ViewHolder
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            // Super ViewHolder constructor
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            // Listener implemented by CrimeHolder on the View (itemView), returns event to CrimeHolder
            itemView.setOnClickListener(this);

            // Gets references to resource IDs
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mSolvedImageView = itemView.findViewById(R.id.crime_solved);
        }

        // Called each time a new Crime needs displayed in CrimeHolder
        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            // Sets visibility of crime solved image based on if "is solved" box is checked
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onClick(View view) {
            // Passes crimeID from the newIntent method created in CrimeActivity
            Intent intent = CrimeActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        // Method overrides implemented below
        @NonNull
        @Override
        // Called by RecyclerView when ViewHolder is needed to display items with
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Constructs new CrimeHolder for RecyclerView
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        // Called when RecyclerView needs CrimeHolder to be bound to a given crime
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime); // Binds crime to CrimeHolder
        }

        @Override
        public int getItemCount() {
            return mCrimes.size(); // # of crimes
        }
    }
}
