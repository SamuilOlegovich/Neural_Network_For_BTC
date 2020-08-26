package main;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Objects;

public class Layer {
    public int ID;
    public int size;
    public int nextSize;
    public double[] biases;     // смещение
    @JsonIgnore
    public double[] neurons;    // нейроны
    public double[][] weights;  // веса


    public Layer(int size, int nextSize) {
        this.weights = new double[size][nextSize];
        this.neurons = new double[size];
        this.biases = new double[size];
        this.nextSize = nextSize;
        this.size = size;
    }

    public Layer(int size, int nextSize, double[] biases, double[][] weights) {
        this.weights = new double[size][nextSize];
        this.neurons = new double[size];
        this.biases = new double[size];
        this.nextSize = nextSize;
        this.size = size;
        copyWeights(weights);
        copyBiases(biases);
    }


    private void copyWeights(double[][] in) {
        for (int i = 0; i < weights.length; i++) {
            System.arraycopy(in[i], 0, weights[i], 0, weights[i].length);
        }
    }

    private void copyBiases(double[] in) {
        System.arraycopy(in, 0, biases, 0, biases.length);
    }



    @Override
    public int hashCode() {
        double result = size + nextSize;

        for (double d : biases) {
            result = result + d;
        }

        for (double[] weight : weights) {
            for (double d : weight) {
                result = result + d;
            }
        }
        return (int) (result * 31);
    }



    public void setID() {
        ID = hashCode();
    }

    public int getID() {
        return ID;
    }
}
