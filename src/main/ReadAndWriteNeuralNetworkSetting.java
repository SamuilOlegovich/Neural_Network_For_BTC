package main;

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
//        readFileWeights();
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
            /////////////////////////////////
//            for (String s : listHistory) {
//                ConsoleHelper.writeMessage(s);
//            }
            ////////////////////////////////
        return listHistory;
    }



    // сохраняем найденые веса после обучения NN
    protected void saveAllNeuralNetworkData(ArrayList<String> in) {
        ArrayList<String> arrayList = new ArrayList<>(in);
        StringBuilder stringBuilder = new StringBuilder(Enums.START.toString() + "\n");
//        /////////////////////////////////
//            for (String s : in) {
//                ConsoleHelper.writeMessage(s);
//            }
//        ////////////////////////////////
        if (in.get(0).equalsIgnoreCase(Enums.NEXT.toString())) {
            arrayList.remove(0);
        }

        for (String s : arrayList) {
            stringBuilder.append(s);
        }

        stringBuilder.append("\n").append(Enums.END.toString()).append("\n\n");

        try {
            WriterAndReadFile.writerFile(stringBuilder.toString(), pathSavedWeights, false);
        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.WEIGHTS_NOT_SAVED));
        }
    }
}
