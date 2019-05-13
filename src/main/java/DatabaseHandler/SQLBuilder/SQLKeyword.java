package DatabaseHandler.SQLBuilder;


public
    enum SQLKeyword
        implements CommandGetter {

    INSERT {
        private String command = null;

        @Override
        public String getCommand() {
            if (command == null) return (command = "INSERT INTO ");
            else return command;
        }
    }, UPDATE {
        private String command = null;

        @Override
        public String getCommand() {
            if (command == null) return (command = "UPDATE ");
            else return command;
        }
    }, DELETE {
        private String command = null;

        @Override
        public String getCommand() {
            if (command == null) return (command = "DELETE FROM ");
            else return command;
        }
    }, SET {
        private String command = null;

        @Override
        public String getCommand() {
            if (command == null) return (command = " SET ");
            else return command;
        }
    }, WHERE {
        private String command = null;

        @Override
        public String getCommand() {
            if (command == null) return (command = " WHERE ");
            else return command;
        }
    }, VALUES {
        private String command = null;

        @Override
        public String getCommand() {
            if (command == null) return (command = " VALUES ");
            else return command;
        }
    }
}
