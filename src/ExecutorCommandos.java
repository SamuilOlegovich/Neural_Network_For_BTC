


public class ExecutorCommandos {
    private ReadAndWriteSetting readAndWriteSetting;

    public ExecutorCommandos() {
        Gasket.setExecutorCommandos(this);
    }

    public void setReadAndWriteSetting(ReadAndWriteSetting readAndWriteSetting) {
        this.readAndWriteSetting = readAndWriteSetting;
    }




    public void parseAndExecute(String string) {

        String[] strings = string.trim().split(" === ");

        if (strings.length < 1  || strings.length == 1) {
            if (string.equalsIgnoreCase(Gasket.getSettingNow())) {
                return;
            }
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal()
                    + " --- Вы допустили ошибку, повторите ввод === " + string
                    + "\n"
            );
            return;
        } else {
            strings[1] = strings[1].replaceAll(",", ".");
        }

        try {
            switch (strings[0]) {
                case "whetherOrNotShowLearningProcess" :
                    Gasket.setWhetherOrNotShowLearningProcess(strings[1].equalsIgnoreCase("true"));
                    ConsoleHelper.writeMessage("whetherOrNotShowLearningProcess === "
                            + Gasket.isWhetherOrNotShowLearningProcess()
                    );
                    break;
                case "numberOfTrainingCycles" :
                    Gasket.setNumberOfTrainingCycles(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage("numberOfTrainingCycles === "
                            + Gasket.getNumberOfTrainingCycles()
                    );
                    break;
                case "numberOfOutputNeurons" :
                    Gasket.setNumberOfOutputNeurons(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage("numberOfOutputNeurons === "
                            + Gasket.getNumberOfOutputNeurons()
                    );
                    break;
                case "numberOfInputNeurons" :
                    Gasket.setNumberOfInputNeurons(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage("numberOfInputNeurons === "
                            + Gasket.getNumberOfInputNeurons()
                    );
                    break;
                case "dateDifference" :
                    Gasket.setDateDifference(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage("dateDifference === "
                            + Gasket.getDateDifference()
                    );
                    break;
                case "SETTINGS" :
                    // SETTINGS=RESTART программа перезапустит настройки не отключаясь
                    if (strings[1].equalsIgnoreCase("RESTART")) readAndWriteSetting.writeSettings();
                    break;
                default:
                    ConsoleHelper.writeMessage(DatesTimes.getDateTerminal()
                            + " --- Вы ввели неверную команду, попробуйте еще раз === " + string
                            + "\n"
                    );
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage(DatesTimes.getDateTerminal()
                    + " --- Ошибочка, повторите ввод === " + string
                    + "\n"
            );
        }
        readAndWriteSetting.writeSettings();
    }
}

