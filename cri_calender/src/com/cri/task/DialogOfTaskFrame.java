package com.cri.task;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class DialogOfTaskFrame extends JDialog implements ActionListener{

	private String taskTitle;
	private String taskText;
	private String taskDate;
	public  String taskContent;
	private File file;

	private JPanel contentPane;
	private JPanel boxArea;
	private JPanel buttonArea;
	private JLabel heading;
	private JLabel year, month, day;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField textField, saveNumber;
	private JComboBox yearsBox, monthsBox, dateBox;
	private JButton cancelBtn, appendBtn, deleteBtn, changeBtn;

	//ダイアログの引数は、オーナー、追加するダイアログか変更するダイアログか、リストの番号とダイアログに表示する値をまとめたもの
	DialogOfTaskFrame(JFrame owner,boolean whichDialog,HashMap hm){

		super(owner);

		//不可視のテキストフィールドにlistNumberをもたせる
		saveNumber = new JTextField();
		saveNumber.setText(hm.get("listNumber").toString());

		contentPane = new JPanel();
		getContentPane().setLayout(new GridBagLayout());
		setContentPane(contentPane);
		contentPane.setBackground(Color.GRAY);

		setSize(228,184);

		GridBagConstraints gbc = new GridBagConstraints();

		//見出し
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,0,0,0);

		//呼び出し元がリストなら編集、＋ボタンなら登録
		if(whichDialog) {
			heading = new JLabel("イベントの登録");
		}else {
			heading = new JLabel("イベントの編集");
		}

		heading.setForeground(Color.WHITE);
		contentPane.add(heading,gbc);

		//イベントのタイトル
		gbc.gridy = 1;

		textField = new JTextField();
		textField.setText(hm.get("title").toString());
		textField.setPreferredSize(new Dimension(140,15));
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		textField.setMargin(new Insets(0,0,0,0));
		textField.setBackground(Color.LIGHT_GRAY);
		contentPane.add(textField,gbc);

		//コンボボックス等を配置するパネル
		gbc.gridy = 2;

		boxArea = new JPanel();
		boxArea.setLayout(new FlowLayout(0,0,0));
		boxArea.setBackground(Color.GRAY);
		contentPane.add(boxArea,gbc);

		//コンボボックスの設定
		yearsBox = new JComboBox();
		for(int i=2000;i<=2025;i++){
			yearsBox.addItem(i);
		}
		yearsBox.setPreferredSize(new Dimension(55,20));
		yearsBox.setSelectedItem(Integer.parseInt(hm.get("year").toString()));
		yearsBox.setBackground(Color.LIGHT_GRAY);

		monthsBox = new JComboBox();
		for(int i=1;i<=12;i++){
			monthsBox.addItem(i);
		}
		monthsBox.setPreferredSize(new Dimension(40,20));
		monthsBox.setSelectedItem(Integer.parseInt(hm.get("month").toString()));
		monthsBox.setBackground(Color.LIGHT_GRAY);

		dateBox = new JComboBox();
		for(int i=1;i<=31;i++){
			dateBox.addItem(i);
		}
		dateBox.setPreferredSize(new Dimension(40,20));
		dateBox.setSelectedItem(Integer.parseInt(hm.get("date").toString()));
		dateBox.setBackground(Color.LIGHT_GRAY);

		//コンボボックスに付属するラベル
		year = new JLabel("年");
		year.setForeground(Color.WHITE);
		month = new JLabel("月");
		month.setForeground(Color.WHITE);
		day = new JLabel("日");
		day.setForeground(Color.WHITE);

		//boxAreaへの配置
		boxArea.add(yearsBox);
		boxArea.add(year);
		boxArea.add(monthsBox);
		boxArea.add(month);
		boxArea.add(dateBox);
		boxArea.add(day);

		//スクロールパネル
		gbc.gridy = 3;
		gbc.gridheight = 2;

		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(140,30));
		contentPane.add(scrollPane,BorderLayout.CENTER);

		//イベントの内容
		textArea = new JTextArea();
		textArea.setText(hm.get("text").toString());
		textArea.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		textArea.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(textArea);

		//ボタンを配置する場所
		gbc.gridy = 5;
		gbc.gridheight = 1;

		buttonArea = new JPanel();
		contentPane.add(buttonArea,gbc);
		buttonArea.setLayout(new FlowLayout());
		buttonArea.setBackground(Color.GRAY);

		//ボタンの設定
		cancelBtn = new JButton("キャンセル");
		cancelBtn.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		cancelBtn.setBackground(Color.LIGHT_GRAY);
		appendBtn = new JButton("登録");
		appendBtn.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		appendBtn.setBackground(Color.LIGHT_GRAY);
		deleteBtn = new JButton("削除");
		deleteBtn.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		deleteBtn.setBackground(Color.LIGHT_GRAY);
		changeBtn = new JButton("変更");
		changeBtn.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		changeBtn.setBackground(Color.LIGHT_GRAY);

		//cancelボタン押下時
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});

		//appendボタン押下時
		appendBtn.addActionListener(this);

		//deleteボタン押下時
		deleteBtn.addActionListener(new DeleteActionListener());

		//changeボタン押下時
		changeBtn.addActionListener(new ChangeActionListener());

		//追加するダイアログなら、追加とキャンセルボタン、変更するダイアログならキャンセル、削除、変更ボタン
		if(whichDialog) {
			buttonArea.add(cancelBtn);
			buttonArea.add(appendBtn);
		}else {
			buttonArea.add(cancelBtn);
			buttonArea.add(deleteBtn);
			buttonArea.add(changeBtn);
		}

	}

	public void actionPerformed(ActionEvent e){

		setTaskContent();
		dispose();
	}

	private void setTaskContent() {

		String year;
		String month;
		String date;

		taskTitle = textField.getText() + "：";
		taskText = textArea.getText();

		year = yearsBox.getSelectedItem().toString();
		month = monthsBox.getSelectedItem().toString();
		date = dateBox.getSelectedItem().toString();

		if(month.length() == 1) {
			month = "0" + month;
		}
		if(date.length() == 1) {
			date = "0" + date;
		}

		taskDate = year + "年" + month + "月" + date + "日：";
		taskContent = taskDate  + taskTitle + taskText;

		TaskXml xml = new TaskXml();
		//ファイルを指定
		file = new File("taskXML.xml");
		try {
		//既設のXMLがあれば追加、なければルートノードのみ作成し、追加
			if(!file.exists()) {
				xml.createXml();
			}
			xml.appendXml(taskDate,taskTitle,taskText);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	//deleteボタン用のアクション
	class DeleteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e){

			TaskXml xml = new TaskXml();
			int listNumber = Integer.parseInt(saveNumber.getText());

			try {

				xml.removeTask(listNumber);
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			dispose();
		}

	}

	//changeボタン用のアクション　削除して追加している
	class ChangeActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			TaskXml xml = new TaskXml();
			int listNumber = Integer.parseInt(saveNumber.getText());
			try {

				xml.removeTask(listNumber);
			} catch (Exception e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			setTaskContent();
			dispose();

		}
	}

}