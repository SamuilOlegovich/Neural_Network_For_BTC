package main.model;

import main.Enums;
import main.Gasket;
import main.StringHelper;
import main.view.ConsoleHelper;

import java.util.ArrayList;




// Сохраняем считанные данные, и преобразовываем их в матрицы обучения
public class DownloadedData {
    private ArrayList<String> downloadedDataList;   // массив полученых строк, в каждой строке по 117 свечей (одна строка один паттерн)
    private int sizeDownloadedDataList;
    private int numberOfInputNeurons;               // количество входных нейронов
    private double[][] dataForNN;                   // готовые данные для НН
    private int[] repliesForNN;                     // ответы по данным для НН
    private boolean predictNo;                      // прогнозировать или нет

    public DownloadedData() {
        this.numberOfInputNeurons = Gasket.getNumberOfInputNeurons();
        this.downloadedDataList = new ArrayList<>();
        this.predictNo = false;
        Gasket.setDownloadedData(this);
    }

    public DownloadedData(boolean b) {
        this.numberOfInputNeurons = Gasket.getNumberOfInputNeurons();
        this.downloadedDataList = new ArrayList<>();
        this.predictNo = b;
        Gasket.setDownloadedData(this);
    }




    // buy;90.0;78.0;-56.0; .... ;6757.7
    public void fillMatrixArray() {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.FORMING_MATRIX_FOR_TRAINING_NN));
        int sizeOut = downloadedDataList.size() % 100;

        for (int i = sizeOut; i > 0; i--) {
            downloadedDataList.remove(i);
        }

        if (predictNo) {
            dataForNN = new double[downloadedDataList.size()][numberOfInputNeurons];
            repliesForNN = new int[downloadedDataList.size()];
            for (int a = 0; a < downloadedDataList.size(); a++) {
                String[] strings = downloadedDataList.get(a).split(";");
                for (int i = 0; i < strings.length; i++) {

                    if (i != 0) {
                        dataForNN[a][i - 1] = Double.parseDouble(strings[i]);
                    } else {
                        if (Double.parseDouble(strings[i]) == 0.0) {
                            repliesForNN[a] = 0;
                        } else if (Double.parseDouble(strings[i]) == 1.0) {
                            repliesForNN[a] = 1;
                        }
                    }
                }
            }
        } else {
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
        }

        ConsoleHelper.writeMessage(StringHelper.getString(downloadedDataList.size() + " "
                + Enums.MATRIX_FORMED.toString()));
        sizeDownloadedDataList = downloadedDataList.size();
        downloadedDataList.clear();
    }


    public int getSizeDownloadedDataList() {
        return sizeDownloadedDataList;
    }

    public void addDownloadedList(String s) {
        downloadedDataList.add(s);
    }

    public void clearDownloadedDataList() {
        downloadedDataList.clear();
    }

    public int getNumberOfSensoryNeurons() {
        return numberOfInputNeurons;
    }

    public double[][] getDataForNN() {
        return dataForNN;
    }


    public int[] getRepliesForNN() {
        return repliesForNN;
    }
}
