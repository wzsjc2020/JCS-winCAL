package com.wzssoft.proj.winCal.asserts;


import com.wzssoft.proj.widgetToolkit.*;
import com.wzssoft.proj.winCal.ProjConstant;
import com.wzssoft.proj.winCal.ProjStarter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class SideMenuBar extends JPanel {
    private SpringLayout springLayout = new SpringLayout();
    private ProjStarter projStarter;
    private SideMenuBar sideMenuBar = this;
    private int nowIndex = 99;

    public SideMenuBar(ProjStarter projStarter) {
        this.projStarter = projStarter;
        this.setLayout(springLayout);
        this.setBackground(ProjConstant.color_sideMenuBar);
        this.setVisible(false);

        init();
    }

    private void init() {

        DefaultTableModel model = new DefaultTableModel(0, 1);

        for (int i = 0; i < ProjConstant.registerItems.size(); i++) {
            model.addRow(new Object[]{ProjConstant.registerItems.get(i)});
        }

        //菜单列表
        JTable table = new JTable();
        table.setModel(model);
        table.setRowHeight(50);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setPreferredSize(new Dimension(0, 0));
        table.getTableHeader().setDefaultRenderer(renderer);
        table.getColumnModel().getColumn(0).setCellRenderer(new SideBarCellRender());
        table.setShowGrid(false);
        table.setBackground(ProjConstant.color_sideMenuBar);


        //JScrollPane functionPanels = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane functionPanels = new JScrollPane(table);
        functionPanels.setBorder(BorderFactory.createEmptyBorder());//陷阱
        functionPanels.getViewport().setBackground(ProjConstant.color_sideMenuBar);
        springLayout.putConstraint(SpringLayout.NORTH, functionPanels, 50, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, functionPanels, 361, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.WEST, functionPanels, 0, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, functionPanels, -45, SpringLayout.SOUTH, this);

        //控制按键颜色变化以及重绘
        table.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r != SideBarCellRender.cover_r) {
                    SideBarCellRender.cover_r = r;
                    table.repaint();
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                projStarter.disableSideBar();

                int r = table.rowAtPoint(e.getPoint());
                if (nowIndex != r) {
                    projStarter.register.shift(ProjConstant.registerItems.get(r).getTag());
                    nowIndex = r;
                }

            }
        });
        //添加组件
        this.add(functionPanels);
    }
}