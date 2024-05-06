package com;

import com.data.Dataset;
import com.data.Instance;
import com.proccesor.DatasetReader;
import com.proccesor.KnnClassifier;

import java.util.Scanner;

public class KnnClassificationApp {

    public static void main(String[] args) {
        String irisDataPath = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\iris.csv";
        String glassDataPath = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\glass.csv";

        System.out.println("------------------ MENU ------------------");
        System.out.println(String.format("1. Change dataset (current: %s)", "iris"));
        System.out.println("2. Show data");
        System.out.println("3. Classify instance");

        Dataset dataset = DatasetReader.readFromFile(glassDataPath);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String action = scanner.nextLine();

            if (!action.isEmpty()) {
                switch (action) {
                    case "1":
                        break;
                    case "2":
                        dataset.print();
                        break;
                    case "3":
                            KnnClassifier knn = new KnnClassifier(dataset, 5);
                            int noOfColumns = dataset.getNumCols();
                            double[] coefficients = new double[noOfColumns];
                            for (int i = 0; i < noOfColumns; i++) {
                                coefficients[i] = scanner.nextDouble();
                            }
                            Instance instance = new Instance(coefficients);
                            System.out.println(knn.classifyInstance(instance));
                            break;
                    case "exit":
                        return;
                    default:
                        System.out.println("------------------ MENU ------------------");
                        System.out.println(String.format("1. Change dataset (current: %s)", "iris"));
                        System.out.println("2. Show data");
                        System.out.println("3. Classify instance");
                        break;
                }
            }
        }
    }

}
