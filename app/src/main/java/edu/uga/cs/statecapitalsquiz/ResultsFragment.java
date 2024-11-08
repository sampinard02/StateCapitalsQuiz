package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class ResultsFragment extends Fragment {

    private TextView resultText;

    private Button backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_results, container, false);

        resultText = view.findViewById(R.id.resultText);

        backButton = view.findViewById(R.id.backButton);

        Bundle args = getArguments();

        if (args != null) {

            int score = args.getInt("score", 0);

            int totalQuestions = args.getInt("totalQuestions", 0);

            resultText.setText("Your score: " + score + " out of " + totalQuestions);
        }

        backButton.setOnClickListener(v -> navigateToMainScreen());

        return view;
    }

    private void navigateToMainScreen() {

        NavHostFragment.findNavController(ResultsFragment.this)

                .navigate(R.id.action_resultsFragment_to_splashFragment);
    }
}
