package PixelWars.GameLogic.MapLogic.MapEntities.Buildings;

import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.MapEntities.Player;
import PixelWars.GameLogic.Messaging.GlobalSpeaker;
import javafx.scene.image.Image;

public abstract class Building extends MapEntity implements Runnable, GlobalSpeaker {
    private Player owner;

    public Building(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    @Override
    public Image getIcon() {
        return ImageLoader.getIcon(getConcreteName().toLowerCase(), owner.getColor());
    }

    //region Messaging
    public void speak(String message) {
        GlobalSpeaker.super.speak(message);
    }
    //endregion

    //region THREADING
    private boolean isProdctuionThreadStarted = false;
    private Thread productionThread;

    protected boolean getIsProductionThreadStarted()
    {
        return isProdctuionThreadStarted;
    }

    public void startProductionthread() {
        if (!isProdctuionThreadStarted) {
            //System.out.println(toString()+" thread is stopped. We are trying to start it");
            isProdctuionThreadStarted = true;
            productionThread = new Thread(this);
            productionThread.start();
            //System.out.println(toString() + " thread STARTED");
        }
    }

    public void interruptProductionThread() {
        if (isProdctuionThreadStarted) {
            isProdctuionThreadStarted = false;
        }
        productionThread.interrupt();
    }

    public void joinProductionThread() {
        try {
            productionThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}