package com.example.autoanswer.test;



import com.example.autoanswer.HttpUtil.HttpUtil;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestHttpUtils extends AndroidTestCase
{
	public void testSendInfo()
	{
		String res = HttpUtil.DoGet("给我讲个笑话");
		Log.e("TAG", res);
		res = HttpUtil.DoGet("给我讲个鬼故事");
		Log.e("TAG", res);
		res = HttpUtil.DoGet("你好");
		Log.e("TAG", res);
		res = HttpUtil.DoGet("你真美");
		Log.e("TAG", res);
	}
}
