package com.example.raul.myboomnavdrawer.Model;

/**
 * Created by malik on 7/14/2017.
 */

public class ReminderList {
    String study;
    String exam;
    String question;

    public String getStudy() {
        return study;
    }

    public String getExam() {
        return exam;
    }

    public String getQuestion() {
        return question;
    }

     public ReminderList(String question, String exam, String study){
         this.question = question;
         this.exam = exam;
         this.study = study;
     }
}
