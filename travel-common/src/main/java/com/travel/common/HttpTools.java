package com.travel.common;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpTools {

	private Logger log = Logger.getLogger(this.getClass());
	
	
	/**
	 * 通过get请求往服务器提交数据
	 * 
	 * @param path
	 *            请求路径
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	public boolean get(String path, String param) throws Exception {
		StringBuilder sb = new StringBuilder(path);
		sb.append("?");
		sb.append(param);

		URL url = new URL(sb.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			return true;
		}
		return false;
	}
	public String getString(String path, String param) throws Exception {
		StringBuilder sb = new StringBuilder(path);
		sb.append("?");
		sb.append(param);

		URL url = new URL(sb.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode() == 200) {
			String result="";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line = null;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			return result;
		}
		return "";
	}

	/**
	 * 通过post请求向服务器提交数据 post 请求首先是先把数据写入到缓存。一定要向服务器去获取数据
	 * 
	 * @param path
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public String post(String path, String content) throws Exception {
		String result = "";
		BufferedReader in = null;
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setRequestMethod("POST");

//			StringBuilder sb = new StringBuilder();
//			sb.append(content);
			/*byte[] entity = sb.toString().getBytes("UTF-8");*/

			// 设置请求参数
			conn.setRequestProperty("Content-Type", "application/xml");// 实体参数的类型
			/*conn.setRequestProperty("Content-Length", entity.length + "");// 实体参数的长度
*/			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			// 允许对外输出
			conn.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(conn
                    .getOutputStream(),"UTF-8");
			log.debug(content);
			out.write(content);
			out.flush();  
	        out.close();  
			if (conn.getResponseCode() == 200) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				log.debug("返回内容初始长度：" + result.getBytes().length);
				result = new String(result.getBytes(),"UTF-8");
				log.debug("返回内容解码长度：" + result.getBytes().length);
			}else{
				log.error("网络连接异常：" + conn.getResponseCode());
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error(e.getMessage(),e);
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 通过HttpClient 以get请求向服务器提交数据
	 * 
	 * @param path
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean httpClientGet(String path, String param) throws Exception {

		StringBuilder sb = new StringBuilder(path);
		sb.append("?");
		sb.append(param);

		// 1 得到浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 设置响应时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();

		// 2 指定请求方式
		HttpGet httpGet = new HttpGet(sb.toString());
		httpGet.setConfig(requestConfig);

		// 2 指定请求方式

		// 3执行请求
		HttpResponse httpResponse = httpClient.execute(httpGet);

		// 4判断请求是否成功
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			return true;
		}
		return false;
	}

	/**
	 * 通过httpClient 以post请求向服务器发送数据
	 * 
	 * @param path
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean httpClientByPost(String path, Map<String, String> param,
                                    String paramCharset) throws Exception {

		// 1 得到浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 设置响应时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();

		// 2 指定请求方式
		HttpPost httpPost = new HttpPost(path);
		httpPost.setConfig(requestConfig);
		// 3构建请求实体的数据
		List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
		for (String key : param.keySet()) {
			// log.debug("post参数：" + key + ":" + param.get(key));
			nvpList.add(new BasicNameValuePair(key, param.get(key)));
		}

		// 4 构建实体
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvpList, "utf-8");

		// 5 把实体数据设置到请求对象
		httpPost.setEntity(entity);

		// 6 执行请求
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// 7 判断请求是否成功
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			return true;
		}

		return false;
	}
	
	public String httpClientByPostXml(String path, String xml,
                                      String paramCharset) throws Exception {

		// 1 得到浏览器
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 设置响应时间
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();

		// 2 指定请求方式
		HttpPost httpPost = new HttpPost(path);
		httpPost.setConfig(requestConfig);

		// 4 构建实体
		StringEntity entity = new StringEntity(xml, "text/xml","utf-8");

		// 5 把实体数据设置到请求对象
		httpPost.setEntity(entity);

		// 6 执行请求
		HttpResponse httpResponse = httpClient.execute(httpPost);

		// 7 判断请求是否成功
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			return EntityUtils.toString(httpResponse.getEntity());
		}

		return null;
	}
	
}
