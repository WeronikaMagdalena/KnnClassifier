package com.data;

import com.model.Vector;

public class Instance extends Vector {
    private String className;

    public Instance(int dimension) {
        super(dimension);
    }

    public Instance(double[] coefficients) {
        super(coefficients);
    }

    public Instance(double[] coefficients, String className) {
        super(coefficients);
        this.className = className;
    }

    public void setClassName(String name) {
        className = name;
    }

    public String getClassName() {
        return className;
    }

}
