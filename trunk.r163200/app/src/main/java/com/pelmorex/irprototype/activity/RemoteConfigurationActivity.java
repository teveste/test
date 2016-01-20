package com.pelmorex.irprototype.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.pelmorex.irprototype.R;
import com.pelmorex.irprototype.application.IRApplication;
import com.pelmorex.irprototype.ir.CustomInfraredFactory;
import com.pelmorex.irprototype.ir.InfraredConfiguration;
import com.pelmorex.irprototype.ir.RemoteConfiguration;
import com.pelmorex.irprototype.ui.InstantAutoCompleteTextView;
import com.pelmorex.irprototype.util.UiUtil;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class RemoteConfigurationActivity extends AppCompatActivity {
    private static final String[] REMOTE_TV_SPINNER_CHOICES = { "PELMOREX TV", "EDWIN TV", "PHILIPPE TV" };
    private static final String[] REMOTE_STB_SPINNER_CHOICES = { "NO SET TOP BOX", "EDWIN SET TOP BOX", "PHILIPPE SET TOP BOX" };

    private static final String[] LIST_ALL_TVS = {"3M", "ABB", "Acer", "ADA", "Adcom", "Aethra", "Aiwa", "Akai", "Alpine", "AMC", "Ampro", "Anthem", "Apex", "Apti", "Aragon", "Ask", "Atlantic Technology",
            "Audio Design Associates", "Audio Ease", "Audio International", "Audio Request", "Audioaccess", "Audioease", "Audiosource", "Auton", "Auto-Vue", "B & K", "Bang & Olufsen", "Barco", "Biamp", "BMB",
            "Bogen", "Bose", "Broksonic", "BTX", "California Audio Labs", "Canon", "Carver", "Channel Master", "Chaparral", "Chiro", "Chisholm", "Christie Digital Systems", "Clarion", "Classe", "Counterpoint",
            "CTX", "CTX-Optima2", "Daewoo", "Dalite", "Davis", "Dbox", "Denon", "Digital Projection", "Dish Network", "DMX", "Draper", "Dual Cassette", "Dukane", "Dwin", "Echostar", "Eike", "Eiki", "Elan",
            "Electrohome", "Electronics", "Elero", "Elmo", "Epson", "Escient", "Everquest", "Extron", "Fanfare", "Fanon", "Farenheit", "Faroudja", "Fisher", "Fosgate Audionics", "Fostex", "Fox", "Fujitsu", "Funai",
            "GE", "GC Electronics", "General Instruments", "Getner", "Globecast", "Go Video", "Grand Channel", "Grand Tech", "Grundig", "Harmon Kardon", "Hitachi", "Houston Tracking Systems", "Hughes", "Hunter Douglas",
            "Imerge", "Infocus", "Integra", "I-Point", "Jaton", "JBL", "Jerrold", "JVC", "KDS", "Kenavision", "Kenwood", "Kinergetics Research", "Kloss", "MTX", "Kodak", "Krell", "Kustom", "Lexicon", "Linn",
            "Litetouch", "Loewe", "Lutron", "Luxman", "Magnavox", "Makita Electric Works", "Marantz", "Mark Levinson", "Matrix Systems", "Mcintosh", "Mediland", "Megapower", "Meridian", "Microsoft",
            "Mindpath", "Mitsubishi", "Monivision", "Monovision", "Motorola", "Multi Video Labs", "NAD", "Nakamichi", "NEC", "Netware", "Nikko", "Niles", "Novaplex", "NSM", "Nuview", "Nview", "Oak", "On Command",
            "Ong Corporation", "Onkyo", "Pace", "Pace Sky Manual", "Panasat", "Panasonic", "Parasound", "Philips", "Piano Disc", "Picturetel", "Pinnacle", "Pioneer", "Plus", "Poloroid", "Polycom", "Primare",
            "Primestar", "Princeton", "Pro Presenter", "Proceed", "Projectavision", "Proscan", "Proton", "Proxima", "QRS", "Quasar", "RCA", "Replay TV", "Request Multimedia", "Revox", "RFT", "Rock-OLA", "Rotel",
            "Runco", "Russound", "Samsung", "Sanyo", "Scientific Atlanta", "Sears", "Seleco", "Sharp", "Sherwood", "Silent Gliss", "Sima", "Sky", "Somfy", "Sony", "Sonic Blue", "Southerland", "Sunfire", "Sunteca",
            "Tagmclaren", "Tandberg", "Tascam", "Teac", "Technics", "Telex", "Theta Casablanca", "Theta Digital", "Toshiba", "Totevision", "Travelors", "Turtle Beach", "Uniden", "US Electronics", "Vantage",
            "Vaux", "Velodyne", "Videolabs", "Vidikron", "Viewsonic", "Viewtech", "Vu-Tec", "Wireless Mouse Inc", "Wisetech", "Wolf", "X-10", "Xantech", "Yamaha", "Zenith" };

    private final Navigator navigator = new Navigator();
    private Spinner tvSpinner;
    private ArrayAdapter<String> tvSpinnerAdapter;
    private Spinner stbSpinner;
    private ArrayAdapter<String> stbSpinnerAdapter;
    private InstantAutoCompleteTextView twnChannelIactv;
    private Button validationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        buildUi();
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent event) {
        UiUtil.clearFocusOnOutsideTap(this, event, EditText.class);
        return super.dispatchTouchEvent( event );
    }

    private void buildUi() {
        setContentView(R.layout.activity_remote_configuration);

        tvSpinner = (Spinner) findViewById(R.id.remote_config_tv_spn);
        tvSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, REMOTE_TV_SPINNER_CHOICES);
        //tvSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, LIST_ALL_TVS);

        tvSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tvSpinner.setAdapter(tvSpinnerAdapter);

        stbSpinner = (Spinner) findViewById(R.id.remote_config_stb_spn);
        stbSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, REMOTE_STB_SPINNER_CHOICES);
        stbSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stbSpinner.setAdapter(stbSpinnerAdapter);

        twnChannelIactv = (InstantAutoCompleteTextView) findViewById(R.id.remote_config_twn_channel);

        validationButton = (Button) findViewById(R.id.remote_config_validation_btn);
        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteConfiguration remoteConfiguration = getRequestedRemoteConfiguration();
                if ( isRemoteConfigurationValid(remoteConfiguration) ) {
                    saveRemoteConfiguration(remoteConfiguration);
                    navigator.exit();
                }
            }
        });

        RemoteConfiguration remoteConfiguration = ((IRApplication) getApplicationContext()).getRemoteConfiguration();
        tvSpinner.setSelection(tvSpinnerAdapter.getPosition(remoteConfiguration.getTvIrConfig().getName()));
        stbSpinner.setSelection(stbSpinnerAdapter.getPosition(remoteConfiguration.getStbIrConfig() != null ? remoteConfiguration.getStbIrConfig().getName() : "NO SET TOP BOX"));
        twnChannelIactv.setText(String.valueOf(remoteConfiguration.getTwnChannel()));

        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out);
    }

    private boolean isRemoteConfigurationValid(RemoteConfiguration rc) {
        return rc.getTvIrConfig() != null && rc.getTwnChannel() != null;
    }

    private void saveRemoteConfiguration(RemoteConfiguration remoteConfiguration) {
        ((IRApplication) getApplicationContext()).setRemoteConfiguration(remoteConfiguration);
    }

    private RemoteConfiguration getRequestedRemoteConfiguration() {
        final String tvSpinnerText = ((TextView) tvSpinner.getSelectedView()).getText().toString();
        final String stbSpinnerText = ((TextView) stbSpinner.getSelectedView()).getText().toString();
        final int twnChannel = Integer.parseInt(twnChannelIactv.getText().toString());

        RemoteConfiguration remoteConfiguration = new RemoteConfiguration();
        remoteConfiguration.setTvIrConfig(getTvInfraredConfiguration(tvSpinnerText));
        remoteConfiguration.setStbIrConfig(getStbInfraredConfiguration(stbSpinnerText));
        remoteConfiguration.setTwnChannel(twnChannel);

        Log.d("REMOTE CONFIGURATION", "GENERATED REMOTE CONFIGURATION WITH THE FOLLOWING VALUES: ");
        Log.d("REMOTE CONFIGURATION", "TV: " + tvSpinnerText);
        Log.d("REMOTE CONFIGURATION", "STB: " + stbSpinnerText);
        Log.d("REMOTE CONFIGURATION", "TWN CHANNEL: " + twnChannel);

        return remoteConfiguration;
    }

    private InfraredConfiguration getTvInfraredConfiguration(String configurationName) {
        if ( configurationName.equals("PELMOREX TV") ) {
            return CustomInfraredFactory.createPelmorexTvIrConfig();
        } else if ( configurationName.equals("EDWIN TV") ) {
            return CustomInfraredFactory.createEdwinTvIrConfig();
        } else if ( configurationName.equals("PHILIPPE TV") ) {
            return CustomInfraredFactory.createPhilippeTvIrConfig();
        } else {
            return null;
        }
    }

    private InfraredConfiguration getStbInfraredConfiguration(String configurationName) {
        if ( configurationName.equals("EDWIN SET TOP BOX") ) {
            return CustomInfraredFactory.createEdwinStbIrConfig();
        } else if ( configurationName.equals("PHILIPPE SET TOP BOX") ) {
            return CustomInfraredFactory.createPhilippeStbIrConfig();
        } else {
            return null;
        }
    }

    private class Navigator {
        private void exit() {
            finish();
        }
    }
}
