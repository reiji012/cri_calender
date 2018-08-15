package com.cri.base;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 *
 * @author kom
 */
public class Button extends javax.swing.JDialog {
    public boolean btnflag=false;
    public String strDate;
    private String[] columnNames = {"日", "月", "火", "水", "木", "金", "土"};
    //NetBeansのデザイナのjTable1のプロパティのmodelに、ユーザコード→カスタムコードで、
    //tableModelを設定。(  jTable1.setModel(tableModel); となるように。)
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    String[][] tabledata = new String[6][7];
	private JTable jSpinner2;
	private Component jTable1;

    public Button(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        getComponents();

        SpinnerNumberModel spmodel1 = new SpinnerNumberModel(2014, 1, null, 1);
        JTable jSpinner1;
		jSpinner1.setModel((TableModel) spmodel1);
        SpinnerNumberModel spmodel2 = new SpinnerNumberModel(1, 1, 12, 1);
        jSpinner2.setModel((TableModel) spmodel2);

        for(int i = 0 ; i < tabledata.length ; i++){
            tableModel.addRow(tabledata[i]);
        }

        jTable1.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));//通常表示時のフォント

        TableCellMyRenderer myRen1 = new TableCellMyRenderer();

        TableCellMyRenderer myRen2 = new TableCellMyRenderer();
        myRen2.setBackground(Color.PINK);
        
        TableCellMyRenderer myRen3 = new TableCellMyRenderer();
        myRen3.setBackground(Color.CYAN);

    
        for (int i = 0; i < jTable1.getColumnCount(); ++i) {
            TableColumn tc = jTable1.getColumn(jTable1.getColumnName(i));
            if(i==0){
                tc.setCellRenderer(myRen2);//Pink Background(日曜日)
            }else if(i==6){
                tc.setCellRenderer(myRen3);//Cyan Background(土曜日)
            }else{
                tc.setCellRenderer(myRen1);
            }
        }
        //以下の2行は、セル単位で選択できるようにするための記述。
        jTable1.setColumnSelectionAllowed(false);
        jTable1.setRowSelectionAllowed(false);
        //以下は、年表示スピナで、３桁ごとのコンマ表示をさせないため。
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(jSpinner1, "0");
        jSpinner1.setEditor(editor);
        
        //以下は、起動時に現在の年月でカレンダが表示されるようにするためのもの。
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Japan"));
        Integer Year=new Integer(cal.get(Calendar.YEAR));
        jSpinner1.setValue(Year);
        Integer Month=new Integer(cal.get(Calendar.MONTH)+1);
        jSpinner2.setValue(Month);
        makeTable();
        
        //クリックではセル内容編集状態にならないようにする。(ダブルクリックすると
        //編集状態になるが)
        jTable1.putClientProperty("JTable.autoStartsEdit", Boolean.FALSE);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                btnflag=false;
            }
        });
        moveCenter();
        
    }
    private void moveCenter(){
        Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
        setLocation( (dim.width-getSize().width)/2,(dim.height-getSize().height)/2);
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        int selRow = jTable1.getSelectedRow();
        int selCol= jTable1.getSelectedColumn();
        String strcd= (String)jTable1.getValueAt(selRow, selCol);
        if(strcd==null || strcd.equals("")){
            return;
        }
        int cd=Integer.parseInt(strcd);
        if(cd<1 || cd>31){
            return;
        }else{
            strDate=Integer.toString((Integer)jSpinner1.getValue())+
                    "/"+Integer.toString((Integer)jSpinner2.getValue())+
                    "/"+strcd;
        }
    }                                    

    private void jBtnOKActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
    }                                      


    private void jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        strDate="";
        this.setVisible(false);
    }                                          

    private void makeTable(){
        int year,month,day=1,m=1,n, lastDay,i1;
        int ypos=0;
        String strYear = jSpinner1.getValue().toString();
        String strMonth = jSpinner2.getValue().toString();
        year=Integer.parseInt(strYear);
        month=Integer.parseInt(strMonth);

        dataClear(); //表の内容をいったんクリア

        CalcDate a=new CalcDate(year,month,day);
        CalcDate b=new CalcDate(1,1,1);
        n = a.defference(b); //西暦1年1/1から現在の月の初めまでの日数
        n = n % 7 + 1; //現在の月の初日の曜日(1:日曜)
        if( n == 7 ){
            n = 0;
        }

        lastDay=CalcDate.dayNumber( year, month);

        for(int i=0 ; i < 7 ; i++){ //表の一行目の処理
            if(i>=n){
                jTable1.setValueAt(Integer.toString(m), ypos, i);
                m++;
            }
        }
        ypos++; //次の行へ

        while(m <= lastDay){ //lastDayは月の最終日
            for(int i=0; i<7 && m<=lastDay; i++){
                jTable1.setValueAt(Integer.toString(m), ypos, i);
                m++;
            }
            ypos++;
        }
    }
    
    void dataClear(){
        //DefaultTableModel jTable1Model = (DefaultTableModel)jTable1.getModel();
        int ix=jTable1.getColumnCount();
        int iy=jTable1.getRowCount();
        for(int i=0;i<ix;i++){
            for(int j=0;j < iy;j++){
                jTable1.setValueAt(null, j, i);
            }
        }
    }        

/*
   クラスDateCalcの定義
 */
class CalcDate{
    private int year;  //現在年
    private int month; //現在月
    private int day;   //現在日
    public int totalDay;    //西暦１年１月１日から現在までの日数
    //
    CalcDate(){}
    //
    CalcDate(int cyear, int cmonth, int cday)
    {
        int wkYear, wkMonth;
        year = cyear;
        month = cmonth;
        day = cday;
        totalDay = day; //西暦１年からの通算日数
        for(wkYear=1; wkYear < year; wkYear++){
            //現在の年の前年までの日数をまず計算
            if(isLeapYear(wkYear))
                totalDay += 366; //閏年なら366日
            else
                totalDay += 365;
        }
        for(wkMonth=1; wkMonth month; wkMonth++){
            //現在の年の現在の月の前月までの日数を計算
            totalDay += dayNumber(year,  wkMonth);
        }
    }

    CalcDate(int dayCount)
    {
        //AD１年１月１日からの経過日数をパラメータとしてコンストラクタを呼び出す場合
        int wkSum=0;
        boolean sw=false; //作業用変数
        totalDay = 0;
        year = 1;
        month = 1;

        while(true){
            //まず、１年単位で日数を加えていき、与えられた日数を越えたら
            //ループを終了
            if(isLeapYear(year)){
                wkSum += 366;
            } else {
                wkSum +=365;
            }
            
            if(wkSum < dayCount){
                totalDay=wkSum;
                year++;
            } else {
                break;
            }
        }

        while(true){
            //その後、月単位で１月から順に日数を加えていく
            wkSum=dayNumber(year,  month); //月の日数
            
            if(totalDay+wkSum <  dayCount){
                totalDay += wkSum;
                month++;
            }
            else{
                day = dayCount - totalDay;
                totalDay += day;
                break;
            }
        }
    }
    
    static boolean isLeapYear(int y){
        if((y %4 == 0 && y % 100 != 0) || y % 400 == 0){
            return true;
        }else{
            return false;
        }
    }

    static int dayNumber(int yr, int mn){
        int wk=0;
        if(mn==2){
                if(isLeapYear(yr)){
                    wk=29;
                } else{
                    wk=28;
                }
            } else{
                if(mn==4 || mn==6 || mn==9 || mn==11){
                    wk=30;
                } else{
                    wk=31;
                }
            }
        return wk;
    }

    int defference(CalcDate d2)
    {
        //他のDateCalcの持つ日数との差を計算する。
        //月初めまでの日数を求めるために使う。
        //月初めまでの日数がわかれば、月初めの曜日が計算できる。
        return totalDay - d2.totalDay;
    }
}}





