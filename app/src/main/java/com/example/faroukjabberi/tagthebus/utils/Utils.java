package com.example.faroukjabberi.tagthebus.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.faroukjabberi.tagthebus.R;
import com.example.faroukjabberi.tagthebus.events.PictureValidationEvent;
import com.example.faroukjabberi.tagthebus.models.Station;
import com.example.faroukjabberi.tagthebus.models.StationPicture;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static com.example.faroukjabberi.tagthebus.utils.Constants.IMAGE_DIRECTORY_NAME;

/**
 * Created by farouk.jabberi on 09/12/2017.
 */

public class Utils {


    private static Uri fileUri;

    public static void replaceFragment(Activity activity, Fragment fragment) {
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit();
    }

    public static void navigate(Context context, Class<?> destination, Station station, StationPicture stationPicture) {
        Intent intent = new Intent(context, destination);
        if (station != null) {
            intent.putExtra(Constants.STATION, station);
        }
        if (stationPicture != null) {
            intent.putExtra(Constants.STATION_PICTURE, stationPicture);
        }
        context.startActivity(intent);
    }


    /**
     * Capturing Camera Image will lauch camera app requrest image capture
     */
    public static void captureImage(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        Log.e("fileUri", getOutputMediaFileUri(MEDIA_TYPE_IMAGE).getPath());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        activity.startActivityForResult(intent, Constants.CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Creating file uri to store image/video
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }


    public static void previewCapturedImage(final Activity activity, final Realm realm, final int StationId) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        //inflate the view
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_picture_preview, null);
        dialogBuilder.setView(dialogView);
        final ImageView image = (ImageView) dialogView.findViewById(R.id.picture);
        final EditText title = (EditText) dialogView.findViewById(R.id.title);
        final Button cancel = (Button) dialogView.findViewById(R.id.cancel);
        final Button validate = (Button) dialogView.findViewById(R.id.validate);
        dialogBuilder.setTitle(R.string.picture_preview);
        final AlertDialog b = dialogBuilder.create();
        // load picture
        image.setImageBitmap(getBitmap());
        // listner for  cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide  the alert dialog
                b.dismiss();
            }
        });
        // listner for  validate button
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!title.getText().toString().equals(activity.getString(R.string.nothing))) {
                    // save  picture in data base
                    Uri pictureUri = Uri.fromFile(new File(fileUri.getPath()));
                    Log.e("fileUriRealm", fileUri.getPath());
                    // save picture and details in realm data base************
                    Date currentTime = Calendar.getInstance().getTime();
                    addStationPicture(realm, StationId, getDate(currentTime), String.valueOf(pictureUri), title.getText().toString());
                    //***************************************************************
                    // send  event  to StationPictureActivity in the purpose to refresh the  pictures list
                    EventBus.getDefault().postSticky(new PictureValidationEvent(true));
                    Log.e("realmobject", realm.where(StationPicture.class).findAll().toString());
                    // hide  the alert dialog
                    b.dismiss();
                }else{
                    Toast.makeText(activity,"Add title please",Toast.LENGTH_SHORT).show();
                }
            }
        });


        b.show();

    }

    private static Bitmap getBitmap() {
        // bimatp factory
        BitmapFactory.Options options = new BitmapFactory.Options();
        // downsizing image as it throws OutOfMemory Exception for larger
        // images
        options.inSampleSize = 8;
        Log.e("fileUriPreview", fileUri.getPath());
        final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                options);

        return bitmap;
    }

    // save  picture in realm data base
    public static void addStationPicture(Realm realm, int id, String date, String picture, String title) {
        realm.beginTransaction();
        StationPicture stationPicure = realm.createObject(StationPicture.class);
        stationPicure.setId(id);
        stationPicure.setPicture(picture);
        stationPicure.setDate(date);
        stationPicure.setTitle(title);
        realm.commitTransaction();
    }

    // get date in "dd/MM/yyyy" format
   public  static  String  getDate(Date date) {
       // Display a date in day, month, year format
       DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
       String today = formatter.format(date);
       return today;
   }
}
