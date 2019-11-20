package PixelWars.GameLogic.MapLogic.MapCreation.TerrainCreation.Terrains;

import javafx.scene.paint.Color;

public class Grass extends Terrain {
    protected Color myColor() {
        return Color.GREEN;
    }

    public boolean isOperational() {
        return true;
    }
}
