package main.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.Enums;
import main.Gasket;
import main.view.ConsoleHelper;

import java.util.ArrayList;
import java.util.function.UnaryOperator;



public class TeacherNeuralNetwork {
    @JsonIgnore
    private UnaryOperator<Double> dsigmoid;
    @JsonIgnore
    private UnaryOperator<Double> sigmoid;
    @JsonIgnore
    private double learningRate;
    @JsonIgnore
    private double[][] inputs;                   // паттерны
    @JsonIgnore
    private boolean predictNo;                   // прогнозировать или нет
    @JsonIgnore
    private NeuralNetwork nn;
    @JsonIgnore
    private int[] digits;                        // для ответов к паттенам


    private int[] numbersOfNeuronsInLayer;        // количество нейронов в слое
    private int numberOfSensoryNeurons;
    private int numberOfOutputNeurons;
    private int samples;                         // количество входящих паттернов
    private int epochs;




    public TeacherNeuralNetwork() {
        this.numberOfSensoryNeurons = Gasket.getDownloadedData().getNumberOfSensoryNeurons();
        this.samples = Gasket.getDownloadedData().getSizeDownloadedDataList();
        this.numberOfOutputNeurons = Gasket.getNumberOfOutputNeurons();
        this.digits = Gasket.getDownloadedData().getRepliesForNN();
        this.inputs = Gasket.getDownloadedData().getDataForNN();
        this.epochs = Gasket.getNumberOfTrainingCycles();
        this.learningRate = Gasket.getLearningRate();
        createSigmoidDsigmoid();
        this.predictNo = false;
        this.nn = createNeuralNetwork();
        Gasket.setTeacherNeuralNetwork(this);
        Gasket.setNeuralNetwork(nn);
        digits();
    }

    public TeacherNeuralNetwork(boolean b) {
        this.samples = Gasket.getDownloadedData().getSizeDownloadedDataList();
        this.digits = Gasket.getDownloadedData().getRepliesForNN();
        this.inputs = Gasket.getDownloadedData().getDataForNN();
        this.epochs = Gasket.getNumberOfTrainingCycles();
        this.learningRate = Gasket.getLearningRate();
        this.numberOfSensoryNeurons = 3;
        this.numberOfOutputNeurons = 2;
        createSigmoidDsigmoid();
        this.predictNo = b;
        this.nn = createNeuralNetwork();
        Gasket.setTeacherNeuralNetwork(this);
        Gasket.setNeuralNetwork(nn);
        digits();
    }


    private NeuralNetwork createNeuralNetwork() {
        if (predictNo) {
            numbersOfNeuronsInLayer = new int[4];
            numbersOfNeuronsInLayer[0] = 3;
            numbersOfNeuronsInLayer[1] = 6;
            numbersOfNeuronsInLayer[2] = 4;
            numbersOfNeuronsInLayer[3] = 2;
        } else {
//            numbersOfNeuronsInLayer = new int[6];
//            numbersOfNeuronsInLayer[0] = numberOfSensoryNeurons;
//            numbersOfNeuronsInLayer[1] = (int) Math.round(numberOfSensoryNeurons * 0.8);
//            numbersOfNeuronsInLayer[2] = (int) Math.round(numberOfSensoryNeurons * 0.6);
//            numbersOfNeuronsInLayer[3] = (int) Math.round(numberOfSensoryNeurons * 0.4);
//            numbersOfNeuronsInLayer[4] = (int) Math.round(numberOfSensoryNeurons * 0.2);
//            numbersOfNeuronsInLayer[5] = 3;


            numbersOfNeuronsInLayer = new int[5];
            numbersOfNeuronsInLayer[0] = numberOfSensoryNeurons;
            numbersOfNeuronsInLayer[1] = 8;
            numbersOfNeuronsInLayer[2] = 6;
            numbersOfNeuronsInLayer[3] = 5;
            numbersOfNeuronsInLayer[4] = 3;
        }
        return new NeuralNetwork(learningRate, sigmoid, dsigmoid, numbersOfNeuronsInLayer);
    }




    private void digits() {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.NN_LEARNING_PROCESS_STARTED));
        int interest = (epochs / 100);

        for (int i = 1; i < epochs; i++) {
            int right = 0;
            double errorSum = 0;
            int batchSize = 100;

            for (int j = 0; j < batchSize; j++) {
                int imgIndex = (int)(Math.random() * samples);
                double[] targets = new double[numberOfOutputNeurons];
                int digit = digits[j];
                targets[digit] = 1;

                double[] outputs = nn.feedForward(inputs[j]);
                int maxDigit = 0;
                double maxDigitWeight = -1;

                for (int k = 0; k < numberOfOutputNeurons; k++) {
                    if (outputs[k] > maxDigitWeight) {
                        maxDigitWeight = outputs[k];
                        maxDigit = k;
                    }
                }

                if (digit == maxDigit) right++;

                for (int k = 0; k < numberOfOutputNeurons; k++) {
                    errorSum += (targets[k] - outputs[k]) * (targets[k] - outputs[k]);
                }
                nn.backpropagation(targets);
            }

            // выводим процент обучения NN
            if (interest * 10 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("10%"));
            } else if (interest * 20 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("20%"));
            } else if (interest * 30 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("30%"));
            } else if (interest * 40 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("40%"));
            } else if (interest * 50 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("50%"));
            } else if (interest * 60 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("60%"));
            } else if (interest * 70 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("70%"));
            } else if (interest * 80 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("80%"));
            } else if (interest * 90 == i) {
                ConsoleHelper.writeMessage(StringHelper.getString("90%"));
            }

            if (Gasket.isWhetherOrNotShowLearningProcess()) {
                ConsoleHelper.writeMessage(StringHelper.getString("epoch: " + i + ". correct: " + right
                        + ". error: " + errorSum));
            }
        }

        ConsoleHelper.writeMessage(StringHelper.getString(Enums.
                THE_NN_LEARNING_PROCESS_IS_COMPLETED_I_AM_SAVING));
        keepTheNumberOfNeuronsInTheLayer();
    }


    // сохранить количество нейронов в слое
    private void keepTheNumberOfNeuronsInTheLayer() {
        ArrayList<String> arrayList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            arrayList.add(objectMapper.writeValueAsString(this));
        } catch (JsonProcessingException ex) { }

        arrayList.add("\n" + Enums.NEXT.toString() + "\n");
        arrayList.addAll(nn.saveBalanceData());
        Gasket.getReadAndWriteNeuralNetworkSetting().saveAllNeuralNetworkData(arrayList, predictNo);
    }



    private void createSigmoidDsigmoid() {
        sigmoid = x -> 1 / (1 + Math.exp(-x));
        dsigmoid = y -> y * (1 - y);
    }








    public int getNumberOfSensoryNeurons() {
        return numberOfSensoryNeurons;
    }

    public void setNumberOfSensoryNeurons(int numberOfSensoryNeurons) {
        this.numberOfSensoryNeurons = numberOfSensoryNeurons;
    }

    public int getNumberOfOutputNeurons() {
        return numberOfOutputNeurons;
    }

    public void setNumberOfOutputNeurons(int numberOfOutputNeurons) {
        this.numberOfOutputNeurons = numberOfOutputNeurons;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public int getEpochs() {
        return epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    public int[] getNumbersOfNeuronsInLayer() {
        return numbersOfNeuronsInLayer;
    }

    public void setNumbersOfNeuronsInLayer(int[] numberOfNeuronsInLayer) {
        this.numbersOfNeuronsInLayer = numberOfNeuronsInLayer;
    }
}
