package PixelWars.GameLogic.MapLogic.MapCreation.TerrainCreation.Terrains;

import javafx.scene.paint.Color;

public class Water extends Terrain {
    protected Color myColor() {
        return Color.DODGERBLUE;
    }

    public boolean isOperational() {
        return false;
    }
}
