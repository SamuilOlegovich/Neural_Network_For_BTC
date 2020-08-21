import java.util.function.UnaryOperator;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;





public class Main {

    public static void main(String[] args) throws IOException {
        // Запускаем все что связано с путями, файлами, чнением и настройками.
        FilesAndPathCreator filesAndPathCreator = new FilesAndPathCreator();
        Gasket.setFilesAndPathCreator(filesAndPathCreator);
        ExecutorCommandos executorCommandos = new ExecutorCommandos();
        ReadAndWriteSetting readAndWriteSetting = new ReadAndWriteSetting(executorCommandos);
        ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting = new ReadAndWriteNeuralNetworkSetting();






        DownloadedData downloadedData = new DownloadedData();
        Gasket.setDownloadedData(downloadedData);
        TeacherNeuralNetwork teacherNeuralNetwork = new TeacherNeuralNetwork();
        teacherNeuralNetwork.startLearning();
        Gasket.setTeacherNeuralNetwork(teacherNeuralNetwork);
    }

}
