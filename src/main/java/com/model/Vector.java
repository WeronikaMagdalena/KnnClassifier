package com.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//		Write a Java program, with a class called Vector, that stores an n-dimensional vector
//	of doubles. The class must have a method to display the dimension of the vector. It also 
//	includes a print method that displays the vector coefficients on the screen.
//	It includes another method that allows performing the dot product of 2 n-dimensional vectors.
//	Call the previous methodScalarProduct. Add the constructors for the class
//	that you deem appropriate. The program will request the dimension of the vectors
//	to be multiplied and the components of both vectors, displaying the vectors
//	and the result on the screen.

public class Vector {
    protected int dimension;
    protected double[] coefficients;

    // Constructor to create a vector with specified dimension
    public Vector(int dimension) {
        this.dimension = dimension;
        this.coefficients = new double[dimension];
    }

    // Constructor to create a vector with specified coefficients
    public Vector(double[] coefficients) {
        this.dimension = coefficients.length;
        this.coefficients = coefficients.clone();
    }

    // Method to display the vector dimension
    public int getDimension() {
        return dimension;
    }

    public void setCoefficients(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    // Method to display the vector coefficients
    public void print() {
        System.out.print("Vector Coefficients: ");
        for (double coefficient : coefficients) {
            System.out.print(coefficient + " ");
        }
        System.out.println();
    }

    // Method to calculate the scalar product of two vectors
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

//    	Add a write method to the Vector class to write the vector data in a plain text file,
//    where each coefficient is separated from the next by a comma.
//    The method receives the name of the file as an argument.
    public void writeToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (double coefficient : coefficients) {
                writer.write(coefficient + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the dimension of Vector 1: ");
        int dimension1 = scanner.nextInt();
        Vector vector1 = new Vector(dimension1);

        System.out.print("Enter the coefficients of Vector 1: ");
        for (int i = 0; i < dimension1; i++) {
            vector1.coefficients[i] = scanner.nextDouble();
        }

        System.out.print("Enter the dimension of Vector 2: ");
        int dimension2 = scanner.nextInt();
        Vector vector2 = new Vector(dimension2);

        System.out.print("Enter the coefficients of Vector 2: ");
        for (int i = 0; i < dimension2; i++) {
            vector2.coefficients[i] = scanner.nextDouble();
        }

        vector1.writeToFile("vector1");
        vector2.writeToFile("vector2");
        
        scanner.close();
    }

}
