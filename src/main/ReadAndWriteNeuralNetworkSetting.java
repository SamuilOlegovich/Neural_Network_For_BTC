package main;

import main.view.ConsoleHelper;

import java.util.ArrayList;



public class ReadAndWriteNeuralNetworkSetting {
    private String pathSavedWeights;
    private String pathWeights;

    public ReadAndWriteNeuralNetworkSetting() {
        this.pathSavedWeights = Gasket.getFilesAndPathCreator().getPathSavedWeights();
        this.pathWeights = Gasket.getFilesAndPathCreator().getPathWeights();
        Gasket.setReadAndWriteNeuralNetworkSetting(this);
        readFileWeights();
    }




    // считываем настройки NN
    protected void readFileWeights() {
        try {
            ArrayList<String> listHistory =  WriterAndReadFile.readFile(pathSavedWeights);

            if (listHistory.size() < 1) {
                ConsoleHelper.writeMessage(StringHelper.getString(Enums.SETTINGS_IN_THE_HISTORY_FILE_NOT_DETECTED));
            }

            ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_SUCCESSFULLY_READ));

            listHistory.remove(0);

            /////////////////////////////////
//            for (int i = listHistory.size() - 1; i >= 1000; i--) {
//                listHistory.remove(i);
//            }
            /////////////////////////////////

//            findPatterns(listHistory);
            listHistory.clear();
        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.ERROR_WHEN_READING_THE_HISTORY_FILE));
        }
    }


    // сохраняем найденые веса после обучения NN
    protected void saveAllNeuralNetworkData(ArrayList<String> in) {
        ArrayList<String> arrayList = new ArrayList<>(in);
        StringBuilder stringBuilder = new StringBuilder(Enums.START.toString() + "\n");

        if (in.get(0).equalsIgnoreCase(Enums.NEXT.toString())) {
            arrayList.remove(0);
        }

        for (String s : arrayList) {
            stringBuilder.append(s);
        }

        stringBuilder.append("\n" + Enums.END.toString()).append("\n\n");

        try {
            WriterAndReadFile.writerFile(stringBuilder.toString(), pathSavedWeights, false);
        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.WEIGHTS_NOT_SAVED));
        }
    }
}
