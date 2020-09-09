package main.controller;


import main.Gasket;
import main.model.ReadAndWriteNeuralNetworkSetting;
import main.model.ReadAndWriteSetting;
import main.model.FilesAndPathCreator;
import main.view.View;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        View view = new View();
        Gasket.setViewThread(view);
        Gasket.setCommandos(new Commandos());
        view.start();

        while (true) {
            if (view.isStartFlag()) {
                break;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Запускаем все что связано с путями, файлами, чнением и настройками.
        FilesAndPathCreator filesAndPathCreator = new FilesAndPathCreator();
        ExecutorCommandos executorCommandos = new ExecutorCommandos();
        ReadAndWriteSetting readAndWriteSetting = new ReadAndWriteSetting(executorCommandos);
        ReadAndWriteNeuralNetworkSetting readAndWriteNeuralNetworkSetting = new ReadAndWriteNeuralNetworkSetting();

        // запускаем визуальную часть
    }

}
