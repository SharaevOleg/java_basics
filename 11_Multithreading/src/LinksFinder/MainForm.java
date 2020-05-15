package LinksFinder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.Timer;

public class MainForm {
    private JPanel rootPanel;
    private JTextField linkTextField;
    private JButton startStopButton;
    private JButton pauseContinueButton;
    private JTextPane textPane1;
    private JTextPane timeTextPane;
    private JTextPane textPane2;
    private JTextPane linksTextPane;
    private JTextPane chooserTextPane;
    private JPanel topPanel;
    private JLabel fileNameLabel;
    private JButton chooseButton;
    private Finder finder;

    private File file;
    private Timer timer;

    private static final String startText = "Старт";
    private static final String stopText = "Стоп";
    private static final String pauseText = "Пауза";
    private static final String continueText = "Продолжить";
    private static final String chooseFileText = "Выберите файл";
    private static final String openFileText = "Открыть файл";
    private static final String fileNotChosenText = "Файл не выбран";

    public MainForm() {
        JFileChooser fc = new JFileChooser();
        pauseContinueButton.setEnabled(false);


        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ret = fc.showDialog(null, openFileText);
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fc.getSelectedFile();
                    fileNameLabel.setText(file.getAbsolutePath());
                }
            }
        });

        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileNameLabel.getText().equals(chooseFileText)) {
                    JOptionPane.showMessageDialog(rootPanel, fileNotChosenText);  //выскакивает окошко "файл не выбран"
                    return;
                }

                if (startStopButton.getText().equals(stopText)) {  //если текст на кнопке startStop соответствует "Стоп"
                    stopTimer();                                   //запускаем метод stopTimer
                    pauseContinueButton.setEnabled(false);         //делаем кнопку pauseContinue неактивной
                    pauseContinueButton.setText(pauseText);        //меняем кнопке pauseContinue текст на "Пауза"
                    linksTextPane.setText("00");                   //меняем текстовому фрейму links текст на "00"
                    try {
                        PrintStream out = new PrintStream(file);   //создаем новый обьект класса PrintStream с именем out
                        printUrl(linkTextField.getText(), finder.getLinks(), new HashSet<>(), 0, out);  // запускаем метод printUrl с кучей параметров
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    startStopButton.setText(startText);             // меняем кнопке startStop текст на "Старт"
                } else {
                    finder = new Finder();                          // создаем екземпляр класса Finder
                    pauseContinueButton.setEnabled(true);           // делаем кнопку pauseContinue активной
                    startTimer();                                   // запускаем метод startTimer
                    finder.addTask(linkTextField.getText());        // к экземпляру Finder применяем метод класса Finder addTask с параметрами (строка введенная в linkTextField)
                    startStopButton.setText(stopText);              //меняем кнопке startStop текст на "Стоп"
                }
            }
        });

        pauseContinueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startStopButton.getText().equals(startText)) {     // если текст кнопки startStop равен "Старт"
                    // то ничего не делать
                } else {                                                // если нет - ...
                    if (pauseContinueButton.getText().equals(pauseText)) {
                        finder.setPaused(true);                         // применяем к экземпляру finder метод setPaused с параметром true
                        pauseContinueButton.setText(continueText);      //меняем кнопке pauseContinue текст на "Продолжить"
                        pauseTimer();                                   //запускаем метод pauseTimer
                    } else {
                        finder.setPaused(false);                        // применяем к экземпляру finder метод setPaused с параметром false
                        pauseContinueButton.setText(pauseText);         //меняем кнопке pauseContinue текст на "Пауза"
                        startTimer();                                   //запускаем метод startTimer
                    }
                }
            }
        });
    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JTextPane getLinkTextPane() {
        return linksTextPane;
    }

    public JTextPane getTimeTextPane() {
        return timeTextPane;
    }

    private void startTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                timeTextPane.setText(Integer.parseInt(getTimeTextPane().getText()) + 1 + "");
                linksTextPane.setText(finder.getCount().toString());
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    private void stopTimer() {
        timer.cancel();
        getTimeTextPane().setText("0");
    }

    private void pauseTimer() {
        timer.cancel();
    }

    public void printUrl(String url, Map<String, Set<String>> map, Set<String> printed, int level, PrintStream out) throws Exception {
        //  printUrl(linkTextField.getText(), finder.getLinks(), new HashSet<>(), 0, out);

        if (!map.containsKey(url))
            return;
        out.print(getIndent(level));
        out.println(url);
        if (printed.contains(url))
            return;
        printed.add(url);
        Set<String> children = map.get(url);
        for (String s : children) {
            printUrl(s, map, printed, level + 1, out);
        }

    }

    private String getIndent(int level) {
        String result = "";
        for (int i = 0; i < level; i++) {
            result += " ";
        }
        return result;
    }


}
