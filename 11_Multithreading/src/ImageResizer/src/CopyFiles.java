package ImageResizer.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CopyFiles extends Thread {
    ScaleExample se = new ScaleExample();

    private File[] files;
    private String dstFolder;
    private JSlider slider;
    private long start;

    public CopyFiles(File[] files, String dstFolder, JSlider slider, long start) {
        this.files = files;
        this.dstFolder = dstFolder;
        this.slider = slider;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }

                File newFile = new File(dstFolder + "/" + file.getName());

                ImageIO.write(se.scale(image, image.getWidth() / slider.getValue(),
                        image.getHeight() / slider.getValue()), "jpg", newFile);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finish -  duration: "
                + (System.currentTimeMillis() - start));
    }
}
