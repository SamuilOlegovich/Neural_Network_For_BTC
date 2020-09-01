package main;

import main.model.DownloadedData;
import main.view.ConsoleHelper;

import java.util.ArrayList;



public class ReadAndWriteNeuralNetworkSetting {
//    private static ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting;
    private String pathSavedWeights;
    private String pathWeights;


    public ReadAndWriteNeuralNetworkSetting() {
        this.pathSavedWeights = Gasket.getFilesAndPathCreator().getPathSavedWeights();
        this.pathWeights = Gasket.getFilesAndPathCreator().getPathWeights();
        Gasket.setReadAndWriteNeuralNetworkSetting(this);
    }



    // считываем настройки NN
    protected ArrayList<String> readFileWeights() {
        ArrayList<String> listHistory = null;
        try {
            listHistory =  new ArrayList<>(WriterAndReadFile.readFile(pathWeights));
            if (listHistory.size() < 1) {
                ConsoleHelper.writeMessage(StringHelper.getString(Enums.WEIGHTS_FILE_NOT_DETECTED));
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.ERROR_WHEN_READING_THE_WEIGHTS_FILE));
        }
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.WEIGHTS_SUCCESSFULLY_READ));
        return listHistory;
    }



    // сохраняем найденые веса после обучения NN
    protected void saveAllNeuralNetworkData(ArrayList<String> in, boolean b) {
        ArrayList<String> arrayList = new ArrayList<>(in);
        StringBuilder stringBuilder = new StringBuilder(Enums.START.toString() + "\n");
        if (in.get(0).equalsIgnoreCase(Enums.NEXT.toString())) {
            arrayList.remove(0);
        }

        for (String s : arrayList) {
            stringBuilder.append(s);
        }

        stringBuilder.append("\n").append(Enums.END.toString()).append("\n\n");

        try {
            if (b) {
                WriterAndReadFile.writerFile(stringBuilder.toString(),
                        Gasket.getFilesAndPathCreator().getPathSavedWeightsPredictor(), false);
            } else {
                WriterAndReadFile.writerFile(stringBuilder.toString(), pathSavedWeights, false);
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.WEIGHTS_NOT_SAVED));
        }
    }


    // сохраняем историю ответов после тестировки NN
    public void saveHistoryForFortuneteller(ArrayList<String> in, String path) {
        ArrayList<String> arrayList = new ArrayList<>(in);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("answer;flat;buy;sell\n");

        for (String s : arrayList) {
            stringBuilder.append(s).append("\n");
        }

        try {
            WriterAndReadFile.writerFile(stringBuilder.toString(), path, false);
        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_FOR_FORTUNETELLER_NOT_SAVED));
        }
        readHistoryForFortuneteller(path);
    }



    private void readHistoryForFortuneteller(String path) {
        try {
            ArrayList<String> listHistory =  new ArrayList<>(WriterAndReadFile.readFile(path));
            if (listHistory.size() < 1) {
                ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_FOR_FORTUNETELLER_FILE_NOT_DETECTED));
            }

            listHistory.remove(0);
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_FOR_FORTUNETELLER_SUCCESSFULLY_READ));
            DownloadedData downloadedData = new DownloadedData(true);
            for (String s : listHistory) {
                downloadedData.addDownloadedList(s);
            }

            downloadedData.fillMatrixArray();
            new TeacherNeuralNetwork(true);
            listHistory.clear();
        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.ERROR_WHEN_READING_THE_HISTORY_FILE));
        }
    }
}
