package GCM;

public class IDGenerator {
    private static int currentId = 1;

    public static int generateId() {
        return currentId++;
    }
}
