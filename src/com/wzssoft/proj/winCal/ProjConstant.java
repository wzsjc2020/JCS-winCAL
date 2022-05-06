package com.wzssoft.proj.winCal;

import com.wzssoft.proj.widgetToolkit.SideBarMenuItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class ProjConstant {

    //default默认类
    public static String genPath = null;
    public static String texturePath = null;
    public static String projName = null;
    public static String projVersion = null;
    public static BufferedImage projIcon = null;
    public static int WINDOW_W = 0;
    public static int WINDOW_H = 0;
    public static float WINDOW_OPACITY = 0f;
    public static boolean actionLocked = false;
    public static ArrayList<SideBarMenuItem> registerItems = new ArrayList<SideBarMenuItem>();
    public static String universalFont = null;

    //注册图标
    public static BufferedImage icon_minimize = null;
    public static BufferedImage icon_close = null;
    public static BufferedImage icon_maximize = null;
    public static BufferedImage icon_menu = null;
    public static BufferedImage icon_error = null;
    public static BufferedImage icon_checkbox = null;
    public static BufferedImage icon_uncheckbox = null;


    //颜色类
    public static Color color_maximize = null;
    public static Color color_minimize = null;
    public static Color color_close = null;
    public static Color color_menu = null;
    public static Color color_topConsole = null;
    public static Color color_sideMenuBar = null;

    //functions


    public static void init() {
        genPath = System.getProperty("user.dir") + "/resources/winCal/";
        texturePath = genPath + "light/";
        projName = "Example example";
        projVersion = "V0.0.0";
        projIcon = null;
        WINDOW_W = 875;//宽度
        WINDOW_H = 715;//高度
        WINDOW_OPACITY = 1.0f;
        universalFont = "Arial";


        try {
            color_close = new Color(232, 17, 35);
            color_maximize = new Color(175, 175, 175);
            color_minimize = new Color(175, 175, 175);
            color_menu = new Color(175, 175, 175);
            color_topConsole = Color.orange;
            color_sideMenuBar = new Color(225, 225, 225);

            projIcon = ImageIO.read(new File(texturePath + "icon/icon.png"));
            icon_minimize = ImageIO.read(new File(texturePath + "icon/minimize.png"));
            icon_close = ImageIO.read(new File(texturePath + "icon/close.png"));
            icon_maximize = ImageIO.read(new File(texturePath + "icon/maximize.png"));
            icon_menu = ImageIO.read(new File(texturePath + "icon/menu.png"));
            icon_error = ImageIO.read(new File(texturePath + "misc/error.png"));

        } catch (Exception exception) {
            System.out.println("ProjConstant.ProjConstant");
        }

        try {
            icon_checkbox = ImageIO.read(new File(texturePath + "icon/checkbox.png"));
            icon_uncheckbox = ImageIO.read(new File(texturePath + "icon/uncheckbox.png"));
        } catch (Exception exception) {
            System.out.println("ProjConstant.init Standard img loading Error");
        }
    }
}
