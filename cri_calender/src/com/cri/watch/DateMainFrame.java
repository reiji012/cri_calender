package com.cri.watch;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateMainFrame {
	private String setNowDate() {

		//Calendarクラスをインポートしてインスタンスを作成
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());

		//時間を取得する
		cal.setTime(new Date());

		//年
		int y = cal.get(Calendar.YEAR);
		//月
		int mon = cal.get(Calendar.MONTH) + 1;
		//日
		int d = cal.get(Calendar.DATE);

		//変数dateに日付の表示を代入
		String date = y + "年" + mon + "月" + d + "日" ;

		//この処理を行うとdateを返す（戻り値）
		return date;
	}
	//getNowDate関数を作成
	public String getNowDate() {
		//dateにsetNowDate関数を代入
		String date = setNowDate();
		//この処理を行うとdateを返す（戻り値）
		return date;
	}
}
