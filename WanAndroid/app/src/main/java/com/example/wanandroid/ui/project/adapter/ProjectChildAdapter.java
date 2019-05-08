package com.example.wanandroid.ui.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.http.bean.ProjectListBean;
import com.example.wanandroid.util.SpUtil;

import java.util.ArrayList;

/**
 * Created by 张嘉河 on 2019/5/1.
 */

public class ProjectChildAdapter extends RecyclerView.Adapter {
    public ArrayList<ProjectListBean.DataBean.DatasBean> mBeans;
    private Context mContext;
    private OnClickListener mListener;

    public ProjectChildAdapter(ArrayList<ProjectListBean.DataBean.DatasBean> beans, Context context) {

        mBeans = beans;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.project_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ProjectListBean.DataBean.DatasBean bean = mBeans.get(position);
        viewHolder.mItemProjectListAuthorTv.setText(bean.getAuthor());
        viewHolder.mItemProjectListContentTv.setText(bean.getDesc());
        viewHolder.mItemProjectListTimeTv.setText(bean.getNiceDate());
        viewHolder.mItemProjectListTitleTv.setText(bean.getTitle());
        boolean img = (boolean) SpUtil.getParam("img", false);
        if(img){
            Glide.with(mContext).load(R.mipmap.bg_android).into(viewHolder.mItemProjectListIv);
        }else{
            Glide.with(mContext).load(bean.getEnvelopePic()).into(viewHolder.mItemProjectListIv);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void setData(ProjectListBean bean) {
        mBeans.addAll(bean.getData().getDatas());
        notifyDataSetChanged();
    }


    public interface OnClickListener {
        void onClick(View view, int position);
    }

    public void setOnClickListener(OnClickListener listener) {

        mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mItemProjectListIv;
        TextView mItemProjectListTitleTv;
        TextView mItemProjectListContentTv;
        TextView mItemProjectListTimeTv;
        TextView mItemProjectListAuthorTv;
        TextView mItemProjectListInstallTv;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mItemProjectListIv = (ImageView) itemView.findViewById(R.id.item_project_list_iv);
            this.mItemProjectListTitleTv = (TextView) itemView.findViewById(R.id.item_project_list_title_tv);
            this.mItemProjectListContentTv = (TextView) itemView.findViewById(R.id.item_project_list_content_tv);
            this.mItemProjectListTimeTv = (TextView) itemView.findViewById(R.id.item_project_list_time_tv);
            this.mItemProjectListAuthorTv = (TextView) itemView.findViewById(R.id.item_project_list_author_tv);
            this.mItemProjectListInstallTv = (TextView) itemView.findViewById(R.id.item_project_list_install_tv);
        }
    }
}
