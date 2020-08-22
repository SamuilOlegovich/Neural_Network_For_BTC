import java.util.ArrayList;

public class ReadAndConvert {
    private DownloadedData downloadedData;
    private String pathHistory;

    public ReadAndConvert() {
        this.pathHistory = Gasket.getFilesAndPathCreator().getPathHistory();
        this.downloadedData = Gasket.getDownloadedData();
        readFileHistory();
    }




    private void readFileHistory() {
        try {
            ArrayList<String> listHistory =  WriterAndReadFile.readFile(pathHistory);

            if (listHistory.size() < 1) {
                ConsoleHelper.writeMessage(StringHelper.getString(Enums.SETTINGS_IN_THE_HISTORY_FILE_NOT_DETECTED));
            }
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.HISTORY_SUCCESSFULLY_READ));

            listHistory.remove(0);
            convertHistory(listHistory);
            listHistory.clear();

        } catch (Exception e) {
            ConsoleHelper.writeMessage(StringHelper.getString(Enums.ERROR_WHEN_READING_THE_HISTORY_FILE));
        }
    }



    private void convertHistory(ArrayList<String> in) {
        ConsoleHelper.writeMessage(StringHelper.getString(Enums.STARTING_CONVERTING_HISTORY));
        ArrayList<String> outList = new ArrayList<>();
            ConsoleHelper.writeMessage(in.get(0));
            ConsoleHelper.writeMessage(in.get(1));
            ConsoleHelper.writeMessage(in.get(2));
            ConsoleHelper.writeMessage(in.get(3));
            ConsoleHelper.writeMessage(in.get(4));

    }

    private double getData(Enums e, String in) {
        return 0.0;
    }

    // DATE_TIME;OPEN;HIGH;LOW;CLOSE;VOLUME
    //22.07.2020 0:00:00;9393,5;9398,0;9393,0;9394,0;4808147
    //22.07.2020 0:01:00;9394,0;9394,5;9389,5;9390,0;2206761
    //22.07.2020 0:02:00;9390,0;9392,0;9388,5;9391,5;1017803
    //22.07.2020 0:03:00;9391,5;9392,0;9391,5;9391,5;1142942
}
