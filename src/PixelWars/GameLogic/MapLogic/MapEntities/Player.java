package PixelWars.GameLogic.MapLogic.MapEntities;

import PixelWars.GUI.Events.EventBroadcaster;
import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.Resource;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import PixelWars.GameLogic.MapLogic.MapCreation.Map;
import javafx.scene.image.Image;

import java.util.*;

public class Player extends MapEntity implements Runnable {
    private static final String[] PLAYER_COLORS = new String[]{"Red", "Orange", "Yellow", "Brown", "Green", "Cyan", "Blue", "Purple"};
    private static final String[] BEGIN_THREAT_MESSAGES = new String[]{"I'm going to spank you until time and space have no meaning!", "All resources on this battlefield will be mine!", "Challenge me and i will send you to the nothingness!", "The god of death demands its pay!", "You will be obliterated them from history!", "Please don't hurt me, i'm neutral... for now", "This is where we fight! This is where they die!", "I will defend my fortress at any cost!"};
    private String name;
    private String color;
    private Map map;
    private final HashMap<String,ResourceValueWrapper> resourceBar;

    public static class ResourceValueWrapper {
        private int value;
        private ResourceValueWrapper() {
            setValue(0);
        }
        public int getValue() {
            return value;
        }
        private void setValue(int value) {
            this.value=value;
            eb.notifyEventCapturers();
        }
        //Events
        private EventBroadcaster eb = new EventBroadcaster(this);

        public EventBroadcaster getEventBroadcaster()
        {
            return eb;
        }
    }

    public Player(String name, String color) {
        super(-1, -1);
        this.name = name;
        this.color = color;
        resourceBar=new LinkedHashMap<>();
        for(String res: Resource.RESOURCE_TYPES) {
            resourceBar.put(res,new ResourceValueWrapper());
        }
    }

    public void setMap(Map map)
    {
        this.map=map;
    }

    public void setPos(int posX, int posY)
    {
        try {
            System.out.println("X: " + posX + " Y: " + posY + " ");
            if (posX >= 0 && posX < map.getWidth() && posY >= 0 && posY < map.getHeight() && map.getMapTilesMatrix()[posY][posX].getTerrain().isOperational()) {
                super.setPos(posX, posY);
                map.getMapTilesMatrix()[posY][posX].setMapEntity(this);
                eb.notifyEventCapturers();
            } else {
                throw new IllegalStateException();
            }
        }catch(ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
    }

    //Events
    private EventBroadcaster eb = new EventBroadcaster(this);

    public EventBroadcaster getEventBroadcaster()
    {
        return eb;
    }

    public HashMap<String,ResourceValueWrapper> getResourceBar() {
        return resourceBar;
    }

    public void updateResourceValueInsideResourceBar(String resourceName, int resourceValue)
    {
        resourceBar.get(resourceName).setValue(resourceValue);
    }

    @Override
    public void run() {
        Random r = new Random(System.nanoTime());
        speak(BEGIN_THREAT_MESSAGES[r.nextInt(BEGIN_THREAT_MESSAGES.length)]);
        try {
            int direction=1;
            while (true) {
                setPos(getPosX() + direction, getPosY());
                delayForDebug(100, 0);
            }
        }catch(InterruptedException | IllegalArgumentException e)
        {
            stop();
        }
    }

    private void speak(String s) {
        MessagingSystem.chat(this.name + " (" + color + ")", s);
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public Image getIcon() {
        return ImageLoader.getIcon("Player", color);
    }

    public static String[] getPlayersColors() {
        return PLAYER_COLORS;
    }

    //THREADING
    private boolean isStarted = false;
    private Thread thread;

    public void start() {
        if (!isStarted) {
            //System.out.println(toString()+" thread is stopped. We are trying to start it");
            isStarted = true;
            thread = new Thread(this);
            thread.start();
            //System.out.println(toString() + " thread STARTED");
        }
    }

    public void stop() {
        if (isStarted) {
            //System.out.println(toString() + " thread is started. We are trying to stop it");
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            isStarted = false;
            //System.out.println(toString() + " thread STOPPED");
        }

    }

    public void delayForDebug(long millis,int nanos) throws InterruptedException {
        Thread.sleep(millis,nanos);
    }
}