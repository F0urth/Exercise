package DatabaseHandler;

import ReadData.Read;

/**
 * @author F0urth
 */


public
    enum Controler {
    INSTANCE;

    private Database database;
    private Read reader;

    {
        this.database = Database.getInstance();
    }

}
