package ro.lab11.tools;

public class Meta {
    /**
     * @return the name of the caller method
     */
    public static String getMethodName() {
        return getMethodName(2);
    }

    /**
     * <a href="https://www.techiedelight.com/get-name-current-method-being-executed-java/">Source</a>
     */
    public static String getMethodName(int level) {
        return new Throwable().getStackTrace()[level].getMethodName();
    }
}
