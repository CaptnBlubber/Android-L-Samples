package s3xy.de.android_lsamples.api;

import android.content.Context;
import android.webkit.WebView;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import s3xy.de.android_lsamples.R;

/**
 * Created by arueggeberg on 05.06.14.
 */
public class SampleClient {

    //Singleton Instance of our Rest Client
    private static FlickrApiInterface sFlickrService;


    //Singleton Instance of our API Headers
    private static ApiHeaders sApiHeaders;

    public static ApiHeaders getApiHeaders(Context ctx) {
        if (sApiHeaders == null) {
            sApiHeaders = new ApiHeaders(ctx);
        }
        return sApiHeaders;
    }


    //returns the Singleton Instance of our Rest client or Creates an new instance
    public static FlickrApiInterface getFlickrApiClient(Context ctx) {
        if (sFlickrService == null) {

            OkHttpClient httpClient = new OkHttpClient();
            try {
                httpClient.setCache(new Cache(ctx.getCacheDir(), 50 * 1024 * 1024));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Client client = new OkClient(httpClient);

            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ctx.getString(R.string.api_endpoint))
                    .setClient(client)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor(getApiHeaders(ctx))
                    .build();

            sFlickrService = adapter.create(FlickrApiInterface.class);
        }

        return sFlickrService;
    }


    public static class ApiHeaders implements RequestInterceptor {
        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        private String apiKey;
        private String userAgent;

        public ApiHeaders(Context ctx) {

            userAgent = new WebView(ctx).getSettings().getUserAgentString();
            apiKey = ctx.getString(R.string.api_key);
        }

        public String getUserAgent() {
            return userAgent;
        }

        @Override
        public void intercept(RequestFacade request) {

            if (getUserAgent() != null) {
                request.addHeader("User-Agent", getUserAgent());
                request.addQueryParam("api_key", getApiKey());
            }

        }
    }
}
