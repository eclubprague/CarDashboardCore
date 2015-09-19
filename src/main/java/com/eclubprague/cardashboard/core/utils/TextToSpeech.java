package com.eclubprague.cardashboard.core.utils;

import android.content.Context;

import com.eclubprague.cardashboard.core.application.GlobalDataProvider;
import com.eclubprague.cardashboard.core.modules.base.IModuleContext;

/**
 * Created by Michael on 19.09.2015.
 */
public class TextToSpeech {

    private static android.speech.tts.TextToSpeech tts;
    private final static String ID = "tts";

    static {
        initTts(GlobalDataProvider.getInstance().getContext());
        GlobalDataProvider.getInstance().register(new GlobalDataProvider.ModuleContextChangeListener() {
            @Override
            public void onChange(IModuleContext newContext) {
                initTts(newContext.getContext());
            }
        });
    }

    private static void initTts(Context context) {
        if (tts != null) {
            tts.shutdown();
        }
        tts = new android.speech.tts.TextToSpeech(context,
                new android.speech.tts.TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {

                    }
                });
    }

    public static void speak(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        tts.stop();
        tts.speak(text, android.speech.tts.TextToSpeech.QUEUE_FLUSH, null, ID);
    }
}
