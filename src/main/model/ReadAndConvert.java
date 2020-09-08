package main.model;

import main.Enums;
import main.Gasket;
import main.view.ConsoleHelper;

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



    private void convertHistory(ArrayList<String> in) {
        ArrayList<String> arrayList = new ArrayList<>(in);
        StringBuilder stringBuilder = new StringBuilder(arrayList.get(0));
        transformerHistory.startData(arrayList.get(1));
        arrayList.remove(1);
        arrayList.remove(0);

        for (String s : arrayList) {
            stringBuilder.append(";").append(transformerHistory.transformHistory(s));
        }
        arrayList.clear();
        // наполняем входящий лист отконвертированными строками для обучения NN
        if (forTestedNN) {
            testerNN.addDownloadedList(stringBuilder.toString());
        } else {
            downloadedData.addDownloadedList(stringBuilder.toString());
        }
    }



    // находим паттерны
    private void findPatterns(ArrayList<String> in) {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.STARTING_CONVERTING_HISTORY));

        if (Gasket.isPredictNextCandle()) {
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
