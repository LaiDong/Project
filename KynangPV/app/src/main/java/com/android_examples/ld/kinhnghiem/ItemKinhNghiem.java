package com.android_examples.ld.kinhnghiem;

/**
 * Created by LD on 9/28/2016.
 */
public class ItemKinhNghiem {
    public String tittle;
    public String content;
    int image;

    public ItemKinhNghiem(String tittle, String content, int image) {
        this.tittle = tittle;
        this.content = content;
        this.image = image;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
