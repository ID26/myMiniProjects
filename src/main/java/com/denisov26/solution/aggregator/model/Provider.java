package com.denisov26.solution.aggregator.model;

import com.denisov26.solution.aggregator.vo.Vacancy;

import java.util.List;

public class Provider {
    private Strategy strategy;

    public List<Vacancy>  getJavaVacancies(String searchString) {
        return strategy.getVacancies(searchString);
    }

    public Provider(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
