package com.proccesor;

import com.data.Dataset;
import com.data.Instance;

import java.util.HashMap;
import java.util.Map;

public class ConfusionMatrix {

    private KnnClassifier knnClassifier;
    private Dataset testDataset;

    public ConfusionMatrix(KnnClassifier knnClassifier) {
        this.knnClassifier = knnClassifier;
        this.testDataset = knnClassifier.getTestData();
    }

    public Map<String, Map<String, Integer>> computeConfusionMatrix() {
        // Initialize confusion matrix
        Map<String, Map<String, Integer>> matrix = new HashMap<>();
        for (String actualClass : knnClassifier.getDataset().getClasses()) {
            matrix.put(actualClass, new HashMap<>());
            for (String predictedClass : testDataset.getClasses()) {
                matrix.get(actualClass).put(predictedClass, 0);
            }
        }

        // Compute confusion matrix
        for (Instance testInstance : testDataset.getInstances()) {
            String actualClass = testInstance.getClassName();
            String predictedClass = knnClassifier.classify(testInstance);
            System.out.println("actual class: " + actualClass + " predicted class: " + predictedClass);
            System.out.println(matrix.get(actualClass));
            System.out.println(matrix.get(actualClass).get(predictedClass));
            int count = matrix.get(actualClass).get(predictedClass);
            matrix.get(actualClass).put(predictedClass, count + 1);
        }
        return matrix;
    }
}

