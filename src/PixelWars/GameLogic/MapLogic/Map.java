package PixelWars.GameLogic.MapLogic;

import PixelWars.GUI.Events.EventBroadcaster;
import PixelWars.GameLogic.MapLogic.MapEntities.MapEntity;
import PixelWars.GameLogic.MapLogic.TerrainCreation.Terrains.Terrain;

import java.util.Random;

public class Map {

    private MapTile[][] mapTilesMatrix;
    private int width, height;

    Map(MapTile[][] mapTilesMatrix) {
        this.mapTilesMatrix = mapTilesMatrix;
        this.width = mapTilesMatrix[0].length;
        this.height = mapTilesMatrix.length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized boolean isFreeAtCoords(Point coords) {
        if(areCoordsValid(coords)) {
                return mapTilesMatrix[coords.getY()][coords.getX()].isFree();
        }
        else {
            throw new IllegalArgumentException(Thread.currentThread().toString() + "-> Map: isFreeAtCoords(Point): coords not valid");
        }
    }

    public synchronized MapEntity getMapEntityAtCoords(Point coords) {
        if(areCoordsValid(coords)) {
            return mapTilesMatrix[coords.getY()][coords.getX()].getMapEntity();
        }
        else {
            throw new IllegalArgumentException(Thread.currentThread().toString()+"-> Map: getMapEntityAtCoords(Point): coords not valid");
        }
    }

    public synchronized void setMapEntityAtCoords(Point coords, MapEntity mapEntity) {
        if(areCoordsValid(coords)) {
            mapTilesMatrix[coords.getY()][coords.getX()].setMapEntity(mapEntity);
        }
        else {
            throw new IllegalArgumentException(Thread.currentThread().toString()+"-> Map: updateMapEntityAtCoords(Point,MapEntity): coords not valid");
        }
    }

    public boolean isOperationalAtCoords(Point coords) {
        if(areCoordsValid(coords)) {
                return mapTilesMatrix[coords.getY()][coords.getX()].isOperational();
        }
        else {
            throw new IllegalArgumentException(Thread.currentThread().toString()+"-> Map: isOperationalAtCoords(Point): coords not valid");
        }
    }

    public Terrain getTerrainAtCoords(Point coords)
    {
        if(areCoordsValid(coords)) {
            return mapTilesMatrix[coords.getY()][coords.getX()].getTerrain();
        }
        else {
            throw new IllegalArgumentException(Thread.currentThread().toString()+"-> Map: getTerrainAtCoords(Point): coords not valid");
        }
    }

    public boolean areCoordsValid(Point coords) {
        int x=coords.getX();
        int y=coords.getY();
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Point generateRandomEligiblePoint()
    {
        Random r = new Random();
        //long t = System.currentTimeMillis(); COUNTER IN CASE THE MAP IS FULL OF ENTITIES OR IS FULL UNOPERATIONAL
        Point pos;
        do {
            /*if(System.currentTimeMillis()-t>5000)
                throw new IllegalStateException("Couldn't find an eligible point on the map.");*/
            pos=new Point(r.nextInt(width),r.nextInt(height));
        } while (!isOperationalAtCoords(pos)||!isFreeAtCoords(pos)); //Rule that declines a randomly generated point

        return pos;
    }

    //region EVENTS
    public EventBroadcaster getEventBroadcasterAtCoords(Point coords)
    {
        if(areCoordsValid(coords))
        {
            return mapTilesMatrix[coords.getY()][coords.getX()].getEventBroadcaster();
        }
        else {
            throw new IllegalArgumentException(Thread.currentThread().toString()+"-> Map: getEventBroadcasterAtCoords(Point,MapEntity): coords not valid");
        }

    }
    //endregion
}