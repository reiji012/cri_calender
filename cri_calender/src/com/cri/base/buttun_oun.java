package com.cri.base;
import javax.swing.JFrame;

public class buttun_oun {
        public static void main(String[] args) {
                JFrame frame = new JFrame("Swing Calender");
//              frame.setSize(400,600);
                frame.add(new SwingCalendarBase());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
        }
}



