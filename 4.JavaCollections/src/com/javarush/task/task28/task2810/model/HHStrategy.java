package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";
    private static final String ATTR_KEY = "data-qa";
    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        try {
            int i = 0;
            while (true) {
                Document doc = getDocument(searchString, i++);
                Elements elements = doc.getElementsByAttributeValue(ATTR_KEY, "vacancy-serp__vacancy");
                if (elements.isEmpty()) break;
                for (Element e : elements) {
                    Vacancy vacancy = new Vacancy();
                    Elements title = e.getElementsByAttributeValue(ATTR_KEY, "vacancy-serp__vacancy-title");
                    vacancy.setTitle(title.text());
                    vacancy.setCity(e.getElementsByAttributeValue(ATTR_KEY, "vacancy-serp__vacancy-address").text());
                    vacancy.setCompanyName(e.getElementsByAttributeValue(ATTR_KEY, "vacancy-serp__vacancy-employer").text());
                    vacancy.setSalary(e.getElementsByAttributeValue(ATTR_KEY, "vacancy-serp__vacancy-compensation").text());
                    vacancy.setUrl(title.attr("href"));
                    vacancy.setSiteName("hh.ua");
                    vacancies.add(vacancy);
                }
            }
        } catch (IOException ignore) {
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        return Jsoup.connect(url)
                .userAgent("User-Agent: Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:77.0) Gecko/20100101 Firefox/77.0")
                .referrer("")
                .get();
    }
}
