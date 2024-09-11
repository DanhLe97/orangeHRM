package utils.helpers;

public class DataFaker {

    public static String generateRandomNumber(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (int) (Math.random() * 100) % 10;
        }
        return result;
    }

    public static String generateRandomString(int length) {
        String charSet1 = "abcdefghijklmnopqrstuvwxyz";
        String charSet2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String charSet3 = "0123456789";
        String charSetAll = charSet1 + charSet2 + charSet3;
        String result = "";

        if(length >= 3) {
            result += charSet1.charAt((int) ((Math.random() * 100) % 26));
            result += charSet2.charAt((int) ((Math.random() * 100) % 26));
            result += charSet3.charAt((int) ((Math.random() * 100) % 10));

            for (int i = 3; i < length; i++) {
                result += charSetAll.charAt((int) ((Math.random() * 100) % 36));
            }
        }else{
            for (int i = 0; i < length; i++) {
                result += charSetAll.charAt((int) ((Math.random() * 100) % 36));
            }
        }

        return result;
    }
}
