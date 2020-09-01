package main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            numbersOfNeuronsInLayer = new int[5];
            numbersOfNeuronsInLayer[0] = 3;
            numbersOfNeuronsInLayer[1] = 6;
            numbersOfNeuronsInLayer[2] = 6;
            numbersOfNeuronsInLayer[3] = 4;
            numbersOfNeuronsInLayer[4] = 2;
        } else {
            numbersOfNeuronsInLayer = new int[6];
            numbersOfNeuronsInLayer[0] = numberOfSensoryNeurons;
            numbersOfNeuronsInLayer[1] = (int) Math.round(numberOfSensoryNeurons * 2.1);    // 4258
            numbersOfNeuronsInLayer[2] = (int) Math.round(numberOfSensoryNeurons * 1.2);    // 2281
            numbersOfNeuronsInLayer[3] = (int) Math.round(numberOfSensoryNeurons * 0.5);    // 912
            numbersOfNeuronsInLayer[4] = (int) Math.round(numberOfSensoryNeurons * 0.1);    // 152
            numbersOfNeuronsInLayer[5] = 3;
        }
        return new NeuralNetwork(learningRate, sigmoid, dsigmoid, numbersOfNeuronsInLayer);
    }




//    public boolean startLearning() {
//        digits();
//        ConsoleHelper.writeMessage("Хух! НАУЧИЛСЯ!");
//        FormDigits f = new FormDigits(nn);
//        new Thread(f).start();
//        return true;
//    }



    private void digits() {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.NN_LEARNING_PROCESS_STARTED));

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

            if (Gasket.isWhetherOrNotShowLearningProcess()) {
                ConsoleHelper.writeMessage(StringHelper.getString("epoch: " + i + ". correct: " + right
                        + ". error: " + errorSum));
            }
        }

        ConsoleHelper.writeMessage(StringHelper.getString(Enums.
                THE_NN_LEARNING_PROCESS_IS_COMPLETED_I_AM_SAVING));
        keepTheNumberOfNeuronsInTheLayer();
    }



//    private void digits() {
//        ConsoleHelper.writeMessage(StringHelper.getString(Enums.NN_LEARNING_PROCESS_STARTED));
//
//        for (int i = 1; i < epochs; i++) {
//            int right = 0;
//            double errorSum = 0;
//            int batchSize = 100;
//
//            for (int j = 0; j < batchSize; j++) {
//                int imgIndex = (int)(Math.random() * samples);
////                double[] targets = new double[10];
//                double[] targets = new double[numberOfOutputNeurons];
//                int digit = digits[imgIndex];
//                targets[digit] = 1;
//
//                double[] outputs = nn.feedForward(inputs[imgIndex]);
//                int maxDigit = 0;
//                double maxDigitWeight = -1;
//
////                for (int k = 0; k < 10; k++) {
//                for (int k = 0; k < numberOfOutputNeurons; k++) {
//                    if (outputs[k] > maxDigitWeight) {
//                        maxDigitWeight = outputs[k];
//                        maxDigit = k;
//                    }
//                }
//
//                if (digit == maxDigit) right++;
//
////                for (int k = 0; k < 10; k++) {
//                for (int k = 0; k < numberOfOutputNeurons; k++) {
//                    errorSum += (targets[k] - outputs[k]) * (targets[k] - outputs[k]);
//                }
//                nn.backpropagation(targets);
//            }
//
//            if (Gasket.isWhetherOrNotShowLearningProcess()) {
//                ConsoleHelper.writeMessage(StringHelper.getString("epoch: " + i + ". correct: " + right
//                        + ". error: " + errorSum));
//            }
//        }
//        ConsoleHelper.writeMessage(StringHelper.getString(Enums.
//                THE_NN_LEARNING_PROCESS_IS_COMPLETED_I_AM_SAVING));
//        keepTheNumberOfNeuronsInTheLayer();
//    }



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
