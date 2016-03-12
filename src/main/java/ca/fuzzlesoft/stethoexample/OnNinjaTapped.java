package ca.fuzzlesoft.stethoexample;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author mitch
 * @since 3/12/16.
 */
public class OnNinjaTapped implements OnClickListener {

    private final Activity activity;
    private final OkHttpClient client;
    private final ProgressBar mainProgress;

    public OnNinjaTapped(Activity activity, OkHttpClient client, ProgressBar mainProgress) {
        this.activity = activity;
        this.client = client;
        this.mainProgress = mainProgress;
    }

    @Override
    public void onClick(View v) {
        final Ninja ninja = (Ninja) v.getTag(R.id.NINJA_ID);
        mainProgress.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(ninja.getPictureUrl())
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mainProgress.setVisibility(View.INVISIBLE);

                        ImageView imageView = new ImageView(activity);
                        imageView.setImageBitmap(bmp);

                        Dialog dialog = new Dialog(activity);
                        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(imageView, new LayoutParams(bmp.getWidth() * 4, bmp.getHeight() * 4));
                        dialog.show();
                    }
                });
            }
        }).start();
    }
}
