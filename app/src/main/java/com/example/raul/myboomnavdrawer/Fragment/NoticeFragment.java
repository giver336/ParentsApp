package com.example.raul.myboomnavdrawer.Fragment;

import android.os.Bundle;
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
import com.example.raul.myboomnavdrawer.Model.NoticeList;
import com.example.raul.myboomnavdrawer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by malik on 7/13/2017.
 */

public class NoticeFragment extends Fragment {
    RecyclerView recyclerViewNotice;
    RecyclerView.Adapter adapter;
    List<NoticeList> noticeList;
    private static final String TAG = NoticeFragment.class.getSimpleName();
    private String url = "http://rjtmobile.com/aamir/school-mgt/school_parents_app/school_notice_board.php";
    private String NoticeBoard = "NoticeBoard", NoticeTitle = "NoticeTitle", NoticeDesc = "NoticeDesc",
            NoticeDate = "NoticeDate", NoticeThumb = "NoticeThumb", NoticeThumb1 = "NoticeThumb1",
            NoticeThumb2 = "NoticeThumb2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_notice, container, false);
        recyclerViewNotice = (RecyclerView) view.findViewById(R.id.recyclerViewNotice);
        recyclerViewNotice.setHasFixedSize(true);
        recyclerViewNotice.setLayoutManager(new LinearLayoutManager(getActivity()));
        noticeList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG,response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray categories = jsonObject.getJSONArray(NoticeBoard);
                    for (int i = 0; i < categories.length(); i++) {
                        JSONObject item = categories.getJSONObject(i);
                        NoticeList myNoticeList = new NoticeList(item.getString(NoticeTitle),
                                item.getString(NoticeDate), item.getString(NoticeDesc),
                                item.getString(NoticeThumb), item.getString(NoticeThumb1),
                                item.getString(NoticeThumb2));
                        noticeList.add(myNoticeList);
                    }

                    adapter = new NoticeAdapter(noticeList,getActivity().getApplicationContext());
                    recyclerViewNotice.setAdapter(adapter);
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
