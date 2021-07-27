package com.denisov26.solution.aggregator;

import com.denisov26.solution.aggregator.model.Model;

public class Controller {

    private Model model;

    public Controller(Model model) {
        if (model == null) throw new IllegalArgumentException();
        this.model = model;
    }

    public void onCitySelect(String cityName) {
        model.selectCity(cityName);
    }
    //    private Provider [] providers;

//    public Controller(Provider ... providers) {
//        if (providers.length == 0)
//            throw new IllegalArgumentException();
//        this.providers = providers;
//    }

//    @Override
//    public String toString() {
//        return "Controller{" +
//                "providers=" + Arrays.toString(providers) +
//                '}';
//    }

//    public void scan() {
//        List<Vacancy> vacancies = new ArrayList<>();
//        for (Provider provider : providers) {
//            vacancies.addAll(provider.getJavaVacancies("Rostov-On-Don"));
//        }
//        System.out.println(vacancies.size());
//    }
}
