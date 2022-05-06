package com.wzssoft.proj.LayoutToolkit;


import java.awt.*;

/**
 * 基于gridLayout，按照格子占据父组件大小的比例动态调整的布局，2022年5月5日，WZSJC2022，加拿大，埃德蒙顿
 * 如何实现自定义组件高度，首先获取每行的高度，然后进行枕余计算即可
 * 注意，这边的高度是动态的，不要忘了修改
 * 添加于 wincalBeta1.3
 */

public class StripLayout implements LayoutManager {
    int rows;
    int cols;
    int hgap;
    int vgap;
    int componentCount;
    boolean isHorizontal;
    int[] heightOnComponents;
    float[] share;

    public StripLayout() {
        this(1, false, 0, 0, new float[]{1.0f});
    }

    public StripLayout(int componentCount, boolean isHorizontal, int hgap, int vgap, float[] share) {
        this.componentCount = componentCount;
        this.isHorizontal = isHorizontal;
        this.hgap = hgap;
        this.vgap = vgap;
        this.share = share;

        if (componentCount == 0 || share.length < componentCount) {
            throw new IllegalArgumentException("rows or cols cannot be zero");
        }

        float f = 0f;
        for (float v : share) {
            f += v;
        }
        if (f != 1.0f) {
            throw new IllegalArgumentException("share cannot add up to 1.0f");
        }

        if (isHorizontal) {
            rows = 1;
            cols = componentCount;
        } else {
            rows = componentCount;
            cols = 1;
        }
        heightOnComponents = new int[componentCount];
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        //leave it alone
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        //leave it alone
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();//内部组件的总数
            int nrows = rows;
            int ncols = cols;

            if (nrows > 0) {
                ncols = (ncomponents + nrows - 1) / nrows;
            } else {
                nrows = (ncomponents + ncols - 1) / ncols;
            }
            int w = 0;
            int h = 0;
            for (int i = 0; i < ncomponents; i++) {
                Component comp = parent.getComponent(i);
                Dimension d = comp.getPreferredSize();
                if (w < d.width) {
                    w = d.width;
                }
                if (h < d.height) {
                    h = d.height;
                }
            }
            return new Dimension(insets.left + insets.right + ncols * w + (ncols - 1) * hgap,
                    insets.top + insets.bottom + nrows * h + (nrows - 1) * vgap);
        }
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            int nrows = rows;
            int ncols = cols;

            if (nrows > 0) {
                ncols = (ncomponents + nrows - 1) / nrows;
            } else {
                nrows = (ncomponents + ncols - 1) / ncols;
            }
            int w = 0;
            int h = 0;
            for (int i = 0; i < ncomponents; i++) {
                Component comp = parent.getComponent(i);
                Dimension d = comp.getMinimumSize();
                if (w < d.width) {
                    w = d.width;
                }
                if (h < d.height) {
                    h = d.height;
                }
            }
            return new Dimension(insets.left + insets.right + ncols * w + (ncols - 1) * hgap,
                    insets.top + insets.bottom + nrows * h + (nrows - 1) * vgap);
        }
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int ncomponents = parent.getComponentCount();
            int nrows = rows;
            int ncols = cols;
            boolean ltr = parent.getComponentOrientation().isLeftToRight();

            if (ncomponents == 0) {
                return;
            }
            if (nrows > 0) {
                ncols = (ncomponents + nrows - 1) / nrows;
            } else {
                nrows = (ncomponents + ncols - 1) / ncols;
            }
            int totalGapsWidth = (ncols - 1) * hgap;
            int widthWOInsets = parent.getWidth() - (insets.left + insets.right);
            int widthOnComponent = (widthWOInsets - totalGapsWidth) / ncols;
            int extraWidthAvailable = (widthWOInsets - (widthOnComponent * ncols + totalGapsWidth)) / 2;

            int totalGapsHeight = (nrows - 1) * vgap;
            int heightWOInsets = parent.getHeight() - (insets.top + insets.bottom);

            int heightOnComponent = 0;
            for (int i = 0; i < heightOnComponents.length; i++) {
                int h = (int) ((heightWOInsets - totalGapsHeight) * share[i]);
                heightOnComponents[i] = h;
                heightOnComponent += h;
            }
            int extraHeightAvailable = (heightWOInsets - (heightOnComponent + totalGapsHeight)) / 2;

            if (ltr) {
                for (int c = 0, x = insets.left + extraWidthAvailable; c < ncols; c++, x += widthOnComponent + hgap) {
                    for (int r = 0, y = insets.top + extraHeightAvailable; r < nrows; r++, y += heightOnComponents[r - 1] + vgap) {
                        int i = r * ncols + c;
                        if (i < ncomponents) {
                            parent.getComponent(i).setBounds(x, y, widthOnComponent, heightOnComponents[r]);
                        }
                    }
                }
            } else {
                for (int c = 0, x = (parent.getWidth() - insets.right - widthOnComponent) - extraWidthAvailable; c < ncols; c++, x -= widthOnComponent + hgap) {
                    for (int r = 0, y = insets.top + extraHeightAvailable; r < nrows; r++, y += heightOnComponents[r - 1] + vgap) {
                        int i = r * ncols + c;
                        if (i < ncomponents) {
                            parent.getComponent(i).setBounds(x, y, widthOnComponent, heightOnComponents[r]);
                        }
                    }
                }
            }
        }
    }
}
