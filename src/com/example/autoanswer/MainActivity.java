package com.example.autoanswer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.autoanswer.HttpUtil.HttpUtil;
import com.example.autoanswer.been.message;
import com.example.autoanswer.been.message.Type;

import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private ListView mMsgs;
	private MyAdapter mAdapter;
	private List<message> mdata;
	
	private EditText mInputMsg;
	private Button mSendMsg;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			message fromMessage = (message) msg.obj;
			mdata.add(fromMessage);
			mAdapter.notifyDataSetChanged();
		};
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        initView();
        initDatas();
        initListener();
    }

	private void initListener() {
		
        mSendMsg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String toMsg = mInputMsg.getText().toString();
				if(TextUtils.isEmpty(toMsg)){
					Toast.makeText(getApplicationContext(), "发送消息不能为空", Toast.LENGTH_SHORT).show();
					return;
				}
				
				message toMessage = new message();
				toMessage.setDate(new Date());
				toMessage.setMsg(toMsg);
				toMessage.setType(Type.OUTCOMING);
				mdata.add(toMessage);
				mAdapter.notifyDataSetChanged();
				
				mInputMsg.setText("");
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						message fromMessage = new message();
						fromMessage = HttpUtil.sendMessage(toMsg);
						Message m  =  Message.obtain();
						m.obj = fromMessage;
						handler.sendMessage(m);
						
					}
				}).start();
				
			}
		});
		
	}

	private void initDatas() {
		
		mdata = new ArrayList<message>();
		mdata.add(new message("你好，超超为你服务", Type.INCOMING, new Date()));
		mAdapter = new MyAdapter(getApplicationContext(), mdata);
		mMsgs.setAdapter(mAdapter);
		
	}

	private void initView() {
		
		mMsgs = (ListView) findViewById(R.id.id_listview_msgs);
		mInputMsg = (EditText) findViewById(R.id.id_input_msg);
		mSendMsg = (Button) findViewById(R.id.id_send_msg);

		
	}


    
}
