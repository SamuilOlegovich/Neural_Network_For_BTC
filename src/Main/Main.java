package Main;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        // Запускаем все что связано с путями, файлами, чнением и настройками.
        FilesAndPathCreator filesAndPathCreator = new FilesAndPathCreator();
        ExecutorCommandos executorCommandos = new ExecutorCommandos();
        ReadAndWriteSetting readAndWriteSetting = new ReadAndWriteSetting(executorCommandos);
        ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting = new ReadAndWriteNeuralNetworkSetting();

        // если нужно обучение, запускаем вначале обучение, иначе запускаем востановление параметров NN
        if (Gasket.isTurnOnTurnOffLearning()) {
            DownloadedData downloadedData = new DownloadedData();
            ReadAndConvert readAndConvert = new ReadAndConvert();
            TeacherNeuralNetwork teacherNeuralNetwork = new TeacherNeuralNetwork();
//            teacherNeuralNetwork.startLearning();
        } else {
            CreateAndRestoreNeuralNetwork createAndRestoreNeuralNetwork = new CreateAndRestoreNeuralNetwork();
        }

        // запускаем визуальную часть
//        Main.FormDigits f = new Main.FormDigits(Main.Main.Gasket.getNeuralNetwork());
//        new Thread(f).start();
    }

}
