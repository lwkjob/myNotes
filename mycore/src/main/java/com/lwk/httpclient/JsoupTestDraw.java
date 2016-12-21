package com.lwk.httpclient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by lwk on 2016/2/22.
 */
public class JsoupTestDraw {

    public static void main(String[] args) throws Exception{
        String url="http://t.dianping.com/citylist";
        HttpResponse httpResponse=  HttpRequester.sendGet(url, null);
        Document document=Jsoup.parse( httpResponse.getHtml());
        Elements elements= document.select("div.cityes-list");
        for (Element element:elements){

        }
    }
}
