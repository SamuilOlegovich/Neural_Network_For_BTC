import java.io.BufferedReader;
import java.io.InputStreamReader;




public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void writeMessage(String string) {
//        bitmex.Bot.model.Gasket.getViewThread().updateInfoView(string);

//        WriterAndReadFile.writerFile(string + "\n"
//                , Gasket.getFilesAndPathCreator().getPathLogs()
//                , true);
        System.out.println(string);
    }



    public static String getStringInfoSettings() {
        return  Gasket.getSettingNow()
                + "\n"
                + "\n"
                + "whetherOrNotShowLearningProcess === " + Gasket.isWhetherOrNotShowLearningProcess()
                + " ----- выводить или нет выводить на экран процесс обучения НН\n"
                + "numberOfTrainingCycles === " + Gasket.getNumberOfTrainingCycles()
                + " ----- количество циклов обучения НН\n"
                + "numberOfOutputNeurons === " + Gasket.getNumberOfOutputNeurons()
                + " ----- количество выходных нейронов НН\n"
                + "numberOfInputNeurons === " + Gasket.getNumberOfInputNeurons()
                + " ----- количество входных нейронов (стоит соблюдать кратность параметру - всегда квадрат - количество ячеек в екм кратно 13)\n"
                + "\n"
                + "\n"
                + "dateDifference === " + Gasket.getTeacherNeuralNetwork()
                + " ----- на сколько время в терминале отстает или опережает местное\n"
                + "\n";
    }



    public static void printInfoSettings() {
        ConsoleHelper.writeMessage("\n\n"
                + getStringInfoSettings()

                + "\n"

                + "\nЕСЛИ ВЫ ЖЕЛАЕТЕ - ЭТИ НАСТРОЙКИ МОЖНО ИЗМЕНИТЬ\n"
                + "ВВЕДИТЕ ЖЕЛАЕМЫЙ ПАРАМЕТР И ЗНАЧЕНИЕ В ФОРМАТЕ\n"
                + "команда=значение ----> PORT=777\n"
                + "\n"
        );
    }



    public static void printStatisticsMartingale() {
        ConsoleHelper.writeMessage("\n"
        );
    }


    public static void showCommands() {
        ConsoleHelper.writeMessage("\n\n"
                + "whetherOrNotShowLearningProcess"
                + "numberOfTrainingCycles"
                + "numberOfOutputNeurons"
                + "dateDifference"
                + "\n"
        );
    }
}
