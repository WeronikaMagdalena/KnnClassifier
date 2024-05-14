package com.proccesor;

import com.data.Dataset;
import com.data.Instance;

import java.util.HashMap;
import java.util.Map;

public class ConfusionMatrix {

    private KnnClassifier knnClassifier;
    private Dataset testDataset;

    public ConfusionMatrix(KnnClassifier knnClassifier, Dataset testDataset) {
        this.knnClassifier = knnClassifier;
        this.testDataset = testDataset;
    }

    public Map<String, Map<String, Integer>> computeConfusionMatrix() {
        // Initialize confusion matrix
        Map<String, Map<String, Integer>> matrix = new HashMap<>();
        for (String actualClass : testDataset.getClasses()) {
            matrix.put(actualClass, new HashMap<>());
            for (String predictedClass : testDataset.getClasses()) {
                matrix.get(actualClass).put(predictedClass, 0);
            }
        }

        // Compute confusion matrix
        for (Instance testInstance : testDataset.getInstances()) {
            String actualClass = testInstance.getClassName();
            String predictedClass = knnClassifier.classify(testInstance);
            int count = matrix.get(actualClass).get(predictedClass);
            matrix.get(actualClass).put(predictedClass, count + 1);
        }

        return matrix;
    }
}

