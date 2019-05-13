package DatabaseHandler.SQLBuilder;

/**
 * @author F0urth
 * Enum contains basic SQL Syntax
 */
public
    enum SQLKeyword
        implements CommandGetter {

    INSERT {
        private String command = null;

        {
            this.command = "INSERT INTO ";
        }

        @Override
        public String getCommand() {
            return command;
        }
    }, UPDATE {
        private String command = null;

        {
            this.command = "UPDATE ";
        }

        @Override
        public String getCommand() {
            return command;
        }
    }, DELETE {
        private String command = null;

        {
            this.command = "DELETE FROM ";
        }

        @Override
        public String getCommand() {
            return command;
        }
    }, SET {
        private String command = null;

        {
            this.command = command = " SET ";
        }

        @Override
        public String getCommand() {
            return command;
        }
    }, WHERE {
        private String command = null;

        {
            this.command = command = " WHERE ";
        }

        @Override
        public String getCommand() {
            return command;
        }
    }, VALUES {
        private String command = null;

        {
            this.command = " VALUES ";
        }

        @Override
        public String getCommand() {
            return command;
        }
    }
}
