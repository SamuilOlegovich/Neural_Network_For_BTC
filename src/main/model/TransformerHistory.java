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




    public void startData(String in) {
        volume = StringHelper.getDataHistory(Enums.VOLUME, in);
        close = StringHelper.getDataHistory(Enums.CLOSE, in);
        open = StringHelper.getDataHistory(Enums.OPEN, in);
        high = StringHelper.getDataHistory(Enums.HIGH, in);
        low = StringHelper.getDataHistory(Enums.LOW, in);
        directionCandle = 0;
        numberCandle = 0;
    }



//    public String transformHistory(String in) {
//        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
//        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
//        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
//        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
//        lowIn = StringHelper.getDataHistory(Enums.LOW, in);
//
//        int direction = 0;
//        if (openIn > closeIn) direction = -1;
//        else if (openIn < closeIn) direction = 1;
//        else  direction = 0;
//
//        if (directionCandle == direction) numberCandle++;
//        else numberCandle = 1;
//
//        directionCandle = direction;
//        int result = 0;
//
//        if (direction != 0) result = numberCandle * direction;
//        else result = numberCandle;
//
//        return new StringBuilder().append(result / 100.0).toString();
//    }



//    public String transformHistory(String in) {
//        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
//        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
//        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
//        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
//        lowIn = StringHelper.getDataHistory(Enums.LOW, in);
//
//        double direction = 0.0;
//        if (openIn > closeIn) direction = 0.0;
//        else if (openIn < closeIn) direction = 1.0;
//        else  direction = 0.5;
//
//        return new StringBuilder().append(direction).toString();
//    }



//    public String transformHistory(String in) {
//        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
//        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
//        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
//        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
//        lowIn = StringHelper.getDataHistory(Enums.LOW, in);
//
//
//        double candleOpen = calculateDeviationFromCenter(openIn, closeIn);
//        double candleClose = calculateDeviationFromCenter(closeIn, openIn);
//        double candleHigh = calculateDeviationFromCenter(highIn, lowIn);
//        double candleLow = calculateDeviationFromCenter(lowIn, highIn);
//
//
//        return new StringBuilder()
//                .append(candleOpen).append(";")
//                .append(candleClose).append(";")
//                .append(candleHigh).append(";")
//                .append(candleLow)
//                .toString();
//    }




//    public String transformHistory(String in) {
//        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
//        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
//        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
//        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
//        lowIn = StringHelper.getDataHistory(Enums.LOW, in);
//
//
//        // тело свечи
//        double candleBody = changeTotal(openIn, closeIn) * getDirection(openIn, closeIn);
////        double candleBody = changeTotal(open, openIn) * getDirection(open, openIn);
//        // от хай до лов (вся свеча)
//        double fromHighToLow = changeTotal(highIn, lowIn) * getDirection(openIn, closeIn);
//        // опен хай
//        double openHigh = changeTotal(highIn, openIn) * getDirection(openIn, closeIn);
//        // опен лов
//        double openLow = changeTotal(openIn, lowIn) * getDirection(openIn, closeIn);
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
//                .append(openLow)
////                .append(";")
////                .append(candleBodyPerDif).append(";")
////                .append(wholeCandlePerDif).append(";")
////                .append(openHighPerDif).append(";")
////                .append(openLowPerDif)
////                .append(";")
////                .append(volumePerDif)
//                .toString();
//    }




    public String transformHistory(String in) {
        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
        lowIn = StringHelper.getDataHistory(Enums.LOW, in);


        // тело свечи
        double candleBody = changeTotal(openIn, closeIn) * getDirection(openIn, close);
        // от хай до лов (вся свеча)
        double fromHighToLow = changeTotal(highIn, lowIn);
        // опен хай
        double openHigh = changeTotal(highIn, openIn);
        // опен лов
        double openLow = changeTotal(openIn, lowIn);

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
                .append(openLow).append(";")
                .append(candleBodyPerDif).append(";")
                .append(wholeCandlePerDif).append(";")
                .append(openHighPerDif).append(";")
                .append(openLowPerDif).append(";")
                .append(volumePerDif).toString();
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
//            return (center - a) / 100.0;
        }
        return 0.0;
    }

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
