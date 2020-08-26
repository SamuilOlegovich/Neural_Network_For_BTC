package main;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Layer {
    public int size;
    private int nextSize;
    public double[] biases;     // смещение
    @JsonIgnore
    public double[] neurons;    // нейроны
    public double[][] weights;  // веса


    public Layer(int size, int nextSize) {
        weights = new double[size][nextSize];
        neurons = new double[size];
        biases = new double[size];
        this.nextSize = nextSize;
        this.size = size;
    }

    public Layer() { }
}
