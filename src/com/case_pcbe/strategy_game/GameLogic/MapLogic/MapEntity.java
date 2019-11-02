package com.case_pcbe.strategy_game.GameLogic.MapLogic;

import javafx.scene.paint.Color;

import javax.annotation.Resource;

public abstract class MapEntity {
    private int posX;
    private int posY;
    Color color;

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

abstract class ResourceBank extends MapEntity {
    private int hp;

    public ResourceBank(int posX, int posY) {
        super(posX, posY);
        this.hp = getHP();
    }

    protected abstract int getHP();
}

class Food extends ResourceBank {
    public Food(int posX, int posY) {
        super(posX, posY);
    }

    public int getHP() {
        return 100;
    }

    public Color getColor() {
        return Color.DARKRED;
    }
}

class Wood extends ResourceBank {
    public Wood(int posX, int posY) {
        super(posX, posY);
    }

    public int getHP() {
        return 90;
    }

    public Color getColor() {
        return Color.BROWN;
    }
}

class Stone extends ResourceBank {
    public Stone(int posX, int posY) {
        super(posX, posY);
    }

    public int getHP() {
        return 75;
    }

    public Color getColor() {
        return Color.GREENYELLOW;
    }
}

class Iron extends ResourceBank {
    public Iron(int posX, int posY) {
        super(posX, posY);
    }

    public int getHP() {
        return 50;
    }

    public Color getColor() {
        return Color.DARKSLATEBLUE;
    }
}

class Gold extends ResourceBank {
    public Gold(int posX, int posY) {
        super(posX, posY);
    }

    public int getHP() {
        return 50;
    }

    public Color getColor() {
        return Color.GOLD;
    }
}

