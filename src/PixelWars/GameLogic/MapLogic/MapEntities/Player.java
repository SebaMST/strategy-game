package PixelWars.GameLogic.MapLogic.MapEntities;

import PixelWars.GUI.Events.EventBroadcaster;
import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.MapLogic.Map;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Building;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.ResourceBank;
import PixelWars.GameLogic.MapLogic.Point;
import PixelWars.GameLogic.Messaging.GlobalSpeaker;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import javafx.scene.image.Image;

import java.util.*;

public class Player extends MapEntity implements Runnable, GlobalSpeaker {
    public static final String[] PLAYER_COLORS = new String[]{"red", "orange", "yellow", "brown", "green", "cyan", "blue", "purple"};
    private final String name;
    private final String color;
    private final HashMap<String, ResourceValueWrapper> resourceBar;

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
        this.name = name;
        this.color = color;
        resourceBar=new LinkedHashMap<>();
        for(String res: ResourceBank.RESOURCEBANK_TYPES) {
            resourceBar.put(res,new ResourceValueWrapper());
        }
    }

    private void moveTo(Point coords) {
        if(Point.equals(this.getCoords(),coords))
            return;
        Map map = getMap();
        if(map.isFreeAtCoords(coords)) {
            if(map.isOperationalAtCoords(coords)) {
                map.setMapEntityAtCoords(getCoords(),null);
                setCoords(coords);
                map.setMapEntityAtCoords(coords,this);
                eb.notifyEventCapturers();
            }
        }
    }

    private Point searchForResource(String resourceName)
    {
        Map map = getMap();
        int[] translateX={-1,0,1,0};
        int[] translateY={0,1,0,-1};
        boolean[][] marked = new boolean[map.getHeight()][map.getWidth()];

        Queue<Integer> pointXQueue = new LinkedList<>();
        Queue<Integer> pointYQueue = new LinkedList<>();

        pointXQueue.add(getCoords().getX()); pointYQueue.add(getCoords().getY());

        Point crtPoint, newPoint;
        int crtX, crtY,newX,newY;
        while(!(pointXQueue.isEmpty()&&pointYQueue.isEmpty()))
        {
            crtX=pointXQueue.remove();
            crtY=pointYQueue.remove();
            crtPoint = new Point(crtX,crtY);

            if(!map.isFreeAtCoords(crtPoint))
            {   MapEntity currentEntity = map.getMapEntityAtCoords(crtPoint);
                if((currentEntity instanceof ResourceBank || currentEntity instanceof Building)&&currentEntity.getConcreteName().equals(resourceName) /*&& (Arrays.binarySearch(((ProductionHandler) currentEntity).getProduction(),resourceName)>=0)*/) {
                    if (neighbourFreePoint(crtPoint) != null)
                        return crtPoint;
                }
            }

            for(int i=0;i<translateX.length;i++)
            {
                newX=translateX[i]+crtX;
                newY=translateY[i]+crtY;
                newPoint = new Point(newX,newY);

                if(map.areCoordsValid(newPoint)&&!marked[newY][newX])
                {
                    marked[newY][newX]=true;
                    pointXQueue.add(newX);
                    pointYQueue.add(newY);
                }
            }
        }
        return null;
    }

    private Point neighbourFreePoint(Point where)
    {
        Map map = getMap();
        int[] translateX={-1,0,1,0};
        int[] translateY={0,1,0,-1};
        int crtX=where.getX(),crtY=where.getY(),newX,newY;
        Point newPoint;
        for(int i=0;i<translateX.length;i++)
        {
            newX=translateX[i]+crtX;
            newY=translateY[i]+crtY;
            newPoint = new Point(newX,newY);
            if(map.areCoordsValid(newPoint)) {
                if(map.isOperationalAtCoords(newPoint))

                    if (map.isFreeAtCoords(newPoint)||Point.equals(getCoords(),newPoint)) {
                        return newPoint;
                    }
            }
        }
        return null;
    }

    public synchronized HashMap<String, ResourceValueWrapper> getResourceBar()
    {
        return resourceBar;
    }

    private synchronized void updateResourceBar(String resourceName, int resourcecamount) {
        ResourceValueWrapper rvw = resourceBar.get(resourceName);
        if(rvw!=null)
        {
            int futureAmount=rvw.getValue()+resourcecamount;
            if(futureAmount>=0)
            {
                rvw.setValue(futureAmount);
            }
            else {
                throw new IllegalArgumentException("Player: updateResourceBar(String resourceName,int resourceAmount): can't update "+resourceName+" on the bar with "+resourcecamount+" because the value will be lower than 0.");
            }
        }
        else {
            throw new IllegalArgumentException("Player: updateResourceBar(String resourceName,int resourceAmount): invalid resourceName("+resourceName+") to update");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public Image getIcon() {
        return ImageLoader.getIcon("player",color);
    }

    @Override
    public String getConcreteName() {
        return "Player";
    }

    //region Messaging
    public void speak(String message) {
        GlobalSpeaker.super.speak(message);
    }
    //endregion

    //region Events
    private EventBroadcaster eb = new EventBroadcaster(this);

    public EventBroadcaster getEventBroadcaster()
    {
        return eb;
    }
    //endregion

    //region THREADING
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
            isStarted = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            //System.out.println(toString() + " thread STOPPED");
        }

    }

    private void delayForDebug(long millis) {
        try {
            Thread.sleep(millis);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    //endregion

    @Override
    public void run() {
       /*

            int direction=1;
            while (isStarted) {
                try {
                    Point oldCoords = getCoords();
                    moveTo(new Point(oldCoords.getX()+direction,oldCoords.getY()));
                    delayForDebug(100, 0);
                } catch(IllegalArgumentException e)
                    {
                        isStarted=false;
                        e.printStackTrace();
                    }
            }*/
        Random r = new Random(System.nanoTime());
        speak(MessagingSystem.BEGIN_THREAT_MESSAGES[r.nextInt(MessagingSystem.BEGIN_THREAT_MESSAGES.length)]);
        boolean started=true;
       while(started) {
           Point resourcePoint;
           do {
               resourcePoint = searchForResource(ResourceBank.RESOURCEBANK_TYPES[r.nextInt(5)]);
               if (resourcePoint != null) {
                   //trebuie facut lock la resursa de cand o gaseste si aia e.
                   ResourceBank exploatable = (ResourceBank)getMap().getMapEntityAtCoords(resourcePoint);
                   String resourceName=exploatable.getConcreteName();
                   System.out.println(color + " FOUND "+ resourceName +" AT " + resourcePoint.getX() + " " + resourcePoint.getY());
                   moveTo(neighbourFreePoint(resourcePoint));
                       int amount = exploatable.exploit();
                       if (amount > 0) {
                           updateResourceBar(resourceName, amount);
                           speak("Mined from " + resourceName + " at " + resourcePoint.getX() + " " + resourcePoint.getY());
                           delayForDebug(1);
                       }
               } else {
                   System.out.println("DIDNT FIND ANYTHING");
                   started=false;
               }
           } while (resourcePoint == null);
       }
    }
}