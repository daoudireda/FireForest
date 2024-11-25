package org.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

public class FireSimulationBFS {
    private static int[][] grid; // 0: Unburnt, 1: On fire, 2: Ashes
    private static int height; // Number of rows
    private static int width; // Number of columns
    private static double probability; // Probability of fire propagation
    private static Queue<Cell> fireQueue = new LinkedList<>(); // Cells on fire, stored as (x, y, time)
    private static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up


    public static void loadConfig(String filePath) throws IOException {
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            props.load(input);
        }

        height = Integer.parseInt(props.getProperty("height"));
        width = Integer.parseInt(props.getProperty("width"));
        probability = Double.parseDouble(props.getProperty("propagationProbability"));

        grid = new int[height][width];

        // Initialize fire start positions
        String[] startPositions = props.getProperty("fireStart").split(";");
        for (String position : startPositions) {
            String[] coordinates = position.split(",");
            int startX = Integer.parseInt(coordinates[0]);
            int startY = Integer.parseInt(coordinates[1]);
            grid[startX][startY] = 1; // Fire state
            fireQueue.add(new Cell(startX, startY, 0));
        }
    }

    public static int[] simulateFire() {
        int ashes = 0; // Number of cells reduced to ashes
        int steps = 0; // Number of steps elapsed

        // BFS to simulate fire propagation in the forest
        while (!fireQueue.isEmpty()) {
            // Burn the current cell
            Cell current = fireQueue.poll();
            int x = current.x;
            int y = current.y;
            // Update the number of steps elapsed
            steps = Math.max(steps, current.time);

            // Burn the current cell to ashes
            //"Le feu s'éteint dans cette case (la case est remplie de cendre et ne peut ensuite plus brûler)"
            grid[x][y] = 2; // Ashes
            ashes++;

            // Spread fire to adjacent cells
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                //Il y a une probabilité p que le feu se propage à chacune des 4 cases adjacentes
                if (isValidCell(newX, newY) && grid[newX][newY] == 0) {
                    // Propagate fire to the adjacent cell
                    if (Math.random() < probability) { // Randomly decide whether to propagate fire
                        grid[newX][newY] = 1; // Set on fire
                        fireQueue.add(new Cell(newX, newY, current.time + 1)); // Add the cell to the queue
                    }
                }
            }
        }

        // Return the number of cells reduced to ashes and the number of steps elapsed
        return new int[]{ashes, steps};
    }

    /**
     * Check if the cell is within the grid and not burnt yet
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if the cell is valid, false otherwise
     */
    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < width;
    }
}
