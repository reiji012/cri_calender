package com.cri.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TaskFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(475,220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0,0));
		contentPane.setBackground(Color.GRAY);
		setContentPane(contentPane);


		JPanel head = new JPanel();
		head.setLayout(new BorderLayout(0,0));
		contentPane.add(head, BorderLayout.NORTH);

		//日付を追加する
		JLabel date = new JLabel("日付");
		head.add(date, BorderLayout.WEST);

		//ボタンを追加する
		JButton btn1 = new JButton("+");
		head.add(btn1, BorderLayout.EAST);


	}

}
