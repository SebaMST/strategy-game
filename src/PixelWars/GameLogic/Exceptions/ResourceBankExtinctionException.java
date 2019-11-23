package PixelWars.GameLogic.Exceptions;

import PixelWars.GameLogic.MapLogic.MapEntities.Resources.ResourceBank;

public class ResourceBankExtinctionException extends RuntimeException {
    private String extinctResource;
    public ResourceBankExtinctionException(String rb)
    {
        super("ResourceBankExtinctionException: "+rb);
        this.extinctResource=rb;
    }
    public String getExtinctResource()
    {
        return extinctResource;
    }
}