package com.test;

import com.data.Dataset;
import com.data.Instance;
import com.proccesor.*;
import com.proccesor.DatasetReader;
import org.junit.Test;

public class KNNClassifierTest {
    @Test
    public void testClassifier1() {
        String dataFile = "C:\\Users\\werka\\IdeaProjects\\SoftwareSystemModeling\\src\\knn\\iris.csv";
        Dataset dataset = DatasetReader.readFromFile(dataFile);
        KnnClassifier knn = new KnnClassifier(dataset, 0.8, 8);
        System.out.println("Predicted class: " + knn.classify(new Instance(new double[]{5.9d,3d,4.2d,1.5d})));
    }

    @Test
    public void testClassifier2() {
        String dataFile = "C:\\Users\\werka\\IdeaProjects\\SoftwareSystemModeling\\src\\knn\\iris.csv";
        Dataset dataset = DatasetReader.readFromFile(dataFile);
        KnnClassifier knn = new KnnClassifier(dataset, 0.8, 8);
        System.out.println(knn.classify(new Instance(new double[]{5d,3d,1.3d,10d})));
    }

    @Test
    public void testClassifierAccuracy() {
        String dataFile = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\glass.csv";
        Dataset dataset = DatasetReader.readFromFile(dataFile);
        KnnClassifier knn = new KnnClassifier(dataset, 0.8, 8);
//        double accuracy = knn.calculateAccuracy();
//        System.out.println("Accuracy: " + accuracy);
    }

    @Test
    public void testClassifyAllInstancesIris() {
        String dataFile = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\iris.csv";
        Dataset dataset = DatasetReader.readFromFile(dataFile);
        KnnClassifier knn = new KnnClassifier(dataset, 0.8, 8);
        for (int i = 0; i < 150; i++) {
//            String result = knn.classifyInstance(dataset.getInstances()[i]);
//            System.out.println(i + " " + result);
        }
    }

    @Test
    public void testClassifyAllInstancesGlass() {
        String dataFile = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\glass.csv";
        Dataset dataset = DatasetReader.readFromFile(dataFile);
        KnnClassifier knn = new KnnClassifier(dataset, 0.8, 8);
        for (int i = 0; i < 214; i++) {
//            String result = knn.classifyInstance(dataset.getInstances()[i]);
//            System.out.println(i + " " + result);
        }
    }

    @Test
    public void setsDivision() {
        String dataFile = "C:\\Users\\werka\\IdeaProjects\\KnnClassifier\\src\\main\\resources\\glass.csv";
        Dataset dataset = DatasetReader.readFromFile(dataFile);
        KnnClassifier knn = new KnnClassifier(dataset, 0.8, 8);
        System.out.println(knn.getTrainingData().getNumRows());
        System.out.println(knn.getTestData().getNumRows());
    }

}
