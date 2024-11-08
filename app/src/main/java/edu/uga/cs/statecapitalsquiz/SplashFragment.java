package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SplashFragment extends Fragment {

    private Button startQuizButton;
    private Button viewResultsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_splash, container, false);

        startQuizButton = view.findViewById(R.id.startQuizButton);

        viewResultsButton = view.findViewById(R.id.viewResultsButton);

        startQuizButton.setOnClickListener(v -> startQuiz());

        viewResultsButton.setOnClickListener(v -> showResults());

        return view;
    }

    private void startQuiz() {

        NavHostFragment.findNavController(SplashFragment.this)

                .navigate(R.id.action_splashFragment_to_quizFragment);
    }

    private void showResults() {

        NavHostFragment.findNavController(SplashFragment.this)

                .navigate(R.id.action_splashFragment_to_resultsFragment);
    }
}
