package PixelWars.GameLogic.MapLogic.MapCreation.TerrainCreation.Terrains;

import javafx.scene.paint.Color;

public abstract class Terrain {
    private final Color color = myColor();

    public Color getColor() {
        return color;
    }

    public abstract boolean isOperational();

    protected abstract Color myColor();
}
