package PixelWars.GameLogic.MapLogic;

public class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public static double calculateDistance(Point p1, Point p2)
    {
        return Math.sqrt((p2.getX()-p1.getX())*(p2.getX()-p1.getX())+(p2.getY()-p1.getY())*(p2.getY()-p1.getY()));
    }

    public static boolean equals(Point p1, Point p2)
    {
        return p1.getX()==p2.getX()&&p1.getY()==p2.getY();
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }
}