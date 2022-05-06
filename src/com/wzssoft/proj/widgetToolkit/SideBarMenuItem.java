package com.wzssoft.proj.widgetToolkit;

import com.wzssoft.proj.winCal.ProjConstant;

import java.awt.image.BufferedImage;

public class SideBarMenuItem {
    private String tag = "unknown";
    private BufferedImage img = ProjConstant.icon_error;

    public String getTag() {
        return tag;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

}
