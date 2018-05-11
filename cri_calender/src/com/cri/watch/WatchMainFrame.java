package com.cri.watch;

import java.awt.Container;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class WatchMainFrame {
	//曜日の取得メソッド
	public static String getDayOfTheWeek() {
		//Calendarをインスタンス化
		Calendar ca = Calendar.getInstance();
		//switchで取得したデータに対する戻り値を指定
		switch (ca.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY: return "日";
		case Calendar.MONDAY: return "月";
		case Calendar.TUESDAY: return "火";
		case Calendar.WEDNESDAY: return "水";
		case Calendar.THURSDAY: return "木";
		case Calendar.FRIDAY: return "金";
		case Calendar.SATURDAY: return "土";
		}
		return null;
	}

	public static void main(String args[]) throws java.io.IOException {

		//フレームを作成する
		JFrame frame = new JFrame("サンプル");
<<<<<<< HEAD

		//ウィンドウのサイズ指定
		frame.setBounds(0,0,920,680);

		//閉じるボタンを押した時のアプリの振る舞い
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//ウィンドウの表示
		frame.setVisible(true);

		//コンテンツ区画の取得
		Container container = frame.getContentPane();

		//ラベルを作る
		final JLabel jl = new JLabel();

		//ラベルをコンテンツ区画に追加する
		container.add(jl);

		//Calendarクラスをインポートしてインスタンスを作成
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());

		//getDayOfTheWeekメソッドを変数weに代入
		String we = getDayOfTheWeek();

		//SwingUtilitiesクラスをインポートして処理の結果が変わるごとに処理を実行する
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				//時間を取得する
				cal.setTime(new Date());

				//年
				int y = cal.get(Calendar.YEAR);
				//月
				int mon = cal.get(Calendar.MONTH) + 1;
				//日
				int d = cal.get(Calendar.DATE);
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

				//変数DateSに日付の表示を代入
				String DateS = y + "/" + mon + "/" + d + "\t" + "(" + we + ")";

				//変数TimeSに時刻の表示を代入
				String TimeS = h + ":" + min + ":" + sec;

				//ラベルに日付と時刻のテキストを表示させる
				jl.setText(DateS + "\t" + TimeS);

				//他の処理とこの処理を柔軟に行う（スレッド）
				SwingUtilities.invokeLater(this);

=======

		//ウィンドウのサイズ指定
		frame.setBounds(0,0,920,680);

		//閉じるボタンを押した時のアプリの振る舞い
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//ウィンドウの表示
		frame.setVisible(true);

		//コンテンツ区画の取得
		Container container = frame.getContentPane();

		//ラベルを作る
		final JLabel jl = new JLabel();

		//ラベルをコンテンツ区画に追加する
		container.add(jl);

		//Calendarクラスをインポートしてインスタンスを作成
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());

		//getDayOfTheWeekメソッドを変数weに代入
		String we = getDayOfTheWeek();

		//SwingUtilitiesクラスをインポートして処理の結果が変わるごとに処理を実行する
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				//時間を取得する
				cal.setTime(new Date());

				//年
				int y = cal.get(Calendar.YEAR);
				//月
				int mon = cal.get(Calendar.MONTH) + 1;
				//日
				int d = cal.get(Calendar.DATE);
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

				//変数DateSに日付の表示を代入
				String DateS = y + "/" + mon + "/" + d + "\t" + "(" + we + ")";

				//変数TimeSに時刻の表示を代入
				String TimeS = h + ":" + min + ":" + sec;

				//ラベルに日付と時刻のテキストを表示させる
				jl.setText(DateS + "\t" + TimeS);

				//他の処理とこの処理を柔軟に行う（スレッド）
				SwingUtilities.invokeLater(this);

>>>>>>> d9451711fea03fdb70b68a28afd8971e3cafc86b
				//ラベルを左右中央に配置
				jl.setHorizontalAlignment(SwingConstants.CENTER);
			}
		});
	}
<<<<<<< HEAD
}

=======
}
>>>>>>> d9451711fea03fdb70b68a28afd8971e3cafc86b
