package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private final String filePath = String.format(
            "./4.JavaCollections/src/%s/vacancies.html",
            getClass()
                    .getPackage()
                    .getName()
                    .replace(".", File.separator)
    );
    private Controller controller;

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String fileName = getUpdatedFileContent(vacancies);
            updateFile(fileName);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod(){
        controller.onCitySelect("Odessa");
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document doc = null;
        try {
            doc = getDocument();
            Element template = doc.getElementsByClass("template").first();
            Element temp = template.clone();
            temp.removeAttr("style");
            temp.removeClass("template");

            doc
                    .getElementsByAttributeValueEnding("class", "vacancy")
                    .remove();

            vacancies.forEach(vacancy -> {
                Element newTemplate = temp.clone();
                newTemplate
                        .getElementsByClass("city")
                        .first()
                        .text(vacancy.getCity());
                newTemplate
                        .getElementsByClass("companyName")
                        .first()
                        .text(vacancy.getCompanyName());
                newTemplate
                        .getElementsByClass("salary")
                        .first()
                        .text(vacancy.getSalary());

                Element tagA = newTemplate.getElementsByTag("a").first();
                tagA.text(vacancy.getTitle());
                tagA.attr("href", vacancy.getUrl());
                template.before(newTemplate.outerHtml());
            });
        } catch (Exception e){
            e.printStackTrace();
            return "Some exception occurred";
        }
        return doc.html();
    }

    private void updateFile(String data) throws IOException{
        try(FileWriter writer = new FileWriter(filePath)){
            writer.write(data);
        }
    }
}
