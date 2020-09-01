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
                    new DownloadedData();
                    new ReadAndConvert();
                    new TeacherNeuralNetwork();
//            teacherNeuralNetwork.startLearning();
                    new TesterNN();
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
                        new TesterNN(true);
                Gasket.getViewThread().setPreviousColor();
            }
        }.start();
    }


}
