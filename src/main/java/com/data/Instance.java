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

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

}
