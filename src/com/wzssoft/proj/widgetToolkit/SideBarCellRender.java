package com.wzssoft.proj.widgetToolkit;

import com.wzssoft.proj.winCal.ProjConstant;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class SideBarCellRender extends JPanel implements TableCellRenderer {
    static int sad = 0;
    public static int cover_r = -1;
    public static int select_r = -1;
    private JLabel itemTag;
    private JLabel itemImg;
    private String font = "Arial";

    public SideBarCellRender() {
        super();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(ProjConstant.color_menu);    //后期得改

        itemImg = new JLabel();
        itemTag = new JLabel();
        itemTag.setFont(new Font(font, Font.BOLD, 30));

        this.add(itemImg);
        this.add(itemTag);

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        SideBarMenuItem item = (SideBarMenuItem) value;

        itemImg.setIcon(new ImageIcon(item.getImg()));
        itemTag.setText(item.getTag());

        if (row == select_r) {
            System.out.println("exception occur");
        } else if (row == cover_r) {
            this.setOpaque(true);
        } else {
            this.setOpaque(false);
        }
        return this;
    }
}
