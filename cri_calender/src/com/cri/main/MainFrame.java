package com.cri.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.cri.base.SwingCalendarBase;
import com.cri.calendarBase.CalendarMainFrame;
import com.cri.task.TaskFrame;
import com.cri.watch.DateMainFrame;
import com.cri.watch.WatchMainFrame;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	WatchMainFrame watchMainFrame = new WatchMainFrame();
	DateMainFrame dateMainFrame = new DateMainFrame();
	CalendarMainFrame calendarMainFrame = new CalendarMainFrame();
static //	TaskFrame taskFrame = new TaskFrame();
	TaskFrame taskFrame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					taskFrame = new TaskFrame();
					MainFrame frame = new MainFrame();
					
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public MainFrame() {
		

		//フレームの作成
		//パネルの色
		setForeground(Color.GRAY);
		//ウィンドウタイプの指定
		setType(Type.UTILITY);
		//背景の指定
		setBackground(Color.GRAY);
		//「閉じる」を押した時の動作
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//フレームのサイズ
		setSize(475,800);
		//フレームの表示位置
		setBounds(475, 30, 475, 800);
		//サイズ固定
		setResizable(false);
	    
		//コンテンツを配置するパネル
		//Jpanelのインスタンス化
		contentPane = new JPanel();
		//パネルの背景を指定
		//contentPane.setBackground(Color.GRAY);
		//パネルのサイズ指定
		contentPane.setPreferredSize(new Dimension(475, 800));
		//レイアウトの指定
		contentPane.setLayout(null);
		//contentPaneのプロパティを設定
		setContentPane(contentPane);
		contentPane.setBounds(0,0,1000,1000);

		//時計を配置するパネル
		//JPanelのインスタンス化
		JPanel watchPanel = new JPanel();
		//watchPanelのレイアウトを指定
		watchPanel.setLayout(null);
		//watchPanelをcontentPaneに追加
		contentPane.add(watchPanel);
		//パネルの背景を設定
		watchPanel.setBackground(Color.GRAY);
		watchPanel.setBounds(0,0,1000,90);

		//時計を表示するラベル
		//Jlabelのインスタンス化
		JLabel watchLabel = new JLabel();
		//watchLabelのレイアウトを指定
		watchLabel.setLayout(null);
		//watchLabelをwatchPanelに追加
		watchPanel.add(watchLabel);
		//watchLabelの背景の指定
		watchLabel.setBackground(Color.GRAY);
		//watchLabelのフォントの色の指定
		watchLabel.setForeground(Color.WHITE);
		//watchLabelのフォントの指定（サイズなど）
		watchLabel.setFont(new Font("", Font.PLAIN, 45));
		//watchLabelの位置を指定
		watchLabel.setBounds(30,30,450,60);

		//日付を配置するパネル
		//datePanelのインスタンス化
		JPanel datePanel = new JPanel();
		//datePanelのレイアウトを指定
		datePanel.setLayout(null);
		//datePanelをcontentPaneに追加
		contentPane.add(datePanel);
		//datePanelの背景の指定
		datePanel.setBackground(Color.GRAY);
		//datePanelの位置を指定
		datePanel.setBounds(0,50,1000,80);

		//日付を表示するラベル
		//JLabelのインスタンス化
		JLabel dateLabel = new JLabel();
		//dateLabelのレイアウトを指定
		dateLabel.setLayout(null);
		//dateLabelをdatePanelに追加
		datePanel.add(dateLabel);
		//dateLabalの背景の指定
		dateLabel.setBackground(Color.GRAY);
		//dateLabelのフォントの色の指定
		dateLabel.setForeground(Color.blue);
		//dateLabelのフォントの指定（サイズなど）
		dateLabel.setFont(new Font("", Font.PLAIN, 15));
		//dateLabelの位置を指定
		dateLabel.setBounds(35,30,450,40);

//		setContentPane(taskContentPane);
		JPanel taskWrapPanel = new JPanel();
		taskWrapPanel.setBackground(Color.GRAY);
		taskWrapPanel.setForeground(Color.GRAY);
		taskWrapPanel.setBounds(0,560,475, 300);
		JPanel taskContentPane = taskFrame.contentPane;
		contentPane.add(taskWrapPanel);
		taskWrapPanel.add(taskContentPane);

		JPanel basePanel = new SwingCalendarBase();
		basePanel.setBounds(0,120,475,440);
		basePanel.setBackground(Color.GRAY);
		contentPane.add(basePanel);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				//watchLabelに表示するもの
				watchLabel.setText(watchMainFrame.getNowTime());

				//dateLabelに表示するもの
				dateLabel.setText(dateMainFrame.getNowDate());

				//他の処理との処理を柔軟に行う（スレッド）
				SwingUtilities.invokeLater(this);
			}
		});
	}
}
