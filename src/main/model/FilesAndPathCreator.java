package main.model;

import main.Gasket;
import main.view.ConsoleHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;




public class FilesAndPathCreator {

    private String pathHistoryForFortuneteller;
    private String pathSavedWeightsPredictor;
    private String pathSavedWeights;
    private String pathSettings;
    private String pathWeights;
    private String pathHistory;
    private String pathLogs;


    public FilesAndPathCreator() {
        Gasket.setFilesAndPathCreator(this);
        createdPath();
        createdFileLog();
        isTheFileInPlace();
        showPath();
    }



    private void createdPath() {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String[] stringsSplit = path.split("/");
        path = stringsSplit[stringsSplit.length - 1];

        String[] strings = getClass().getResource("").getPath().split(path);
        String finish = strings[0].replaceAll("file:", "");



        if (System.getProperty("os.name").startsWith("Windows")) {
            finish = finish.replaceFirst("/", "").replaceAll("/", "\\\\");
        }

        if (strings.length == 2) {

            if (System.getProperty("os.name").startsWith("Windows")) {

                Path  weights = Paths.get(finish + "Weights");

                if (!Files.exists(weights)) {
                    try {
                        Files.createDirectories(Paths.get("Weights"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path history = Paths.get(finish + "History");

                if (!Files.exists(history)) {
                    try {
                        Files.createDirectories(Paths.get("History"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pathSetting = Paths.get(finish + "Settings");

                if (!Files.exists(pathSetting)) {
                    // действия, если папка существует
                    try {
                        Files.createDirectories(Paths.get("Settings"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pathLog = Paths.get(finish + "Logs");

                if (!Files.exists(pathLog)) {
                    // действия, если папка существует
                    try {
                        Files.createDirectories(Paths.get("Logs"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                pathLogs = finish  + "Logs\\" + DatesTimes.getDateLogs().replaceAll(":", "-")
                        + " Log.txt";
                pathHistoryForFortuneteller = finish + "History\\HistoryForFortuneteller.txt";
                pathSavedWeightsPredictor = finish + "Weights\\SavedWeightsPredictor.txt";
                pathSavedWeights = finish + "Weights\\SavedWeights.txt";
                pathSettings = finish + "Settings\\Settings.txt";
                pathHistory = finish + "History\\History.txt";
                pathWeights = finish + "Weights\\Weights.txt";

            } else {

                Path weights = Paths.get(strings[0] + "Weights");

                if (!Files.exists(weights)) {
                    try {
                        Files.createDirectories(Paths.get("Weights"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path history = Paths.get(strings[0] + "History");

                if (!Files.exists(history)) {
                    try {
                        Files.createDirectories(Paths.get("History"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pathSetting = Paths.get(strings[0] + "Settings");

                if (!Files.exists(pathSetting)) {
                    // действия, если папка существует
                    try {
                        Files.createDirectories(Paths.get("Settings"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pathLog = Paths.get(strings[0] + "Logs");

                if (!Files.exists(pathLog)) {
                    // действия, если папка существует
                    try {
                        Files.createDirectories(Paths.get("Logs"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                pathLogs = finish + "Logs/" + DatesTimes.getDateLogs() + " Log.txt";
                pathHistoryForFortuneteller = finish + "History/HistoryForFortuneteller.txt";
                pathSavedWeightsPredictor = finish + "Weights/SavedWeightsPredictor.txt";
                pathSavedWeights = finish + "Weights/SavedWeights.txt";
                pathHistory = finish + "History/History.txt";
                pathSettings = finish + "Settings/Settings.txt";
                pathWeights = finish + "Weights/Weights.txt";
            }
        } else {
            String string = getClass().getResource("").getPath()
                    .replaceAll("target/classes", "src/main/java")
                    .replaceAll("model/", "");

            pathLogs = string + "Logs/Log/" + DatesTimes.getDateLogs() + "===Log.txt";
            pathHistoryForFortuneteller = string + "Logs/History/HistoryForFortuneteller.txt";
            pathSavedWeightsPredictor = string + "Logs/Weights/SavedWeightsPredictor.txt";
            pathSavedWeights = string + "Logs/Weights/SavedWeights.txt";
            pathWeights = string + "Logs/Weights/Weights.txt";
            pathHistory = string + "Logs/History/History.txt";
            pathSettings = string + "Logs/Settings.txt";
        }

        if (System.getProperty("os.name").startsWith("Windows")) {

            pathHistoryForFortuneteller = pathHistoryForFortuneteller
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathWeights = pathWeights
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathSavedWeights = pathSavedWeights
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathSavedWeightsPredictor = pathSavedWeightsPredictor
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathSettings = pathSettings
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathHistory = pathHistory
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathLogs = pathLogs
                    .replaceFirst("/", "").replaceAll("/", "\\\\");
        }
    }



    private void showPath() {
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathHistoryForFortuneteller);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathSavedWeightsPredictor);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathSavedWeights);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathSettings);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathWeights);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathHistory);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathLogs);
    }



    private void isTheFileInPlace() {

        if (!Files.exists(Paths.get(pathHistoryForFortuneteller))) {
            createdHistoryForFortuneteller();
        }

        if (!Files.exists(Paths.get(pathSavedWeightsPredictor))) {
            createdFileSavedWeightsPredictor();
        }

        if (!Files.exists(Paths.get(pathSavedWeights))) {
            createdFileSavedWeights();
        }

        if (!Files.exists(Paths.get(pathWeights))) {
            createdFileWeights();
        }

        if (!Files.exists(Paths.get(pathSettings))) {
            createdFileSettings();
        }

        if (!Files.exists(Paths.get(pathHistory))) {
            createdFileHistory();
        }
    }



    private void createdHistoryForFortuneteller() {
        File file = new File(pathHistoryForFortuneteller);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл HistoryForFortuneteller успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл HistoryForFortuneteller.");
        }
    }



    private void createdFileWeights() {
        File file = new File(pathWeights);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл Weights успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Weights.");
        }
    }



    private void createdFileSettings() {
        File file = new File(pathSettings);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл Settings успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось Settings файл Настроек.");
        }
    }



    private void createdFileSavedWeights() {
        File file = new File(pathSavedWeights);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для SavedWeights успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл SavedWeights.");
        }
    }


    private void createdFileSavedWeightsPredictor() {
        File file = new File(pathSavedWeightsPredictor);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для SavedWeightsPredictor успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл SavedWeightsPredictor.");
        }
    }


    private void createdFileHistory() {
        File file = new File(pathHistory);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для History успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл History.");
        }
    }

    private void createdFileLog() {
        File file = new File(pathLogs);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый Лог файл успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать Лог файл.");
        }
    }


    public String getPathSettings() {
        return pathSettings;
    }

    public String getPathSavedWeights() {
        return pathSavedWeights;
    }

    public String getPathSavedWeightsPredictor() {
        return pathSavedWeightsPredictor;
    }

    public String getPathHistory() {
        return pathHistory;
    }

    public String getPathLogs() {
        return pathLogs;
    }

    public String getPathWeights() {
        return pathWeights;
    }

    public String getPathHistoryForFortuneteller() {
        return pathHistoryForFortuneteller;
    }
}

