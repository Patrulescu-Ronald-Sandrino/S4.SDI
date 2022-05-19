package ro.lab11;

public class ClientApp {
    public static void main(String[] args) {
        techiedelight();
    }

    public static void techiedelight() {
        String methodName = new Throwable()
                .getStackTrace()[0]
                .getMethodName();

        System.out.println("Current Method is " + methodName);
        System.out.println("Parent Method is " + new Throwable().getStackTrace()[1].getMethodName());
    }
}
