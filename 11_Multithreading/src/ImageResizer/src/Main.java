package ImageResizer.src;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            MainForm mainForm = new MainForm();

            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
            frame.setSize(600, 200);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setContentPane(mainForm.getRootPanel());
        });

    }
}
