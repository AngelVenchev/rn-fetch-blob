package com.RNFetchBlob.Utils;

import android.util.Base64;
import com.RNFetchBlob.RNFetchBlobFS;

import org.apache.commons.io.input.TailerListenerAdapter;

public class FsListener extends TailerListenerAdapter {
    private RNFetchBlobFS fs;
    private String streamId;

    public FsListener(RNFetchBlobFS fs, String streamId) {
        super();
        this.fs = fs;
        this.streamId = streamId;
    }

    public void handle(String buffer) {
        // copy to a readable byte array
        byte [] bufferBytes = buffer.getBytes();
        byte [] copy = new byte[bufferBytes.length];
        for(int i =0; i < bufferBytes.length; i++) {
            copy[i] = bufferBytes[i];
        }
        
        // send the base64 encoded data to the JS context
        this.fs.emitStreamEvent(this.streamId, "data", Base64.encodeToString(copy, Base64.NO_WRAP));
    }
}