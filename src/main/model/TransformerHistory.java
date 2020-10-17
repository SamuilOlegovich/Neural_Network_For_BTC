package main.model;

import main.Enums;

public class TransformerHistory {
    private double volume;
    private double close;
    private double open;
    private double high;
    private double low;

    private double volumeIn;
    private double closeIn;
    private double openIn;
    private double highIn;
    private double lowIn;

    private int numberCandle;
    private int directionCandle;




    public synchronized void startData(String in) {
        volume = StringHelper.getDataHistory(Enums.VOLUME, in);
        close = StringHelper.getDataHistory(Enums.CLOSE, in);
        open = StringHelper.getDataHistory(Enums.OPEN, in);
        high = StringHelper.getDataHistory(Enums.HIGH, in);
        low = StringHelper.getDataHistory(Enums.LOW, in);
        directionCandle = 0;
        numberCandle = 0;
    }



    public String transformHistory(String in) {
        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
        lowIn = StringHelper.getDataHistory(Enums.LOW, in);


        // тело свечи
        double candleBody = 1 / (changeTotal(openIn, closeIn) * getDirection(openIn, close));
        // от хай до лов (вся свеча)
        double fromHighToLow = 1 / (changeTotal(highIn, lowIn));
        // опен хай
        double openHigh = 1 / (changeTotal(highIn, openIn));
        // опен лов
        double openLow = 1 / (changeTotal(openIn, lowIn));

        // тело свечи разница
        double candleBodyPerDif = 1 / (changeTotal(open, close, openIn, closeIn)
                * changeOfDirection(open, close, openIn, closeIn));
        // разница от хай до лов
        double wholeCandlePerDif = 1 / (changeTotal(high, low, highIn, lowIn)
                * changeOfDirection(high, low, highIn, lowIn));
        // разница опен хай
        double openHighPerDif = 1 / (changeTotal(high, open, highIn, openIn)
                * changeOfDirection(high, open, highIn, openIn));
        // разница опен лов
        double openLowPerDif = 1 / (changeTotal(open, low, openIn, lowIn)
                * changeOfDirection(open, low, openIn, lowIn));
        // разница объема всей свечи
        double volumePerDif = 1 / (((1.0 / 100) * (Math.abs(volume - volumeIn) / (volume / 100.0)))
                * getDirection(volume, volumeIn));

        this.volume = volumeIn;
        this.close = closeIn;
        this.open = openIn;
        this.high = highIn;
        this.low = lowIn;

        return new StringBuilder()
                .append(candleBody).append(";")
                .append(fromHighToLow).append(";")
                .append(openHigh).append(";")
                .append(openLow)
                .append(";")
//                .append(candleBodyPerDif).append(";")
//                .append(wholeCandlePerDif).append(";")
//                .append(openHighPerDif).append(";")
//                .append(openLowPerDif).append(";")
//                .append(volumePerDif)
                .toString();
    }



    private double calculateCenter(double a, double b) {
        return (a + b) / 2.0;
    }

    private double calculateDeviationFromCenter(double a, double b) {
        double center = calculateCenter(a, b);
        double onePercent = center / 100.0;

        if (a > center) {
            return (a - center) / onePercent;
        } else if (a < center) {
            return ((center - a) / 100.0) * -1;
        }
        return 0.0;
    }

    private double getDirection(double a, double b) {
        if (a > b) return 0.01;
        if (a < b) return 0.99;
        return 0.5;
    }

    private double changeOfDirection(double a, double b, double c, double d) {
        if (Math.abs(a - b) < Math.abs(c - d)) return 0.99;
        if (Math.abs(a - b) > Math.abs(c - d)) return 0.01;
        return 0.5;
    }

    private double changeTotal(double a, double b,double c,double d) {
        double before = Math.abs(a - b);
        double now = Math.abs(c - d);
        return (1.0 / 100) * (Math.abs(before - now) / (open / 100));
    }

    private double changeTotal(double a, double b) {
        return (Math.abs(a - b) / (openIn / 100));
    }

}
