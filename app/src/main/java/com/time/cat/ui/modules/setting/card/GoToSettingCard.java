package com.time.cat.ui.modules.setting.card;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;

import com.timecat.commonjar.contentProvider.SPHelper;
import com.time.cat.R;
import com.time.cat.ui.base.baseCard.AbsCard;
import com.time.cat.data.Constants;
import com.time.cat.util.override.SnackBarUtil;

public class GoToSettingCard extends AbsCard {


    private SwitchCompat autoOpenSwitch;

    public GoToSettingCard(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.card_goto_setting, this);
        findViewById(R.id.goto_access_rl).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    mContext.startActivity(intent);
                } catch (Throwable e) {
                    SnackBarUtil.show(v, R.string.open_setting_failed_diy);
                }
            }
        });
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//            findViewById(R.id.goto_voice_rl).setVisibility(VISIBLE);
//            findViewById(R.id.goto_voice_rl).setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    try {
//                        Intent intent = new Intent(Settings.ACTION_VOICE_INPUT_SETTINGS);
//                        mContext.startActivity(intent);
//                    } catch (Throwable e) {
//                        SnackBarUtil.show(v, R.string.open_setting_failed_diy);
//                    }
//                }
//            });
//        }

        autoOpenSwitch = findViewById(R.id.auto_open_switch);
        autoOpenSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPHelper.save(Constants.AUTO_OPEN_SETTING, isChecked);
            }
        });
        findViewById(R.id.auto_open_rl).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                autoOpenSwitch.setChecked(!autoOpenSwitch.isChecked());
                mContext.sendBroadcast(new Intent(Constants.BROADCAST_TIMECAT_MONITOR_SERVICE_MODIFIED));
            }
        });

        autoOpenSwitch.setChecked(SPHelper.getBoolean(Constants.AUTO_OPEN_SETTING, false));
    }

}
