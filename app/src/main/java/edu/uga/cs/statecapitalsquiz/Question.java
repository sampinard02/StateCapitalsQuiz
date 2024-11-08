package edu.uga.cs.statecapitalsquiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {

    private String stateName;

    private String capital;

    private List<String> quizoptions;

    public Question(String stateName, String capital, String city1, String city2) {

        this.stateName = stateName;

        this.capital = capital;

        this.quizoptions = new ArrayList<>(Arrays.asList(capital, city1, city2));

        Collections.shuffle(this.quizoptions);
    }

    public String getStateName() {

        return stateName;

    }

    public String getCapital() {

        return capital;

    }

    public List<String> getShuffledOptions() {

        return quizoptions;

    }
}

