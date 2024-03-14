package com.juanmuscaria.nuke.ui.component;


import javax.swing.*;
import java.awt.*;

public class ScrollablePane extends JPanel implements Scrollable {
    private boolean scrollableTracksViewportWidth;
    private boolean scrollableTracksViewportHeight;

    public ScrollablePane(LayoutManager layout, boolean isDoubleBuffered, boolean scrollableTracksViewportWidth, boolean scrollableTracksViewportHeight) {
        this(layout, isDoubleBuffered);
        this.scrollableTracksViewportWidth = scrollableTracksViewportWidth;
        this.scrollableTracksViewportHeight = scrollableTracksViewportHeight;
    }

    public ScrollablePane(LayoutManager layout, boolean scrollableTracksViewportWidth, boolean scrollableTracksViewportHeight) {
        this(layout);
        this.scrollableTracksViewportWidth = scrollableTracksViewportWidth;
        this.scrollableTracksViewportHeight = scrollableTracksViewportHeight;
    }

    public ScrollablePane(boolean isDoubleBuffered, boolean scrollableTracksViewportWidth, boolean scrollableTracksViewportHeight) {
        this(isDoubleBuffered);
        this.scrollableTracksViewportWidth = scrollableTracksViewportWidth;
        this.scrollableTracksViewportHeight = scrollableTracksViewportHeight;
    }

    public ScrollablePane(boolean scrollableTracksViewportWidth, boolean scrollableTracksViewportHeight) {
        this();
        this.scrollableTracksViewportWidth = scrollableTracksViewportWidth;
        this.scrollableTracksViewportHeight = scrollableTracksViewportHeight;
    }

    public ScrollablePane(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public ScrollablePane(LayoutManager layout) {
        super(layout);
    }

    public ScrollablePane(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public ScrollablePane() {
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return this.getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return getScrollableBlockIncrement(visibleRect, orientation, direction) / 10;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        if (orientation == SwingConstants.VERTICAL) {
            return visibleRect.height;
        } else {
            return visibleRect.width;
        }
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return scrollableTracksViewportWidth || (this.getParent().getWidth() + 1 > this.getSize().width);
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return scrollableTracksViewportHeight || (this.getParent().getHeight() + 1 > this.getSize().height);
    }
}
