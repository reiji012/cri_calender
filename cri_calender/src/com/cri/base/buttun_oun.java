package com.cri.base;
import javax.swing.JFrame;

public class buttun_oun {
        public static void main(String[] args) {
        		//「Swing Calender」フレーム作成
                JFrame frame = new JFrame("Swing Calender");
//              frame.setSize(400,600);
                //フレームにSwingCalendarBaseを入れる
                frame.add(new SwingCalendarBase());
                //×で閉じる
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //サイズを中のパネルで決める
                frame.pack();
                //フレーム表示
                frame.setVisible(true);
        }
}



