package com.proccesor;

import com.data.Dataset;
import com.data.Instance;

import java.util.*;

public class KnnClassifier {
    Dataset trainingData;
    Dataset testData;
    int k;

    public KnnClassifier(Dataset normalizedData, double trainingDatasetSize, int k) {
        normalizedData.shuffleInstances();
        int splitIndex = (int) (normalizedData.getNumRows() * trainingDatasetSize);

        Instance[] instances = normalizedData.getInstances();

        Instance[] trainingInstances = Arrays.copyOfRange(instances, 0, splitIndex);
        Instance[] testInstances = Arrays.copyOfRange(instances, splitIndex, instances.length);

        this.trainingData = new Dataset(trainingInstances.length, normalizedData.getNumCols(), trainingInstances);
        this.testData = new Dataset(testInstances.length, normalizedData.getNumCols(), testInstances);
        this.k = k;
    }

    public String classify(Instance instance) {
        // Calculate distances between 'instance' and all instances in trainingData
        PriorityQueue<NeighborDistance> pq = new PriorityQueue<>(k);
        for (Instance trainInstance : trainingData.getInstances()) {
            double distance = calculateDistance(instance, trainInstance);
            pq.offer(new NeighborDistance(trainInstance, distance));
        }

        // Count the occurrences of each class among the k-nearest neighbors
        Map<String, Integer> classVotes = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Instance neighbor = pq.poll().instance;
            String classLabel = neighbor.getClassName();
            classVotes.put(classLabel, classVotes.getOrDefault(classLabel, 0) + 1);
        }

        // Assign the class with the highest count as the predicted class for 'instance'
        String predictedClass = null;
        int maxVotes = -1;
        for (Map.Entry<String, Integer> entry : classVotes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                predictedClass = entry.getKey();
            }
        }
        return predictedClass;
    }

    // Helper method to calculate Euclidean distance between two instances
    private double calculateDistance(Instance instance1, Instance instance2) {
        double[] features1 = instance1.getCoefficients();
        double[] features2 = instance2.getCoefficients();
        double sum = 0.0;
        for (int i = 0; i < features1.length; i++) {
            sum += Math.pow(features1[i] - features2[i], 2);
        }
        return Math.sqrt(sum);
    }

    // Class to represent a neighbor along with its distance
    private class NeighborDistance implements Comparable<NeighborDistance> {
        Instance instance;
        double distance;

        NeighborDistance(Instance instance, double distance) {
            this.instance = instance;
            this.distance = distance;
        }

        public int compareTo(NeighborDistance other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public Dataset getTrainingData() {
        return trainingData;
    }

    public Dataset getTestData() {
        return testData;
    }

}
