package com.example.autoanswer;

import java.text.SimpleDateFormat;
import java.util.List;

import com.example.autoanswer.been.message;
import com.example.autoanswer.been.message.Type;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<message> mDatas;

	public MyAdapter(Context context , List<message> mDatas){
		mInflater = LayoutInflater.from(context);
		this.mDatas = mDatas;
	}
	@Override
	public int getCount() {
		
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}
	
	

	@Override
	public int getItemViewType(int position) {
		
		message message = mDatas.get(position);
		if(message.getType() == Type.INCOMING){
			return 0;
		}
		return 1;
	}
	
	@Override
	public int getViewTypeCount() {
	
		return 2;
	}
	
	@Override
	//通过不同的type进行布局
	public View getView(int position, View convertView, ViewGroup partent) {
	
		message message = mDatas.get(position);
		ViewHolder viewHolder = null;
		if(convertView == null){
			if(getItemViewType(position) == 0){
			convertView = mInflater.inflate(R.layout.item_from_msg, partent,false);
			viewHolder = new ViewHolder();
			viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_form_msg_date);
			viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_from_msg_info);
			}
		
		else{
			convertView = mInflater.inflate(R.layout.item_to_msg, partent,false);
			viewHolder = new ViewHolder();
			viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_to_msg_date);
			viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_to_msg_info);
		}
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		viewHolder.mDate.setText(df.format(message.getDate()));
		viewHolder.mMsg.setText(message.getMsg());
		return convertView;
	}
	
	class ViewHolder{
		TextView mDate;
		TextView mMsg;
	}

}
