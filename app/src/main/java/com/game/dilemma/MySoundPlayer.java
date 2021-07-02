package com.game.dilemma;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.HashMap;

public class MySoundPlayer {
    public static final int A1_SOUND = R.raw.a1_sound;
    public static final int A2_SOUND = R.raw.a2_sound;
    public static final int A3_SOUND = R.raw.a3_sound;
    public static final int A6_SOUND = R.raw.a6_sound;

    private static SoundPool soundPool;
    private static HashMap<Integer, Integer> soundPoolMap;

    // sound media initialize
    public static void initSounds(Context context) {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

        soundPoolMap = new HashMap(2);
        soundPoolMap.put(A1_SOUND, soundPool.load(context, A1_SOUND, 1));
        soundPoolMap.put(A2_SOUND, soundPool.load(context, A2_SOUND, 2));
        soundPoolMap.put(A3_SOUND, soundPool.load(context, A3_SOUND, 3));
        soundPoolMap.put(A6_SOUND, soundPool.load(context, A6_SOUND, 4));
    }

    public static void play(int raw_id){
        if( soundPoolMap.containsKey(raw_id) ) {
            soundPool.play(soundPoolMap.get(raw_id), 1, 1, 1, 0, 1f);
        }
    }
}