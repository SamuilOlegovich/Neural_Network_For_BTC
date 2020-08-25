package main;


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



    // DATE_TIME;OPEN;HIGH;LOW;CLOSE;VOLUME
    //22.07.2020 0:00:00;9393,5;9398,0;9393,0;9394,0;4808147
    //22.07.2020 0:01:00;9394,0;9394,5;9389,5;9390,0;2206761
    //22.07.2020 0:02:00;9390,0;9392,0;9388,5;9391,5;1017803
    //22.07.2020 0:03:00;9391,5;9392,0;9391,5;9391,5;1142942
    public static synchronized double getDataHistory(Enums e, String in) {
        String[] strings = in.replaceAll(",", ".").split(";");

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
        return 0.0;
    }
}
