package ro.lab11.tools;

public class Meta {
    /**
     * @return the name of the caller method
     */
    public static String getMethodName() {
        return new Throwable().getStackTrace()[1].getMethodName();
    }
}
