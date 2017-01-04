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
import java.util.ArrayList;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "http://www.baidu.com/baidu?wd=java";
        String baseUrl = "http://www.baidu.com";
        ArrayList<String> al = allLink(url,baseUrl);
        Iterator<String> iter = al.iterator();
        System.out.println(al.size());
        while (iter.hasNext()){
            downloadP(iter.next());
        }
    }

    public static ArrayList<String> allLink(String url, String baseUrl) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements pages = doc.getElementById("page").getElementsByTag("a");
        ArrayList<String> results = new ArrayList();
        for (Element pg : pages) {
            String page = pg.attr("href");
            if (!page.substring(0, 3).equals("http"))
                page = baseUrl + page;
            results.addAll(getLink(page));
        }
        return results;
    }

    public static void downloadP(String url) throws IOException {

        Document doc = Jsoup.connect(url).userAgent("Mozilla").timeout(10000).get();
        String title = doc.title().replaceAll("[/\\\\:*?<>|]","");
        String path = System.getProperty("user.dir") + "\\baidu\\" + title + "_p.txt";
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        Elements paras = doc.getElementsByTag("p");
        for (Element para : paras) {
            String pText = para.text();
            fw.write(pText + "\n");
            fw.flush();
        }
        fw.close();

    }

    public static void downloadLink(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        String path = System.getProperty("user.dir") + "\\baidu\\" + doc.title() + "_link.txt";
        File file = new File(path);
        FileWriter fw = new FileWriter(file, true);
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            fw.write(linkHref + ", " + linkText + "\n");
            fw.flush();
        }
        fw.close();

    }

    public static ArrayList<String> getLink(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements results = doc.getElementsByClass("t");
        ArrayList<String> out = new ArrayList();
        for(Element result : results){
            String resUrl = result.getElementsByTag("a").attr("href");
            out.add(resUrl);
        }
        File links = new File("clink.txt");
        FileWriter fw = new FileWriter(links,true);
        Iterator<String> iter = out.iterator();
        while (iter.hasNext()) {
            fw.write(iter.next()+"\n");
            fw.flush();
        }
        fw.close();
        return out;
    }
}
