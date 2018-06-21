package com.cri.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TaskFrame extends JFrame{

	private JPanel contentPane;
	private JPanel head;
	private JLabel date;
	private JButton addButton;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	public JTextArea getTextArea() {
		return textArea;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskFrame frame = new TaskFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return
	 */

	public TaskFrame(){
		setForeground(Color.GRAY);
		setType(Type.UTILITY);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setPreferredSize(new Dimension(475, 220));
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0,0));
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);


		head = new JPanel();
		head.setLayout(new BorderLayout(0,0));
		contentPane.add(head, BorderLayout.NORTH);

		//日付を追加する
		date = new JLabel("日付");
		head.add(date, BorderLayout.WEST);
		head.setBackground(Color.GRAY);

		//ボタンを追加する
		addButton = new JButton("+");
		head.add(addButton, BorderLayout.EAST);
		addButton.setBackground(Color.GRAY);

		//スクロールパネルを追加する
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		setBackground(Color.GRAY);

		//テキストエリアを追加する
		textArea = new JTextArea();
		textArea.setText("イベントなし");
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Color.GRAY);

		//ダイアログの親をTaskFrameにする
		DialogOfTaskFrame dlg = new DialogOfTaskFrame(this);
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				textArea.setEditable(true);
				textArea.setText("");
				textArea.paste();
				//テキストエリアが空欄のとき
				if(textArea.getText().equals("")) {
					textArea.setText("イベントなし");
				}

				textArea.setEditable(false);
			}
		});



		//マウスをクリックした時
		addButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				dlg.setVisible(true);

			}
		});

	}

}
