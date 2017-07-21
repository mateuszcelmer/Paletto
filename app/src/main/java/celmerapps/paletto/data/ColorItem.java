package celmerapps.paletto.data;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

/**
 * Created by Mati on 2017-07-19.
 */

public class ColorItem {

    private ColorDrawable color;
    private int textColor = Color.BLACK;
    private String info = "";
    private String text = "";

    public ColorItem(ColorDrawable color) {
        this.color = color;
    }

    public ColorItem(int color_int) {
        this.color = new ColorDrawable(color_int);
    }

    public ColorItem(int color_int, int textColor, String info) {
        this.color = new ColorDrawable(color_int);
        this.textColor = textColor;
        this.info = info;
    }

    public ColorItem(String color_HEX_code) {
        this.color = new ColorDrawable(
                Color.parseColor(color_HEX_code)
        );

    }

    public ColorDrawable getColor() {
        return color;
    }

    public void setColor(ColorDrawable color) {
        this.color = color;
    }

    public boolean isSwatch(){
        if (this.text != "")
            return false;
        else
            return true;
    }

    public String getText() {
        if (this.text != "")
            return this.text;
        else
            return toHex();
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toHex() {
        int col = color.getColor();
        int r = Color.red(col);
        int g = Color.green(col);
        int b = Color.blue(col);
        return "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
    }

    public static String toHex(int r, int g, int b) {
        return "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
    }

    private static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString().toUpperCase();
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
