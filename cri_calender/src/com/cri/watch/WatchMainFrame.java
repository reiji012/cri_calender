package com.cri.watch;

import java.awt.BorderLayout;
import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WatchMainFrame extends JFrame {

	public static void main (String args[]) {
		//Dateクラスをインポートして現在時刻を取得する
		Date d = new Date();

		//SimpleDateFormatクラスをインポートして日付表示を指定
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy/MM/dd");

		//指定した表示に「d」を当てはめる
		String c1 = d1.format(d);

		//SimpleDateFormatクラスをインポートして時刻表示を指定
		SimpleDateFormat d2 = new SimpleDateFormat("hh:mm");

		//指定した表示に「d2」を当てはめる
		String c2 = d2.format(d);

		//JFrameのインスタンスを生成
        JFrame mainFrame = new JFrame("サンプル");

        //閉じるボタンをクリックした時のアプリの振る舞いを決定
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ウィンドウの初期サイズをピクセル単位で指定
        mainFrame.setSize(320, 160);

        //ウィンドウの表示場所の指定
        mainFrame.setLocationRelativeTo(null);

        //ウィンドウを表示
        mainFrame.setVisible(true);

        //JFrameよりContentPaneを取得
        Container contentPane = mainFrame.getContentPane();

        //ラベルのインスタンスを生成
        JLabel label = new JLabel(c1 + "\n" + c2);

       //ラベルをContentPaneに配置
       contentPane.add(label, BorderLayout.CENTER);

       //ラベルを左右中央に配置
       label.setHorizontalAlignment(SwingConstants.CENTER);
	}
}
