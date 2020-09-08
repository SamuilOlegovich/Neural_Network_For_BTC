package main.controller;

import main.*;
import main.model.CreateAndRestoreNeuralNetwork;
import main.model.DownloadedData;
import main.model.ReadAndConvert;
import main.model.TeacherNeuralNetwork;

public class RunTheProgram extends Thread {

    public RunTheProgram() {
        this.start();
    }

    @Override
    public void run() {


        // если нужно обучение, запускаем вначале обучение, иначе запускаем востановление параметров NN
        if (Gasket.isTurnOnTurnOffLearning()) {
            DownloadedData downloadedData = new DownloadedData();
            ReadAndConvert readAndConvert = new ReadAndConvert();
            TeacherNeuralNetwork teacherNeuralNetwork = new TeacherNeuralNetwork();
//            teacherNeuralNetwork.startLearning();
        } else {
            CreateAndRestoreNeuralNetwork createAndRestoreNeuralNetwork = new CreateAndRestoreNeuralNetwork();
        }
    }

    public void startProgram() {

    }

    public void stopPrograms() {

    }

}
