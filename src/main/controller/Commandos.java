package main.controller;

import main.Gasket;
import main.model.*;


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
                    new TesterNN(); // псевдо тест, используем для получения материалов и обучения предсказателя
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
//                        new CreateAndRestoreNeuralNetwork();
                        new CreateAndRestoreNeuralNetwork(true); // востанавливаем и обычную сеть и сеть предсказателя
                Gasket.getViewThread().setPreviousColor();
            }
        }.start();
    }


    public void testNN() {
        new Thread() {
            @Override
            public void run() {
//                TesterNN testerNN =
                        new TesterNN(true); // тестируем уже с предсказателем
                Gasket.getViewThread().setPreviousColor();
            }
        }.start();
    }

}
