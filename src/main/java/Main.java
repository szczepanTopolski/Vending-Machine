import view.TerminalView;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller(new TerminalView(), new DataAccess());
        controller.startTransaction();
    }
}
