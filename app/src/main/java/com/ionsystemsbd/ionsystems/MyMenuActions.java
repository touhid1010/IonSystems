package com.ionsystemsbd.ionsystems;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.Html;
import android.widget.Toast;


public class MyMenuActions extends Activity {

    /**
     * <p>Intent to open feedback app.</p>
     *
     * <p>Example usage:</p>
     *
     * {@code Intent intent = MenuAction.MenuFeedback(this);
     *          startActivity(Intent.createChooser(intent, "Send Feedback:"));;
     *
     *
     *
     *
     * @return An intent that will open app list to throw feedback
     */
    public static Intent MenuFeedback() {
        return new Intent(Intent.ACTION_SEND)
                .setType("text/email")
                .putExtra(Intent.EXTRA_EMAIL, new String[]{"touhidapps@gmail.com"})
                .putExtra(Intent.EXTRA_SUBJECT, "App Feedback (" + R.string.app_name + ")")
                .putExtra(Intent.EXTRA_TEXT, "Hello Touhid," + "");

    }

    /**
     * <p>Intent to open the google play app. If the google play app is not installed then toast
     * message will appear.</p>
     *
     * <p>Example usage:</p>
     *
     * {@code Intent intent = MenuAction.MenuRate(this);
     *          try {
     *              startActivity(intent);
     *          } catch (ActivityNotFoundException e) {
     *              Toast.makeText(this, "Google play not installed on your device.", Toast.LENGTH_LONG).show();
     *          }}
     *
     * @param context
     *     application context is: this
     *
     * @return An intent that will open google play of this app
     */
    public static Intent MenuRate(Context context) {

        Uri uri = Uri.parse("market://details?id=" + new ContextWrapper(context).getPackageName()); // play store of this app link
        return new Intent(Intent.ACTION_VIEW, uri);

    }

    /**
     * <p>Intent to open the app list to share.</p>
     *
     * <p>Example usage:</p>
     *
     * {@code Intent intent = MenuAction.MenuShare(this);
     *          startActivity(intent);;
     *
     *
     * @param context
     *     application context is: this
     *
     * @return An intent that will open the app list to share
     */
    public static Intent MenuShare(Context context) {

        return new Intent(android.content.Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject test")
                .putExtra(android.content.Intent.EXTRA_TEXT, "market://details?id=" + new ContextWrapper(context).getPackageName()); // play store this app link

    }

    /**
     * <p>Intent to open the google play app. If the google play app is not installed then toast
     * message show.</p>
     *
     * <p>Example usage:</p>
     *
     * {@code Intent intent = MenuAction.MenuMoreApps();
     *          try {
     *              startActivity(intent);
     *          } catch (ActivityNotFoundException e) {
     *              Toast.makeText(this, "Google play not installed on your device.", Toast.LENGTH_LONG).show();
     *          }}
     *
     *          Use try catch exception if the app is not installed then a message will show.
     *
     *
     * @return An intent that will open the google play store
     */
    public static Intent MenuMoreApps() {

        return new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:\"Touhid Apps!\""));

    }

    /**
     * <p>Intent to open the about me AlertDialog</p>
     *
     * <p>Example usage:</p>
     *
     * {@code AlertDialog alertDialog = MenuAction.MenuAboutMe(this);
     *          alertDialog.show();
     *          ((TextView)alertDialog.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());} // this line will make link clickable
     *
     * @param context
     *     application context is: this
     *
     * @return An AlertDialog that will open the about me info
     */
    public static AlertDialog MenuAboutMe(Context context) {

        return new AlertDialog.Builder(context)
                .setTitle("Ion Systems App")
                .setIcon(R.mipmap.ic_launcher)
                .setMessage(Html.fromHtml("<span style=\"color:red\">Developed by: Touhid Apps!</span>" +
                        "<br/><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; www.touhidapps.com</span>" +
                        "<br/> "))
                .setCancelable(true)
                .setPositiveButton("Ok", null) // no need to onClickListener so null used
                .create();

    }

    /**
     * MyMenuActions.MenuExit(this).show();
     * @param context
     * @return
     */

    public static AlertDialog MenuExit(Context context) {
        return new AlertDialog.Builder(context)
                .setMessage("Want to exit?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity a = new Activity();
                        a.finish(); // finish current activity
                        System.exit(0);
                    }
                }).create();

    }


    /**
     * <p>Intent to open the official Facebook app. If the Facebook app is not installed then the
     * default web browser will be used.</p>
     *
     * <p>Example usage:</p>
     *
     * {@code newFacebookIntent(ctx.getPackageManager(), "https://www.facebook.com/TouhidApps");}
     *
     * @param pm
     *     The {@link PackageManager}. You can find this class through {@link
    //*     Context#getPackageManager()}.
     * @param url
     *     The full URL to the Facebook page or profile.
     * @return An intent that will open the Facebook page/profile.
     */
    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // Source: http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {

        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

}
