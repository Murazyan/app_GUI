package com.Download_Video_Youtube.view;




import com.Download_Video_Youtube.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class App {
    JFrame frame;
   static JLabel  message;
   static JTextField textField;
   static String userName;

    public App() {
        frame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        frame.setBounds(screenSize.width / 2 - 500, screenSize.height / 2 - 300, 800, 800);
        final JTextField textField = new JTextField();
        JLabel jLabel = new JLabel("Մուտքագրել վիդեոյի հղումը");
        jLabel.setBounds(screenSize.width / 2 - 800, screenSize.height / 2 - 500, 400, 40);
        textField.setBounds(screenSize.width / 2 - 600, screenSize.height / 2 - 500, 400, 40);
        textField.setBackground(new Color(202, 219, 193));
      final   String userName = System.getProperty("user.name");
        message = new JLabel();
        String OS_name = System.getProperty("os.name");
        if (OS_name.contains("Windows")) {
            message.setText("Ֆայլը պահպանված է համակարգչում։ Հղում՝ C:/Download_Video_Youtube/video/");
        }else{

            message.setText("Ֆայլը պահպանված է համակարգչում։ Հղում՝ "+"/home/"+userName+"/Download_Video_Youtube/youtube");
        }
        message.setVisible(false);
        message.setBounds(screenSize.width / 2 - 800, screenSize.height / 2 - 500, 600, 200);
        frame.add(message);
        JButton jButton = new JButton();
        ClassLoader classLoader = App.class.getClassLoader();
        URL buttonIconpath = classLoader.getResource("static/image/download.png");
        jButton.setIcon(new ImageIcon(buttonIconpath));
        jButton.setText("Ներբեռնել  ");
        jButton.setBackground(new Color(180, 219, 201));
        jButton.setBounds(screenSize.width / 2 - 800, screenSize.height / 2 - 300, 200, 200);
        this.textField = textField;
        this.userName = userName;
        jButton.addActionListener(new ButtonActionListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(jLabel);
        frame.add(textField);
        frame.add(jButton);
        URL framIconPath = classLoader.getResource("static/image/icon.png");
        frame.setIconImage(new ImageIcon(framIconPath).getImage());
        frame.setTitle("Download video from YOUTUBE");
        frame.setLayout(null);
        frame.setVisible(true);

    }
    static class ButtonActionListener extends AbstractAction{

        public ButtonActionListener(){
            putValue(AbstractAction.SHORT_DESCRIPTION, "Ներբեռնել");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            message.setVisible(false);
            String videoUrl = textField.getText();
            String dirname = "";
            String OS_name = System.getProperty("os.name");
            if (OS_name.contains("Windows")) {
                dirname = "C:\\Download_Video_Youtube\\video";
                File file = new File(dirname);
                if (!file.exists()) {
                    file.mkdirs();
                }
            } else {
                dirname ="/home/"+userName+"/Download_Video_Youtube/video";

                File file = new File(dirname);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            String userName = System.getProperty("user.name");
            Util.saveFileFromUrlWithDL(dirname, videoUrl,OS_name,userName);
            message.setVisible(true);
        }
    }
}
