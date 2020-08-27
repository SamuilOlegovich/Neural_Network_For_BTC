package main.model;


import main.*;
import main.view.ConsoleHelper;

import java.util.ArrayList;




public class TesterNN {
    private ArrayList<String> downloadedDataList;   // массив полученых строк, в каждой строке по 117 свечей (одна строка один паттерн)
    private int sizeDownloadedDataList;
    private int numberOfOutputNeurons;
    private int numberOfInputNeurons;               // количество входных нейронов
    private double[][] dataForNN;                   // готовые данные для НН
    private int[] repliesForNN;                     // ответы по данным для НН
    private NeuralNetwork nn;


    public TesterNN() {
        this.numberOfOutputNeurons = Gasket.getNumberOfOutputNeurons();
        this.numberOfInputNeurons = Gasket.getNumberOfInputNeurons();
        this.downloadedDataList = new ArrayList<>();
        this.nn = Gasket.getNeuralNetwork();
        Gasket.setTesterNN(this);
        new ReadAndConvert(true);
    }


    private void digits() {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.NN_TESTING_PROCESS_STARTED));

        int right = 0;
        double errorSum = 0;
        int batchSize = 100;

        for (int a = 0; a < repliesForNN.length; a++) {
            int imgIndex = a;

            double[] targets = new double[numberOfOutputNeurons];
            int digit = repliesForNN[imgIndex];
            targets[digit] = 1;

            double[] outputs = nn.feedForward(dataForNN[imgIndex]);
            int maxDigit = 0;
            double maxDigitWeight = -1;
        }

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
        ConsoleHelper.writeMessage(StringHelper.getString(downloadedDataList.size() + " "
                + Enums.MATRIX_FORMED.toString()));
        sizeDownloadedDataList = downloadedDataList.size();
        downloadedDataList.clear();
    }



    public void addDownloadedList(String s) {
        downloadedDataList.add(s);
    }






}
