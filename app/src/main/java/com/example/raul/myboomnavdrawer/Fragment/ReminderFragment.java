package com.example.raul.myboomnavdrawer.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.raul.myboomnavdrawer.Adapter.NoticeAdapter;
import com.example.raul.myboomnavdrawer.Adapter.ReminderAdapter;
import com.example.raul.myboomnavdrawer.Model.NoticeList;
import com.example.raul.myboomnavdrawer.Model.ReminderList;
import com.example.raul.myboomnavdrawer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malik on 7/14/2017.
 */

public class ReminderFragment extends Fragment{
    RecyclerView recyclerViewReminder;
    RecyclerView.Adapter adapter;
    List<ReminderList> reminderList;
    private static final String TAG = NoticeFragment.class.getSimpleName();
    private String url = "http://rjtmobile.com/aamir/school-mgt/school_parents_app/reminder.php";
    private String Reminder = "Reminder", Question = "Question", StudyReminder = "StudyReminder",
            ExamReminder = "ExamReminder";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        recyclerViewReminder = (RecyclerView) view.findViewById(R.id.recyclerViewReminder);
        recyclerViewReminder.setHasFixedSize(true);
        recyclerViewReminder.setLayoutManager(new LinearLayoutManager(getActivity()));
        reminderList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG,response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray categories = jsonObject.getJSONArray(Reminder);
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject item = categories.getJSONObject(i);
                        ReminderList myReminderList = new ReminderList(item.getString(Question),
                                item.getString(StudyReminder), item.getString(ExamReminder));
                        reminderList.add(myReminderList);
                    }

                    adapter = new ReminderAdapter(reminderList,getActivity().getApplicationContext());
                    recyclerViewReminder.setAdapter(adapter);
                } catch (JSONException e) {
                    Log.i(TAG, e.toString());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
        return view;
    }
}
