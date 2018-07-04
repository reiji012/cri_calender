package com.cri.task.test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

class DialogWindow extends JDialog implements ActionListener {
	   DialogWindow(JFrame owner) {
	      super(owner);
	      getContentPane().setLayout(new FlowLayout());

	      JButton btn = new JButton("ボタン表示");
	      btn.addActionListener(this);
	      getContentPane().add(btn);

	      setTitle("ダイアログウィンドウ");
	      setSize(200, 150);
	      setVisible(true);
	   }
	   public void actionPerformed(ActionEvent e) {
	      setVisible(true);
	   }
	}
