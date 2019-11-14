package PixelWars.GameLogic.MapLogic.MapEntities;

import javafx.scene.paint.Color;

public abstract class MapEntity {
    private int posX;
    private int posY;
    private Color color;

    public MapEntity(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.color = this.getColor();
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public abstract Color getColor();
}

