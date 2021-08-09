package ui;

public class Main {
    public static void main(String[] args) {
//        databaseConsole();
        new PatientDatabaseGUI();
    }

    private static void databaseConsole() {
        PatientDatabase p;
        p = new PatientDatabase();
        p.systemApp();
    }
}
