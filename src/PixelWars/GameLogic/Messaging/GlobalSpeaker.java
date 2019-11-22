package PixelWars.GameLogic.Messaging;

public interface GlobalSpeaker {
    default void speak(String what)
    {
        MessagingSystem.chat(this,what);
    }
}
