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

		setSize(228,184);

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
		textField.setPreferredSize(new Dimension(140,15));
		textField.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		textField.setMargin(new Insets(0,0,0,0));

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
		yearsBox.setPreferredSize(new Dimension(55,20));

		monthsBox = new JComboBox();
		for(int i=1;i<=12;i++){
			monthsBox.addItem(i);
		}
		monthsBox.setPreferredSize(new Dimension(40,20));

		dateBox = new JComboBox();
		for(int i=1;i<=31;i++){
			dateBox.addItem(i);
		}
		dateBox.setPreferredSize(new Dimension(40,20));


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
		scrollPane.setPreferredSize(new Dimension(140,30));
		contentPane.add(scrollPane,BorderLayout.CENTER);

		//イベントの内容
		textArea = new JTextArea();
		textArea.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
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



}