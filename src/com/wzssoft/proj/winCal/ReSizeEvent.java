package com.wzssoft.proj.winCal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReSizeEvent extends MouseAdapter {
    public JFrame jf;
    private Point prePos, curPos, jfPos;
    private static final double BREADTH = 4.0;//边界拉伸范围,不清楚BUG是怎么产生的
    private int dragType;

    private static final int NO_DRAG = 0;
    private static final int UP = 1;
    private static final int UP_LEFT = 2;
    private static final int LEFT = 3;
    private static final int BOTTOM_LEFT = 4;
    private static final int BOTTOM = 5;
    private static final int BOTTOM_RIGHT = 6;
    private static final int RIGHT = 7;
    private static final int UP_RIGHT = 8;

    public ReSizeEvent(JFrame jf) {
        this.jf = jf;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prePos = e.getLocationOnScreen();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        areaCheck(e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        curPos = e.getLocationOnScreen();
        jfPos = jf.getLocation();
        dragAction();
        prePos = curPos;
    }

    private void dragAction() {
        switch (dragType) {
            case UP://x位置不变，y位置变化，并且Height变化
                jf.setLocation(jfPos.x,
                        jfPos.y + curPos.y - prePos.y);
                jf.setSize(jf.getWidth(), jf.getHeight() - (curPos.y - prePos.y));
                break;
            case LEFT://y位置不变，x位置变化，width变化
                jf.setLocation(jfPos.x + curPos.x - prePos.x,
                        jfPos.y);
                jf.setSize(jf.getWidth() - (curPos.x - prePos.x), jf.getHeight());
                break;
            case RIGHT://x,y位置不变，width变化
                jf.setLocation(jfPos.x,
                        jfPos.y);
                jf.setSize(jf.getWidth() + (curPos.x - prePos.x), jf.getHeight());
                break;
            case BOTTOM://x,y位置不变，Height变化
                jf.setLocation(jfPos.x,
                        jfPos.y);
                jf.setSize(jf.getWidth(), jf.getHeight() + (curPos.y - prePos.y));
                break;
            case UP_LEFT://x,y位置均变化，h,w均变化
                jf.setLocation(jfPos.x + curPos.x - prePos.x,
                        jfPos.y + curPos.y - prePos.y);
                jf.setSize(jf.getWidth() - (curPos.x - prePos.x), jf.getHeight() - (curPos.y - prePos.y));
                break;
            case BOTTOM_RIGHT://x,y位置均不变，h,w变化
                jf.setLocation(jfPos.x,
                        jfPos.y);
                jf.setSize(jf.getWidth() + (curPos.x - prePos.x), jf.getHeight() + (curPos.y - prePos.y));
                break;
            case UP_RIGHT://x位置不变，y,w,h变化
                jf.setLocation(jfPos.x,
                        jfPos.y + curPos.y - prePos.y);
                jf.setSize(jf.getWidth() + (curPos.x - prePos.x), jf.getHeight() - (curPos.y - prePos.y));
                break;
            case BOTTOM_LEFT://y不变，xwh变化
                jf.setLocation(jfPos.x + curPos.x - prePos.x,
                        jfPos.y);
                jf.setSize(jf.getWidth() - (curPos.x - prePos.x), jf.getHeight() + (curPos.y - prePos.y));
                break;
            default:
                break;
        }
    }


    private boolean areaCheck(Point p) {
        if (p.getX() <= BREADTH && p.getY() <= BREADTH) {
            dragType = UP_LEFT;
            jf.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
        } else if (p.getX() > BREADTH
                && p.getX() < (jf.getWidth() - BREADTH)
                && p.getY() <= BREADTH) {
            dragType = UP;
            jf.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
        } else if (p.getX() >= (jf.getWidth() - BREADTH) && p.getY() <= BREADTH) {
            dragType = UP_RIGHT;
            jf.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
        } else if (p.getX() <= BREADTH
                && p.getY() < (jf.getHeight() - BREADTH)
                && p.getY() > BREADTH) {
            dragType = LEFT;
            jf.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
        } else if (p.getX() >= (jf.getWidth() - BREADTH)
                && p.getY() < (jf.getHeight() - BREADTH)
                && p.getY() > BREADTH) {
            dragType = RIGHT;
            jf.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
        } else if (p.getX() <= BREADTH
                && p.getY() >= (jf.getHeight() - BREADTH)) {
            dragType = BOTTOM_LEFT;
            jf.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
        } else if (p.getX() > BREADTH
                && p.getX() < (jf.getWidth() - BREADTH)
                && p.getY() >= (jf.getHeight() - BREADTH)) {
            dragType = BOTTOM;
            jf.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));       //设置光标
        } else if (p.getX() >= (jf.getWidth() - BREADTH)
                && p.getY() >= (jf.getHeight() - BREADTH)) {
            dragType = BOTTOM_RIGHT;
            jf.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        }else {
            dragType = NO_DRAG;
            jf.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            return false;
        }
        return true;
    }
}
