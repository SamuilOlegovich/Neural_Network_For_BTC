import java.util.ArrayList;

public class ReadAndWriteNeuralNetworkSetting {
    private String path;

    public ReadAndWriteNeuralNetworkSetting() {
        this.path = Gasket.getFilesAndPathCreator().getPathWeights();
        Gasket.setReadAndWriteNeuralNetworkSetting(this);
        readFileWeights();
    }

    private void readFileWeights() {

    }

    protected void saveAllNeuralNetworkData(ArrayList<String> in) {

    }
}
