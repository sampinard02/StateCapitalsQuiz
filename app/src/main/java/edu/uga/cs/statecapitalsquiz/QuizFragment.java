package edu.uga.cs.statecapitalsquiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

public class QuizFragment extends Fragment {

    private TextView questionText;

    private RadioGroup choicesGroup;

    private RadioButton choice1, choice2, choice3;

    private Button nextButton;

    private List<Question> questions;

    private int currentQuestionIndex = 0;

    private int score = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        questionText = view.findViewById(R.id.questionText);

        choicesGroup = view.findViewById(R.id.choicesGroup);

        choice1 = view.findViewById(R.id.choice1);

        choice2 = view.findViewById(R.id.choice2);

        choice3 = view.findViewById(R.id.choice3);

        nextButton = view.findViewById(R.id.nextButton);

        DatabaseHelper dbHelper = new DatabaseHelper(getContext());

        new PopulateDatabaseTask(getContext(), dbHelper) {
            @Override

            protected void onPostExecute(Void result) {

                super.onPostExecute(result);

                loadQuestionsFromDatabase(dbHelper);
            }
        }.execute();

        nextButton.setOnClickListener(v -> nextQuestion());

        return view;
    }

    private void loadQuestionsFromDatabase(DatabaseHelper dbHelper) {

        questions = dbHelper.getRandomQuestions(6);

        initializeQuestions(questions);
    }

    private void initializeQuestions(List<Question> loadedQuestions) {

        questions = loadedQuestions;

        if (questions != null && !questions.isEmpty()) {

            displayQuestion();

        }

    }

    private void displayQuestion() {

        if (questions == null || currentQuestionIndex >= questions.size()) {

            return;

        }

        Question question = questions.get(currentQuestionIndex);

        questionText.setText("What is the capital of " + question.getStateName() + "?");

        List<String> options = question.getShuffledOptions();

        choice1.setText(options.get(0));

        choice2.setText(options.get(1));

        choice3.setText(options.get(2));

        choicesGroup.clearCheck();
    }

    private void checkAnswer() {

        int selectedId = choicesGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {

            RadioButton selectedButton = getView().findViewById(selectedId);

            String selectedAnswer = selectedButton.getText().toString();

            if (selectedAnswer.equals(questions.get(currentQuestionIndex).getCapital())) {
                score++;
            }
        }
    }

    private void nextQuestion() {

        checkAnswer();

        if (currentQuestionIndex < questions.size() - 1) {

            currentQuestionIndex++;

            displayQuestion();

        } else {

            Bundle result = new Bundle();

            result.putInt("score", score);

            result.putInt("totalQuestions", questions.size());

            NavHostFragment.findNavController(QuizFragment.this)
                    .navigate(R.id.action_quizFragment_to_resultsFragment, result);
        }
    }
}

