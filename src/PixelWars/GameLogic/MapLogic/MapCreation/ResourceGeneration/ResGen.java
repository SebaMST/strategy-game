package PixelWars.GameLogic.MapLogic.MapCreation.ResourceGeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import PixelWars.GameLogic.MapLogic.MapCreation.Map;
import PixelWars.GameLogic.MapLogic.MapEntities.Resources.Wood;
import PixelWars.GameLogic.MapLogic.MapTile;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Point)) return false;
        Point o = (Point) obj;
        return o.x == this.x && o.y == this.y;
    }

}

public class ResGen {

    private int forest = 10;
    private int food = 6;
    private int mining = 4;

    /*
    public static void startGen(Map map, String density) {



        int[][] resMap = new int[map.getHeight()][map.getWidth()];
        MapTile[][] mapTiles = map.getMapTilesMatrix();

        for (int i = 0; i < map.getHeight(); i++) {
            for(int j = 0; j < map.getWidth(); j++) {
                if(!mapTiles[i][j].getTerrain().isOperational() || mapTiles[i][j].getMapEntity() != null) {
                    resMap[i][j] = -1;
                } else resMap[i][j] = 0;
            }
        }

        resMap = startGen(map.getHeight(), map.getWidth(), resMap, 20);

        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if(resMap[i][j] > 0) {
                    map.getMapTilesMatrix()[i][j].setMapEntity(new Wood(i, j));
                }
            }
        }
    } */

    public static void startGen(Map tileMap, String density, int steps) {



        int size_x = tileMap.getWidth();
        int size_y = tileMap.getHeight();

        int[][] map =  new int[size_x][size_y];

        int[] moves_x = new int[] {0, +1, 0, -1};
        int[] moves_y = new int[] {+1, 0, -1, 0};

        List<Point> candidate_points = new ArrayList<>();

        Random rand = new Random();
        int start_x = rand.nextInt(size_x);
        int start_y = rand.nextInt(size_y);

        candidate_points.add(new Point(start_x, start_y));
        map[start_x][start_y] = steps;
        tileMap.getMapTilesMatrix()[start_x][start_y].setMapEntity(new Wood(start_x, start_y));

        while(true) {

            List<Point> new_points = new ArrayList<>();

            for(Point pt : candidate_points) {
                int startx = pt.x;
                int starty = pt.y;
                int step = map[startx][starty];

                for(int i = 0; i < 4; i++) {
                    int movex = startx + moves_x[i];
                    int movey = starty + moves_y[i];

                    if(movex >= size_x || movex < 0) continue;
                    if(movey >= size_y || movey < 0) continue;
                    if(map[movex][movey] != 0
                            || !tileMap.getMapTilesMatrix()[movex][movey].getTerrain().isOperational()
                            || tileMap.getMapTilesMatrix()[movex][movey].getMapEntity() != null) continue;

                    int p = rand.nextInt(101);
                    if(p <= 2) { // grow a bunch
                        tileMap.getMapTilesMatrix()[movex][movey].setMapEntity(new Wood(movex, movey));
                        map[movex][movey] = step+3;
                        new_points.add(new Point(movex, movey));
                    }
                    if(p <= 10) { //same size
                        tileMap.getMapTilesMatrix()[movex][movey].setMapEntity(new Wood(movex, movey));
                        map[movex][movey] = step;
                        new_points.add(new Point(movex, movey));
                    } else if(p <= 80) { // decrease once
                        if(step > 1) {
                            tileMap.getMapTilesMatrix()[movex][movey].setMapEntity(new Wood(movex, movey));
                            map[movex][movey] = step - 1;
                            new_points.add(new Point(movex, movey));
                        }
                    } else { // decrease twice
                        if(step > 2) {
                            tileMap.getMapTilesMatrix()[movex][movey].setMapEntity(new Wood(movex, movey));
                            map[movex][movey] = step-2;
                            new_points.add(new Point(movex, movey));
                        }
                    }
                }
            }
            if(new_points.isEmpty()) break;
            candidate_points.clear();
            candidate_points = new_points;
        }

    }

}
