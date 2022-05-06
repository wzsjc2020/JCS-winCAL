package com.wzssoft.proj.winCal;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseInputListener;
import com.wzssoft.proj.widgetToolkit.TToggleLabel;
import com.wzssoft.proj.winCal.asserts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProjStarter extends JFrame implements NativeMouseInputListener {
    public Register register;
    public TopConsole topConsole;
    public SideMenuBar sideMenuBar;
    public TToggleLabel sideMenuButton;
    private SpringLayout springLayout = new SpringLayout();

    public ProjStarter() {
        ProjConstant.init();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int window_W = ProjConstant.WINDOW_W;
        int window_H = ProjConstant.WINDOW_H;

        ReSizeEvent reSizeEvent = new ReSizeEvent(this);
        this.addMouseListener(reSizeEvent);
        this.addMouseMotionListener(reSizeEvent);

        //全局监听器
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeMouseListener(this);
            GlobalScreen.addNativeMouseMotionListener(this);
        } catch (NativeHookException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }

        Dimension size = toolkit.getScreenSize();
        this.setBounds(size.width / 2 - window_W / 2, size.height / 2 - window_H / 2, window_W, window_H);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setIconImage(ProjConstant.projIcon);
        this.setOpacity(ProjConstant.WINDOW_OPACITY);
        this.setMinimumSize(new Dimension(402, 627));
        this.setMaximumSize(size);
        this.setTitle(ProjConstant.projName + " " + ProjConstant.projVersion);
        this.setLayout(springLayout);

        init();
    }

    private void init() {
        //注册主窗口面板
        register = new Register(this);
        springLayout.putConstraint(SpringLayout.WEST, register, 0, SpringLayout.WEST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, register, 45, SpringLayout.NORTH, this.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, register, 0, SpringLayout.EAST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, register, 0, SpringLayout.SOUTH, this.getContentPane());


        //置顶的控制面板
        topConsole = new TopConsole(this);
        springLayout.putConstraint(SpringLayout.WEST, topConsole, 0, SpringLayout.WEST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, topConsole, 0, SpringLayout.NORTH, this.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, topConsole, 0, SpringLayout.EAST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, topConsole, 45, SpringLayout.NORTH, this.getContentPane());

        //侧边栏
        sideMenuBar = new SideMenuBar(this);
        springLayout.putConstraint(SpringLayout.WEST, sideMenuBar, 0, SpringLayout.WEST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, sideMenuBar, 45, SpringLayout.NORTH, this.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, sideMenuBar, 361, SpringLayout.WEST, this.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, sideMenuBar, 0, SpringLayout.SOUTH, this.getContentPane());

        //侧板菜单栏按钮
        sideMenuButton = new TToggleLabel();
        sideMenuButton.setIcon_on(ProjConstant.icon_menu);
        sideMenuButton.setIcon_off(ProjConstant.icon_menu);
        springLayout.putConstraint(SpringLayout.NORTH, sideMenuButton, 50, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, sideMenuButton, 50, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.WEST, sideMenuButton, 0, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, sideMenuButton, 90, SpringLayout.NORTH, this);

        sideMenuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (sideMenuButton.isSelected()) {
                    disableSideBar();
                } else {
                    enableSideBar();
                }
            }
        });


        //添加组件
        getContentPane().add(sideMenuButton, 0);
        getContentPane().add(sideMenuBar, 1);
        getContentPane().add(topConsole, 2);
        getContentPane().add(register, 3);//设置底层面板
    }

    public void enableSideBar() {
        sideMenuBar.setVisible(true);
        sideMenuButton.setSelected(true);
        ProjConstant.actionLocked = true;
    }

    public void disableSideBar() {
        sideMenuBar.setVisible(false);
        sideMenuButton.setSelected(false);
        disableActionLock();
    }

    //睡眠关闭大法
    public void disableActionLock() {
        new Thread(() -> {
            try {
                Thread.sleep(200);
                ProjConstant.actionLocked = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    //全局监听器
    @Override
    public void nativeMousePressed(NativeMouseEvent nativeEvent) {
        try {
            if (sideMenuBar.isVisible()) {
                Rectangle safe = new Rectangle(getX(), getY() - 45, 361, getHeight());
                if (!safe.contains(nativeEvent.getPoint())) {
                    disableSideBar();
                }
            }
        } catch (Exception a) {
            System.out.println("ProjStarter.nativeMousePressed");
        }
    }
}