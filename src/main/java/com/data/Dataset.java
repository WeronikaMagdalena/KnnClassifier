package com.data;

import com.data.Instance;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Dataset {
    private int numRows;
    private int numCols;
    private Instance[] elements;

    public Instance[] getElements() {
        return elements;
    }

    public void setElements(Instance[] elements) {
        this.elements = elements;
    }

    public Dataset(int numRows, int numCols, Instance[] coefficients) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.elements = coefficients.clone();
    }

    // Getter for the number of rows
    public int getNumRows() {
        return numRows;
    }

    // Getter for the number of columns
    public int getNumCols() {
        return numCols;
    }

    // Display matrix
    public void print() {
        System.out.println("Dataset:");
        for (int i = 0; i < numRows; i++) {
            System.out.println(Arrays.toString(elements[i].getCoefficients()) + ' ' + elements[i].getClassName());
        }
    }

    public Set<String> getClasses() {
        Set<String> classes = new HashSet<>();
        for (Instance instance : elements) {
            classes.add(instance.getClassName());
        }
        return classes;
    }
}

