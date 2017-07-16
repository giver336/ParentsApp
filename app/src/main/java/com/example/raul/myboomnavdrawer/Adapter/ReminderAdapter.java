package com.example.raul.myboomnavdrawer.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.raul.myboomnavdrawer.Model.NoticeList;
import com.example.raul.myboomnavdrawer.Model.ReminderList;
import com.example.raul.myboomnavdrawer.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by malik on 7/14/2017.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {
    List<ReminderList> reminderList;
    private Context context;
    public ReminderAdapter(List<ReminderList> reminderList, Context context){
        this.reminderList = reminderList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.reminder_detail,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ReminderAdapter.ViewHolder holder, int position) {
        ReminderList myReminderList = reminderList.get(position);
        holder.tv_question.setText(myReminderList.getQuestion());
        holder.tv_study.setText(myReminderList.getStudy());
        holder.tv_exam.setText(myReminderList.getExam());
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        String currentDateandTime = sdf.format(new Date());
        holder.et_date.setText("Date: "+currentDateandTime);
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_question, tv_study, tv_exam;
        public EditText et_date;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_question = (TextView)itemView.findViewById(R.id.tv_question);
            tv_study = (TextView)itemView.findViewById(R.id.tv_study);
            tv_exam = (TextView)itemView.findViewById(R.id.tv_exam);
            et_date = (EditText)itemView.findViewById(R.id.et_date);

        }
    }
}
