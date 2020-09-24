package main.model;

import main.Enums;
import main.Gasket;
import main.view.ConsoleHelper;

import java.awt.*;
import java.util.ArrayList;




public class ReadAndConvert {
    private TransformerHistory transformerHistory;
    private DownloadedData downloadedData;
    private boolean forTestedNN;
    private String pathHistory;
    private TesterNN testerNN;

    public ReadAndConvert() {
        this.transformerHistory = new TransformerHistory();
        this.pathHistory = Gasket.getFilesAndPathCreator().getPathHistory();
        this.downloadedData = Gasket.getDownloadedData();
        this.forTestedNN = false;
        readFileHistory();
    }

    public ReadAndConvert(boolean b) {
        this.transformerHistory = new TransformerHistory();
        this.pathHistory = Gasket.getFilesAndPathCreator().getPathHistory();
        this.downloadedData = Gasket.getDownloadedData();
        this.testerNN = Gasket.getTesterNN();
        this.forTestedNN = b;
        readFileHistory();
    }




    private void readFileHistory() {
        try {
            ArrayList<String> listHistory =  new ArrayList<>(WriterAndReadFile.readFile(pathHistory));

            if (listHistory.size() < 1) {
                ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_FILE_NOT_DETECTED));
            }
            listHistory.remove(0);
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_SUCCESSFULLY_READ));
            findPatterns(listHistory);
            listHistory.clear();
        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.ERROR_WHEN_READING_THE_HISTORY_FILE));
        }
    }



//    private void convertHistory(ArrayList<String> in) {
//        ArrayList<String> arrayList = new ArrayList<>(in);
//        StringBuilder stringBuilder = new StringBuilder(arrayList.get(0));
//        transformerHistory.startData(arrayList.get(1));
//        arrayList.remove(1);
//        arrayList.remove(0);
//
//        for (String s : arrayList) {
//            stringBuilder.append(";").append(transformerHistory.transformHistory(s));
//        }
//        arrayList.clear();
//        // наполняем входящий лист отконвертированными строками для обучения NN
//        if (forTestedNN) {
//            testerNN.addDownloadedList(stringBuilder.toString());
//        } else {
//            downloadedData.addDownloadedList(stringBuilder.toString());
//        }
//    }




    // тест еще одной идеи
    private void convertHistory(ArrayList<String> in) {
        ArrayList<String> arrayList = new ArrayList<>(in);
        StringBuilder stringBuilder = new StringBuilder(arrayList.get(0));
        transformerHistory.startData(arrayList.get(1));
        arrayList.remove(1);
        arrayList.remove(0);

//        double a = StringHelper.getDataHistory(Enums.OPEN, arrayList.get(0));
//        double b = StringHelper.getDataHistory(Enums.CLOSE, arrayList.get(23));
        double a = StringHelper.getDataHistory(Enums.OPEN, arrayList.get(arrayList.size() - 25));
        double b = StringHelper.getDataHistory(Enums.CLOSE, arrayList.get(arrayList.size() - 1));
        double directionPreviousDay = 0.5;
        if (a > b) directionPreviousDay = 0.0;
        if (a < b) directionPreviousDay = 1.0;
        double directionFourClock = 0.5;
        a = StringHelper.getDataHistory(Enums.OPEN, arrayList.get(arrayList.size() - 5));
        b = StringHelper.getDataHistory(Enums.CLOSE, arrayList.get(arrayList.size() - 1));
        if (a > b) directionFourClock = 0.0;
        if (a < b) directionFourClock = 1.0;
        double one = 0.5;
        a = StringHelper.getDataHistory(Enums.OPEN, arrayList.get(arrayList.size() - 1));
        b = StringHelper.getDataHistory(Enums.CLOSE, arrayList.get(arrayList.size() - 1));
        if (a > b) one = 0.0;
        if (a < b) one = 1.0;
        double two = 0.5;
        a = StringHelper.getDataHistory(Enums.OPEN, arrayList.get(arrayList.size() - 2));
        b = StringHelper.getDataHistory(Enums.CLOSE, arrayList.get(arrayList.size() - 2));
        if (a > b) two = 0.0;
        if (a < b) two = 1.0;
        double three = 0.5;
        a = StringHelper.getDataHistory(Enums.OPEN, arrayList.get(arrayList.size() - 4));
        b = StringHelper.getDataHistory(Enums.CLOSE, arrayList.get(arrayList.size() - 4));
        if (a > b) three = 0.0;
        if (a < b) three = 1.0;
        double four = 0.5;
        a = StringHelper.getDataHistory(Enums.OPEN, arrayList.get(arrayList.size() - 5));
        b = StringHelper.getDataHistory(Enums.CLOSE, arrayList.get(arrayList.size() - 5));
        if (a > b) four = 0.0;
        if (a < b) four = 1.0;

        stringBuilder.append(";").append(directionPreviousDay).append(";").append(directionFourClock).append(";")
                .append(one).append(";").append(two).append(";").append(three).append(";").append(four);
        arrayList.clear();
        
        // наполняем входящий лист отконвертированными строками для обучения NN
        if (forTestedNN) {
            testerNN.addDownloadedList(stringBuilder.toString());
        } else {
            downloadedData.addDownloadedList(stringBuilder.toString());
        }
    }



//    // тест новой идеи
//    private void convertHistory(ArrayList<String> in) {
//        ArrayList<String> arrayList = new ArrayList<>(in);
//        StringBuilder stringBuilder = new StringBuilder(arrayList.get(0));
//        transformerHistory.startData(arrayList.get(1));
//        arrayList.remove(1);
//        arrayList.remove(0);
//        ArrayList<String> strings = new ArrayList<>();
//
//        for (String s : arrayList) {
//            strings.add(transformerHistory.transformHistory(s));
//        }
//        for (int i = 0; i < strings.size(); i++) {
//            if (i != strings.size() - 1) {
//                double a = Double.parseDouble(strings.get(i));
//                double b = Double.parseDouble(strings.get(i + 1));
//                if (a > 0 && b > 0 && a < b) strings.set(i, 0.0 + "");
//                else if (a < 0 && b < 0 && a > b) strings.set(i, 0.0 + "");
//            }
//        }
//        for (String s : strings) {
//            stringBuilder.append(";").append(s);
//        }
//
//        arrayList.clear();
//        strings.clear();
//        // наполняем входящий лист отконвертированными строками для обучения NN
//        if (forTestedNN) {
//            testerNN.addDownloadedList(stringBuilder.toString());
//        } else {
//            downloadedData.addDownloadedList(stringBuilder.toString());
//        }
//    }



//    // находим паттерны
//    private void findPatterns(ArrayList<String> in) {
//        ConsoleHelper.writeMessage(StringHelper.getString(Enums.STARTING_CONVERTING_HISTORY));
//
//        if (Gasket.isPredictNextCandle()) {
//            for (int a = 1; a < ((in.size() - (Gasket.getNumberOfInputNeurons()
//                    / Gasket.getNumberOfIndicatorsForOneCandle()))
//                    - Gasket.getNumberOfCandlesToDetectMovement()); a++) {
//                ArrayList<String> outList = new ArrayList<>();
//                int finish = 0;
//
//                for (int i = a - 1; i < (a + (Gasket.getNumberOfInputNeurons()
//                        / Gasket.getNumberOfIndicatorsForOneCandle())); i++) {
//                    outList.add(in.get(i));
//                    finish = i;
//                }
//                finish++;
//
//                String s = in.get(finish);
//                double close = StringHelper.getDataHistory(Enums.CLOSE, s);
//                double open = StringHelper.getDataHistory(Enums.OPEN, s);
//
//                if (open < close) {
//                    outList.add(0, Enums.BUY.toString());
//                } else if (open > close) {
//                    outList.add(0, Enums.SELL.toString());
//                } else {
//                    outList.add(0, Enums.FLAT.toString());
//                }
//
//                // конвертируем паттерны и наполняем входящий лист строками для обучения NN
//                convertHistory(outList);
//            }
//        } else {
//
//            // перебираем входящий список
//            for (int a = 1; a < ((in.size() - (Gasket.getNumberOfInputNeurons()
//                    / Gasket.getNumberOfIndicatorsForOneCandle()))
//                    - Gasket.getNumberOfCandlesToDetectMovement()); a++) {
//                ArrayList<String> outList = new ArrayList<>();
//                int finish = 0;
//
//                for (int i = a - 1; i < (a + (Gasket.getNumberOfInputNeurons()
//                        / Gasket.getNumberOfIndicatorsForOneCandle())); i++) {
//                    outList.add(in.get(i));
//                    finish = i;
//                }
//
//                double finishCloseSell = StringHelper.getDataHistory(Enums.CLOSE, in.get(finish))
//                        - Gasket.getPriceChangeToFormHistoryPattern();
//                double finishCloseBuy = StringHelper.getDataHistory(Enums.CLOSE, in.get(finish))
//                        + Gasket.getPriceChangeToFormHistoryPattern();
//                int sizeListOut = outList.size();
//                finish++;
//
//                for (int i = finish; i < finish + Gasket.getNumberOfCandlesToDetectMovement(); i++) {
//                    String s = in.get(i);
//                    double close = StringHelper.getDataHistory(Enums.CLOSE, s);
//                    double high = StringHelper.getDataHistory(Enums.HIGH, s);
//                    double low = StringHelper.getDataHistory(Enums.LOW, s);
//
//                    if (finishCloseBuy < close || finishCloseBuy < high) {
//                        outList.add(0, Enums.BUY.toString());
//                        break;
//                    } else if (finishCloseSell > close || finishCloseSell > low) {
//                        outList.add(0, Enums.SELL.toString());
//                        break;
//                    }
//                }
//
//                if (outList.size() == sizeListOut) {
//                    outList.add(0, Enums.FLAT.toString());
//                }
//                // конвертируем паттерны и наполняем входящий лист строками для обучения NN
//                convertHistory(outList);
//            }
//        }
//
//        ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_CONVERSION_OVER));
//        // формируем марицу для обучения
//        if (forTestedNN) {
//            testerNN.fillMatrixArray();
//        } else {
//            downloadedData.fillMatrixArray();
//        }
//    }


    // находим паттерны
    private void findPatterns(ArrayList<String> in) {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.STARTING_CONVERTING_HISTORY));

        if (Gasket.isPredictNextCandle()) {
            int referencePoint = 2 * 24;
            for (int a = 1; a < in.size() - 49; a++) {
                ArrayList<String> outList = new ArrayList<>();
                int finish = 0;

                for (int i = a - 1; i < a + 48; i++) {
                    outList.add(in.get(i));
                    finish = i;
                }
                finish++;

                String s = in.get(finish);
                double close = StringHelper.getDataHistory(Enums.CLOSE, s);
                double open = StringHelper.getDataHistory(Enums.OPEN, s);

                if (open < close) {
                    outList.add(0, Enums.BUY.toString());
                } else if (open > close) {
                    outList.add(0, Enums.SELL.toString());
                } else {
                    outList.add(0, Enums.FLAT.toString());
                }

                // конвертируем паттерны и наполняем входящий лист строками для обучения NN
                convertHistory(outList);
            }
        } else {

            // перебираем входящий список
            for (int a = 1; a < ((in.size() - (Gasket.getNumberOfInputNeurons()
                    / Gasket.getNumberOfIndicatorsForOneCandle()))
                    - Gasket.getNumberOfCandlesToDetectMovement()); a++) {
                ArrayList<String> outList = new ArrayList<>();
                int finish = 0;

                for (int i = a - 1; i < (a + (Gasket.getNumberOfInputNeurons()
                        / Gasket.getNumberOfIndicatorsForOneCandle())); i++) {
                    outList.add(in.get(i));
                    finish = i;
                }

                double finishCloseSell = StringHelper.getDataHistory(Enums.CLOSE, in.get(finish))
                        - Gasket.getPriceChangeToFormHistoryPattern();
                double finishCloseBuy = StringHelper.getDataHistory(Enums.CLOSE, in.get(finish))
                        + Gasket.getPriceChangeToFormHistoryPattern();
                int sizeListOut = outList.size();
                finish++;

                for (int i = finish; i < finish + Gasket.getNumberOfCandlesToDetectMovement(); i++) {
                    String s = in.get(i);
                    double close = StringHelper.getDataHistory(Enums.CLOSE, s);
                    double high = StringHelper.getDataHistory(Enums.HIGH, s);
                    double low = StringHelper.getDataHistory(Enums.LOW, s);

                    if (finishCloseBuy < close || finishCloseBuy < high) {
                        outList.add(0, Enums.BUY.toString());
                        break;
                    } else if (finishCloseSell > close || finishCloseSell > low) {
                        outList.add(0, Enums.SELL.toString());
                        break;
                    }
                }

                if (outList.size() == sizeListOut) {
                    outList.add(0, Enums.FLAT.toString());
                }
                // конвертируем паттерны и наполняем входящий лист строками для обучения NN
                convertHistory(outList);
            }
        }

        ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_CONVERSION_OVER));
        // формируем марицу для обучения
        if (forTestedNN) {
            testerNN.fillMatrixArray();
        } else {
            downloadedData.fillMatrixArray();
        }
    }
}
