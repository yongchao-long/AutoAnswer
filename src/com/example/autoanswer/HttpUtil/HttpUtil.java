package com.example.autoanswer.HttpUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import org.apache.http.HttpConnection;

import android.util.Log;

import com.example.autoanswer.been.Result;
import com.example.autoanswer.been.message;
import com.example.autoanswer.been.message.Type;
import com.google.gson.Gson;

public class HttpUtil {

	private static final String URL = "http://www.tuling123.com/openapi/api";
	private static final String key = "93275753c1bbc3671b941437b133b40c";
	
	/*
	 * 发送一个消息，得到返回的一个消息
	 */
	public static message sendMessage(String msg){
		
		message message = new message();
		String jsonRes = DoGet(msg);
		Gson gson = new Gson();
		Result result = null;
		try {
			result = gson.fromJson(jsonRes, Result.class);
			message.setMsg(result.getText());
		} catch (Exception e) {
			message.setMsg("服务器繁忙，请稍后再试");
		}
		
		message.setDate(new Date());
		message.setType(Type.INCOMING);
		return message;
	}
	
	public static String DoGet(String Msg){
		
		String result = "";
		
		String url = setParams(Msg);
		
		HttpURLConnection connection = null;
		InputStream in = null;
		ByteArrayOutputStream baos = null;
		try {
			URL Urlnet = new URL(url);
			connection = (HttpURLConnection) Urlnet.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			
		    in = connection.getInputStream();
			int len = -1;
			byte[] buf = new byte[128];
			baos = new ByteArrayOutputStream();
			while((len = in.read(buf)) != -1){
				baos.write(buf, 0, len);
			}
			baos.flush();
			result = new String(baos.toByteArray());
		} 
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(baos != null){
				try {
					baos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	private static String setParams(String msg) {
		
	  String url = "";
	try {
		url = URL + "?key=" + key + "&info="+ URLEncoder.encode(msg, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return url;
	}

}
