package com.denisov26.solution.aggregator.view;

import com.denisov26.solution.aggregator.Controller;
import com.denisov26.solution.aggregator.vo.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
