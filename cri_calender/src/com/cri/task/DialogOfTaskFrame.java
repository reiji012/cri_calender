package com.cri.task;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	private JPanel contentPane;
	private JPanel boxArea;
	private JPanel buttonArea;
	private JLabel heading;
	private JLabel year, month, day;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField textField;
	private JComboBox yearsBox, monthsBox, daysBox;
	private JButton cancel, change;

	//クリップボードの取得
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();






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

		daysBox = new JComboBox();
		for(int i=1;i<=31;i++){
			daysBox.addItem(i);
		}
		daysBox.setPreferredSize(new Dimension(40,20));


		//コンボボックスに付属するラベル
		year = new JLabel("年");
		month = new JLabel("月");
		day = new JLabel("日");

		//boxAreaへの配置
		boxArea.add(yearsBox);
		boxArea.add(year);
		boxArea.add(monthsBox);
		boxArea.add(month);
		boxArea.add(daysBox);
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

		setVisible(true);


		//テキストエリアが空のときクリップボードを空にする
		if(textArea.getText().equals("")) {
			StringSelection ss = new StringSelection(textArea.getText());
			clipboard.setContents(ss, null);
		}else {
			textArea.selectAll();
			textArea.copy();
		}
		dispose();

	}




}