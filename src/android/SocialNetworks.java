package com.petru224.socialnetworks;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

public class SocialNetworks extends CordovaPlugin {


    // The callback context used when calling back into JavaScript
    private CallbackContext command;

    @Override
    public boolean execute (String action, JSONArray args,
                            CallbackContext callback) throws JSONException {

        this.command = callback;

        if ("open".equals(action)) {
            open(args);

            return true;
        }

        return false;
    }


    public static Intent getOpenFacebookIntent(Context context,String uri,String url) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(uri));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url));
        }
    }

    public static Intent getOpenTwitterIntent(Context context,String uri,String url) {
        try {
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            return  new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        } catch (Exception e) {
            return  new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        }
    }

    public static Intent newInstagramProfileIntent(Context context, String uri,String url) {
        PackageManager pm = context.getPackageManager();
        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                if (uri.endsWith("/")) {
                    uri = uri.substring(0, uri.length() - 1);
                }
                final String username = uri.substring(uri.lastIndexOf("/") + 1);
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
                return intent;
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        intent.setData(Uri.parse(url));
        return intent;
    }

    public static Intent getOpenSnapchatIntent(Context context,String uri,String url) {
        try {
            if (uri.endsWith("/")) {
                uri = uri.substring(0, uri.length() - 1);
            }
            final String username = uri.substring(uri.lastIndexOf("/") + 1);

            context.getPackageManager().getPackageInfo("com.snapchat.android", 0);
            return  new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/"+username)).setPackage("com.snapchat.android");
        } catch (Exception e) {
            return  new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        }
    }


    private void open (JSONArray args) throws JSONException {
        JSONObject properties = args.getJSONObject(0);
        String app = properties.getString("app");
        String uri = properties.getString("uri");
        String url = properties.getString("url");
        Context context=this.cordova.getActivity().getApplicationContext();
        Intent intent = null;
        if(app.equals("facebook"))
            intent = getOpenFacebookIntent(context,uri,url);
        else if(app.equals("twitter"))
            intent = getOpenTwitterIntent(context,uri,url);
        else if(app.equals("instagram"))
            intent = newInstagramProfileIntent(context,uri,url);
        else if(app.equals("snapchat"))
            intent = getOpenSnapchatIntent(context,uri,url);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

  
}






