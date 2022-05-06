package com.wzssoft.proj.widgetToolkit;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class TTransparentLabel extends JLabel {
    private String tag;

    public TTransparentLabel(String text, String tag) {
        this.tag = tag;
        this.setText(text);
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    public TTransparentLabel(BufferedImage image, String tag) {
        this.tag = tag;
        this.setIcon(new ImageIcon(image));
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    public String getTag() {
        return tag;
    }

    public void doOpaque(boolean isOpaque) {
        this.setOpaque(isOpaque);
        this.repaint();
    }
}
