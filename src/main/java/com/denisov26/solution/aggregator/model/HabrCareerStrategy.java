package com.denisov26.solution.aggregator.model;

import com.denisov26.solution.aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HabrCareerStrategy implements Strategy {
//    private static final String URL_FORMAT = "https://career.habr.com/vacancies?page=%s&type=all";
    private static final String URL_FORMAT = "https://career.habr.com/vacancies?q=java+%s&page=%d";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        int page = 0;
        try {
            do {
                Document document = getDocument(searchString, page);
                Elements vacancyElements = document.getElementsByClass("job");
                if (vacancyElements.isEmpty()) break;

                for (Element element : vacancyElements) {
                    Elements title = element.getElementsByClass("title");
                    Elements links = title.get(0).getElementsByTag("a");
                    Elements locations = element.getElementsByClass("location");
                    Elements companyName = element.getElementsByClass("company_name");
                    Elements salary = element.getElementsByClass("count");

                    Vacancy vacancy = new Vacancy();
                    vacancy.setSiteName("career.habr.com");
                    vacancy.setTitle(links.get(0).text());
                    vacancy.setUrl("https://career.habr.com" + links.get(0).attr("href"));
                    vacancy.setCity(locations.size() > 0 ? locations.get(0).text() : "");
                    vacancy.setCompanyName(companyName.get(0).text());
                    vacancy.setSalary(salary.size() > 0 ? salary.get(0).text() : "");

                    vacancies.add(vacancy);
                }
                page++;
            } while (true);


            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document doc = Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36").referrer("https://career.habr.com/").get();
        return doc;
    }
}
