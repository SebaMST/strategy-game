package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;

public abstract class ResourceBank extends MapEntity {
    private int hp;

    public ResourceBank(int posX, int posY) {
        super(posX, posY);
        this.hp = initHP();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    protected abstract int initHP();
}



