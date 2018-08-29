package com.cri.base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class SwingCalendarBase extends JPanel {
		//月のLabelの宣言
        private JLabel  monthLabel = new JLabel();
        //年のLabelの宣言
        private JLabel  yearLabel = new JLabel();
        //Buttunの作成
        private JButton b1 = new JButton("<");
        private JButton b2 = new JButton(">");

        //Calendarクラスのインスタンス化取得
        Calendar cal = Calendar.getInstance();
      


        //曜日のLabelの作成
        private JLabel[]    weekLabels = new JLabel[7];
        //日にちのLabelの作成
        private JLabel[][]  dayLabels = new JLabel[6][7];

        //曜日パネルの色
        private static final Color WEEK_BG = new Color(128,128,128);
        //private static final LineBorder WEEK_BORDER = new LineBorder(new Color(160,160,230), 2, false);
        
        //日付パネルの色
        private static final Color DAY_BG = new Color(128,128,128);
        private static final Color DAY_FG = Color.BLACK;//2018年、8月色
        private static final LineBorder DAY_BORDER = new LineBorder(new Color(128,128,128), 2, false); //日付枠線

        //今日のlabel
        private static final Color TODAY_BG = new Color(128,128,128); //背景
        //private static final Color TODAY_FG = new Color(105,50,50); //文字
        private static final LineBorder TODAY_BORDER = new LineBorder(new Color(255,255,255), 2, false); //枠


        //日付のLabel
        private static final Dimension DAY_LABEL_SIZE = new Dimension(50,50);
        private static final String[] WEEK_NAMES = new String[] {"日", "月", "火", "水", "木", "金", "土"};
        private static final String[] MONTH_NAMES = new String[] {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
       
        
        String month = MONTH_NAMES[cal.get(Calendar.MONTH)];
        int todayOfday = cal.get(Calendar.DATE);
        
        //
        public SwingCalendarBase() {
                try {
                        UIManager.setLookAndFeel(
                                "javax.swing.plaf.nimbus.NimbusLookAndFeel"
                        );
                } catch (ClassNotFoundException | InstantiationException
                                | IllegalAccessException | UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                }


                //減少側ボタンの中身
                /*b1Button*/
                b1.addActionListener(new ActionListener() {
                    @Override
					public void actionPerformed(ActionEvent ae) {
                      cal.add(Calendar.MONTH, -1);
                      //b.1のmonthLabelの減少処理
                      int targetMonth = 999;
                      int year = cal.get(Calendar.YEAR);
                      String month = monthLabel.getText();
                      for(int i = 0; i < MONTH_NAMES.length; i++) {
                    	  	if(month.equals(MONTH_NAMES[i])) {
                    	  		targetMonth = i - 1;
                    	  	}

                      }
                      //b1のyearLabelの減少処理
                      //targetMonth = 当月
                      if(targetMonth == -1) {
                    	  	targetMonth = 11;
                    	  	year = cal.get(Calendar.YEAR);
                      }
                      changeMonth(targetMonth);
                      changeYear(year);
                      updateMonth(cal, true);
                      
                      

                      System.out.println("targetmanth" + targetMonth); //確認
                      System.out.println("year" + year); //確認
                    }
                    
                  });
                
                /*b2Button*/
                b2.addActionListener(new ActionListener() {
                  @Override
				public void actionPerformed(ActionEvent ae) {
                    cal.add(Calendar.MONTH, +1);
                    //b2のmonthLabelの増やす処理
                    int targetMonth = 999;
                    int year = cal.get(Calendar.YEAR);
                    String month = monthLabel.getText();
                    for (int i = 0; i < MONTH_NAMES.length; i++) {
                    		if(month.equals(MONTH_NAMES[i])) {
                    			targetMonth = i +1;
                    		}
                    }
                    //b2のyearLabelの増やす処理
                    if(targetMonth == 12) {
                    		targetMonth = 0;
                    		year = cal.get(Calendar.YEAR);

                    }
                    changeMonth(targetMonth);
                    changeYear(year);
                    updateMonth(cal, true);
                  }
                });

                /* MONTH Label *///8月
                Dimension dm = monthLabel.getPreferredSize();
                dm.width += 10; //dm.height += 1;
                monthLabel.setPreferredSize(dm);
                monthLabel.setForeground(DAY_FG);
                monthLabel.setHorizontalTextPosition(JLabel.RIGHT);
               

                /* YEAR Label *///2018年
                Dimension dy = yearLabel.getPreferredSize();
                dy.width += 10; //dy.height += 1;
                yearLabel.setPreferredSize(dy);
                yearLabel.setForeground(DAY_FG);
                
                

                /* DAY PANEL *///一番下のパネル
                JPanel dayPanel = new JPanel();
                GridLayout layout = new GridLayout(7,7);
                layout.setHgap(0);
                layout.setVgap(0);
                dayPanel.setLayout(layout);
                dayPanel.setBackground(DAY_BG);
                

                //曜日の配列
                for( int i=0; i<7; i++) {
                        weekLabels[i] = new JLabel(WEEK_NAMES[i]);
                        weekLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
                        weekLabels[i].setVerticalAlignment(SwingConstants.CENTER);
                        weekLabels[i].setPreferredSize(DAY_LABEL_SIZE);
                        weekLabels[i].setOpaque(true);
                        weekLabels[i].setBackground(WEEK_BG);
                        
                        if( i == 0 ) {
                                weekLabels[i].setForeground(Color.RED);
                        } else if ( i == 6 ) {
                                weekLabels[i].setForeground(Color.BLUE);
                        }

                        //System.out.println("kakunin" + i); //確認
                        dayPanel.add(weekLabels[i]);
                }

                
                
                //日付の配列
                for( int i=0; i<6; i++) {
                    for( int j=0; j<7; j++) {
                    	dayLabels[i][j] = new DayLabel("");
                            //System.out.println("i" + i); //確認
                            //System.out.println("j" + j); //確認
                            
                            if(j == 0 ) {
                            	dayLabels[i][j].setForeground(Color.RED);
                            } else if (j == 6 ) {
                            	dayLabels[i][j].setForeground(Color.BLUE);
                            }
                         
                                dayPanel.add(dayLabels[i][j]);
                        }

                }
               


                //JPanelインスタンス化
                JPanel monthPanel = new JPanel();
                
                //GridLayoutの追加
                monthPanel.setLayout(new GridLayout());
                
                //Label,Buttonの追加//
                //yearLabelの左に余白挿入
                monthPanel.add(Box.createGlue());
                monthPanel.add(yearLabel);
                
                //yearLabelとmonthLabelの間に余白挿入
                monthPanel.add(monthLabel);
                
                //monthLabelとb1の間に余白4つ挿入
                monthPanel.add(Box.createGlue());
                monthPanel.add(Box.createGlue());
                monthPanel.add(Box.createGlue());
                monthPanel.add(Box.createGlue());
                monthPanel.add(b1);
                //b1.setMargin(new Insets(10, 10, 10, 10));
                monthPanel.add(b2);
                //b2の後に余白挿入
                monthPanel.add(Box.createGlue());
                
                //DayColorの設定//2018~>のパネル
                monthPanel.setBackground(DAY_BG);


                //Layoutの設定
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                GridLayout gl = new GridLayout(1,2);
                gl.setHgap(10); gl.setVgap(10); //間隔の指定


                //年月ボタン表示パネル、日にちパネルの追加
                add(monthPanel);
                add(dayPanel);


                //今月のデータ取得、ラベルに表記
                monthLabel.setText(MONTH_NAMES[cal.get(Calendar.MONTH)]);
                //今年のデータを取得、ラベルに表記
                int year = cal.get(Calendar.YEAR);
                String s = Integer.toString(year) + "年";
                yearLabel.setText(s);
              
                //updateMonthメソッドの呼び出し
                updateMonth(cal, true);
        }



        /**
         * カレンダーの表示月を調整する
         * @param month  表示する月−１
         */
        private void changeMonth(int month) {
                monthLabel.setText(MONTH_NAMES[month]);
        }
        /**
         * カレンダーの表示年を調節する
         * @param year  表示する年
         */
        private void changeYear(int year) {
        		String s = Integer.toString(year) + "年";
        			yearLabel.setText(s);
    }

        /**
         * ラベルの変更 Buttonの中身のメソッド
         * @param cal Calendar変数
         * @param currentMonth
         */
        private void updateMonth(Calendar cal, boolean currentMonth) {
                int maxDate = cal.getActualMaximum(Calendar.DATE); //その月の最大日を取得
                int today = cal.get(Calendar.DATE); //今日の日付取得

                System.out.println("今日" + today); //確認
                System.out.println("month" + month); //確認
                System.out.println("最大日" + maxDate); //確認
                
                
                cal.set(Calendar.DATE, 1); //次月１日を取得
               
                //月の初めを取得 
                int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                System.out.println("月初" + firstDayOfWeek); //確認
                
                //最大日以降非表示
                for( JLabel[] ll : dayLabels) {
                		//変数lにll格納
                        for( JLabel l : ll ) {
                        		//dayLabels上の数字表示
                                l.setText("");
                        }
                }

                //月の最大日までループ
                for( int day=1; day<=maxDate; day++ ) {
                        getDayLabel( day, firstDayOfWeek ).setText(Integer.toString(day));
                        //System.out.println("day" + day); //確認
                }
                

                for( int i=0; i<dayLabels.length; i++) {
                        for( int j=0; j<dayLabels[i].length; j++) {
                                JLabel l = dayLabels[i][j];
                                l.setBackground(DAY_BG);
                                //l.setForeground(Color.BLUE);
                                l.setBorder(DAY_BORDER);

                                if( l.getText().length() != 0 ) {
                                        l.setVisible(true);
                                } else {
                                        l.setVisible(false);
                                }
                        }
                        
                }

                
                if( currentMonth) {
                        JLabel l = getDayLabel( today, firstDayOfWeek );

                       String labelMonth = monthLabel.getText();
                        if (month == labelMonth) {       //month = 1月〜12月,labelMonth = 表示されている1月〜12月
                        	JLabel u = getDayLabel( todayOfday, firstDayOfWeek );
                        	u.setBackground(TODAY_BG);
                            //u.setForeground(TODAY_FG);★ 
                            u.setBorder(TODAY_BORDER);
                            System.out.println("todayofday" + todayOfday); //確認
                        	return;
                        }
                        
                        
                        l.setBackground(TODAY_BG);
                        //l.setForeground(TODAY_FG);★
                        //l.setBorder(TODAY_BORDER);★
                      
                }}
                

        

        private JLabel getDayLabel( int day , int firstDayOfWeek ) {
                return dayLabels[(day + firstDayOfWeek - 2)/7][(day + firstDayOfWeek - 2)%7];
        }
}
