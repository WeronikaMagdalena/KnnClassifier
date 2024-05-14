package com.model;

import java.io.FileWriter;
import java.io.IOException;

public class Vector {
    private int dimension;
    private double[] coefficients;

    public Vector(int dimension) {
        this.dimension = dimension;
        this.coefficients = new double[dimension];
    }

    public Vector(double[] coefficients) {
        this.dimension = coefficients.length;
        this.coefficients = coefficients.clone();
    }

    public int getDimension() {
        return dimension;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public void print() {
        System.out.print("Vector Coefficients: ");
        for (double coefficient : coefficients) {
            System.out.print(coefficient + " ");
        }
        System.out.println();
    }

    public double scalarProduct(Vector anotherVector) {
        if (this.dimension != anotherVector.dimension) {
            throw new IllegalArgumentException("Vectors must have the same dimension for scalar product.");
        }

        double result = 0;
        for (int i = 0; i < dimension; i++) {
            result += this.coefficients[i] * anotherVector.coefficients[i];
        }

        return result;
    }

    public void writeToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (double coefficient : coefficients) {
                writer.write(coefficient + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
