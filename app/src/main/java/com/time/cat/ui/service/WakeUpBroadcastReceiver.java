package com.time.cat.ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.time.cat.util.override.LogUtil;

public class WakeUpBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.d("timecat", "xposed wake");
        try {
            context.startService(new Intent(context, ListenClipboardService.class));
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
