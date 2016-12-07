package com.android.settings.simpleaosp;

import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v7.preference.PreferenceScreen;
import android.support.v14.preference.SwitchPreference;
import android.provider.Settings;

import com.android.internal.logging.MetricsProto.MetricsEvent;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class SystemUISettings extends SettingsPreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String KEY_HEADS_UP_SETTINGS = "heads_up_settings";

    private PreferenceScreen mHeadsUp;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.systemui_settings);
        PreferenceScreen prefScreen = getPreferenceScreen();

        mHeadsUp = (PreferenceScreen) findPreference(KEY_HEADS_UP_SETTINGS);
    }

     private boolean getUserHeadsUpState() {
         return Settings.Global.getInt(getContentResolver(),
                Settings.Global.HEADS_UP_NOTIFICATIONS_ENABLED,
                Settings.Global.HEADS_UP_ON) != 0;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {

		// preference changes here
        return false;
    }

    @Override
    protected int getMetricsCategory() {
        return MetricsEvent.BOLT;
    }

    @Override
    public void onResume() {
        super.onResume();

        mHeadsUp.setSummary(getUserHeadsUpState()
                ? R.string.summary_heads_up_enabled : R.string.summary_heads_up_disabled);
    }
}
