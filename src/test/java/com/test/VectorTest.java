package com.test;

import com.model.Vector;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class VectorTest {
    private Vector vector1 = new Vector(3);
    private Vector vector2 = new Vector(3);

    @Test
    public void testScalarProduct1() {
        vector1.setCoefficients(new double[]{1, 2, 3});
        vector2.setCoefficients(new double[]{2, 1, 2});
        double result = vector1.scalarProduct(vector2);
        System.out.println(result);
        Assert.assertEquals(10, result, 0);
    }

    @Test
    public void testScalarProduct2() {
        vector1.setCoefficients(new double[]{3, 5, 4});
        vector2.setCoefficients(new double[]{2, 4, 1});
        double result = vector1.scalarProduct(vector2);
        System.out.println(result);
        Assert.assertEquals(30, result, 0);
    }

    @Test
    public void testScalarProduct3() {
        vector1.setCoefficients(new double[]{2, 1, -3, 0});
        vector2.setCoefficients(new double[]{4, -2, 0, 5});
        double result = vector1.scalarProduct(vector2);
        System.out.println(result);
        Assert.assertEquals(6, result, 0);
    }

}
