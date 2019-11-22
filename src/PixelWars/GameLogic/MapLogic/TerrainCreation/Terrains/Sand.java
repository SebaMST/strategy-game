package PixelWars.GameLogic.MapLogic.TerrainCreation.Terrains;

import javafx.scene.paint.Color;

public class Sand extends Terrain {
    protected Color myColor() {
        return Color.BEIGE;
    }

    public boolean isOperational() {
        return true;
    }
}
