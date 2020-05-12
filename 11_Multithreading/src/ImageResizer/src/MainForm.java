package ImageResizer.src;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Objects;

public class MainForm {
    private JPanel rootPanel;
    private JButton copyFromButton;
    private JButton copyToButton;
    private JLabel text1;
    private JLabel text2;
    private JSlider slider;
    private JLabel value;
    private JButton sTARTButton;

    private static final String fromFileText = "Copy from", toFileText = "Copy to";
    private File fromFile, toFile;
    private int processors = Runtime.getRuntime().availableProcessors();
    private String folderNotSelected = "folder is not selected";


    public MainForm() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        copyFromButton.addActionListener(e -> {
            int ret = fc.showDialog(null, fromFileText);

            if (ret == JFileChooser.APPROVE_OPTION) {
                fromFile = fc.getSelectedFile();
                text1.setText(fromFile.getName());
            }
        });

        copyToButton.addActionListener(e -> {
            int ret = fc.showDialog(null, toFileText);

            if (ret == JFileChooser.APPROVE_OPTION) {
                toFile = fc.getSelectedFile();
                text2.setText(toFile.getName());
            }
        });

        slider.addChangeListener(e -> value.setText("Value : " + ((JSlider) e.getSource()).getValue()));

        sTARTButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    if (fromFile.getPath().length() > 0 && toFile.getPath().length() > 0) {

                        long start = System.currentTimeMillis();

                        File[] files = fromFile.listFiles();
                        int step = Objects.requireNonNull(files).length / processors;

                        int var = 0;
                        File[] temp = new File[step];

                        for (File file : files) {
                            temp[var++] = file;
                            if (var == step) {
                                new CopyFiles(temp, toFile.getAbsolutePath(), slider, start).start();
                                temp = new File[step];
                                var = 0;
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(rootPanel, folderNotSelected);
                    return;
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
