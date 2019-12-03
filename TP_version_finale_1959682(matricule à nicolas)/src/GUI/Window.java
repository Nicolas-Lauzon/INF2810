package GUI;


import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {

    private static void Window(int width, int height, String title){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }
    public static void main(String[] args){
        Window(500, 500, "TEST");
    }
}
