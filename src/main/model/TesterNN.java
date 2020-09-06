package main.model;


import main.*;
import main.view.ConsoleHelper;

import java.util.ArrayList;




public class TesterNN {
    private ArrayList<String> downloadedDataList;       // массив полученых строк, в каждой строке по ... свечей (одна строка один паттерн)
    private ArrayList<String> dataForNextNN;            // данные для следующей сети предсказателя
    private int sizeDownloadedDataList;
    private int numberOfOutputNeurons;
    private int numberOfInputNeurons;               // количество входных нейронов
    private double[][] dataForNN;                   // готовые данные для НН
    private NeuralNetwork nnTwo;
    private int[] repliesForNN;                     // ответы по данным для НН
    private NeuralNetwork nn;
    private boolean flag;


    public TesterNN() {
//        this.dataForNextNN = new double[Gasket.getNumberOfOutputNeurons() + 1];
        this.numberOfOutputNeurons = Gasket.getNumberOfOutputNeurons();
        this.numberOfInputNeurons = Gasket.getNumberOfInputNeurons();
        this.downloadedDataList = new ArrayList<>();
        this.dataForNextNN = new ArrayList<>();
        this.nn = Gasket.getNeuralNetwork();
        Gasket.setTesterNN(this);
        new ReadAndConvert(true);
        this.flag = false;
        testStart();
    }

    public TesterNN(boolean b) {
//        this.dataForNextNN = new double[Gasket.getNumberOfOutputNeurons() + 1];
        this.numberOfOutputNeurons = Gasket.getNumberOfOutputNeurons();
        this.numberOfInputNeurons = Gasket.getNumberOfInputNeurons();
        this.downloadedDataList = new ArrayList<>();
        this.nnTwo = Gasket.getNeuralNetworkTwo();
        this.dataForNextNN = new ArrayList<>();
        this.nn = Gasket.getNeuralNetwork();
        Gasket.setTesterNN(this);
        new ReadAndConvert(true);
        this.flag = b;
        testStart();
    }

    // доделать вторую нейро сеть и сравнение


    private void testStart() {
        if (flag) ConsoleHelper.writeMessage(StringHelper.getString(Enums.NN_TESTING_PROCESS_STARTED));
        int buyGood = 0;
        int buyBad = 0;
        int dB = 0;
        int sellGood = 0;
        int sellBad = 0;
        int dS = 0;
        int flatGood = 0;
        int flatBad = 0;


        for (int a = 0; a < repliesForNN.length; a++) {
            int imgIndex = a;

            double[] targets = new double[numberOfOutputNeurons];

            int digit = repliesForNN[imgIndex];
            targets[digit] = 1;
            int maxDigit = 0;           // номер в массиве где находится самые большие веса
            double maxDigitWeight = -1;
            double minDigitWeightForBuy = Gasket.getMinDigitWeightForBuy();
            double minDigitWeightForSell = Gasket.getMinDigitWeightForSell();
            double[] outputs = nn.feedForward(dataForNN[imgIndex]);
            double[] outputsTwo = new double[2];
            if (flag) { outputsTwo = nnTwo.feedForward(outputs); }



            for (int i = 0; i < outputs.length; i++) {
                if (maxDigitWeight < outputs[i]) {
                    maxDigitWeight = outputs[i];
                    maxDigit = i;
                }
            }




            if (flag) {
                if (digit == maxDigit && digit == 1
                        && outputsTwo[1] > 0.98
                        && maxDigitWeight > 0.987) {
                    buyGood++;
                } else if (digit != maxDigit && digit == 1
                        && outputsTwo[1] > 0.98
                        && maxDigitWeight > 0.985) {
                    if (maxDigit == 0) dB++;
                    buyBad++;
                    ConsoleHelper.writeMessage(StringHelper.getString(a + ""));

                } else if (digit == maxDigit && digit == 2
                        && outputsTwo[1] > 0.98
                        && maxDigitWeight > 0.98) {
                    sellGood++;
                } else if (digit != maxDigit && digit == 2
                        && outputsTwo[1] > 0.98
                        && maxDigitWeight > 0.98) {
                    if (maxDigit == 0) dS++;
                    sellBad++;
                } else if (digit == maxDigit && digit == 0 && outputsTwo[1] > 0.98) {
                    flatGood++;
                } else if (digit != maxDigit && digit == 0 && outputsTwo[1] > 0.98) {
                    flatBad++;
                }
            }


            // заполняем данные для сети предсказателя
            if (!flag) {
                StringBuilder outForList = new StringBuilder();
                if (digit == maxDigit) outForList.append(1 + ";");
                else outForList.append(0 + ";");
                for (int i = 0; i < outputs.length; i++) {
                    if (i != outputs.length - 1) {
                        outForList.append(outputs[i]).append(";");
                    } else {
                        outForList.append(outputs[i]);
                    }
                }
                dataForNextNN.add(outForList.toString());
            }
        }

        if (flag) {
            ConsoleHelper.writeMessage("\n"
                    + "   === STATISTIC ===   " + "\n"
                    + buyGood + " === " + buyBad + " === " + dB + "\n"
                    + sellGood + " === " + sellBad + " === " + dS + "\n"
                    + flatGood + " === " + flatBad + "\n"
            );
        }

        if (!flag) saveTestResults();

        if (flag) ConsoleHelper.writeMessage(StringHelper.getString(Enums.
                THE_NN_TESTING_PROCESS_IS_COMPLETED));
    }









    // buy;90.0;78.0;-56.0; .... ;6757.7
    public void fillMatrixArray() {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.FORMING_MATRIX_FOR_TRAINING_NN));
        int sizeOut = downloadedDataList.size() % 100;

        for (int i = sizeOut; i > 0; i--) {
            downloadedDataList.remove(i);
        }

        dataForNN = new double[downloadedDataList.size()][numberOfInputNeurons];
        repliesForNN = new int[downloadedDataList.size()];

        for (int a = 0; a < downloadedDataList.size(); a++) {
            String[] strings = downloadedDataList.get(a).split(";");

            for (int i = 0; i < strings.length; i++) {
                if (i != 0) {
                    dataForNN[a][i - 1] = Double.parseDouble(strings[i]);
                } else {
                    if (strings[i].equalsIgnoreCase(Enums.BUY.toString())) {
                        repliesForNN[a] = 1;
                    } else if (strings[i].equalsIgnoreCase(Enums.SELL.toString())) {
                        repliesForNN[a] = 2;
                    } else if (strings[i].equalsIgnoreCase(Enums.FLAT.toString())) {
                        repliesForNN[a] = 0;
                    }
                }
            }
        }
        ConsoleHelper.writeMessage(StringHelper.getString(downloadedDataList.size()
                + " " + Enums.MATRIX_FORMED.toString()));
        sizeDownloadedDataList = downloadedDataList.size();
        downloadedDataList.clear();
    }



    public void addDownloadedList(String s) {
        downloadedDataList.add(s);
    }


    private void saveTestResults() {
        Gasket.getReadAndWriteNeuralNetworkSetting().saveHistoryForFortuneteller(dataForNextNN,
                Gasket.getFilesAndPathCreator().getPathHistoryForFortuneteller());
    }
}
