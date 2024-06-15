package com.proccesor;

import com.data.Dataset;
import com.data.Instance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatasetReader {

    public static Dataset readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int numRows = Integer.parseInt(reader.readLine());
            int numCols = Integer.parseInt(reader.readLine());

            Instance[] matrix = new Instance[numRows];

            for (int i = 0; i < numRows; i++) {
                String[] values = reader.readLine().split(",");
                double[] coefficients = new double[numCols];
                for (int j = 0; j < numCols; j++) {
                    coefficients[j] = Double.parseDouble(values[j]);
                }
                matrix[i] = new Instance(coefficients, values[numCols]);
            }

            Dataset dataset = new Dataset(numRows, numCols, matrix);
            return dataset;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
