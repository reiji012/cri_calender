package com.cri.task.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TaskMainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel head;
	private JLabel date;
	private JButton addButton;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaskMainFrame frame = new TaskMainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaskMainFrame() {
		setForeground(Color.GRAY);
		setType(Type.UTILITY);
		setBackground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setPreferredSize(new Dimension(475, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//タスクの日付と追加するためのボタンを配置するパネル
		JPanel head = new JPanel();
		head.setLayout(new BorderLayout(0, 0));
		contentPane.add(head, BorderLayout.NORTH);
        head.setBackground(Color.GRAY);


		//日付
		JLabel date = new JLabel("今日");
		head.add(date, BorderLayout.WEST);
        head.setBackground(Color.GRAY);


		//追加ボタン
		JButton addButton = new JButton("+");
		head.add(addButton, BorderLayout.EAST);
		addButton.setBackground(Color.GRAY);


		//タスクを追加していくパネル
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBackground(Color.GRAY);


		JTextArea textArea = new JTextArea();
		textArea.setText("タスクはありません");
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Color.GRAY);

		addButton.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			    DialogWindow dlg = new DialogWindow(null);
				getContentPane().setLayout(new FlowLayout());

				dlg.actionPerformed(null);
			}

		});

	}

}
