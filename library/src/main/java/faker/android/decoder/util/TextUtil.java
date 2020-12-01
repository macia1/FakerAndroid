package faker.android.decoder.util;

public class TextUtil {
    public static boolean isEmpty(String text) {
        if (text != null && !"".equals(text)) {
            return false;
        }
        return true;
    }
}
