public class Gasket {
    private static ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting;
    private static TeacherNeuralNetwork teacherNeuralNetwork;
    private static FilesAndPathCreator filesAndPathCreator;
    private static ExecutorCommandos executorCommandos;
    private static DownloadedData downloadedData;
    private static NeuralNetwork neuralNetwork;


    private static boolean whetherOrNotShowLearningProcess = false;     // показывать или нет процесс обучения
    private static double priceChangeToFormHistoryPattern = 30;         // изменение цены для формирования паттерна истории
    private static int numberOfIndicatorsForOneCandle = 13;             // количество показателей по одной свече
    private static int numberOfCandlesToDetectMovement = 5;             // количество свечек для определения движения buy, sell, flat
    private static boolean turnOnTurnOffLearning = true;                // включить выключить процесс обучения
    private static int numberOfTrainingCycles = 10;
    private static int numberOfInputNeurons = 1521;
    private static int numberOfOutputNeurons = 3;
    private static int dateDifference = -3;         // разница в часовом поясе


    private static final String SETTING_NOW = " --- SETTING_NOW --- ";
    private static final String LEARNING_PROCESS_HAS_STARTED_NN = " ----- СТАРТОВАЛ процесс обучения NN";
    private static final String THE_NN_LEARNING_PROCESS_IS_COMPLETED_I_AM_SAVING =
            " ----- ПРОЦЕСС ОБУЧЕНИЯ NN ОКОНЧЕН - СОХРАНЯЮСЬ";








    public static String getSettingNow() {
        return SETTING_NOW;
    }

    public static String getLearningProcessHasStarted() {
        return LEARNING_PROCESS_HAS_STARTED_NN;
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
