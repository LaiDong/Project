package com.android_examples.ld.tracnghiem;

/**
 * Created by LD on 9/28/2016.
 */
public class ItemTracNghiem {
    public String tittle;
    public String time;
    public String sentence;

    public ItemTracNghiem(String tittle, String time, String sentence) {
        this.tittle = tittle;
        this.time = time;
        this.sentence = sentence;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
