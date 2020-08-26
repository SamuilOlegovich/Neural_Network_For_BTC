package main;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.function.UnaryOperator;





public class CreateAndRestoreNeuralNetwork {
    private ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting;
    private ArrayList<String> listInSavedWeights;
    private UnaryOperator<Double> dsigmoid;
    private UnaryOperator<Double> sigmoid;
    private NeuralNetwork neuralNetwork;
    private String pathSavedWeights;
    private double learningRate;


    private int[] numbersOfNeuronsInLayer;   // количество нейронов в слое
    private double[][] weights;             // веса
    private double[] neurons;               // нейроны
    private double[] biases;                // смещение
    private Layer[] layers;
    private int size;


    public CreateAndRestoreNeuralNetwork() {
        this.readAndWriteNeuralNetworkSetting = Gasket.getReadAndWriteNeuralNetworkSetting();
        this.pathSavedWeights = Gasket.getFilesAndPathCreator().getPathSavedWeights();
        Gasket.setCreateAndRestoreNeuralNetwork(this);
        this.learningRate = Gasket.getLearningRate();
        createSigmoidDsigmoid();
        restoreNN();
    }




    public void restoreNN() {
        // {"numberOfNeuronsInLayer":[90,189,108,45,9,3],"numberOfSensoryNeurons":90,
        // "numberOfOutputNeurons":3,"samples":43900,"epochs":5}
        listInSavedWeights = new ArrayList<>(readAndWriteNeuralNetworkSetting.readFileWeights());

        for (String s : listInSavedWeights) {
            if (s.equalsIgnoreCase(Enums.START.toString())) {
                String[] param = parseString(Enums.NUMBERSofNEURONSinLAYER,
                        listInSavedWeights.get(listInSavedWeights.indexOf(s) + 1))
                        .replaceAll("\\[", "").replaceAll("]", "")
                        .split(",");
                numbersOfNeuronsInLayer = new int[param.length];
                for (int i = 0; i < param.length; i++) {
                    numbersOfNeuronsInLayer[i] = Integer.parseInt(param[i]);
                }
            }
            break;
        }
        listInSavedWeights.remove(2);
        listInSavedWeights.remove(1);
        listInSavedWeights.remove(0);



        neuralNetwork = new NeuralNetwork(learningRate, sigmoid, dsigmoid, numbersOfNeuronsInLayer);
        Gasket.setNeuralNetwork(neuralNetwork);
    }



    private void createSigmoidDsigmoid() {
        sigmoid = x -> 1 / (1 + Math.exp(-x));
        dsigmoid = y -> y * (1 - y);
    }



    private String parseString(Enums key, String in) {
        String[] strings = in.replaceAll("\\{", "")
                .replaceAll("}", "").split(",\"");
        for (String s : strings) {
            String[] arr = s.replaceAll("\"", "").split(":");
            if (arr[0].equalsIgnoreCase(key.toString())) {
                return arr[1];
            }
        }
        return null;
    }


}
