package com.cri.task;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

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
	private String placeholderField = "イベントのタイトルを入力して下さい";
	private String placeholderArea = "イベントの内容を入力して下さい";
	private File file;

	private JPanel contentPane;
	private JPanel boxArea;
	private JPanel buttonArea;
	private JLabel heading;
	private JLabel year, month, day;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField textField;
	private JComboBox yearsBox, monthsBox, dateBox;
	private JButton cancel, change;

	DialogOfTaskFrame(JFrame owner){
		super(owner);
		contentPane = new JPanel();
		getContentPane().setLayout(new GridBagLayout());
		setContentPane(contentPane);

		setBounds(500, 500, 250, 240);
		//サイズ固定
		setResizable(false);

		GridBagConstraints gbc = new GridBagConstraints();

		//見出し
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0,0,0,0);


		heading = new JLabel("イベントの登録");
		contentPane.add(heading,gbc);

		//イベントのタイトル
		gbc.gridy = 1;

		textField = new JTextField();
<<<<<<< HEAD
		textField.setPreferredSize(new Dimension(140,15));
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		textField.setMargin(new Insets(0,0,0,0));

=======
		textField.setText(hm.get("title").toString());
		textField.setPreferredSize(new Dimension(180,30));
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		textField.setMargin(new Insets(0,0,0,0));
		textField.setBackground(Color.LIGHT_GRAY);
		textField.addFocusListener(new TextFieldFocusListener());
>>>>>>> b94373d143db21e7720b0e2e8c2f993825d01938
		contentPane.add(textField,gbc);


		//コンボボックス等を配置するパネル
		gbc.gridy = 2;

		boxArea = new JPanel();
		boxArea.setLayout(new FlowLayout(0,0,0));
		contentPane.add(boxArea,gbc);


		//コンボボックスの設定
		yearsBox = new JComboBox();
		for(int i=2000;i<=2025;i++){
			yearsBox.addItem(i);
		}
<<<<<<< HEAD
		yearsBox.setPreferredSize(new Dimension(55,20));
=======
		yearsBox.setPreferredSize(new Dimension(80,20));
		yearsBox.setSelectedItem(Integer.parseInt(hm.get("year").toString()));
		yearsBox.setBackground(Color.LIGHT_GRAY);
>>>>>>> b94373d143db21e7720b0e2e8c2f993825d01938

		monthsBox = new JComboBox();
		for(int i=1;i<=12;i++){
			monthsBox.addItem(i);
		}
		monthsBox.setPreferredSize(new Dimension(40,20));

		dateBox = new JComboBox();
		for(int i=1;i<=31;i++){
			dateBox.addItem(i);
		}
<<<<<<< HEAD
		dateBox.setPreferredSize(new Dimension(40,20));

=======
		dateBox.setPreferredSize(new Dimension(50,20));
		dateBox.setSelectedItem(Integer.parseInt(hm.get("date").toString()));
		dateBox.setBackground(Color.LIGHT_GRAY);
>>>>>>> b94373d143db21e7720b0e2e8c2f993825d01938

		//コンボボックスに付属するラベル
		year = new JLabel("年");
		month = new JLabel("月");
		day = new JLabel("日");

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
		scrollPane.setPreferredSize(new Dimension(170,50));
		contentPane.add(scrollPane,BorderLayout.CENTER);

		//イベントの内容
		textArea = new JTextArea();
<<<<<<< HEAD
		textArea.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
=======
		textArea.setText(hm.get("text").toString());
		if(textArea.getText().equals("")) {
			textArea.setText(placeholderArea);
			textArea.setForeground(Color.GRAY);
		}
		textArea.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.addFocusListener(new TextAreaFocusListener());
>>>>>>> b94373d143db21e7720b0e2e8c2f993825d01938
		scrollPane.setViewportView(textArea);

		//ボタンを配置する場所
		gbc.gridy = 5;
		gbc.gridheight = 1;

		buttonArea = new JPanel();
		contentPane.add(buttonArea,gbc);
		buttonArea.setLayout(new FlowLayout());

		//ボタンの設定
		cancel = new JButton("キャンセル");
		cancel.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		change = new JButton("変更");
		change.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		//changeボタン押下時
		change.addActionListener(this);

		//cancelボタン押下時
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		buttonArea.add(cancel);
		buttonArea.add(change);

	}

	public void actionPerformed(ActionEvent e){

		setTaskContent();
		textArea.setText("");
		textField.setText("");
		dispose();
	}

	private void setTaskContent() {

		//透かし文字を保存しない
		if(textArea.getText().equals(placeholderArea)) {
			textArea.setText("");
		}
		if(textField.getText().equals(placeholderField)) {
			textField.setText("");
		}

		String year;
		String month;
		String date;

		taskTitle = textField.getText() + "：";
		taskText = textArea.getText();

		year = yearsBox.getSelectedItem().toString();
		month = monthsBox.getSelectedItem().toString();
		date = dateBox.getSelectedItem().toString();

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

<<<<<<< HEAD
=======
	class TextAreaFocusListener implements FocusListener{

		public void focusLost(FocusEvent e) {

			if(textArea.getText().equals("")) {
				textArea.setText(placeholderArea);
				textArea.setForeground(Color.GRAY);
			}

		}

		public void focusGained(FocusEvent e) {

			if(textArea.getText().equals(placeholderArea)) {
				textArea.setText("");
			}
			textArea.setForeground(Color.BLACK);

		}
	}

	class TextFieldFocusListener implements FocusListener{

		public void focusLost(FocusEvent e) {

			if(textField.getText().equals("")) {
				textField.setText(placeholderField);
				textField.setForeground(Color.GRAY);
			}
		}

		public void focusGained(FocusEvent e) {

			if(textField.getText().equals(placeholderField)) {
				textField.setText("");
			}
			textField.setForeground(Color.BLACK);
		}
	}

	//deleteボタン用のアクション
	class DeleteActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e){

			TaskXml xml = new TaskXml();
			int listNumber = Integer.parseInt(saveNumber.getText());

			try {
>>>>>>> b94373d143db21e7720b0e2e8c2f993825d01938


}