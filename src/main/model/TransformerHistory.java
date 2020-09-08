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




    public void startData(String in) {
        volume = StringHelper.getDataHistory(Enums.VOLUME, in);
        close = StringHelper.getDataHistory(Enums.CLOSE, in);
        open = StringHelper.getDataHistory(Enums.OPEN, in);
        high = StringHelper.getDataHistory(Enums.HIGH, in);
        low = StringHelper.getDataHistory(Enums.LOW, in);
    }



    public String transformHistory(String in) {
        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
        lowIn = StringHelper.getDataHistory(Enums.LOW, in);


        // тело свечи
        double candleBody = changeTotal(openIn, closeIn) * getDirection(openIn, closeIn);
//        double candleBody = changeTotal(open, openIn) * getDirection(open, openIn);
        // от хай до лов (вся свеча)
        double fromHighToLow = changeTotal(highIn, lowIn) * getDirection(openIn, closeIn);
        // опен хай
        double openHigh = changeTotal(highIn, openIn) * getDirection(openIn, closeIn);
        // опен лов
        double openLow = changeTotal(openIn, lowIn) * getDirection(openIn, closeIn);

        // тело свечи разница
        double candleBodyPerDif = changeTotal(open, close, openIn, closeIn)
                * changeOfDirection(open, close, openIn, closeIn);
        // разница от хай до лов
        double wholeCandlePerDif = changeTotal(high, low, highIn, lowIn)
                * changeOfDirection(high, low, highIn, lowIn);
        // разница опен хай
        double openHighPerDif = changeTotal(high, open, highIn, openIn)
                * changeOfDirection(high, open, highIn, openIn);
        // разница опен лов
        double openLowPerDif = changeTotal(open, low, openIn, lowIn)
                * changeOfDirection(open, low, openIn, lowIn);
        // разница объема всей свечи
        double volumePerDif = ((1.0 / 100) * (Math.abs(volume - volumeIn) / (volume / 100.0)))
                * getDirection(volume, volumeIn);

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
//                .append(";")
//                .append(candleBodyPerDif).append(";")
//                .append(wholeCandlePerDif).append(";")
//                .append(openHighPerDif).append(";")
//                .append(openLowPerDif)
//                .append(";")
//                .append(volumePerDif)
                .toString();
    }




//    public String transformHistory(String in) {
//        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
//        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
//        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
//        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
//        lowIn = StringHelper.getDataHistory(Enums.LOW, in);
//
//
//        // тело свечи
//        double candleBody = changeTotal(openIn, closeIn) * getDirection(openIn, close);
//        // от хай до лов (вся свеча)
//        double fromHighToLow = changeTotal(highIn, lowIn);
//        // опен хай
//        double openHigh = changeTotal(highIn, openIn);
//        // опен лов
//        double openLow = changeTotal(openIn, lowIn);
//
//        // тело свечи разница
//        double candleBodyPerDif = changeTotal(open, close, openIn, closeIn)
//                * changeOfDirection(open, close, openIn, closeIn);
//        // разница от хай до лов
//        double wholeCandlePerDif = changeTotal(high, low, highIn, lowIn)
//                * changeOfDirection(high, low, highIn, lowIn);
//        // разница опен хай
//        double openHighPerDif = changeTotal(high, open, highIn, openIn)
//                * changeOfDirection(high, open, highIn, openIn);
//        // разница опен лов
//        double openLowPerDif = changeTotal(open, low, openIn, lowIn)
//                * changeOfDirection(open, low, openIn, lowIn);
//        // разница объема всей свечи
//        double volumePerDif = ((1.0 / 100) * (Math.abs(volume - volumeIn) / (volume / 100.0)))
//                * getDirection(volume, volumeIn);
//
//        this.volume = volumeIn;
//        this.close = closeIn;
//        this.open = openIn;
//        this.high = highIn;
//        this.low = lowIn;
//
//        return new StringBuilder()
//                .append(candleBody).append(";")
//                .append(fromHighToLow).append(";")
//                .append(openHigh).append(";")
//                .append(openLow).append(";")
//                .append(candleBodyPerDif).append(";")
//                .append(wholeCandlePerDif).append(";")
//                .append(openHighPerDif).append(";")
//                .append(openLowPerDif).append(";")
//                .append(volumePerDif).toString();
//    }



    private double getDirection(double a, double b) {
        if (a > b) return -1.0;
        if (a < b) return 1.0;
        return 0.0;
    }

    private double changeOfDirection(double a, double b, double c, double d) {
        if (Math.abs(a - b) < Math.abs(c - d)) return 1;
        if (Math.abs(a - b) > Math.abs(c - d)) return -1;
        return 0.0;
    }

    private double changeTotal(double a, double b,double c,double d) {
        double before = Math.abs(a - b);
        double now = Math.abs(c - d);
        return (1.0 / 100) * (Math.abs(before - now) / (open / 100));
    }

    private double changeTotal(double a, double b) {
//        return (1.0 / 100) * (Math.abs(a - b) / (openIn / 100));
        return (Math.abs(a - b) / (openIn / 100));
//        return (Math.abs(a - b) / (open / 100));
    }

}
