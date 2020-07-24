package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new ArrayList<>();
        String siteName = "https://moikrug.ru";
        try {
            int i = 0;
            while (true) {
                Document doc = getDocument(searchString, i++);
                Elements elements = doc.getElementsByAttributeValue("class", "job");
                elements.addAll( doc.getElementsByAttributeValue("class", "job marked"));
                if (elements.isEmpty()) break;
                for (Element element : elements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.getElementsByClass("title").first().text().trim());
                    vacancy.setSalary(element.getElementsByClass("salary").first().text().trim());
                    vacancy.setCity(element.select("span[class=location]").text().trim());
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(siteName + element.getElementsByClass("title").first().getElementsByTag("a").attr("href").trim());
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
