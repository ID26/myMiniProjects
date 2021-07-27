package com.denisov26.solution.aggregator.view;

import com.denisov26.solution.aggregator.Aggregator;
import com.denisov26.solution.aggregator.Controller;
import com.denisov26.solution.aggregator.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = Aggregator.class.getPackage().getName() + "./view/" + this.getClass().getPackage().getName().replaceAll("[.]", "/") + "/vacancies.html";
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String newContent = getUpdatedFileContent(vacancies);
            updateFile(newContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(vacancies.size());
    }

    private void updateFile(String newContent) {
        File file = new File(filePath);
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            fos.write(newContent.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        try {
            Document doc = getDocument();
            Elements templates = doc.getElementsByClass("template");
            Element templatesCopy = templates.clone().removeAttr("style").removeClass("template").get(0);

//            System.out.println("______________________________");
//            System.out.println(templatesCopy);
//            System.out.println("______________________________");

            Elements previewVacancies = doc.getElementsByClass("Vacancy");
            for (Element element : previewVacancies) {
                if (!element.hasClass("template"))
                    element.remove();
            }


            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = templatesCopy.clone();

                Element vacancyLink = vacancyElement.getElementsByAttribute("href").get(0);
                vacancyLink.appendText(vacancy.getTitle());
                vacancyLink.attr("href", vacancy.getUrl());
                Element city = vacancyElement.getElementsByClass("city").get(0);
                city.appendText(vacancy.getCity());
                Element companyName = vacancyElement.getElementsByClass("companyName").get(0);
                companyName.appendText(vacancy.getCompanyName());
                Elements salaryEls = vacancyElement.getElementsByClass("salary");
                Element salary = salaryEls.get(0);
                salary.appendText(vacancy.getSalary());

                templates.before(vacancyElement.outerHtml());
            }
            return doc.html();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Some exception occurred";
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
