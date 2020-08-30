package main.controller;

import main.*;
import main.model.DownloadedData;
import main.model.TesterNN;
import main.view.ConsoleHelper;


public class Commandos {

    public void educate() {
        new Thread() {
            @Override
            public void run() {
                if (Gasket.isTurnOnTurnOffLearning()) {
                    DownloadedData downloadedData = new DownloadedData();
                    ReadAndConvert readAndConvert = new ReadAndConvert();
                    TeacherNeuralNetwork teacherNeuralNetwork = new TeacherNeuralNetwork();
//            teacherNeuralNetwork.startLearning();
                }
                    Gasket.getViewThread().setPreviousColor();
            }
        }.start();
    }


    public void restore() {
        new Thread() {
            @Override
            public void run() {
//                CreateAndRestoreNeuralNetwork createAndRestoreNeuralNetwork =
                        new CreateAndRestoreNeuralNetwork();
                Gasket.getViewThread().setPreviousColor();
            }
        }.start();
    }


    public void testNN() {
        new Thread() {
            @Override
            public void run() {
//                TesterNN testerNN =
                        new TesterNN();
                Gasket.getViewThread().setPreviousColor();
            }
        }.start();
    }


}
