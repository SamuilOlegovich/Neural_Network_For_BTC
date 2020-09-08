package main.model;


import main.Enums;
import main.model.DatesTimes;

public class StringHelper {

    public static double getDataD(String s) {
        return 0.0;
    }



    public static synchronized String getString(Enums in) {
        return DatesTimes.getDateTerminal() + " --- " + in.toString();
    }



    public static synchronized String getString(String in) {
        return DatesTimes.getDateTerminal() + " --- " + in;
    }



    public static synchronized String getString(Enums key, String in) {
        String out = null;
        if (in.startsWith("{\"")) {
            String[] strings = in.replaceAll("\\{", "")
                    .replaceAll("}", "")
                    .split(",\"");
            for (String s : strings) {
                if (s.replaceAll("\"", "").startsWith(key.toString())) {
                    String[] str = s.split(":");
                    out = str[1];
                }
            }
        }
        return out;
    }




    public static synchronized double getDataHistory(Enums e, String in) {
        String[] strings = in.replaceAll(",", ".").split(";");

        // DATE_TIME;OPEN;HIGH;LOW;CLOSE;VOLUME
        //22.07.2020 0:00:00;9393,5;9398,0;9393,0;9394,0;4808147
        if (strings.length == 6) {
            if (e.equals(Enums.OPEN)) {
                return Double.parseDouble(strings[1]);
            } else if (e.equals(Enums.HIGH)) {
                return Double.parseDouble(strings[2]);
            } else if (e.equals(Enums.LOW)) {
                return Double.parseDouble(strings[3]);
            } else if (e.equals(Enums.CLOSE)) {
                return Double.parseDouble(strings[4]);
            } else if (e.equals(Enums.VOLUME)) {
                return Double.parseDouble(strings[5]);
            }
        } else {
            // <DATE>;<TIME>;<OPEN>;<HIGH>;<LOW>;<CLOSE>;<VOL>
            // 20190731;000000;9543.9600000;9552.3800000;9519.8300000;9530.3900000;29
            if (e.equals(Enums.OPEN)) {
                return Double.parseDouble(strings[2]);
            } else if (e.equals(Enums.HIGH)) {
                return Double.parseDouble(strings[3]);
            } else if (e.equals(Enums.LOW)) {
                return Double.parseDouble(strings[4]);
            } else if (e.equals(Enums.CLOSE)) {
                return Double.parseDouble(strings[5]);
            } else if (e.equals(Enums.VOLUME)) {
                return Double.parseDouble(strings[6]);
            }
        }
        return 0.0;
    }
}
