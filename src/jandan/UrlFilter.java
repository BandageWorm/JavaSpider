package jandan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFilter {// 本类的方法从html源代码中通过正则匹配链接
	// 每一个ooxx图，如oo>100且oo/xx>5，则爬取图片链接
	public static URLset ooxx(String html) {
		URLset ooxxSet = new URLset();
		String rx = "\"(//[\\S]+?\\.(?:jpg|gif|png))\"[\\s\\S]+?>OO<[\\s\\S]+?>([0-9]+)<[\\s\\S]+?>XX<[\\s\\S]+?>([0-9]+)<";
		Pattern ooxx = Pattern.compile(rx);
		Matcher mox = ooxx.matcher(html);
		while (mox.find()) {
			String img = mox.group(1);
			int oo = Integer.valueOf(mox.group(2));
			int xx = Integer.valueOf(mox.group(3));
			if (oo / (xx + 1) > 5 && oo > 100) {
				ooxxSet.putUrl("http:"+img);
			}
		}
		return ooxxSet;
	}

	// 匹配所有http链接，未使用
	public static URLset getAll(String html) {
		URLset allSet = new URLset();
		Pattern urlPattern = Pattern.compile("\"(http://[\\S]+)\"");
		Matcher matcher = urlPattern.matcher(html);
		while (matcher.find()) {
			String match = matcher.group(1);
			allSet.putUrl(match);
		}
		return allSet;
	}
}