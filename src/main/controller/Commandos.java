package main.controller;

import main.*;
import main.model.DownloadedData;


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
                    Gasket.getViewThread().setColorEducateNN();
            }
        }.start();
    }


    public void restore() {
        new Thread() {
            @Override
            public void run() {
                if (!Gasket.isTurnOnTurnOffLearning()) {
                    CreateAndRestoreNeuralNetwork createAndRestoreNeuralNetwork = new CreateAndRestoreNeuralNetwork();
                }
                Gasket.getViewThread().setColorEducateNN();
            }
        }.start();
    }
}
