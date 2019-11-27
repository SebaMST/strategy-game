package PixelWars.GameLogic.MapLogic.MapEntities;

import PixelWars.GUI.Events.EventBroadcaster;
import PixelWars.GUI.ImageLoader;
import PixelWars.GameLogic.Exceptions.ResourceBankExtinctionException;
import PixelWars.GameLogic.Factory.BuildingFactory;
import PixelWars.GameLogic.Factory.ResourceFactory;
import PixelWars.GameLogic.Game;
import PixelWars.GameLogic.MapLogic.Map;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.BuildRequirements;
import PixelWars.GameLogic.MapLogic.MapEntities.Buildings.Building;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.ResourceBank;
import PixelWars.GameLogic.MapLogic.Point;
import PixelWars.GameLogic.Messaging.GlobalSpeaker;
import PixelWars.GameLogic.Messaging.MessagingSystem;
import javafx.scene.image.Image;

import java.util.*;

public class Player extends MapEntity implements Runnable, GlobalSpeaker {
    private static final String[] PLAYER_COLORS = new String[]{"red", "orange", "yellow", "brown", "green", "cyan", "blue", "purple"};
    public static String[] getPlayerColors()
    {
        return PLAYER_COLORS.clone();
    }
    private final String name;
    private final String color;
    private final HashMap<String, ResourceDetailWrapper> resourceBar;
    private final HashMap<String,BuildingDetailWrapper> buildingBar;
    private final BuildingFactory buildingFactory;
    private final String[] needToCompleteObjectives=Game.getNeededObjectives();
    private final boolean[] completedObjectives=new boolean[Game.getNeededObjectives().length];
    private static int winPlace=1;

    public static class ResourceDetailWrapper {
        private int value;

        private ResourceDetailWrapper() {
            setValue(0);
        }

        public synchronized int getValue() {
            return value;
        }

        private synchronized void setValue(int value) {
            this.value = value;
            eb.notifyEventCapturers();
        }

        //region EVENTS
        private EventBroadcaster eb = new EventBroadcaster(this);

        public EventBroadcaster getEventBroadcaster() {
            return eb;
        }
        //endregion
    }

    public static class BuildingDetailWrapper {
        private List<Building> buildingList;

        private BuildingDetailWrapper() {
            buildingList=new LinkedList<>();
        }

        public List<Building> getBuildingList() {
            return buildingList;
        }

        private void addToBuildingList(Building b) {
            buildingList.add(b);
            eb.notifyEventCapturers();
        }

        //region EVENTS
        private EventBroadcaster eb = new EventBroadcaster(this);

        public EventBroadcaster getEventBroadcaster() {
            return eb;
        }
        //endregion
    }

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        resourceBar = new LinkedHashMap<>();
        buildingFactory=new BuildingFactory(this);
        buildingBar=new HashMap<>();
        for (String res : ResourceFactory.getResourceTypes()) {
            resourceBar.put(res, new ResourceDetailWrapper());
        }
        for(String building: BuildingFactory.getBuildingTypes()) {
            buildingBar.put(building,new BuildingDetailWrapper());
        }
    }

    private void moveTo(Point coords) {
        if (Point.equals(this.getCoords(), coords))
            return;
        Map map = getMap();
        synchronized (map) { //safe to ignore warning
            if (map.isFreeAtCoords(coords)) {
                if (map.isOperationalAtCoords(coords)) {
                    map.setMapEntityAtCoords(getCoords(), null);
                    setCoords(coords);
                    map.setMapEntityAtCoords(coords, this);
                    eb.notifyEventCapturers();
                }
            }
        }
    }

    private void searchForResource(String resourceName) {
        Map map = getMap();
        int[] translateX = {-1, 0, 1, 0};
        int[] translateY = {0, 1, 0, -1};
        boolean[][] marked = new boolean[map.getHeight()][map.getWidth()];

        Queue<Integer> pointXQueue = new LinkedList<>();
        Queue<Integer> pointYQueue = new LinkedList<>();

        pointXQueue.add(getCoords().getX());
        pointYQueue.add(getCoords().getY());

        Point crtPoint, newPoint;
        int crtX, crtY, newX, newY;
        while (!(pointXQueue.isEmpty() && pointYQueue.isEmpty())) {
            crtX = pointXQueue.remove();
            crtY = pointYQueue.remove();
            crtPoint = new Point(crtX, crtY);

            if ((!map.isFreeAtCoords(crtPoint))&&(map.isOperationalAtCoords(crtPoint))) {
                    MapEntity currentEntity = map.getMapEntityAtCoords(crtPoint);
                    if (currentEntity instanceof ResourceBank && currentEntity.getConcreteName().equals(resourceName)) {
                        if (!map.areNeighbours(this, currentEntity)) {
                            //System.out.println(getColor()+" NOT NEIGHBOUR WITH "+currentEntity.getConcreteName()+" "+currentEntity.getCoords().getX()+","+currentEntity.getCoords().getY());
                            Point neighbourFree = getMap().neighbourFreeInhabitablePoint(crtPoint);
                            if (neighbourFree != null) {
                                moveTo(neighbourFree);
                                if (map.getMapEntityAtCoords(crtPoint) != null) {
                                    int amount = ((ResourceBank) currentEntity).exploit();
                                    updateResourceBar(resourceName, amount);
                                    speak("Mined " + amount + " from " + resourceName + " at " + crtPoint.getX() + " " + crtPoint.getY() + ". Now has " + resourceBar.get(resourceName).getValue() + " " + resourceName.substring(0, resourceName.length() - 12));
                                }
                                return;
                            }
                        }
                        else
                        {
                            if (map.getMapEntityAtCoords(crtPoint) != null) {
                                int amount = ((ResourceBank) currentEntity).exploit();
                                if(amount>0) {
                                    updateResourceBar(resourceName, amount);
                                    speak("Mined " + amount + " from " + resourceName + " at " + crtPoint.getX() + " " + crtPoint.getY() + ". Now has " + resourceBar.get(resourceName).getValue() + " " + resourceName.substring(0, resourceName.length() - 12));
                                }
                            }
                            return;
                        }
                    }
            }

            for (int i = 0; i < translateX.length; i++) {
                newX = translateX[i] + crtX;
                newY = translateY[i] + crtY;
                newPoint = new Point(newX, newY);

                if (map.areCoordsValid(newPoint) && !marked[newY][newX]) {
                    marked[newY][newX] = true;
                    pointXQueue.add(newX);
                    pointYQueue.add(newY);
                }
            }
        }
        throw new ResourceBankExtinctionException(resourceName);
    }

    private void build(String buildingType)
    {
        BuildRequirements br = BuildingFactory.getRequirements(buildingType);
        if(br!=null) {
            for (HashMap.Entry<String, Integer> reqBuildEntry : br.getRequiredBuildings().entrySet()) {
                if(!isPlayerThreadStarted)
                    return;
                int nrRequiredBuildings = reqBuildEntry.getValue();
                int nrOwnedBuildings = buildingBar.get(reqBuildEntry.getKey()).getBuildingList().size();
                if (nrRequiredBuildings > nrOwnedBuildings) {
                    //System.out.println("DONT HAVE REQUIRED BUILDINGS FOR "+buildingType);
                    int nrToBuild = nrRequiredBuildings - nrOwnedBuildings;
                    for (int i = 0; i < nrToBuild; i++) {
                        //System.out.println("NEED TO BUILD "+reqBuildEntry.getKey());
                        build(reqBuildEntry.getKey());
                    }
                }
            }
            for(HashMap.Entry<String, Integer> reqResEntry : br.getRequiredResources().entrySet()) {
                if(!isPlayerThreadStarted)
                    return;
                int nrRequiredResourceCount=reqResEntry.getValue();
                ResourceDetailWrapper ownedResourceCountWrapper = resourceBar.get(reqResEntry.getKey());
                int nrOwnedResourceCount=ownedResourceCountWrapper.getValue();
                while(nrRequiredResourceCount>nrOwnedResourceCount) {
                        searchForResource(reqResEntry.getKey());
                        rest(40);
                        nrOwnedResourceCount=ownedResourceCountWrapper.getValue();
                }
            }
            Point buildCoords=getMap().neighbourFreeInhabitablePoint(getCoords()); //search if can build nearby actual position
            if(buildCoords==null||getMap().getMapEntityAtCoords(buildCoords)!=null) //este we need another spot
            {
                Point whereToMove;
                do
                {
                    whereToMove=getMap().generateRandomEligiblePoint(); //trying to go to another random spot
                    buildCoords=getMap().neighbourFreeInhabitablePoint(whereToMove); //and check the neighbours there, if it doesnt have any neighbour free, to that again
                }while(buildCoords==null);
                moveTo(whereToMove);//finally we move to a good spot
            }

            Building b = buildingFactory.create(buildingType);
            b.setMap(getMap(),buildCoords);
            buildingBar.get(buildingType).addToBuildingList(b);
            StringBuilder needs= new StringBuilder(" |");
            for(HashMap.Entry<String,Integer> reqResEntry: br.getRequiredResources().entrySet()) {
                needs.append(reqResEntry.getKey(), 0, reqResEntry.getKey().length() - 12).append(" ").append(reqResEntry.getValue()).append("|");
                updateResourceBar(reqResEntry.getKey(),-reqResEntry.getValue());
            }
            speak("Built " + buildingType +  " at " + buildCoords.getX() + " " + buildCoords.getY() + " (costed:"+needs+"). Now has " + buildingBar.get(buildingType).getBuildingList().size() + " x " + buildingType);
            b.startProductionthread();
            rest(80);
        }
        else throw new IllegalStateException("Player build(String buildingType): invalid building type. can't retrieve requirements");
    }

    public synchronized HashMap<String, ResourceDetailWrapper> getResourceBar() {
        return resourceBar;
    }

    public synchronized void updateResourceBar(String resourceName, int resourceAmount) {
        ResourceDetailWrapper rvw = resourceBar.get(resourceName);
        if (rvw != null) {
            int futureAmount = rvw.getValue() + resourceAmount;
            if (futureAmount >= 0) {
                rvw.setValue(futureAmount);
            } else {
                throw new IllegalArgumentException("Player: updateResourceBar(String resourceName,int resourceAmount): can't update " + resourceName + " on the bar with " + resourceAmount + " because the value will be lower than 0.");
            }
        } else {
            throw new IllegalArgumentException("Player: updateResourceBar(String resourceName,int resourceAmount): invalid resourceName(" + resourceName + ") to update");
        }
    }

    public HashMap<String,BuildingDetailWrapper> getBuildingBar() {
        return buildingBar;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public Image getIcon() {
        return ImageLoader.getIcon("player", color);
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

    public EventBroadcaster getEventBroadcaster() {
        return eb;
    }
    //endregion

    //region THREADING
    private boolean isPlayerThreadStarted = false;
    private Thread playerThread;

    public void startPlayerThread() {
        if (!isPlayerThreadStarted) {
            //System.out.println(toString()+" thread is stopped. We are trying to start it");
            isPlayerThreadStarted = true;
            playerThread = new Thread(this);
            playerThread.start();
            //System.out.println(toString() + " thread STARTED");
        }
    }

    public void interruptPlayerThread() {
        if (isPlayerThreadStarted) {
            //System.out.println(toString() + " thread is started. We are trying to stop it");
            isPlayerThreadStarted = false;
            //System.out.println(toString() + " thread STOPPED");
        }
        playerThread.interrupt();
    }

    public void joinPlayerthread() {
        try {
            playerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void rest(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
    //endregion

    @Override
    public void run() {
        Random r = new Random(System.nanoTime());
        speak(MessagingSystem.BEGIN_THREAT_MESSAGES[r.nextInt(MessagingSystem.BEGIN_THREAT_MESSAGES.length)]);
        rest(1);

        boolean noResourceLeft=false;
        while(!noResourceLeft&&!gameCompleted()&& isPlayerThreadStarted) {
            int randomIndex = r.nextInt(needToCompleteObjectives.length);
            if (completedObjectives[randomIndex])
                continue;
            try {
                String nextToBuild = needToCompleteObjectives[randomIndex];
                build(nextToBuild);
                completedObjectives[randomIndex]=true;
            }catch(ResourceBankExtinctionException e) {
                noResourceLeft = true;
                speak("This land is DESOLATE! My empire can't continue living here, we're out!");
                e.printStackTrace();
            }
        }

        for(HashMap.Entry<String,BuildingDetailWrapper> entry: buildingBar.entrySet())
        {
            List<Building> buildings=entry.getValue().getBuildingList();
            for(Building b: buildings)
            {
                b.interruptProductionThread();
                b.joinProductionThread();
            }
        }

        if(gameCompleted())
        {
            synchronized (Player.class) {
                speak("Has completed all the needed objectives on place #" + winPlace);
                winPlace++;
            }
        }
        /*String[] gameResources = ResourceFactory.getResourceTypes();
        HashMap<String,Boolean> extinctResources = new HashMap<>();
        for(String res: gameResources)
        {
            extinctResources.put(res,false);
        }
        boolean noResourceLeft=false;
        do {
            try {
                String resourceName = gameResources[r.nextInt(gameResources.length)];
                if (!extinctResources.get(resourceName)) {
                    searchForResource(resourceName);
                    delayForDebug(25);
                }
            } catch (ResourceBankExtinctionException e) {
                extinctResources.replace(e.getExtinctResource(), true);
                noResourceLeft = true;
                for (HashMap.Entry<String, Boolean> entry : extinctResources.entrySet()) {
                    if (!entry.getValue()) {
                        noResourceLeft = false;
                        break;
                    }
                }
            }
        } while (isStarted&&!noResourceLeft); //While there are resources left mine mine mine...
        */
    }

    private boolean gameCompleted()
    {
        for(boolean b : completedObjectives)
        {
            if(!b)
                return false;
        }
        return true;
    }
}