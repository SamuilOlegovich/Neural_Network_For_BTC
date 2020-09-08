package main.model;

import main.Enums;

public class TransformerHistory {
    private double volume;
    private double close;
    private double open;
    private double high;
    private double low;

    private double closeEnd;

    private double volumeIn;
    private double closeIn;
    private double openIn;
    private double highIn;
    private double lowIn;




    public void startData(String in) {
        this.volume = StringHelper.getDataHistory(Enums.VOLUME, in);
        this.close = StringHelper.getDataHistory(Enums.CLOSE, in);
        this.open = StringHelper.getDataHistory(Enums.OPEN, in);
        this.high = StringHelper.getDataHistory(Enums.HIGH, in);
        this.low = StringHelper.getDataHistory(Enums.LOW, in);
    }

    public void startData(String inStart, String inEnd) {
        this.volume = StringHelper.getDataHistory(Enums.VOLUME, inStart);
        this.close = StringHelper.getDataHistory(Enums.CLOSE, inStart);
        this.open = StringHelper.getDataHistory(Enums.OPEN, inStart);
        this.high = StringHelper.getDataHistory(Enums.HIGH, inStart);
        this.low = StringHelper.getDataHistory(Enums.LOW, inStart);

        this.closeEnd = StringHelper.getDataHistory(Enums.CLOSE, inEnd);
    }



    public String transformHistory(String in) {
        volumeIn = StringHelper.getDataHistory(Enums.VOLUME, in);
        closeIn = StringHelper.getDataHistory(Enums.CLOSE, in);
        openIn = StringHelper.getDataHistory(Enums.OPEN, in);
        highIn = StringHelper.getDataHistory(Enums.HIGH, in);
        lowIn = StringHelper.getDataHistory(Enums.LOW, in);


        double location = changeTotal(openIn, closeEnd) * getDirection(openIn, closeEnd);

        return new StringBuilder()
                .append(location)
                .toString();
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
//        return (Math.abs(a - b) / (openIn / 100));
        return (Math.abs(a - b) / (closeEnd / 100));
//        return (Math.abs(a - b) / (open / 100));
    }

}
