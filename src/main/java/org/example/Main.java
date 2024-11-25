package org.example;


import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try {
            FireSimulationBFS.loadConfig("src/main/resources/config.properties");
            int[] results = FireSimulationBFS.simulateFire();
            System.out.println("Cases reduced to ashes: " + results[0]);
            System.out.println("Steps elapsed: " + results[1]);
        } catch (IOException e) {
            System.err.println("Error reading configuration file: " + e.getMessage());
        }
    }
}