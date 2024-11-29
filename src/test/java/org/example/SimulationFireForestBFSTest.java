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


    public static final String SRC_TEST_JAVA_CONFIG_TEST_PROPERTIES = "src/test/java/configTest.properties";

    @BeforeEach
    void setUp() throws IOException {
        Properties props = new Properties();
        props.setProperty("height", "5");
        props.setProperty("width", "5");
        props.setProperty("propagationProbability", "0.5");
        props.setProperty("fireStart", "1,1;2,2");

        try (OutputStream out = new FileOutputStream(SRC_TEST_JAVA_CONFIG_TEST_PROPERTIES)) {
            props.store(out, null);
        }

        FireSimulationBFS.loadConfig(SRC_TEST_JAVA_CONFIG_TEST_PROPERTIES);

    }

    @Test
    void testLoadConfig() {

        assertEquals(5, FireSimulationBFS.getHeight(), "Incorrect height.");
        assertEquals(5, FireSimulationBFS.getWidth(), "Incorrect width.");
        assertEquals(0.5, FireSimulationBFS.getProbability(), "Incorrect probability.");
        int[][] grid = FireSimulationBFS.getGrid();
        assertEquals(1, grid[1][1], "Incorrect fire state at (1,1).");
        assertEquals(1, grid[2][2], "Incorrect fire state at (2,2).");
    }


    @Test
    void testSimulationFirePropagation() {

        int[] results = FireSimulationBFS.simulateFire();
        // Validate the number of burned cells and steps

        assertEquals(2, results.length, "Incorrect number of results.");
        assertTrue(results[0] > 0, "No huts were burnt to the ground.");
        assertTrue(results[1] > 0, "Incorrect number of steps.");
    }


}
