import java.util.ArrayList;

public class ReadAndConvert {
    private TransformerHistory transformerHistory;
    private DownloadedData downloadedData;
    private String pathHistory;

    public ReadAndConvert() {
        this.transformerHistory = new TransformerHistory();
        this.pathHistory = Gasket.getFilesAndPathCreator().getPathHistory();
        this.downloadedData = Gasket.getDownloadedData();
        readFileHistory();
    }




    private void readFileHistory() {
        try {
            ArrayList<String> listHistory =  WriterAndReadFile.readFile(pathHistory);

            if (listHistory.size() < 1) {
                ConsoleHelper.writeMessage(StringHelper.getString(Enums.SETTINGS_IN_THE_HISTORY_FILE_NOT_DETECTED));
            }

            ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_SUCCESSFULLY_READ));

            listHistory.remove(0);
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
//            double open = getData(Enums.OPEN, s);
//            double high = getData(Enums.HIGH, s);
//            double close = getData(Enums.CLOSE, s);
//            double low = getData(Enums.LOW, s);
//            double volume = getData(Enums.VOLUME, s);
//
//            double candleBody = open - close;                                   // тело свечи
//            double fromHighToLow = high - low;                                  // от хай до лов
//            double upperShadow = open > close ? high - open : high - close;     // верхняя тень
//            double lowerShadow = open > close ? close - low : open - low;       // нижняя тень
//            double openHigh = high - open;                                      // опен хай
//            double openLow = open - low;                                        // опен лов
//            double volumePerPoint = volume / fromHighToLow;                     // объем на пункт
//            double volumeInCandleBody = volumePerPoint * candleBody;            // объем в теле свечи
//            double volumeAtTopOfShadow = volumePerPoint * upperShadow;          // объем в верхней части тени
//            double volumeAtLowOfShadow = volumePerPoint * lowerShadow;          // объем в нижней части тени
//            double volumeOpenHigh = volumePerPoint * openHigh;                  // объем в опен хай
//            double volumeOpenLow = volumePerPoint * openLow;                    // объем в опен лов
//            double volumeOfWholeCandle = volume;                                // объем всей свечи

//            stringBuilder.append(";").append(candleBody).append(";").append(fromHighToLow).append(";")
//                    .append(upperShadow).append(";").append(lowerShadow).append(";").append(openHigh).append(";")
//                    .append(openLow).append(";").append(volumePerPoint).append(";").append(volumeInCandleBody)
//                    .append(";").append(volumeAtTopOfShadow).append(";").append(volumeAtLowOfShadow)
//                    .append(";").append(volumeOpenHigh).append(";").append(volumeOpenLow)
//                    .append(";").append(volumeOfWholeCandle);
        }
        arrayList.clear();
        // наполняем входящий лист отконвертированными строками для обучения NN
        downloadedData.addDownloadedList(stringBuilder.toString());
    }



    // находим паттерны
    private void findPatterns(ArrayList<String> in) {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.STARTING_CONVERTING_HISTORY));

        for (int a = 1; a < ((in.size() - Gasket.getNumberOfInputNeurons()
                / Gasket.getNumberOfIndicatorsForOneCandle()) - Gasket.getNumberOfCandlesToDetectMovement()); a++) {
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
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_CONVERSION_OVER));
        // формируем марицу для обучения
        downloadedData.fillMatrixArray();
    }


//    // DATE_TIME;OPEN;HIGH;LOW;CLOSE;VOLUME
//    //22.07.2020 0:00:00;9393,5;9398,0;9393,0;9394,0;4808147
//    //22.07.2020 0:01:00;9394,0;9394,5;9389,5;9390,0;2206761
//    //22.07.2020 0:02:00;9390,0;9392,0;9388,5;9391,5;1017803
//    //22.07.2020 0:03:00;9391,5;9392,0;9391,5;9391,5;1142942
//    private double getData(Enums e, String in) {
//        String[] strings = in.replaceAll(",", ".").split(";");
//
//        if (e.equals(Enums.OPEN)) {
//            return Double.parseDouble(strings[1]);
//        } else if (e.equals(Enums.HIGH)) {
//            return Double.parseDouble(strings[2]);
//        } else if (e.equals(Enums.LOW)) {
//            return Double.parseDouble(strings[3]);
//        } else if (e.equals(Enums.CLOSE)) {
//            return Double.parseDouble(strings[4]);
//        } else if (e.equals(Enums.VOLUME)) {
//            return Double.parseDouble(strings[5]);
//        }
//        return 0.0;
//    }

}
