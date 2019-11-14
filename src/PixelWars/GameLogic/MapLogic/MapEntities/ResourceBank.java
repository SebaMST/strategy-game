package PixelWars.GameLogic.MapLogic.MapEntities;

import javafx.scene.paint.Color;

public abstract class ResourceBank extends MapEntity {
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