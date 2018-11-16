package adam.lean.inland;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.REQUEST_INSTALL_PACKAGES;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import com.up.ads.UPAdsSdk;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final String TAG = "AdsSdk_demo";

	Button btnVideo;
	Button btnBanner;
	Button btnInterstitial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        UPAdsSdk.init(MainActivity.this, UPAdsSdk.UPAdsGlobalZone.UPAdsGlobalZoneDomestic);
        if (ContextCompat.checkSelfPermission(MainActivity.this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, REQUEST_INSTALL_PACKAGES) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MainActivity.this, READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{WRITE_EXTERNAL_STORAGE, READ_PHONE_STATE}, 001);
        }
        
		btnBanner = (Button) findViewById(R.id.btnBanner);
		btnBanner.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, BannerActivity.class);
				startActivity(intent);
			}
		});

		btnInterstitial = (Button) findViewById(R.id.btnInterstitial);
		btnInterstitial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, InterstitialActivity.class);
				startActivity(intent);
			}
		});

		btnVideo = (Button) findViewById(R.id.btnVideo);
		btnVideo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, VideoActivity.class);
				startActivity(intent);
			}
		});
	}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UPAdsSdk.onApplicationResume();
    }
    
}
