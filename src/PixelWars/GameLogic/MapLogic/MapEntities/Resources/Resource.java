package PixelWars.GameLogic.MapLogic.MapEntities.Resources;

import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;

import java.util.LinkedList;
import java.util.Set;

public abstract class Resource extends MapEntity {
    public static final String[] RESOURCE_TYPES = {"Food","Wood","Stone","Iron","Gold"};
    private int hp;

    public Resource(int posX, int posY) {
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



