package PixelWars.GameLogic.MapLogic.TerrainCreation.Terrains;

import javafx.scene.paint.Color;

public class WaterDeep extends Terrain {
    protected Color myColor() {
        return Color.rgb(0, 93, 244);
    }

    public boolean isOperational() {
        return false;
    }
}
