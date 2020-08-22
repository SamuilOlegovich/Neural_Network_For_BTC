public class CreateAndRestoreNeuralNetwork {
    private ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting;
    private NeuralNetwork neuralNetwork;

    private int[] numberOfNeuronsInLayer;   // количество нейронов в слое
    private double[][] weights;  // веса
    private double[] neurons;    // нейроны
    private double[] biases;     // смещение
    private Layer[] layers;
    private int size;

    public CreateAndRestoreNeuralNetwork() {
        this.readAndWriteNeuralNetworkSetting = Gasket.getReadAndWriteNeuralNetworkSetting();
        restoreNN();
    }


    private void restoreNN() {

    }


}
