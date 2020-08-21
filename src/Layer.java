public class Layer {
    public double[][] weights;  // веса
    public double[] neurons;    // нейроны
    public double[] biases;     // смещение
    public int size;

    public Layer(int size, int nextSize) {
        weights = new double[size][nextSize];
        neurons = new double[size];
        biases = new double[size];
        this.size = size;
    }

    public Layer() { }
}
