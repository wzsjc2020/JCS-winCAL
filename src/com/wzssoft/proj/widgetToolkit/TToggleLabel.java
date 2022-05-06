package com.wzssoft.proj.widgetToolkit;

import com.wzssoft.proj.winCal.ProjConstant;

import javax.swing.*;
import java.awt.*;

//使用Label容器制作开关，如遇到错误，请检查是否初始化constant类
public class TToggleLabel extends JLabel {
    private boolean selected = false;
    private Image icon_on = ProjConstant.icon_checkbox;
    private Image icon_off = ProjConstant.icon_uncheckbox;


    public TToggleLabel() {
        init();
    }

    private void init() {
        this.setBackground(null);
        this.setOpaque(false);
        this.setHorizontalAlignment(JLabel.CENTER);
        repaintImg();
    }

   public void repaintImg() {
        this.setIcon(new ImageIcon(isSelected() ? icon_on :icon_off));
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        this.repaintImg();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setIcon_on(Image icon_on) {
        this.icon_on = icon_on;
        this.repaintImg();
    }

    public void setIcon_off(Image icon_off) {
        this.icon_off = icon_off;
        this.repaintImg();
    }
}
