package PixelWars.GameLogic.MapLogic.MapCreation.TerrainCreation.Terrains;

import javafx.scene.paint.Color;

public class Mountain extends Terrain {
    protected Color myColor() {
        return Color.DARKGREY;
    }

    public boolean isOperational() {
        return true;
    }
}
