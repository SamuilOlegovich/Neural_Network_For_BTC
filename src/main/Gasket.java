package main;

import main.controller.Commandos;
import main.controller.RunTheProgram;
import main.model.DownloadedData;
import main.model.FilesAndPathCreator;
import main.model.TesterNN;
import main.view.View;

public class Gasket {
    private static ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting;
    private static CreateAndRestoreNeuralNetwork createAndRestoreNeuralNetwork;
    private static TeacherNeuralNetwork teacherNeuralNetwork;
    private static FilesAndPathCreator filesAndPathCreator;
    private static ExecutorCommandos executorCommandos;
    private static NeuralNetwork neuralNetworkTow;
    private static DownloadedData downloadedData;
    private static NeuralNetwork neuralNetwork;
    private static RunTheProgram runTheProgram;
    private static Commandos commandos;
    private static TesterNN testerNN;
    private static View view;


    private static boolean whetherOrNotShowLearningProcess = false;     // показывать или нет процесс обучения
    private static double priceChangeToFormHistoryPattern = 30;         // изменение цены для формирования паттерна истории
    private static int numberOfIndicatorsForOneCandle = 13;             // количество показателей по одной свече
    private static int numberOfCandlesToDetectMovement = 5;             // количество свечек для определения движения buy, sell, flat
    private static boolean turnOnTurnOffLearning = true;                // включить выключить процесс обучения
    private static boolean predictNextCandle = true;
    private static int numberOfTrainingCycles = 10;
    private static int numberOfInputNeurons = 1300;
    private static int numberOfOutputNeurons = 3;
    private static double learningRate = 0.001;
    private static int dateDifference = -3;                             // разница в часовом поясе


    private static double minDigitWeightForSell = 0.35;
    private static double minDigitWeightForBuy = 0.5;


    private static final String SETTING_NOW = " --- SETTING_NOW --- ";
    private static final String LEARNING_PROCESS_HAS_STARTED_NN = " ----- СТАРТОВАЛ процесс обучения NN";








    public static String getSettingNow() {
        return SETTING_NOW;
    }

    public static String getLearningProcessHasStarted() {
        return LEARNING_PROCESS_HAS_STARTED_NN;
    }


    public static NeuralNetwork getNeuralNetworkTow() {
        return neuralNetworkTow;
    }

    public static void setNeuralNetworkTow(NeuralNetwork neuralNetworkTow) {
        Gasket.neuralNetworkTow = neuralNetworkTow;
    }

    public static boolean isPredictNextCandle() {
        return predictNextCandle;
    }

    public static void setPredictNextCandle(boolean predictNextCandle) {
        Gasket.predictNextCandle = predictNextCandle;
    }

    public static double getMinDigitWeightForSell() {
        return minDigitWeightForSell;
    }

    public static void setMinDigitWeightForSell(double minDigitWeightForSell) {
        Gasket.minDigitWeightForSell = minDigitWeightForSell;
    }

    public static double getMinDigitWeightForBuy() {
        return minDigitWeightForBuy;
    }

    public static void setMinDigitWeightForBuy(double minDigitWeightForBuy) {
        Gasket.minDigitWeightForBuy = minDigitWeightForBuy;
    }

    public static double getLearningRate() {
        return learningRate;
    }

    public static void setLearningRate(double learningRate) {
        Gasket.learningRate = learningRate;
    }

    public static CreateAndRestoreNeuralNetwork getCreateAndRestoreNeuralNetwork() {
        return createAndRestoreNeuralNetwork;
    }

    public static void setCreateAndRestoreNeuralNetwork(CreateAndRestoreNeuralNetwork createAndRestoreNeuralNetwork) {
        Gasket.createAndRestoreNeuralNetwork = createAndRestoreNeuralNetwork;
    }

    public static TesterNN getTesterNN() {
        return testerNN;
    }

    public static void setTesterNN(TesterNN testerNN) {
        Gasket.testerNN = testerNN;
    }

    public static Commandos getCommandos() {
        return commandos;
    }

    public static void setCommandos(Commandos commandos) {
        Gasket.commandos = commandos;
    }

    public static View getViewThread() {
        return view;
    }

    public static void setViewThread(View view) {
        Gasket.view = view;
    }

    public static RunTheProgram getRunTheProgram() {
        return runTheProgram;
    }

    public static void setRunTheProgram(RunTheProgram runTheProgram) {
        Gasket.runTheProgram = runTheProgram;
    }

    public static int getNumberOfCandlesToDetectMovement() {
        return numberOfCandlesToDetectMovement;
    }

    public static void setNumberOfCandlesToDetectMovement(int numberOfCandlesToDetectMovement) {
        Gasket.numberOfCandlesToDetectMovement = numberOfCandlesToDetectMovement;
    }

    public static double getPriceChangeToFormHistoryPattern() {
        return priceChangeToFormHistoryPattern;
    }

    public static void setPriceChangeToFormHistoryPattern(double priceChangeToFormHistoryPattern) {
        Gasket.priceChangeToFormHistoryPattern = priceChangeToFormHistoryPattern;
    }

    public static int getNumberOfIndicatorsForOneCandle() {
        return numberOfIndicatorsForOneCandle;
    }

    public static void setNumberOfIndicatorsForOneCandle(int numberOfIndicatorsForOneCandle) {
        Gasket.numberOfIndicatorsForOneCandle = numberOfIndicatorsForOneCandle;
    }

    public static boolean isTurnOnTurnOffLearning() {
        return turnOnTurnOffLearning;
    }

    public static void setTurnOnTurnOffLearning(boolean turnOnTurnOffLearning) {
        Gasket.turnOnTurnOffLearning = turnOnTurnOffLearning;
    }

    public static ReadAndWriteNeuralNetworkSetting getReadAndWriteNeuralNetworkSetting() {
        return readAndWriteNeuralNetworkSetting;
    }

    public static void setReadAndWriteNeuralNetworkSetting(ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting) {
        Gasket.readAndWriteNeuralNetworkSetting = readAndWriteNeuralNetworkSetting;
    }

    public static ExecutorCommandos getExecutorCommandos() {
        return executorCommandos;
    }

    public static void setExecutorCommandos(ExecutorCommandos executorCommandos) {
        Gasket.executorCommandos = executorCommandos;
    }

    public static int getNumberOfInputNeurons() {
        return numberOfInputNeurons;
    }

    public static void setNumberOfInputNeurons(int numberOfInputNeurons) {
        Gasket.numberOfInputNeurons = numberOfInputNeurons;
    }

    public static int getNumberOfOutputNeurons() {
        return numberOfOutputNeurons;
    }

    public static void setNumberOfOutputNeurons(int numberOfOutputNeurons) {
        Gasket.numberOfOutputNeurons = numberOfOutputNeurons;
    }

    public static FilesAndPathCreator getFilesAndPathCreator() {
        return filesAndPathCreator;
    }

    public static void setFilesAndPathCreator(FilesAndPathCreator filesAndPathCreator) {
        Gasket.filesAndPathCreator = filesAndPathCreator;
    }

    public static int getDateDifference() {
        return dateDifference;
    }

    public static void setDateDifference(int dateDifference) {
        Gasket.dateDifference = dateDifference;
    }

    public static boolean isWhetherOrNotShowLearningProcess() {
        return whetherOrNotShowLearningProcess;
    }

    public static void setWhetherOrNotShowLearningProcess(boolean whetherOrNotShowLearningProcess) {
        Gasket.whetherOrNotShowLearningProcess = whetherOrNotShowLearningProcess;
    }

    public static TeacherNeuralNetwork getTeacherNeuralNetwork() {
        return teacherNeuralNetwork;
    }

    public static void setTeacherNeuralNetwork(TeacherNeuralNetwork teacherNeuralNetwork) {
        Gasket.teacherNeuralNetwork = teacherNeuralNetwork;
    }

    public static DownloadedData getDownloadedData() {
        return downloadedData;
    }

    public static void setDownloadedData(DownloadedData downloadedData) {
        Gasket.downloadedData = downloadedData;
    }

    public static NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public static void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        Gasket.neuralNetwork = neuralNetwork;
    }

    public static int getNumberOfTrainingCycles() {
        return numberOfTrainingCycles;
    }

    public static void setNumberOfTrainingCycles(int numberOfTrainingCycles) {
        Gasket.numberOfTrainingCycles = numberOfTrainingCycles;
    }
}
