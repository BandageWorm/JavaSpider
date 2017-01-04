package jandan;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

public class spiderIO {// 爬虫IO类
	// 通过url获得html源代码字符串
	public static String getHTML(String strUrl) throws IOException {
		String html = "";
		URL url = new URL(strUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		try {
			InputStreamReader isr = new InputStreamReader(con.getInputStream());
			BufferedReader bfReader = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bfReader.readLine()) != null) {
				sb.append(line);
			}
			html = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return html;
	}

	// 将URLset中的链接输出到markdown文件
	public static void outputFile(URLset set) {
		File f = new File("D:\\ooxx\\OOXX.md");
		HashSet<String> resultSet = set.getSet();
		try {
			if (!f.exists())
				f.createNewFile();
			FileWriter fw = new FileWriter(f);
			Iterator<String> iter = resultSet.iterator();
			while (iter.hasNext()) {
				fw.write("![](" + iter.next() + ")\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 将URLset中的链接直接下载图片
	public static void outputImage(URLset set) throws IOException {
		int i = 1;
		HashSet<String> resultSet = set.getSet();
		Iterator<String> iter = resultSet.iterator();
		while (iter.hasNext()) {
			getImage.dlImage(iter.next(), "" + i++);
		}
	}

	// 将URLset中的链接打印到控制台
	public static void outputPrint(URLset set) {
		HashSet<String> resultSet = set.getSet();
		Iterator<String> iter = resultSet.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
