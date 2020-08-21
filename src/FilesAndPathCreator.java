import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.File;




public class FilesAndPathCreator {
    private String pathLevelsForTrimmedPatternsIIPro;
    private String pathLevelsForTrimmedPatternsII;
    private String pathPureHistoryOfPatternsIn;
    private String pathPatternsTemporaryIIPro;
    private String pathPatternsTemporaryUser;
    private String pathPatternsForUserIIPro;
    private String pathPatternsDeleteIIPro;
    private String pathPatternsDeleteUser;
    private String pathPatternsTemporary;
    private String pathPatternsForUser;
    private String pathPatternsDelete;
    private String pathPatternsIIPro;
    private String pathPatternsUser;

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
                Path pureHistoryOfPatternsIn = Paths.get(finish + "PureHistoryOfPatternsIn");

                if (!Files.exists(pureHistoryOfPatternsIn)) {
                    try {
                        Files.createDirectories(Paths.get("PureHistoryOfPatternsIn"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path  weights = Paths.get(finish + "Weights");

                if (!Files.exists(weights)) {
                    try {
                        Files.createDirectories(Paths.get("Weights"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pathIIProPatterns = Paths.get(finish + "iiProPatterns");

                if (!Files.exists(pathIIProPatterns)) {
                    try {
                        Files.createDirectories(Paths.get("iiProPatterns"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pathIIPatterns = Paths.get(finish + "iiPatterns");

                if (!Files.exists(pathIIPatterns)) {
                    try {
                        Files.createDirectories(Paths.get("iiPatterns"));
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
                pathPureHistoryOfPatternsIn = finish + "PureHistoryOfPatternsIn\\PureHistoryOfPatternsIn.txt";
                pathPatternsTemporaryIIPro = finish + "iiProPatterns\\iiProTemporaryPatterns.txt";
                pathLevelsForTrimmedPatternsIIPro = finish + "uPatterns\\iiProLevelsTrimmed.txt";
                pathPatternsDeleteIIPro = finish + "iiProPatterns\\iiProTemporaryDelete.txt";
                pathLevelsForTrimmedPatternsII = finish + "uPatterns\\iiLevelsTrimmed.txt";
                pathPatternsForUserIIPro = finish + "uPatterns\\iiProPatternsForUser.txt";
                pathPatternsTemporaryUser = finish + "uPatterns\\uTemporaryPatterns.txt";
                pathPatternsTemporary = finish + "iiPatterns\\iiTemporaryPatterns.txt";
                pathPatternsDeleteUser = finish + "uPatterns\\uTemporaryDelete.txt";
                pathPatternsDelete = finish + "iiPatterns\\iiTemporaryDelete.txt";
                pathPatternsForUser = finish + "uPatterns\\iiPatternsForUser.txt";
                pathPatternsIIPro = finish + "iiProPatterns\\iiProPatterns.txt";
                pathPatternsUser = finish + "iiProPatterns\\iiProPatterns.txt";
                pathPatternsUser = finish + "uPatterns\\uPatterns.txt";

                pathSettings = finish + "Settings\\Settings.txt";
                pathHistory = finish + "History\\History.txt";
                pathWeights = finish + "Weights\\Weights.txt";

            } else {
                Path strategiesSettingsMartingale = Paths.get(strings[0] + "StrategiesSettingsMartingale");

                if (!Files.exists(strategiesSettingsMartingale)) {
                    try {
                        Files.createDirectories(Paths.get("StrategiesSettingsMartingale"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pureHistoryOfPatternsIn = Paths.get(strings[0] + "PureHistoryOfPatternsIn");

                if (!Files.exists(pureHistoryOfPatternsIn)) {
                    try {
                        Files.createDirectories(Paths.get("PureHistoryOfPatternsIn"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path weights = Paths.get(strings[0] + "Weights");

                if (!Files.exists(weights)) {
                    try {
                        Files.createDirectories(Paths.get("Weights"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pathIIProPatterns = Paths.get(strings[0] + "iiProPatterns");

                if (!Files.exists(pathIIProPatterns)) {
                    try {
                        Files.createDirectories(Paths.get("iiProPatterns"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                Path pathIIPatterns = Paths.get(strings[0] + "iiPatterns");

                if (!Files.exists(pathIIPatterns)) {
                    try {
                        Files.createDirectories(Paths.get("iiPatterns"));
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

                pathPureHistoryOfPatternsIn = finish + "PureHistoryOfPatternsIn/PureHistoryOfPatternsIn.txt";
                pathPatternsTemporaryIIPro = finish + "iiProPatterns/iiProTemporaryPatterns.txt";
                pathLevelsForTrimmedPatternsIIPro = finish + "uPatterns/iiProLevelsTrimmed.txt";
                pathPatternsDeleteIIPro = finish + "iiProPatterns/iiProTemporaryDelete.txt";
                pathLevelsForTrimmedPatternsII = finish + "uPatterns/iiLevelsTrimmed.txt";
                pathPatternsTemporaryUser = finish + "uPatterns/uTemporaryPatterns.txt";
                pathPatternsTemporary = finish + "iiPatterns/iiTemporaryPatterns.txt";
                pathPatternsForUserIIPro = finish + "uPatterns/iiProPatternsFor.txt";
                pathLogs = finish + "Logs/" + DatesTimes.getDateLogs() + " Log.txt";
                pathPatternsDeleteUser = finish + "uPatterns/uTemporaryDelete.txt";
                pathPatternsDelete = finish + "iiPatterns/iiTemporaryDelete.txt";
                pathPatternsIIPro = finish + "iiProPatterns/iiProPatterns.txt";
                pathPatternsForUser = finish + "uPatterns/iiPatternsFor.txt";
                pathPatternsUser = finish + "uPatterns/uPatterns.txt";

                pathHistory = finish + "History/History.txt";
                pathSettings = finish + "Settings/Settings.txt";
                pathWeights = finish + "Weights/Weights.txt";
            }
        } else {
            String string = getClass().getResource("").getPath()
                    .replaceAll("target/classes", "src/main/java")
                    .replaceAll("model/", "");

            pathPureHistoryOfPatternsIn = string + "Logs/PureHistoryOfPatternsIn/PureHistoryOfPatternsIn.txt";
            pathLevelsForTrimmedPatternsIIPro = string + "Logs/PatternsUser/iiProLevelsTrimmed.txt";
            pathPatternsTemporaryIIPro = string + "Logs/PatternsUser/iiProTemporaryPatterns.txt";
            pathLevelsForTrimmedPatternsII = string + "Logs/PatternsUser/iiLevelsTrimmed.txt";
            pathPatternsForUserIIPro = string + "Logs/PatternsUser/iiProPatternsForUser.txt";
            pathPatternsDeleteIIPro = string + "Logs/PatternsUser/iiProTemporaryDelete.txt";
            pathPatternsTemporaryUser = string + "Logs/PatternsUser/uTemporaryPatterns.txt";
            pathPatternsDeleteUser = string + "Logs/PatternsUser/uTemporaryDelete.txt";
            pathPatternsForUser = string + "Logs/PatternsUser/iiPatternsForUser.txt";
            pathPatternsTemporary = string + "Logs/Patterns/TemporaryPatterns.txt";
            pathPatternsIIPro = string + "Logs/PatternsUser/iiProPatterns.txt";
            pathPatternsDelete = string + "Logs/Patterns/TemporaryDelete.txt";
            pathPatternsUser = string + "Logs/PatternsUser/uPatterns.txt";


            pathLogs = string + "Logs/Log/" + DatesTimes.getDateLogs() + "===Log.txt";
            pathWeights = string + "Logs/Weights/Weights.txt";
            pathHistory = string + "Logs/History/History.txt";
            pathSettings = string + "Logs/Settings.txt";
        }

        if (System.getProperty("os.name").startsWith("Windows")) {
            pathLevelsForTrimmedPatternsIIPro = pathLevelsForTrimmedPatternsIIPro
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathLevelsForTrimmedPatternsII = pathLevelsForTrimmedPatternsII
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPureHistoryOfPatternsIn = pathPureHistoryOfPatternsIn
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathWeights = pathWeights
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsTemporaryIIPro = pathPatternsTemporaryIIPro
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsTemporaryUser = pathPatternsTemporaryUser
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsForUserIIPro = pathPatternsForUserIIPro
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsDeleteIIPro = pathPatternsDeleteIIPro
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsDeleteUser = pathPatternsDeleteUser
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsTemporary = pathPatternsTemporary
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsForUser = pathPatternsForUser
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsDelete = pathPatternsDelete
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsIIPro = pathPatternsIIPro
                    .replaceFirst("/", "").replaceAll("/", "\\\\");

            pathPatternsUser = pathPatternsUser
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
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathLevelsForTrimmedPatternsIIPro);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathLevelsForTrimmedPatternsII);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPureHistoryOfPatternsIn);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsTemporaryIIPro);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsTemporaryUser);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsForUserIIPro);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsDeleteIIPro);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsDeleteUser);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsTemporary);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsForUser);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsDelete);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsIIPro);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathPatternsUser);

        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathWeights);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathSettings);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathHistory);
        ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- " + pathLogs);
    }



    private void isTheFileInPlace() {
        if (!Files.exists(Paths.get(pathLevelsForTrimmedPatternsIIPro))) {
            createdPathLevelsForTrimmedPatternsIIPro();
        }

        if (!Files.exists(Paths.get(pathLevelsForTrimmedPatternsII))) {
            createdPathLevelsForTrimmedPatternsII();
        }

        if (!Files.exists(Paths.get(pathPureHistoryOfPatternsIn))) {
            createdFilePureHistoryOfPatternsIn();
        }

        if (!Files.exists(Paths.get(pathWeights))) {
            createdFileWeights();
        }

        if (!Files.exists(Paths.get(pathPatternsTemporaryUser))) {
            createdFileTemporaryPatternsUser();
        }

        if (!Files.exists(Paths.get(pathPatternsTemporaryIIPro))) {
            createdFileTemporaryPatternsIIPro();
        }

        if (!Files.exists(Paths.get(pathPatternsForUserIIPro))) {
            createdFilePatternsForUserIIPro();
        }

        if (!Files.exists(Paths.get(pathPatternsTemporary))) {
            createdFileTemporaryPatterns();
        }

        if (!Files.exists(Paths.get(pathPatternsDeleteUser))) {
            createdFileDeletePatternsUser();
        }

        if (!Files.exists(Paths.get(pathPatternsDeleteIIPro))) {
            createdFileDeletePatternsIIPro();
        }

        if (!Files.exists(Paths.get(pathPatternsForUser))) {
            createdFilePatternsForUser();
        }

        if (!Files.exists(Paths.get(pathPatternsDelete))) {
            createdFileDeletePatterns();
        }

        if (!Files.exists(Paths.get(pathPatternsUser))) {
            createdFilePatternsUser();
        }

        if (!Files.exists(Paths.get(pathPatternsIIPro))) {
            createdFilePatternsIIPro();
        }

        if (!Files.exists(Paths.get(pathSettings))) {
            createdFileSettings();
        }

        if (!Files.exists(Paths.get(pathHistory))) {
            createdFileHistory();
        }
    }



    private void createdPathLevelsForTrimmedPatternsIIPro() {
        File file = new File(pathLevelsForTrimmedPatternsIIPro);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл PathLevelsForTrimmedPatternsIIPro успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл PathLevelsForTrimmedPatternsIIPro.");
        }
    }


    private void createdPathLevelsForTrimmedPatternsII() {
        File file = new File(pathLevelsForTrimmedPatternsII);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл PathLevelsForTrimmedPatternsII успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл PathLevelsForTrimmedPatternsII.");
        }
    }



    private void createdFilePureHistoryOfPatternsIn() {
        File file = new File(pathPureHistoryOfPatternsIn);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл PureHistoryOfPatternsIn успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл PureHistoryOfPatternsIn.");
        }
    }



    private void createdFileWeights() {
        File file = new File(pathWeights);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл Весов успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Весов.");
        }
    }



    private void createdFileSettings() {
        File file = new File(pathSettings);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл Настроек успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Настроек.");
        }
    }



    private void createdFilePatternsUser() {
        File file = new File(pathPatternsUser);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для User Паттернов успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл User Петтернов.");
        }
    }



    private void createdFilePatternsForUser() {
        File file = new File(pathPatternsForUser);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для FOR User Паттернов успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл FOR User Петтернов.");
        }
    }



    private void createdFilePatternsForUserIIPro() {
        File file = new File(pathPatternsForUserIIPro);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для FOR User Паттернов II Pro успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл FOR User II Pro Петтернов.");
        }
    }



    private void createdFileHistory() {
        File file = new File(pathHistory);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для Истории успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Истории.");
        }
    }



    private void createdFilePatternsIIPro() {
        File file = new File(pathPatternsIIPro);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для Паттернов II Pro успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Петтернов II Pro.");
        }
    }



    private void createdFileDeletePatternsUser() {
        File file = new File(pathPatternsDeleteUser);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для Удаленных User Паттернов успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл User Удаленных Петтернов.");
        }
    }



    private void createdFileDeletePatterns() {
        File file = new File(pathPatternsDelete);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для Удаленных Паттернов успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Удаленных Петтернов.");
        }
    }



    private void createdFileDeletePatternsIIPro() {
        File file = new File(pathPatternsDeleteIIPro);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для Удаленных Паттернов II Pro успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Удаленных Петтернов II Pro.");
        }
    }



    private void createdFileTemporaryPatternsUser() {
        File file = new File(pathPatternsTemporaryUser);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для User Временных Паттернов успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл User Временных Петтернов.");
        }
    }



    private void createdFileTemporaryPatterns() {
        File file = new File(pathPatternsTemporary);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для Временных Паттернов успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Временных Петтернов.");
        }
    }



    private void createdFileTemporaryPatternsIIPro() {
        File file = new File(pathPatternsTemporaryIIPro);
        try {
            boolean newFile = file.createNewFile();
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Новый файл для Временных Паттернов II Pro успешно создан.");
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal() + " --- "
                    + "Не удалось создать файл Временных Петтернов II Pro.");
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



    public String getPathPatternsTemporaryUser() {
        return pathPatternsTemporaryUser;
    }

    public String getPathPatternsTemporary() {
        return pathPatternsTemporary;
    }

    public String getPathPatternsForUser() {
        return pathPatternsForUser;
    }

    public String getPathPatternsDeleteUser() {
        return pathPatternsDeleteUser;
    }

    public String getPathPatternsDelete() {
        return pathPatternsDelete;
    }

    public String getPathSettings() {
        return pathSettings;
    }

    public String getPathPatternsUser() {
        return pathPatternsUser;
    }

    public String getPathHistory() {
        return pathHistory;
    }

    public String getPathLogs() {
        return pathLogs;
    }

    public String getPathPatternsTemporaryIIPro() {
        return pathPatternsTemporaryIIPro;
    }

    public String getPathPatternsForUserIIPro() {
        return pathPatternsForUserIIPro;
    }

    public String getPathPatternsDeleteIIPro() {
        return pathPatternsDeleteIIPro;
    }

    public String getPathPatternsIIPro() {
        return pathPatternsIIPro;
    }

    public String getPathPureHistoryOfPatternsIn() {
        return pathPureHistoryOfPatternsIn;
    }

    public String getPathWeights() {
        return pathWeights;
    }

    public String getPathLevelsForTrimmedPatternsIIPro() {
        return pathLevelsForTrimmedPatternsIIPro;
    }

    public String getPathLevelsForTrimmedPatternsII() {
        return pathLevelsForTrimmedPatternsII;
    }
}

