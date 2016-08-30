package com.chebao.quickIndexbar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by iwan on 16/8/30.
 */
public class MyAdapter extends BaseAdapter {

    private ArrayList<Friend> list;
    private Context context;

    public MyAdapter(ArrayList<Friend> list,Context c) {
        this.list = list;
        this.context = c;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    // 不用设置
    @Override
    public Object getItem(int i) {
        return null;
    }
    // 不用设置
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null){
            view = View.inflate(context,R.layout.adapter_friend,null);
        }
        ViewHolder holder = ViewHolder.getHolder(view);
        // 设置数据
        Friend friend = list.get(i);
        holder.name.setText(friend.getName());


        // 设置首字母
        String currentWord = friend.getPinyin().charAt(0)+"";
        holder.first_word.setText(currentWord);



        return view;
    }

    // 建议写法，提高封装性
    static class ViewHolder{
        TextView name,first_word;

        public ViewHolder(View view) {
            first_word = (TextView)view.findViewById(R.id.first_word);
            name = (TextView)view.findViewById(R.id.name);

        }

        public static ViewHolder getHolder(View view){
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder == null){
                holder = new ViewHolder(view);
                view.setTag(holder);
            }
            return holder;
        }
    }
}
