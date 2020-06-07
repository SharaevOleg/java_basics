package LinksFinder;

import javax.swing.*;

public class Loader {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            MainForm mainForm = new MainForm();

            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setSize(800, 130);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            frame.setContentPane(mainForm.getRootPanel());
        });
    }
}
