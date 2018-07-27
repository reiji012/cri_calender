package com.cri.watch;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class WatchMainFrame {
	private String setNowTime() {
		//Calendarクラスをインポートしてインスタンスを作成
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());

		cal.setTime(new Date());

		//時（後にString型として使用するのでString型に変換）
		String h = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
		//分（後にString型として使用するのでString型に変換）
		String min = String.valueOf(cal.get(Calendar.MINUTE));
		//秒（後にString型として使用するのでString型に変換）
		String sec = String.valueOf(cal.get(Calendar.SECOND));
		//「時」が１桁ならば先頭に０を入れる
		if (h.length() < 2) {
			h = "0" + h;
		}
		//「分」が１桁ならば先頭に０を入れる
		if (min.length() < 2) {
			min = "0" + min;
		}
		//「秒」が１桁ならば先頭に０を入れる
		if (sec.length() < 2) {
			sec = "0" + sec;
		}

		//watchに時間の表示形式を代入
		String watch = (h + ":" + min + ":" + sec);
		//この処理を行うとwatchを返す（戻り値）
		return watch;
	}
	//getNow関数を作成
	public String getNowTime() {
		//watchにsetNowDate関数を代入
		String watch = setNowTime();
		//この処理を行うとwatchを返す（戻り値）
		return watch;
	}
}