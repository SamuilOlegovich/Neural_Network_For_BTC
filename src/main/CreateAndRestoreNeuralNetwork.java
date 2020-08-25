package main;

import java.util.ArrayList;

public class CreateAndRestoreNeuralNetwork {
    private ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting;
    private NeuralNetwork neuralNetwork;
    private String pathSavedWeights;

    private int[] numberOfNeuronsInLayer;   // количество нейронов в слое
    private double[][] weights;  // веса
    private double[] neurons;    // нейроны
    private double[] biases;     // смещение
    private Layer[] layers;
    private int size;


    public CreateAndRestoreNeuralNetwork() {
        this.readAndWriteNeuralNetworkSetting = Gasket.getReadAndWriteNeuralNetworkSetting();
        this.pathSavedWeights = Gasket.getFilesAndPathCreator().getPathSavedWeights();
        restoreNN();
    }


    private void restoreNN() {
//        ArrayList<String> inList = new ArrayList<>(readAndWriteNeuralNetworkSetting.readFileWeights());

    }


}
