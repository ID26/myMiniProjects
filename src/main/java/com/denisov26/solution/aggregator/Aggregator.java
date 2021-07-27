package com.denisov26.solution.aggregator;

import com.denisov26.solution.aggregator.model.HHStrategy;
import com.denisov26.solution.aggregator.model.HabrCareerStrategy;
import com.denisov26.solution.aggregator.model.Model;
import com.denisov26.solution.aggregator.model.Provider;
import com.denisov26.solution.aggregator.view.HtmlView;

public class Aggregator {
    public static void main(String[] args) {
        HtmlView view = new HtmlView();
        Provider provider = new Provider(new HHStrategy());
        Provider provider1 = new Provider(new HabrCareerStrategy());
        Provider [] providerList = new Provider[]{provider, provider1};
        Model model = new Model(view, providerList);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();
    }
}
