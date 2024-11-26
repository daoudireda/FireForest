package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulationFireForestBFSTest {


    @BeforeEach
    void setUp() throws IOException {
        Properties props = new Properties();
        props.setProperty("height", "5");
        props.setProperty("width", "5");
        props.setProperty("propagationProbability", "0.5");
        props.setProperty("fireStart", "1,1;2,2");

        try (OutputStream out = new FileOutputStream("src/test/java/configTest.properties")) {
            props.store(out, null);
        }


    }

    @Test
    void testLoadConfig() throws IOException {
        FireSimulationBFS.loadConfig("src/test/java/configTest.properties");
        assertEquals(5, FireSimulationBFS.getHeight(), "Incorrect height.");
        assertEquals(5, FireSimulationBFS.getWidth(), "Incorrect width.");
        assertEquals(0.5, FireSimulationBFS.getProbability(), "Incorrect probability.");
        int[][] grid = FireSimulationBFS.getGrid();
        assertEquals(1, grid[1][1], "Incorrect fire state at (1,1).");
        assertEquals(1, grid[2][2], "Incorrect fire state at (2,2).");
    }


    @Test
    void testSimulationFirePropagation() throws IOException {
        FireSimulationBFS.loadConfig("src/test/java/configTest.properties");
        int[] results = FireSimulationBFS.simulateFire();
        // Validate the number of burned cells and steps

        assertEquals(2, results.length, "Incorrect number of results.");
        assertTrue(results[0] > 0, "No huts were burnt to the ground.");
        assertTrue(results[1] > 0, "Incorrect number of steps.");
    }


}
