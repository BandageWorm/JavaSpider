package baidu;

/**
 * Created by kurtg on 17/1/4.
 * Test Jsoup
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
        public static void main(String[] args){
            try {
                File html = new File("html.txt");
                File flink = new File("links.txt");
                Document doc = Jsoup.connect("http://www.baidu.com/s?wd=java").get();
                FileWriter fwhtml = new FileWriter(html);
                fwhtml.write(doc.html());
                fwhtml.close();
                Elements links = doc.getElementsByTag("a");
                FileWriter fwlink = new FileWriter(flink);
                for (Element link : links){
                    String linkHref = link.attr("href");
                    String linkText = link.text();
                    fwlink.write(linkHref+", "+linkText+"\n");
                    fwlink.flush();
                }
                fwlink.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
}
