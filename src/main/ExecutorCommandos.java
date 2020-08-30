package main;


import main.model.DatesTimes;
import main.view.ConsoleHelper;

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
                    ConsoleHelper.writeMessage(StringHelper.getString("whetherOrNotShowLearningProcess === "
                            + Gasket.isWhetherOrNotShowLearningProcess())
                    );
                    break;
                case "numberOfTrainingCycles" :
                    Gasket.setNumberOfTrainingCycles(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("numberOfTrainingCycles === "
                            + Gasket.getNumberOfTrainingCycles())
                    );
                    break;
                case "numberOfOutputNeurons" :
                    Gasket.setNumberOfOutputNeurons(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("numberOfOutputNeurons === "
                            + Gasket.getNumberOfOutputNeurons())
                    );
                    break;
                case "numberOfInputNeurons" :
                    Gasket.setNumberOfInputNeurons(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("numberOfInputNeurons === "
                            + Gasket.getNumberOfInputNeurons())
                    );
                    break;
                case "dateDifference" :
                    Gasket.setDateDifference(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("dateDifference === "
                            + Gasket.getDateDifference())
                    );
                    break;
                case "priceChangeToFormHistoryPattern" :
                    Gasket.setPriceChangeToFormHistoryPattern(Double.parseDouble(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("priceChangeToFormHistoryPattern === "
                            + Gasket.getPriceChangeToFormHistoryPattern())
                    );
                    break;
                case "learningRate" :
                    Gasket.setLearningRate(Double.parseDouble(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("learningRate === "
                            + Gasket.getLearningRate())
                    );
                    break;
                case "minDigitWeightForBuy" :
                    Gasket.setMinDigitWeightForBuy(Double.parseDouble(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("minDigitWeightForBuy === "
                            + Gasket.getMinDigitWeightForBuy())
                    );
                    break;
                case "minDigitWeightForSell" :
                    Gasket.setMinDigitWeightForSell(Double.parseDouble(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("minDigitWeightForSell === "
                            + Gasket.getMinDigitWeightForSell())
                    );
                    break;
                case "turnOnTurnOffLearning" :
                    Gasket.setTurnOnTurnOffLearning(strings[1].equalsIgnoreCase(Enums.TRUE.toString()));
                    ConsoleHelper.writeMessage(StringHelper.getString("turnOnTurnOffLearning === "
                            + Gasket.isTurnOnTurnOffLearning())
                    );
                    break;
                case "numberOfCandlesToDetectMovement" :
                    Gasket.setNumberOfCandlesToDetectMovement(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("numberOfCandlesToDetectMovement === "
                            + Gasket.getNumberOfCandlesToDetectMovement())
                    );
                    break;
                case "numberOfIndicatorsForOneCandle" :
                    Gasket.setNumberOfIndicatorsForOneCandle(Integer.parseInt(strings[1]));
                    ConsoleHelper.writeMessage(StringHelper.getString("numberOfIndicatorsForOneCandle === "
                            + Gasket.getNumberOfIndicatorsForOneCandle())
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

