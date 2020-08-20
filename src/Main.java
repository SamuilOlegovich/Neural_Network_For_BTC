import java.util.function.UnaryOperator;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;





public class Main {

    public static void main(String[] args) throws IOException {
        digits();
    }

    private static void digits() throws IOException {
        UnaryOperator<Double> sigmoid = x -> 1 / (1 + Math.exp(-x));
        UnaryOperator<Double> dsigmoid = y -> y * (1 - y);
        // скорость обучения - сигмоиды - количество нейронов в последующем слое
        NeuralNetwork nn = new NeuralNetwork(0.001, sigmoid, dsigmoid, 784, 512, 128, 32, 10);

        int samples = 60000;                                    // количество входящих картинок(файлов для обучения)
        BufferedImage[] images = new BufferedImage[samples];    // буффер для картинок
        int[] digits = new int[samples];                        // буффер для цифр изображенных на этих картинках

        File[] imagesFiles = new File("/Users/samuilolegovich/Documents/JAVA/NET/train").listFiles();


        // заполняем буффера данными
        for (int i = 0; i < samples; i++) {
            images[i] = ImageIO.read(imagesFiles[i]);
            digits[i] = Integer.parseInt(imagesFiles[i].getName().charAt(10) + "");
        }

        // двумерный массив для пикселей картинок
        double[][] inputs = new double[samples][784];

        //
        for (int i = 0; i < samples; i++) {
            for (int x = 0; x < 28; x++) {
                for (int y = 0; y < 28; y++) {
                    inputs[i][x + y * 28] = (images[i].getRGB(x, y) & 0xff) / 255.0;
                }
            }
        }

        // 1000 проходов по 100 обучений - чем больше проходов тем качественней обучение, но и дольше
        int epochs = 3000;

        for (int i = 1; i < epochs; i++) {
            int right = 0;
            double errorSum = 0;
            int batchSize = 100;

            for (int j = 0; j < batchSize; j++) {
                int imgIndex = (int)(Math.random() * samples);
                double[] targets = new double[10];
                int digit = digits[imgIndex];
                targets[digit] = 1;

                double[] outputs = nn.feedForward(inputs[imgIndex]);
                int maxDigit = 0;
                double maxDigitWeight = -1;

                for (int k = 0; k < 10; k++) {
                    if(outputs[k] > maxDigitWeight) {
                        maxDigitWeight = outputs[k];
                        maxDigit = k;
                    }
                }

                if(digit == maxDigit) right++;

                for (int k = 0; k < 10; k++) {
                    errorSum += (targets[k] - outputs[k]) * (targets[k] - outputs[k]);
                }
                nn.backpropagation(targets);
            }
            System.out.println("epoch: " + i + ". correct: " + right + ". error: " + errorSum);
        }
        System.out.println("Хух! НАУЧИЛСЯ!");

        FormDigits f = new FormDigits(nn);
        new Thread(f).start();
    }
}
