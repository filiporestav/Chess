package GUI;

import java.awt.Color;

enum GridColor {
    LIGHT(new Color(238,237,210)),
    GREEN(new Color(118,150,86)),
    ORANGE(new Color(255, 215 ,2));

    private final Color color;

    GridColor(Color color) {
        this.color = color;
    }

    public Color getCustomColor() {
        return this.color;
    }
}
