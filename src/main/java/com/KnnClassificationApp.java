package com;

import com.data.Dataset;
import com.data.Instance;
import com.proccesor.ConfusionMatrix;
import com.proccesor.DatasetReader;
import com.proccesor.KnnClassifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KnnClassificationApp {
    private static final String irisDataPath = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\iris.csv";
    private static final String IRIS = "iris";
    private static final String glassDataPath = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\glass.csv";
    private static final String GLASS = "glass";

    public static void main(String[] args) {
        String currentDataset = IRIS;

        System.out.println("------------------ MENU ------------------");
        System.out.println(String.format("1. Change dataset (current: %s)", currentDataset));
        System.out.println("2. Show data");
        System.out.println("3. Classify instance");
        System.out.println("4. Show confusion matrix");

        Dataset dataset = DatasetReader.readFromFile(irisDataPath);
        KnnClassifier knn = new KnnClassifier(dataset, 9);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String action = scanner.nextLine();

            if (!action.isEmpty()) {
                switch (action) {
                    case "1":
                        if (currentDataset.equals(IRIS)) {
                            currentDataset = GLASS;
                            dataset = DatasetReader.readFromFile(glassDataPath);
                        } else {
                            currentDataset = IRIS;
                            dataset = DatasetReader.readFromFile(irisDataPath);
                        }
                        knn = new KnnClassifier(dataset, 10);
                        break;
                    case "2":
                        dataset.print();
                        break;
                    case "3":
                        int noOfColumns = dataset.getNumCols();
                        double[] coefficients = new double[noOfColumns];
                        for (int i = 0; i < noOfColumns; i++) {
                            coefficients[i] = scanner.nextDouble();
                        }
                        Instance instance = new Instance(coefficients);
                        System.out.println(knn.classifyInstance(instance));
                        break;
                    case "4":
                        ConfusionMatrix matrixCalculator = new ConfusionMatrix(knn, dataset);
                        Map<String, Map<String, Integer>> confusionMatrix = matrixCalculator.computeConfusionMatrix();
                        Set<String> classes = dataset.getClasses();

                        int maxClassNameLength = classes.stream().mapToInt(String::length).max().orElse(0);

                        System.out.print(" ".repeat(maxClassNameLength + 5));
                        for (String predictedClass : classes) {
                            System.out.printf("%" + (maxClassNameLength + 5) + "s", predictedClass);
                        }
                        System.out.println();

                        for (String actualClass : classes) {
                            System.out.printf("%-" + maxClassNameLength + "s", actualClass);
                            for (String predictedClass : classes) {
                                System.out.printf("%" + (maxClassNameLength + 5) + "d", confusionMatrix.get(actualClass).get(predictedClass));
                            }
                            System.out.println();
                        }

                        break;
                    case "exit":
                        return;
                    default:
                        System.out.println("------------------ MENU ------------------");
                        System.out.println(String.format("1. Change dataset (current: %s)", currentDataset));
                        System.out.println("2. Show data");
                        System.out.println("3. Classify instance");
                        System.out.println("4. Show confusion matrix");
                        break;
                }
            }
        }
    }

}
