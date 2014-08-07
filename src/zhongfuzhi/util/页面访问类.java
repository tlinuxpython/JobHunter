package zhongfuzhi.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class 页面访问类 {
	/**
	 * 访问网址并返回网页内容
	 */
	public static String 读取(String 网址) {
		try {
			URL url = new URL(网址);
			HttpURLConnection 连接 = (HttpURLConnection) url.openConnection();
			连接.connect();
			// 读取网页内容
			InputStream 输入流 = 连接.getInputStream();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			int readByte = -1;
			while ((readByte = 输入流.read()) >= 0) {
				byteArrayOutputStream.write(readByte);
			}
			连接.disconnect();
			return new String(byteArrayOutputStream.toByteArray(), "utf-8");
			// return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
