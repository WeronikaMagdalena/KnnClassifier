package com.proccesor;

import com.data.Dataset;
import com.data.Instance;

import java.util.*;

public class KnnClassifier {
    private Dataset trainingDataset;
    private int k;

    public KnnClassifier(Dataset trainingDataset, int k) {
        this.trainingDataset = trainingDataset;
        this.k = k;
    }

    // Performs kNN classification
    public String classifyInstance(Instance instance) {
        List<Instance> neighbors = new ArrayList<>();
        for (Instance neighbor : trainingDataset.getElements()) {
            double distance = calculateDistance(neighbor, instance);
            neighbor.setDistance(distance);
            neighbors.add(neighbor);
        }

        // Sort neighbors by distance
        Collections.sort(neighbors, Comparator.comparingDouble(o -> o.getDistance()));

        // Count occurrences of each class among the k nearest neighbors
// Initialize a Map to store class counts
        Map<String, Integer> classCounts = new HashMap<>();

// Initialize counts for each class to zero
        for (String className : trainingDataset.getClasses()) {
            classCounts.put(className, 0);
        }

// Loop through neighbors
        for (int i = 0; i < k; i++) {
            Instance neighbor = neighbors.get(i);
            String className = neighbor.getClassName();

            // Increment count for the corresponding class
            classCounts.put(className, classCounts.get(className) + 1);
        }

        // Find the class with the most occurrences
        int maxCount = -1;
        String predictedClass = "";

        for (Map.Entry<String, Integer> entry : classCounts.entrySet()) {
            String className = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount) {
                maxCount = count;
                predictedClass = className;
            }
        }

        return predictedClass;
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

    public double calculateAccuracy() {
        if (trainingDataset == null) {
            return 0.0; // Return 0 if test dataset is empty
        }

        int correctPredictions = 0;
        int totalInstances = trainingDataset.getNumRows();

        // Iterate over instances in the test dataset
        for (Instance testInstance : trainingDataset.getElements()) {
            String predictedClass = classifyInstance(testInstance);
            if (predictedClass != null && predictedClass.equals(testInstance.getClassName())) {
                correctPredictions++;
            }
        }

        // Calculate accuracy
        return (double) correctPredictions / totalInstances;
    }

}
