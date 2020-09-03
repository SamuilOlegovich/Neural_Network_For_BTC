package main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.function.UnaryOperator;





public class NeuralNetwork {
    @JsonIgnore
    private UnaryOperator<Double> derivative;
    @JsonIgnore
    private UnaryOperator<Double> activation;
    @JsonIgnore
    private double learningRate;
    @JsonIgnore
    private Layer[] layers;                     // массив слоёв

    private int layersLength;                   // длина масива слоев



    public NeuralNetwork(double learningRate, UnaryOperator<Double> activation,
                         UnaryOperator<Double> derivative, int... sizes) {

        this.learningRate = learningRate;   // скорость обучения
        this.activation = activation;
        this.derivative = derivative;       // производное
        this.layers = new Layer[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            int nextSize = 0;

            if (i < sizes.length - 1) {
                nextSize = sizes[i + 1];
            }

            layers[i] = new Layer(sizes[i], nextSize);

            for (int j = 0; j < sizes[i]; j++) {
                layers[i].biases[j] = Math.random() * 2.0 - 1.0;

                for (int k = 0; k < nextSize; k++) {
                    layers[i].weights[j][k] = Math.random() * 2.0 - 1.0;
                }
            }
        }

        this.layersLength = layers.length;
    }


    public NeuralNetwork(double learningRate, UnaryOperator<Double> activation,
                         UnaryOperator<Double> derivative, int[] sizes, Layer[] layers) {

        this.learningRate = learningRate;   // скорость обучения
        this.activation = activation;
        this.derivative = derivative;       // производное
        fillInLayers(layers);
        this.layersLength = layers.length;
    }





    // кормим нейросеит и получаем данные на выходе
    public double[] feedForward(double[] inputs) {
        System.arraycopy(inputs, 0, layers[0].neurons, 0, inputs.length);
        for (int i = 1; i < layers.length; i++)  {
            Layer l = layers[i - 1];
            Layer l1 = layers[i];
            for (int j = 0; j < l1.size; j++) {
                l1.neurons[j] = 0;
                for (int k = 0; k < l.size; k++) {
                    l1.neurons[j] += l.neurons[k] * l.weights[k][j];
                }
                l1.neurons[j] += l1.biases[j];
                l1.neurons[j] = activation.apply(l1.neurons[j]);
            }
        }
        return layers[layers.length - 1].neurons;
    }


    // обратное распространение ошибки (для обучения)
    public void backpropagation(double[] targets) {
        double[] errors = new double[layers[layers.length - 1].size];
        for (int i = 0; i < layers[layers.length - 1].size; i++) {
            errors[i] = targets[i] - layers[layers.length - 1].neurons[i];
        }
        for (int k = layers.length - 2; k >= 0; k--) {
            Layer l = layers[k];
            Layer l1 = layers[k + 1];
            double[] errorsNext = new double[l.size];
            double[] gradients = new double[l1.size];
            for (int i = 0; i < l1.size; i++) {
                gradients[i] = errors[i] * derivative.apply(layers[k + 1].neurons[i]);
                gradients[i] *= learningRate;
            }
            double[][] deltas = new double[l1.size][l.size];
            for (int i = 0; i < l1.size; i++) {
                for (int j = 0; j < l.size; j++) {
                    deltas[i][j] = gradients[i] * l.neurons[j];
                }
            }
            for (int i = 0; i < l.size; i++) {
                errorsNext[i] = 0;
                for (int j = 0; j < l1.size; j++) {
                    errorsNext[i] += l.weights[i][j] * errors[j];
                }
            }
            errors = new double[l.size];
            System.arraycopy(errorsNext, 0, errors, 0, l.size);
            double[][] weightsNew = new double[l.weights.length][l.weights[0].length];
            for (int i = 0; i < l1.size; i++) {
                for (int j = 0; j < l.size; j++) {
                    weightsNew[j][i] = l.weights[j][i] + deltas[i][j];
                }
            }
            l.weights = weightsNew;
            for (int i = 0; i < l1.size; i++) {
                l1.biases[i] += gradients[i];
            }
        }
    }



    private void fillInLayers(Layer[] in) {
        this.layers = new Layer[in.length];

        for (int i = 0; i < layers.length; i++) {
            layers[i] = in[i];
        }
    }



    // созхранняем все данные весов, нейронов смещения и т д
    protected ArrayList<String> saveBalanceData() {
        ArrayList<String> arrayList = new ArrayList<>();
//        ObjectMapper objectMapp = new ObjectMapper();
        try {
//            arrayList.add(objectMapp.writeValueAsString(this));
//            arrayList.add("\n" + Enums.NEXT.toString() + "\n");
            for (Layer l : layers) {
                l.setID();
                ObjectMapper objectMapper = new ObjectMapper();
                arrayList.add(objectMapper.writeValueAsString(l));
                arrayList.add("\n" + Enums.NEXT.toString() + "\n");
            }
        } catch (JsonProcessingException ex) { }
        // удаляем последеий NEXT
        arrayList.remove(arrayList.size() - 1);
        return arrayList;
    }
}
