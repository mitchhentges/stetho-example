package ca.fuzzlesoft.stethoexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author mitch
 * @since 3/12/16.
 */
public class LoadImageTask extends AsyncTask<Ninja, Integer, Bitmap> {

    private final OkHttpClient client;
    private final ImageLoadedHandler handler;

    public LoadImageTask(OkHttpClient client, ImageLoadedHandler handler) {
        this.client = client;
        this.handler = handler;
    }

    @Override
    protected Bitmap doInBackground(Ninja... params) {
        Request request = new Request.Builder()
                .url("http://" + params[0].getPictureUrl())
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return BitmapFactory.decodeStream(response.body().byteStream());
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        handler.onImageLoaded(bitmap);
    }
}
