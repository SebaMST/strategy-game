package PixelWars.GameLogic.MapLogic.TerrainCreation;

import PixelWars.GameLogic.MapLogic.MapTile;
import PixelWars.GameLogic.MapLogic.TerrainCreation.PerlinNoiseGeneration.NoiseGenerator;
import PixelWars.GameLogic.MapLogic.TerrainCreation.Terrains.*;

public class TerrainBuilder {
    private static final double[] TERRAIN_HEIGHTS = new double[]{0.2D,0.3D,0.33D,0.58D,0.83D,0.91D,1.0D};
    private static final Terrain[] TERRAINS = new Terrain[]{new WaterDeep(),new Water(),new Sand(), new Grass(), new Hill(), new Mountain(),new MountainPeak()};

    private static double[][] createTerrainHeightsMatrix(int width, int height)
    {
        return NoiseGenerator.generateNoiseMatrix(width, height, 70, 5, 0.5D, 1.7D);
    }
    public static MapTile[][] createMapTilesMatrix(int width, int height)
    {
        double[][] terrainHeightsMatrix = createTerrainHeightsMatrix(width,height);
        MapTile[][] mapTilesMatrix = new MapTile[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int h = 0; h < TERRAIN_HEIGHTS.length; h++) {
                    if (terrainHeightsMatrix[i][j] <= TERRAIN_HEIGHTS[h]) {
                            mapTilesMatrix[i][j]=new MapTile(TERRAINS[h]);
                        break;
                    }
                }
            }
        }
        return mapTilesMatrix;
    }
}
