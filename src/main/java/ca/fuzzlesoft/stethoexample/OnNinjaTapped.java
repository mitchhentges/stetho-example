package ca.fuzzlesoft.stethoexample;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;

import okhttp3.OkHttpClient;

/**
 * @author mitch
 * @since 3/12/16.
 */
public class OnNinjaTapped implements OnClickListener, ImageLoadedHandler {

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
        new LoadImageTask(client, this).execute(ninja);
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        mainProgress.setVisibility(View.INVISIBLE);

        ImageView imageView = new ImageView(activity);
        imageView.setImageBitmap(bitmap);

        Dialog dialog = new Dialog(activity);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(imageView,
                new LayoutParams(bitmap.getWidth() * 4, bitmap.getHeight() * 4));
        dialog.show();
    }
}
