import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.IOException;
import java.net.URLEncoder;

public class jsoupSubClass1 {

    public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";

    static {
        //Change the default user-agent header
        Unirest.setDefaultHeader("User-Agent", USER_AGENT);

        //proxy
        Unirest.setProxy(new HttpHost("138.68.156.100", 8118));
    }

    public static void sClass1(String keyword) throws IOException {

        File file = new File("Amazonfinalresult1.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        try {

            Document page = Jsoup.connect("https://www.amazon.com/s/?url=search-alias%3Daps&field-keywords=" + URLEncoder.encode(keyword, "UTF-8")).timeout(30000).userAgent(USER_AGENT).get();
            PrintWriter out = new PrintWriter(new FileWriter(file, true));
            //new FileWriter("Amazonfinalresult1.txt", true)
            for (Element searchResult : page.select("li.s-result-item > div > div.a-row.a-spacing-none.s-color-subdued > div.a-row.a-spacing-micro > a")) {

                String url = searchResult.attr("href");
                String title = searchResult.attr("title");

                System.out.println("TITLE: " + title + "; " + "\n" + "URL: " + url + "\n");

                out.append("TITLE: " + title + " \n " + "URL: " + url + " \n " + " \n ");
            }

            for(Element asinSelector:page.select("li.s-result-item > div > div.a-row.a-spacing-none.s-color-subdued > div.a-row.a-spacing-top-micro.a-spacing-none > span")) {

                String asin = asinSelector.attr("name");

                System.out.println(asin);

                out.append(asin + "\n");

            } out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

//for keen/clothing
