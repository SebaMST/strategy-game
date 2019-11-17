package PixelWars.GameLogic.MapLogic.MapEntities;

import javafx.scene.image.Image;

public abstract class MapEntity {
    private int posX;
    private int posY;


    public MapEntity(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPos(int posX, int posY) {
        this.posX=posX;
        this.posY=posY;
    }

    public abstract Image getIcon();
}

