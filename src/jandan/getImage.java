package jandan;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class getImage {// 下载图片

	// 通过图片链接下载图片
	public static void dlImage(String url, String filename) throws IOException {
		filename += url.substring(url.length() - 4, url.length());
		try {
			URL iurl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) iurl.openConnection();
			con.setRequestMethod("GET");
			con.setConnectTimeout(10 * 1000);
			InputStream is = con.getInputStream();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			is.close();
			byte[] btImg = os.toByteArray();
			if (null != btImg && btImg.length > 0) {
				File file = new File("D:\\ooxx", filename);
				if (!file.exists())
					file.createNewFile();
				FileOutputStream fops = new FileOutputStream(file);
				fops.write(btImg);
				fops.flush();
				fops.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 如本地已存在爬取的链接文件，则直接通过其下载
	public static void main(String[] args) throws IOException {
		File md = new File("D:\\OOXX.md");
		BufferedReader bf = new BufferedReader(new FileReader(md));
		String line;
		int i = 1;
		while ((line = bf.readLine()) != null) {
			line = line.substring(4, line.length() - 1);
			getImage.dlImage(line, "" + i++);
		}
		bf.close();
	}
}
