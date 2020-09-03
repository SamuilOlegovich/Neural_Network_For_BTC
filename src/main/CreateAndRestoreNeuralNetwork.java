package main;

import main.view.ConsoleHelper;

import java.util.ArrayList;
import java.util.function.UnaryOperator;





public class CreateAndRestoreNeuralNetwork {
    private ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting;
    private ArrayList<String> listInSavedWeights;
    private String pathSavedWeightsPredictor;
    private UnaryOperator<Double> dsigmoid;
    private NeuralNetwork neuralNetworkTwo;
    private UnaryOperator<Double> sigmoid;
    private NeuralNetwork neuralNetwork;
    private String pathSavedWeights;
    private double learningRate;


    private int[] numbersOfNeuronsInLayer;   // количество нейронов в слое
    private double[][] weights;             // веса
    private double[] neurons;               // нейроны
    private volatile boolean oneOrTwo;
    private double[] biases;                // смещение
    private Layer[] layers;
    private int nextSize;
    private int size;
    private int ID;


    public CreateAndRestoreNeuralNetwork() {
        this.readAndWriteNeuralNetworkSetting = Gasket.getReadAndWriteNeuralNetworkSetting();
        this.pathSavedWeights = Gasket.getFilesAndPathCreator().getPathSavedWeights();
        Gasket.setCreateAndRestoreNeuralNetwork(this);
        this.learningRate = Gasket.getLearningRate();
        this.oneOrTwo = false;
        createSigmoidDsigmoid();
        restoreNN();
    }

    public CreateAndRestoreNeuralNetwork(boolean b) {
        this.pathSavedWeightsPredictor = Gasket.getFilesAndPathCreator().getPathSavedWeightsPredictor();
        this.readAndWriteNeuralNetworkSetting = Gasket.getReadAndWriteNeuralNetworkSetting();
        this.pathSavedWeights = Gasket.getFilesAndPathCreator().getPathSavedWeights();
        Gasket.setCreateAndRestoreNeuralNetwork(this);
        this.learningRate = Gasket.getLearningRate();
        this.oneOrTwo = false;
        createSigmoidDsigmoid();
        restoreNN();
        this.oneOrTwo = true;
        restoreNN();
    }


    public void restoreNN() {
        // {"numberOfNeuronsInLayer":[90,189,108,45,9,3],"numberOfSensoryNeurons":90,
        // "numberOfOutputNeurons":3,"samples":43900,"epochs":5}
        this.listInSavedWeights = new ArrayList<>(readAndWriteNeuralNetworkSetting.readFileWeights(oneOrTwo));

        // находим первую строку параметров, инициализируем и заполнянем массив количества нейронов,
        // а так же создаем слои и заполняем ими массив слоев
        int index = 0;
        for (String s : listInSavedWeights) {
            if (s.equalsIgnoreCase(Enums.START.toString())) {
                index = listInSavedWeights.indexOf(s) + 1;
                String[] param = parseString(Enums.NUMBERSofNEURONSinLAYER,
                        listInSavedWeights.get(listInSavedWeights.indexOf(s) + 1))
                        .replaceAll("\\[", "").replaceAll("]", "")
                        .split(",");

                numbersOfNeuronsInLayer = new int[param.length];
                layers = new Layer[param.length];

                for (int i = 0; i < param.length; i++) {
                    numbersOfNeuronsInLayer[i] = Integer.parseInt(param[i]);
                }
            } else {
                ConsoleHelper.writeMessage(StringHelper.getString(Enums.INCORRECT_WEIGHT_FORMAT));
            }
            break;
        }

        listInSavedWeights.remove(index);

        for (int i = listInSavedWeights.size() - 1; i >= 0; i--) {
            if (!listInSavedWeights.get(i).startsWith("{\"") && !listInSavedWeights.get(i).endsWith("\"}")) {
                listInSavedWeights.remove(i);
            }
        }
        int countLayers = 0;

        // приступаем к наполнению весов
        for (String s : listInSavedWeights) {
            ID = Integer.parseInt(parseString(Enums.ID, s));
            size = Integer.parseInt(parseString(Enums.SIZE, s));
            nextSize = Integer.parseInt(parseString(Enums.NextSIZE, s));
//            ConsoleHelper.writeMessage(nextSize + "");///////////////////////////////////

            String[] biasesParam = parseString(Enums.BIASES, s)
                    .replaceAll("\\[", "").replaceAll("]", "")
                    .split(",");
            biases = new double[biasesParam.length];
            for (int i = 0; i < biasesParam.length; i++) {
                biases[i] = Double.parseDouble(biasesParam[i]);
            }

            String[] weightsParam = parseString(Enums.WEIGHTS, s).split("],\\[");
            weights = new double[size][nextSize];
            if (nextSize != 0) {
                for (int i = 0; i < weightsParam.length; i++) {
                    String[] outWeightsParam = weightsParam[i]
                            .replaceAll("\\[", "").replaceAll("]", "")
                            .split(",");
                    for (int j = 0; j < outWeightsParam.length; j++) {
                        weights[i][j] = Double.parseDouble(outWeightsParam[j]);
                    }
                }
            }

            Layer layer = new Layer(size, nextSize, biases, weights);
            layer.setID();

            if (layer.getID() == ID) {
                ConsoleHelper.writeMessage(StringHelper.getString((countLayers + 1)
                        + " " + Enums.LAYER_OF_NEURONS_RESTORED.toString()));
                layers[countLayers] = layer;
            } else {
                ConsoleHelper.writeMessage(layer.getID() + " --- " + ID);
                ConsoleHelper.writeMessage(StringHelper.getString((Enums.ERROR.toString()
                        + " " + countLayers) + " " + Enums.LAYER_OF_NEURONS_NOT_RESTORED.toString()));
            }
            countLayers++;


        }

        if (oneOrTwo) {
            neuralNetworkTwo = new NeuralNetwork(learningRate, sigmoid, dsigmoid, numbersOfNeuronsInLayer, layers);
            Gasket.setNeuralNetworkTwo(neuralNetworkTwo);
        } else {
            neuralNetwork = new NeuralNetwork(learningRate, sigmoid, dsigmoid, numbersOfNeuronsInLayer, layers);
            Gasket.setNeuralNetwork(neuralNetwork);
        }
    }




    private void createSigmoidDsigmoid() {
        sigmoid = x -> 1 / (1 + Math.exp(-x));
        dsigmoid = y -> y * (1 - y);
    }



    private String parseString(Enums key, String in) {
//        ConsoleHelper.writeMessage(key.toString() + "   " + in);
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
