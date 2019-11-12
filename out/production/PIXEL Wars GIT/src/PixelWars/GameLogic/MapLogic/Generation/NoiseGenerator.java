package PixelWars.GameLogic.MapLogic.Generation;

import java.util.Random;

public class NoiseGenerator {
    public NoiseGenerator() {
    }

    public static double[][] generateNoiseMatrix(int width, int height, int seed, double scale, int octaves, double persistance, double lacunarity) {
        double[][] noiseMatrix = new double[height][width];
        Random r = new Random(seed);
        double[][] octaveOffsets = new double[octaves][2];

        int y;
        for (y = 0; y < octaves; ++y) {
            double offsetX = r.nextInt(200000) - 100000;
            double offsetY = r.nextInt(200000) - 100000;
            octaveOffsets[y][0] = offsetX;
            octaveOffsets[y][1] = offsetY;
        }

        if (scale <= 0.0D) {
            throw new IllegalArgumentException("Scale must be greater than 0!");
        } else if (persistance > 0.0D && persistance < 1.0D) {
            double minim;
            for (y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    minim = 1.0D;
                    double frequency = 1.0D;
                    double noiseHeight = 0.0D;

                    for (int i = 0; i < octaves; ++i) {
                        double sampleX = (double) x / scale * frequency + octaveOffsets[i][0];
                        double sampleY = (double) y / scale * frequency + octaveOffsets[i][1];
                        double perlinValue = ImprovedNoise.noise(sampleX, sampleY, 0.0D);
                        noiseHeight += perlinValue * minim;
                        minim *= persistance;
                        frequency *= lacunarity;
                    }

                    noiseMatrix[y][x] = noiseHeight;
                }
            }

            double maxim = Double.MIN_VALUE;
            minim = Double.MAX_VALUE;

            int j;
            int i;
            for (i = 0; i < height; ++i) {
                for (j = 0; j < width; ++j) {
                    if (noiseMatrix[i][j] > maxim) {
                        maxim = noiseMatrix[i][j];
                    }

                    if (noiseMatrix[i][j] < minim) {
                        minim = noiseMatrix[i][j];
                    }
                }
            }

            for (i = 0; i < height; ++i) {
                for (j = 0; j < width; ++j) {
                    noiseMatrix[i][j] = (noiseMatrix[i][j] - minim) / (maxim - minim);
                }
            }

            return noiseMatrix;
        } else {
            throw new IllegalArgumentException("Persistance must be between 0 and 1!");
        }
    }
}
