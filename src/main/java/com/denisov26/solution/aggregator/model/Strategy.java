package com.denisov26.solution.aggregator.model;

import com.denisov26.solution.aggregator.vo.Vacancy;

import java.util.List;

public interface Strategy {
     List<Vacancy> getVacancies(String searchString);
}
