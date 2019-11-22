package PixelWars.GameLogic.MapLogic.TerrainCreation.Terrains;

import javafx.scene.paint.Color;

public class MountainPeak extends Terrain {
    protected Color myColor() {
        return Color.WHITESMOKE;
    }

    public boolean isOperational() {
        return false;
    }
}
