package com.wzssoft.proj.winCal.asserts;


import com.wzssoft.proj.widgetToolkit.TTransparentLabel;
import com.wzssoft.proj.winCal.ProjConstant;
import com.wzssoft.proj.winCal.ProjStarter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class TopConsole extends JPanel {
    private SpringLayout springLayout;
    private Point loc = null;
    private Point tmp = null;
    private String font = "Arial";
    private boolean isDragged = false;
    private ProjStarter projStarter;
    private ArrayList<TTransparentLabel> labels = new ArrayList<TTransparentLabel>();

    public TopConsole(ProjStarter projStarter) {
        this.projStarter = projStarter;
        springLayout = new SpringLayout();
        this.setLayout(springLayout);
        this.setBackground(ProjConstant.color_topConsole);

        init();
    }

    private void init() {
        //最小化，最大化，关闭
        TTransparentLabel minimize = new TTransparentLabel(ProjConstant.icon_minimize, "minimize");
        minimize.setBackground(ProjConstant.color_minimize);
        springLayout.putConstraint(SpringLayout.NORTH, minimize, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, minimize, -60, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, minimize, -120, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, minimize, 40, SpringLayout.NORTH, this);

        TTransparentLabel close = new TTransparentLabel(ProjConstant.icon_close, "close");
        close.setBackground(ProjConstant.color_close);
        springLayout.putConstraint(SpringLayout.NORTH, close, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, close, 0, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, close, -60, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, close, 45, SpringLayout.NORTH, this);

        TTransparentLabel maximize = new TTransparentLabel(ProjConstant.icon_maximize, "maximize");
        maximize.setBackground(ProjConstant.color_maximize);
        springLayout.putConstraint(SpringLayout.NORTH, maximize, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, maximize, -120, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, maximize, -180, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, maximize, 40, SpringLayout.NORTH, this);

        //标题
        JLabel title = new JLabel("  " + ProjConstant.projName + "  " + ProjConstant.projVersion);
        title.setFont(new Font(font, Font.PLAIN, 15));
        springLayout.putConstraint(SpringLayout.NORTH, title, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, title, -180, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.WEST, title, 0, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, title, 40, SpringLayout.NORTH, this);

        labels.add(minimize);
        labels.add(close);
        labels.add(maximize);

        //监听事件
        title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
                isDragged = false;
                projStarter.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

            public void mousePressed(java.awt.event.MouseEvent e) {
                tmp = new Point(e.getX(), e.getY());
                isDragged = true;
                projStarter.setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        });

        title.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (isDragged) {
                    loc = new Point(projStarter.getLocation().x + e.getX() - tmp.x,
                            projStarter.getLocation().y + e.getY() - tmp.y);
                    projStarter.setLocation(loc);
                }
            }
        });

        for (TTransparentLabel jLabel : labels) {
            jLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    String command = jLabel.getTag();
                    if (command.equals("minimize")) projStarter.setExtendedState(JFrame.ICONIFIED);
                    else if (command.equals("close")) System.exit(0);
                    else if (command.equals("maximize")) projStarter.setExtendedState(JFrame.MAXIMIZED_BOTH);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    jLabel.doOpaque(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabel.doOpaque(false);
                }
            });
        }

        this.add(minimize);
        this.add(close);
        this.add(maximize);
        this.add(title);
    }
}