import com.mashape.unirest.http.Unirest;
import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import  java.io.PrintWriter;
import java.io.IOException;
import java.net.URLEncoder;

public class AsinSubclass {

    public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";

    static {
        //Change the default user-agent header
        Unirest.setDefaultHeader("User-Agent", USER_AGENT);

        //proxy
        Unirest.setProxy(new HttpHost("138.68.156.100", 8118));
    }

    public static void sClass(String asin) throws IOException {

        File file = new File("AsinLevelDetails.txt");

        /*if (!file.exists()) {
            file.createNewFile();
        }*/

        try {

            Document page = Jsoup.connect("https://www.amazon.com/dp/" + URLEncoder.encode(asin, "UTF-8")).timeout(30000).userAgent(USER_AGENT).get();
            PrintWriter out = new PrintWriter(new FileWriter(file, true));

            Elements title = page.select("#productTitle");
            String titleText = title.text();

            Elements price = page.select("#priceblock_ourprice");
            String priceText = price.text();

            Elements fulfilledBy = page.select("#SSOFpopoverLink");

            String fulfilledByText = fulfilledBy.text();

            Elements fulfilledBy1 = page.select("#merchant-info");

            String fulfilledByText1 = fulfilledBy1.text();

            Elements soldBy = page.select("#merchant-info > a:nth-child(1)");

            String soldByText = soldBy.text();


            //System.out.println("The title --> " + titleText + "\n" + "Price:" + priceText + "\n" + fulfilledByText + "\n" + soldByText);
            //System.out.printf("Title: %s\n Price: %s\n %s\n\n",titleText,priceText,fulfilledByText1);
            out.append("TITLE: " + titleText + " \n " + "Price: " + priceText + " \n " + fulfilledByText1 +" \n \n ");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

//for mini filters

//#result_0 > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div:nth-child(1) > a
//#result_2 > div > div.a-fixed-left-grid > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div:nth-child(1) > a
//#result_3 > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div:nth-child(1) > a
//#result_2 > div > div.a-fixed-left-grid > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div:nth-child(1) > a

//#SSOFpopoverLink
//#merchant-info > a:nth-child(1)
//#merchant-info