package com.RNFetchBlob.Utils;

import android.util.Base64;
import com.RNFetchBlob.RNFetchBlobFS;

import com.RNFetchBlob.Tailer.TailerListenerAdapter;

public class FsListener extends TailerListenerAdapter {
    private RNFetchBlobFS fs;
    private String streamId;

    public FsListener(RNFetchBlobFS fs, String streamId) {
        super();
        this.fs = fs;
        this.streamId = streamId;
    }

    public void handle(byte[] buffer) {
        // copy to a readable byte array
        byte [] copy = new byte[buffer.length];
        for(int i =0; i < buffer.length; i++) {
            copy[i] = buffer[i];
        }

        // send the base64 encoded data to the JS context
        this.fs.emitStreamEvent(this.streamId, "data", Base64.encodeToString(copy, Base64.NO_WRAP));
    }
}