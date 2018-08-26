package com.cri.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TaskFrame extends JFrame implements ActionListener{

	public JPanel contentPane;
	private JPanel head;
	private JLabel date;
	private JButton addButton;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	private DefaultListModel taskListModel;
	private JList taskList;
	private File file = new File("taskXML.xml");
	private TaskXml xml;
	private String[] str;
	private Date today;

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

	public TaskFrame() throws Exception{
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
		SimpleDateFormat sdf = new SimpleDateFormat("M月dd日 E曜日");
		today = new Date();
		date = new JLabel(sdf.format(today));
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

		//リストを追加
		taskListModel = new DefaultListModel();
		taskList = new JList(taskListModel);
		taskList.setBackground(Color.GRAY);
		scrollPane.setViewportView(taskList);
		setToDo();

		//ダイアログの親をTaskFrameにする
		DialogOfTaskFrame dlg = new DialogOfTaskFrame(this,false,0);

		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {

				try {
					setToDo();
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
		});

		addButton.addActionListener(this);

		taskList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				if(taskListModel.getElementAt(0).equals("イベントなし") || taskList.getSelectedIndices().length != 1) {
					return;
				}

				if(e.getClickCount() == 2) {
					dlg.setVisible(true);
				}
			}
		});

	}

	private void setToDo() throws Exception{
		taskListModel.clear();
		//ファイルがあれば読み込む、なければ「イベントなし」と表示
		xml = new TaskXml();
		if(file.exists()) {
			str = xml.viewXml();
			for(int i=0;i<str.length;i++) {
				taskListModel.addElement(str[i]);
			}
		}
		if(taskListModel.size() == 0) {
			taskListModel.addElement("イベントなし");
		}
	}

	public void actionPerformed(ActionEvent e) {

		DialogOfTaskFrame dlg = new DialogOfTaskFrame(this,true,taskList.getSelectedIndex() + 1);
		dlg.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {

				try {
					setToDo();
				} catch (Exception e1) {
					// TODO 自動生成された catch ブロック
					e1.printStackTrace();
				}
			}
		});
		dlg.setVisible(true);
	}

}

