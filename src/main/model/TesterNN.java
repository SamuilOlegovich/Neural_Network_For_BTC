package main.model;


import main.*;
import main.view.ConsoleHelper;

import java.util.ArrayList;




public class TesterNN {
    private ArrayList<String> downloadedDataList;   // массив полученых строк, в каждой строке по 117 свечей (одна строка один паттерн)
    private int sizeDownloadedDataList;
    private int numberOfOutputNeurons;
    private int numberOfInputNeurons;               // количество входных нейронов
    private double[] dataForNextNN;                 // данные для следующей сети предсказателя
    private double[][] dataForNN;                   // готовые данные для НН
    private int[] repliesForNN;                     // ответы по данным для НН
    private NeuralNetwork nn;


    public TesterNN() {
        this.dataForNextNN = new double[Gasket.getNumberOfOutputNeurons() + 1];
        this.numberOfOutputNeurons = Gasket.getNumberOfOutputNeurons();
        this.numberOfInputNeurons = Gasket.getNumberOfInputNeurons();
        this.downloadedDataList = new ArrayList<>();
        this.nn = Gasket.getNeuralNetwork();
        Gasket.setTesterNN(this);
        new ReadAndConvert(true);
        testStart();
    }


    private void testStart() {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.NN_TESTING_PROCESS_STARTED));
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

            for (int i = 0; i < outputs.length; i++) {
                if (maxDigitWeight < outputs[i]) {
                    maxDigitWeight = outputs[i];
                    maxDigit = i;
                }
            }

            dataForNextNN[0] = digit;
            for (int i = 0; i < outputs.length; i++) {
                dataForNextNN[i + 1] = outputs[i];
            }


            if (digit == maxDigit && digit == 1 && maxDigitWeight > minDigitWeightForBuy) {
                buyGood++;
            } else if (digit != maxDigit && digit == 1  && maxDigitWeight > minDigitWeightForBuy) {
                if (maxDigit == 0) dB++;
                buyBad++;
            } else if (digit == maxDigit && digit == 2) {
                sellGood++;
            } else if (digit != maxDigit && digit == 2) {
                if (maxDigit == 0) dS++;
                sellBad++;
            } else if (digit == maxDigit && digit == 0) {
                flatGood++;
            } else if (digit != maxDigit && digit == 0) {
                flatBad++;
            }
        }

        ConsoleHelper.writeMessage("\n"
                + "   === STATISTIC ===   " + "\n"
                + buyGood + " === " + buyBad + " === " + dB + "\n"
                + sellGood + " === " + sellBad + " === " + dS + "\n"
                + flatGood + " === " + flatBad + "\n"
        );


        ConsoleHelper.writeMessage(StringHelper.getString(Enums.
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
}
