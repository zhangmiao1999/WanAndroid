package com.example.wanandroid.ui.knowledge.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.http.bean.KnowledgeBean;
import com.example.wanandroid.util.ColorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张嘉河 on 2019/5/3.
 */

public class KnowledgeRvAdapter extends RecyclerView.Adapter {
    public ArrayList<KnowledgeBean.DataBean> mBeans;
    private Context mContext;
    private OnClickListener mListener;

    public KnowledgeRvAdapter(ArrayList<KnowledgeBean.DataBean> beans, Context context) {

        mBeans = beans;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.knowledge_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        String name = mBeans.get(position).getName();
        List<KnowledgeBean.DataBean.ChildrenBean> children = mBeans.get(position).getChildren();
        StringBuilder sb = new StringBuilder();
        for (KnowledgeBean.DataBean.ChildrenBean child : children) {
            sb.append(child.getName());
            sb.append("  ");
        }
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mKnowledgeTitle.setTextColor(Color.parseColor(ColorUtil.getRandomColor()));
        viewHolder.mKnowledgeTitle.setText(name);
        viewHolder.mKnowledgeInfo.setText(sb.toString());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void setData(List<KnowledgeBean.DataBean> bean) {
        mBeans.addAll(bean);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView mKnowledgeTitle;
        TextView mKnowledgeInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mKnowledgeTitle = (TextView) itemView.findViewById(R.id.knowledge_title);
            this.mKnowledgeInfo = (TextView) itemView.findViewById(R.id.knowledge_info);
        }
    }

    public interface OnClickListener{
        void onClick(View view,int position);
    }

    public void setOnClickListener (OnClickListener listener){

        mListener = listener;
    }
}
