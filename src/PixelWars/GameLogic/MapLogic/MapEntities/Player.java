package PixelWars.GameLogic.MapLogic.MapEntities;

import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import javafx.scene.image.Image;

import java.util.Random;

public class Player extends MapEntity implements Runnable {
    private static final String[] PLAYER_COLORS = new String[]{"Red", "Orange", "Yellow", "Brown", "Green", "Cyan", "Blue", "Purple"};
    private static final String[] BEGIN_THREAT_MESSAGES = new String[]{"I'm going to spank you until time and space have no meaning!", "All resources on this battlefield will be mine!", "Challenge me and i will send you to the nothingness!", "The god of death demands its pay!", "You will be obliterated them from history!", "Please don't hurt me, i'm neutral... for now", "This is where we fight! This is where they die!", "I will defend my fortress at any cost!"};
    private String name;
    private String color;
    //private MessageLog playerLog;

    public Player(String name, String color) {
        super(-1, -1);
        this.name = name;
        this.color = color;
        //this.playerLog = MessagingSystem.MESSAGE_LOG;
    }

    @Override
    public void run() {
        Random r = new Random(System.nanoTime());
        speak(BEGIN_THREAT_MESSAGES[r.nextInt(BEGIN_THREAT_MESSAGES.length)]);
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

    public void delayForDebug(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}