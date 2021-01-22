import database.Database;
import gamemaster.GameMaster;
import systemio.IOFactory;
import systemio.IOSystem;

public final class Main {
    private Main() {
    }

    /**
     * Main method
     *
     * @param args adresele fisierelor de input/output
     * @throws Exception crapa daca nu ii dai fisierele bune
     */
    public static void main(final String[] args) throws Exception {
        Database.resetInstance();

        GameMaster.resetInstance();
        GameMaster gameMaster = GameMaster.getInstance();

        IOSystem reader = IOFactory.createIOSystem("read");
        Database.getInstance().setInputData(reader.doYourMagic(args[0], null));

        GameMaster.setDatabase(Database.getInstance());

        gameMaster.simulate();

        Database.getInstance().prepareOutput();

        IOSystem writer = IOFactory.createIOSystem("write");
        writer.doYourMagic(args[1], Database.getInstance().getOutput());
    }
}
