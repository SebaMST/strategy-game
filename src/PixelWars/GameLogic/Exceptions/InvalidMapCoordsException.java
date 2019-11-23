package PixelWars.GameLogic.Exceptions;

import PixelWars.GameLogic.MapLogic.Point;

public class InvalidMapCoordsException extends RuntimeException {
    public InvalidMapCoordsException(Point wrongCoords)
    {
        super("InvalidMapCoordsException: "+wrongCoords.getX()+" "+wrongCoords.getY());
    }
}
