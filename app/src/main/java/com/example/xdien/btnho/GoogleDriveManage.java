package com.example.xdien.btnho;

import android.util.Log;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;

/**
 * Created by xdien on 3/6/16.
 */
public class GoogleDriveManage {
    final private ResultCallback<DriveResource.MetadataResult> metadataRetrievedCallback = new
            ResultCallback<DriveResource.MetadataResult>() {
                @Override
                public void onResult(DriveResource.MetadataResult result) {
                    if (!result.getStatus().isSuccess()) {
                        Log.v("onResult", "Problem while trying to fetch metadata.");
                        return;
                    }

                    Metadata metadata = result.getMetadata();
                    if(metadata.isTrashed()){
                        Log.v("onResult", "Folder is trashed");
                    }else{
                        Log.v("onResult", "Folder is not trashed");
                    }

                }
            };
}
