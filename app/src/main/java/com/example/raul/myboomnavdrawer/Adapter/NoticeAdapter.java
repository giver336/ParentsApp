package com.example.raul.myboomnavdrawer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raul.myboomnavdrawer.Model.NoticeList;
import com.example.raul.myboomnavdrawer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by malik on 7/13/2017.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    List<NoticeList> noticeList;
    private Context context;

    public NoticeAdapter(List<NoticeList> noticeList, Context context){
        this.noticeList = noticeList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.notice_detail,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticeAdapter.ViewHolder holder, int position) {
        NoticeList myNoticeList = noticeList.get(position);
        holder.tv_notice_title.setText(myNoticeList.getNotice_title());
        holder.tv_notice_date.setText(myNoticeList.getNotice_date());
        holder.tv_notice_description.setText(myNoticeList.getNotice_description());
        Picasso.with(context).load(myNoticeList.getNotice_img1())
                .resize(1010,500)
                .into(holder.iv_notice_1);
        Picasso.with(context).load(myNoticeList.getNotice_img2())
                .resize(1010,500)
                .into(holder.iv_notice_2);
        Picasso.with(context).load(myNoticeList.getNotice_img3())
                .resize(1010,500)
                .into(holder.iv_notice_3);
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_notice_title, tv_notice_date, tv_notice_description;
        public ImageView iv_notice_1, iv_notice_2, iv_notice_3;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_notice_title = (TextView)itemView.findViewById(R.id.tv_notice_title);
            tv_notice_date = (TextView)itemView.findViewById(R.id.tv_notice_date);
            tv_notice_description = (TextView)itemView.findViewById(R.id.tv_notice_description);
            iv_notice_1 = (ImageView)itemView.findViewById(R.id.iv_notice_1);
            iv_notice_2 = (ImageView)itemView.findViewById(R.id.iv_notice_2);
            iv_notice_3 = (ImageView)itemView.findViewById(R.id.iv_notice_3);
        }
    }
}
