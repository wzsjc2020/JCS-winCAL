package com.wzssoft.proj.winCal.asserts;

import com.wzssoft.proj.widgetToolkit.SideBarMenuItem;
import com.wzssoft.proj.winCal.ProjConstant;
import com.wzssoft.proj.winCal.ProjStarter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Register extends JPanel {
    private CardLayout cardLayout;

    public Register(ProjStarter projStarter) {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        //注册功能面板
        ExamplePanel examplePanel = new ExamplePanel();

        //添加到菜单窗口
        panelRegister("example", examplePanel, ProjConstant.icon_error);


        /*
        for (int i = 0; i < 30; i++) {
            JPanel aa = new JPanel();
            panelRegister("abc" + i, aa, ProjConstant.icon_maximize);
        }
         */

    }

    public void panelRegister(String tag, JPanel panel, BufferedImage image) {
        this.add(panel, tag);
        SideBarMenuItem sideBarMenuItem = new SideBarMenuItem();
        sideBarMenuItem.setTag(tag);
        sideBarMenuItem.setImg(image);
        ProjConstant.registerItems.add(sideBarMenuItem);
    }

    public void shift(String str) {
        cardLayout.show(this, str);
    }
}