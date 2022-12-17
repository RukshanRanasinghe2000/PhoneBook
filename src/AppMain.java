import javax.swing.*;

public class AppMain {
    public AppMain() {
    }

    public static void main(String[] args) {
        PhoneBookUI ui = new PhoneBookUI();
        ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ui.setVisible(true);
        ui.setResizable(false);
    }
}
