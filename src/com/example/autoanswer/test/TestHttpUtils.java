package com.example.autoanswer.test;



import com.example.autoanswer.HttpUtil.HttpUtil;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestHttpUtils extends AndroidTestCase
{
	public void testSendInfo()
	{
		String res = HttpUtil.DoGet("���ҽ���Ц��");
		Log.e("TAG", res);
		res = HttpUtil.DoGet("���ҽ��������");
		Log.e("TAG", res);
		res = HttpUtil.DoGet("���");
		Log.e("TAG", res);
		res = HttpUtil.DoGet("������");
		Log.e("TAG", res);
	}
}
