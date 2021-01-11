package systemio;

public final class IOFactory {
    private IOFactory() { }

    /**
     * Factory-method
     * @param file
     * @return
     */
    public static IOSystem createIOSystem(final String file) {
        if (file == null) {
            return null;
        }
        if (file.equals("read")) {
            return new Reader();
        }
        if (file.equals("write")) {
            return new Writer();
        }

        return null;
    }
}
