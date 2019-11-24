package PixelWars.GameLogic.Exceptions;

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