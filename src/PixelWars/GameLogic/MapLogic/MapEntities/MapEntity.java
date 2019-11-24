package PixelWars.GameLogic.MapLogic.MapEntities;

import PixelWars.GameLogic.Exceptions.InvalidMapCoordsException;
import PixelWars.GameLogic.MapLogic.Map;
import PixelWars.GameLogic.MapLogic.Point;
import javafx.scene.image.Image;

public abstract class MapEntity{

    private Point coords;

    protected void setCoords(Point coords) {
        try {
            map.setMapEntityAtCoords(coords,this);
            this.coords=coords;
        }
        catch (InvalidMapCoordsException e) {
            e.printStackTrace();
        }
    }

    public Point getCoords()
    {
        return coords;
    }

    private Map map;

    public void setMap(Map map)
    {
        setMap(map,map.generateRandomEligiblePoint());
    }

    public void setMap(Map map,Point coords)
    {
        this.map=map;
        setCoords(coords);
    }

    protected Map getMap()
    {
        return map;
    }

    public abstract Image getIcon();

    public abstract String getConcreteName();

}
