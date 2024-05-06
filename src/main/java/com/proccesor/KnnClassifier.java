package com.proccesor;

import com.data.Dataset;
import com.data.Instance;

import java.util.HashMap;
import java.util.Map;

public class KnnClassifier {
    private Dataset trainingDataset;
    private int k;

    public KnnClassifier(Dataset trainingDataset, int k) {
        this.trainingDataset = trainingDataset;
        this.k = k;
    }

    public String classifyInstance(Instance instance) {
        // Calculate distances between the new instance and all instances in the training dataset
        Map<Double, String> distancesToClasses = new HashMap<>();
        for (Instance trainingInstance : trainingDataset.getElements()) {
            double distance = calculateDistance(instance, trainingInstance);
            distancesToClasses.put(distance, trainingInstance.getClassName());
        }

        // Sort distances and find the most frequent class among the k nearest neighbors
        String[] nearestNeighbors = new String[k];
        int count = 0;
        for (Map.Entry<Double, String> entry : distancesToClasses.entrySet()) {
            if (count < k) {
                nearestNeighbors[count++] = entry.getValue();
            } else {
                // Find the nearest neighbor with the maximum frequency
                String mostFrequentClass = findMostFrequentClass(nearestNeighbors);
                return mostFrequentClass;
            }
        }
        return null; // Return null if something goes wrong
    }

    // Calculate Euclidean distance between two instances
    private double calculateDistance(Instance instance1, Instance instance2) {
        double[] coefficients1 = instance1.getCoefficients();
        double[] coefficients2 = instance2.getCoefficients();
        double sum = 0.0;
        for (int i = 0; i < coefficients1.length; i++) {
            sum += Math.pow(coefficients1[i] - coefficients2[i], 2);
        }
        return Math.sqrt(sum);
    }

    // Find the most frequent class among the nearest neighbors
    private String findMostFrequentClass(String[] nearestNeighbors) {
        Map<String, Integer> classCount = new HashMap<>();
        for (String neighbor : nearestNeighbors) {
            classCount.put(neighbor, classCount.getOrDefault(neighbor, 0) + 1);
        }
        String mostFrequentClass = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : classCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentClass = entry.getKey();
            }
        }
        return mostFrequentClass;
    }
}
