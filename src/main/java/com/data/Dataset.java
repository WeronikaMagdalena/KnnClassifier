package com.data;

import com.proccesor.DataNormalizer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Dataset {

    private int numRows;
    private int numCols;
    private Instance[] instances;
    private Instance[] preprocessedInstances;

    public Instance[] getInstances() {
        return instances;
    }

    public Dataset(int numRows, int numCols, Instance[] coefficients) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.instances = coefficients.clone();
        this.preprocessedInstances = new Instance[numRows];
        for (int i = 0; i < numRows; i++) {
            preprocessedInstances[i] = new Instance(numRows);
            preprocessedInstances[i].setClassName(instances[i].getClassName());
        }
        minMaxScaler();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void printDataset() {
        System.out.println("Dataset:");
        for (int i = 0; i < numRows; i++) {
            System.out.println(Arrays.toString(instances[i].getCoefficients()) + ' ' + instances[i].getClassName());
        }
    }

    public void printPreprocessedData() {
        System.out.println("Preprocessed data:");
        for (int i = 0; i < numRows; i++) {
            System.out.println(Arrays.toString(preprocessedInstances[i].getCoefficients()) + ' ' + preprocessedInstances[i].getClassName());
        }
    }

    public Set<String> getClasses() {
        Set<String> classes = new HashSet<>();
        for (Instance instance : instances) {
            classes.add(instance.getClassName());
        }
        return classes;
    }

    public void minMaxScaler() {
        double min = DataNormalizer.findMinValue(this);
        double max = DataNormalizer.findMaxValue(this);
        for (int i = 0; i < instances.length; i++) {
            preprocessedInstances[i].setCoefficients(DataNormalizer.minMaxNormalize(instances[i].getCoefficients(), min, max));
        }
    }

    public void applyPreprocessing(int choice) {
        switch (choice) {
            case 1:
                for (int i = 0; i < instances.length; i++) {
                    preprocessedInstances[i].setCoefficients(instances[i].getCoefficients());
                }
                break;
            case 2:
                minMaxScaler();
                break;
            case 3:
                double mean = calculateMean();
                double stdDev = calculateStdDev(mean);
                for (int i = 0; i < instances.length; i++) {
                    preprocessedInstances[i].setCoefficients(DataNormalizer.standardize(instances[i].getCoefficients(), mean, stdDev));
                }
                break;
        }
    }

    private double calculateMean() {
        double sum = 0;
        for (Instance instance : instances) {
            for (double value : instance.getCoefficients()) {
                sum += value;
            }
        }
        return sum / (numRows * numCols);
    }

    private double calculateStdDev(double mean) {
        double sum = 0;
        for (Instance instance : instances) {
            for (double value : instance.getCoefficients()) {
                sum += Math.pow(value - mean, 2);
            }
        }
        return Math.sqrt(sum / (numRows * numCols - 1));
    }

    public void shuffleInstances() {
        Random rand = new Random();
        for (int i = instances.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            // Swap instances[i] with instances[index]
            Instance temp = instances[i];
            instances[i] = instances[index];
            instances[index] = temp;
        }
    }

}

