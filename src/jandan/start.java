package jandan;

import java.io.IOException;

public class start {
	public static void main(String[] args) throws IOException {
		URLset ooxx = new URLset();
		String html = "";
		for (int i = 2287; i > 2240; i--) {
			html = spiderIO.getHTML("http://jandan.net/ooxx/page-" + i + "#comments");
			ooxx = UrlFilter.ooxx(html);
		}
		spiderIO.outputImage(ooxx);
	}

}
