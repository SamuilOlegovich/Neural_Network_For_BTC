package main;

import main.view.ConsoleHelper;

import java.util.ArrayList;

public class ReadAndWriteSetting {
    private ExecutorCommandos executorCommandos;
    private String path;



    public ReadAndWriteSetting(ExecutorCommandos executorCommandos) {
        this.path = Gasket.getFilesAndPathCreator().getPathSettings();
        this.executorCommandos = executorCommandos;
        readFileSettings();
    }



    private void readFileSettings() {
        executorCommandos.setReadAndWriteSetting(this);

        try {
            ArrayList<String> listSettings =  WriterAndReadFile.readFile(path);

            if (listSettings.size() < 1) {
                try {
                    ConsoleHelper.writeMessage("Настроек в файле Settings.txt необнаружено " +
                            "- включены и вписаны настройки по умолчанию.");
                    WriterAndReadFile.writerFile(getStringWrite(), path, false);
                } catch (Exception ex) {
                    ConsoleHelper.writeMessage("Ошибка в ЗАПИСИ файла Settings.txt .");
                }
            }


            for (String string : listSettings) {
                if (string.equalsIgnoreCase(Enums.END.toString())) {
                    ConsoleHelper.writeMessage(StringHelper.getString(Enums.SETTINGS_SUCCESSFULLY_READ));
                    return;
                }

                String[] strings;

                if (string.length() > 4
                        && !string.equalsIgnoreCase(Gasket.getSettingNow())) {
                    strings = string.split(" ----- ");
                    executorCommandos.parseAndExecute(strings[0]);
                }
            }

        } catch (Exception e) {
            ConsoleHelper.writeMessage("Ошибка в ЧТЕНИИ файла Settings.txt");
        }
    }



    public void writeSettings() {
        try {
            WriterAndReadFile.writerFile(getStringWrite(), path, false);
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Настройки не перезаписались после команды изменения.");
        }
    }



    private String getStringWrite() {
        return ConsoleHelper.getStringInfoSettings()
                + "\n"
                + Enums.END.toString()
                + "\n";
    }

}
