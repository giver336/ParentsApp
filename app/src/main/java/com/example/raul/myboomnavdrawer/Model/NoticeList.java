package com.example.raul.myboomnavdrawer.Model;

/**
 * Created by malik on 7/13/2017.
 */

public class NoticeList {
    private String notice_title, notice_date, notice_description, notice_img1, notice_img2, notice_img3;

    public String getNotice_title() {
        return notice_title;
    }

    public String getNotice_date() {
        return notice_date;
    }

    public String getNotice_description() {
        return notice_description;
    }

    public String getNotice_img1() {
        return notice_img1;
    }

    public String getNotice_img2() {
        return notice_img2;
    }

    public String getNotice_img3() {
        return notice_img3;
    }

    public NoticeList(String notice_title, String notice_date, String notice_description,
                      String notice_img1, String notice_img2, String notice_img3){
        this.notice_title = notice_title;
        this.notice_date = notice_date;
        this.notice_description = notice_description;
        this.notice_img1 = notice_img1;
        this.notice_img2 = notice_img2;
        this.notice_img3 = notice_img3;
    }
}
