package com.wzssoft.proj.winCal.asserts;

import com.wzssoft.proj.LayoutToolkit.StripLayout;
import com.wzssoft.proj.winCal.ProjConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ExamplePanel extends JPanel {
    private String font = ProjConstant.universalFont;
    private JPanel upperArea = new JPanel(new FlowLayout());
    private JPanel lowerArea = new JPanel(new StripLayout());

    public ExamplePanel() {
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        upperArea.setBackground(ProjConstant.color_topConsole);
        springLayout.putConstraint(SpringLayout.WEST, upperArea, 0, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, upperArea, 0, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, upperArea, 0, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, upperArea, 50, SpringLayout.NORTH, this);

        lowerArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        springLayout.putConstraint(SpringLayout.WEST, lowerArea, 0, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, lowerArea, 50, SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.EAST, lowerArea, 0, SpringLayout.EAST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, lowerArea, 0, SpringLayout.SOUTH, this);

        init();

        this.add(upperArea);
        this.add(lowerArea);
    }

    private void init() {
        JLabel exampleLabel = new JLabel("example text", JLabel.CENTER);
        exampleLabel.setPreferredSize(new Dimension(200, 50));
        exampleLabel.setFont(new Font(font, Font.BOLD, 20));
        upperArea.add(exampleLabel);

        JTextArea textArea = new JTextArea("hello world");
        textArea.setFont(new Font(font, Font.BOLD, 20));
        lowerArea.add(textArea);

        textArea.addMouseListener(new MouseAdapter() {
            private JTextArea area = textArea;

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!ProjConstant.actionLocked)
                    area.setEnabled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!ProjConstant.actionLocked)
                    area.setEnabled(false);
            }
        });
    }
}

