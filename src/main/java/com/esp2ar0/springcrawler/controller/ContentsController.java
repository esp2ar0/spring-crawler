package com.esp2ar0.springcrawler.controller;

import com.esp2ar0.springcrawler.domain.Contents;
import com.esp2ar0.springcrawler.domain.ContentsRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ContentsController {

    @Autowired
    private ContentsRepository contentsRepository;

    @GetMapping("/")
    public String home(Model model) {
        Contents contents = new Contents();
        contents.setTitle("title");
        contents.setPrice("100");
        contents.setUrl("http://naver.com");

        Contents contents2 = new Contents();
        contents2.setTitle("title2");
        contents2.setPrice("100");
        contents2.setUrl("d");

        contentsRepository.save(contents);
        contentsRepository.save(contents2);

        model.addAttribute("contents", contentsRepository.findAll());
        return "index";
    }

    public void crawler() {
        Contents contents = new Contents();
        String url = "https://search.naver.com/search.naver?where=articlec&query=2018%20%EB%A7%A5%EB%B6%81%ED%94%84%EB%A1%9C%2015%EC%9D%B8%EC%B9%98&ie=utf8&st=date&date_option=1&date_from=20190129162000&date_to=20190130235959&board=&srchby=text&dup_remove=1&cafe_url=&without_cafe_url=&sm=tab_opt&nso=so%3Add%2Cp%3A1d%2Ca%3Aall&t=0&mson=0&prdtype=4";

        Document document = null;
        try {
            document = Jsoup.connect(url + "&start=1").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = document.select("ul.type01");

        for (Element dl : elements.select("dl")) {
            contents.setTitle(dl.select("dt").text());
            contents.setPrice(dl.select("dd.cafe_item_price").text());
            contents.setUrl(dl.select("a.url").attr("href"));
            //dl.select("dd.txt_inline").text();
        }

    }
}
