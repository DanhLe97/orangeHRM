package utils.helpers;

import java.awt.*;

public class Common {
    public static Color fromString(String colorStr) {
        colorStr = colorStr.replace("rgba(", "").replace("rgb(", "").replace(")", "");
        String[] values = colorStr.split(",");
        int r = Integer.parseInt(values[0].trim());
        int g = Integer.parseInt(values[1].trim());
        int b = Integer.parseInt(values[2].trim());
        int a = (values.length == 4) ? (int) (Float.parseFloat(values[3].trim()) * 255) : 255; // Xử lý giá trị alpha
        return new Color(r, g, b, a);
    }

    public static boolean isBackgroundColorExpected(String actualColor, String expectedColor){
        Color colorActual = fromString(actualColor);
        Color colorExpected = fromString(expectedColor);
        return colorActual.equals(colorExpected);

    }
}
