package com.proccesor;

import com.data.Dataset;
import com.data.Instance;

public class DataNormalizer {

    public static double findMinValue(Dataset dataset) {
        double min = Double.MAX_VALUE;
        for (Instance instance : dataset.getElements()) {
            for (double value : instance.getCoefficients()) {
                if (value < min) {
                    min = value;
                }
            }
        }
        return min;
    }

    public static double findMaxValue(Dataset dataset) {
        double max = 0;
        for (Instance instance : dataset.getElements()) {
            for (double value : instance.getCoefficients()) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    public static double[] minMaxNormalize(double[] data, double min, double max) {
        double[] normalizedData = new double[data.length];
        double range = max - min;
        for (int i = 0; i < data.length; i++) {
            normalizedData[i] = (data[i] - min) / range;
        }
        return normalizedData;
    }
}
