package com.example.acer.myapplication.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.service.chooser.ChooserTarget;
import android.service.chooser.ChooserTargetService;

import java.util.List;

/**
 * Created by vijay on 19/8/16.
 */
public class Sampleservice extends ChooserTargetService {
    public Sampleservice() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public List<ChooserTarget> onGetChooserTargets(ComponentName targetActivityName, IntentFilter matchedFilter) {
        return null;
    }
}
