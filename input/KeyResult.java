package input;

public record KeyResult(Integer key, boolean mainMenu, boolean quit) {
    public static KeyResult ofKey(int key) {
        return new KeyResult(key, false, false);
    }

    public static KeyResult ofMainMenu() {
        return new KeyResult(null, true, false);
    }

    public static KeyResult ofQuit() {
        return new KeyResult(null, false, true);
    }
}
