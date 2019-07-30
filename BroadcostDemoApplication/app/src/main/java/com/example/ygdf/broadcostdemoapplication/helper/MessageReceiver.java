package com.example.ygdf.broadcostdemoapplication.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] objs = (Object[]) intent.getExtras().get("pdus");
        for (Object obj : objs) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String body = smsMessage.getMessageBody();
            String sender = smsMessage.getOriginatingAddress();
            Log.i("MessageReceiver", "body:" + body);
            Log.i("MessageReceiver", "sender:" + sender);
            if ("15883376674".equals(sender)) {
                Log.i("MessageReceiver", "垃圾短信,立刻终止");
                abortBroadcast();
            }
        }
    }
}
