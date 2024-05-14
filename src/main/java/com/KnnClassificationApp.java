package com;

import com.data.Dataset;
import com.data.Instance;
import com.proccesor.ConfusionMatrix;
import com.proccesor.DatasetReader;
import com.proccesor.KnnClassifier;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KnnClassificationApp {
    private static final String irisDataPath = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\iris.csv";
    private static final String IRIS = "iris";
    private static final String glassDataPath = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\glass.csv";
    private static final String GLASS = "glass";
    private static final String citiesDataPath = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\ciudades.csv";
    private static final String CITIES = "cities";
    private static String currentDataset = IRIS;

    public static void main(String[] args) {

        printMenu();

        Dataset dataset = DatasetReader.readFromFile(irisDataPath);
        KnnClassifier knn = new KnnClassifier(dataset, 0.8, 8);

        Integer scalingMethod = 2;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String action = scanner.nextLine();

            if (!action.isEmpty()) {
                switch (action) {
                    case "1":
                        if (currentDataset.equals(IRIS)) {
                            currentDataset = GLASS;
                            dataset = DatasetReader.readFromFile(glassDataPath);
                        } else if (currentDataset.equals(GLASS)){
                            currentDataset = CITIES;
                            dataset = DatasetReader.readFromFile(citiesDataPath);
                        } else {
                            currentDataset = IRIS;
                            dataset = DatasetReader.readFromFile(irisDataPath);
                        }
                        knn = new KnnClassifier(dataset, 0.8, 8);
                        break;
                    case "2":
                        dataset.printDataset();
                        break;
                    case "3":
                        int noOfColumns = dataset.getNumCols();
                        double[] coefficients = new double[noOfColumns];
                        for (int i = 0; i < noOfColumns; i++) {
                            coefficients[i] = scanner.nextDouble();
                        }
                        Instance instance = new Instance(coefficients);
                        System.out.println(knn.classify(instance));
                        break;
                    case "4":
                        ConfusionMatrix matrixCalculator = new ConfusionMatrix(knn);
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
                    case "5":
                        System.out.println("Number of attributes: " + dataset.getNumCols());
                        break;
                    case "6":
                        System.out.println("Number of instances: " + dataset.getNumRows());
                        break;
                    case "7":
                        System.out.println("1. No preprocessing" + (scalingMethod == 1 ? " <current" : ""));
                        System.out.println("2. MinMax Scaler" + (scalingMethod == 2 ? " <current" : ""));
                        System.out.println("3. Standardization" + (scalingMethod == 3 ? " <current" : ""));
                        scalingMethod = scanner.nextInt();
                        dataset.applyPreprocessing(scalingMethod);
                        break;
                    case "8":
                        dataset.printPreprocessedData();
                        break;
                    case "exit":
                        return;
                    default:
                        printMenu();
                        break;
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("------------------ MENU ------------------");
        System.out.println(String.format("1. Change dataset (current: %s)", currentDataset));
        System.out.println("2. Show data");
        System.out.println("3. Classify instance");
        System.out.println("4. Show confusion matrix");
        System.out.println("5. Number of attributes");
        System.out.println("6. Number of instances");
        System.out.println("7. Pick preprocessing method");
        System.out.println("8. Show preprocessed data");
    }

}
