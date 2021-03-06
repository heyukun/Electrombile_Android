
package com.xunce.electrombile.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.xunce.electrombile.Base.sdk.CmdCenter;
import com.xunce.electrombile.Base.sdk.SettingManager;
import com.xunce.electrombile.Base.utils.Historys;
import com.xunce.electrombile.xpg.common.useful.NetworkUtils;
import com.xunce.electrombile.xpg.ui.utils.ToastUtils;

/**
 * 所有activity的基类。
 * .
 * 
 * @author lyb
 */
public class BaseActivity extends Activity {

	private boolean isExit = false;
    public AlertDialog.Builder builder;

	/**
	 * 指令管理器.
	 */
	protected CmdCenter mCenter;

	/**
	 * SharePreference处理类.
	 */
	protected SettingManager setManager;


	/** The handler. */
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			isExit = false;
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setManager = new SettingManager(getApplicationContext());
		mCenter = CmdCenter.getInstance(getApplicationContext());
		// 把activity推入历史栈，退出app后清除历史栈，避免造成内存溢出
		Historys.put(this);
        initViews();
        initEvents();
	}

    public void initViews(){}
    public void initEvents(){}

	/**
	 * 重复按下返回键退出app方法
	 */
	public void exit() {
		if (!isExit) {
			isExit = true;
            ToastUtils.showShort(getApplicationContext(), "再按一次退出程序");
			handler.sendEmptyMessageDelayed(0, 2000);
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.startActivity(intent);
			Historys.exit();
		}
	}
}
