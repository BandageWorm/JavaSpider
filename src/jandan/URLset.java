package jandan;

import java.util.HashSet;

public class URLset {
	private static HashSet<String> visitedUrl = new HashSet<String>();

	public void putUrl(String url) {
		if (url != null || !visitedUrl.contains(url)) {
			visitedUrl.add(url);
		}
	}

	public HashSet<String> getSet() {
		return visitedUrl;
	}

}
