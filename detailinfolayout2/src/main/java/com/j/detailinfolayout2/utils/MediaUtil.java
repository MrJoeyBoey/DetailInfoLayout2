package com.j.detailinfolayout2.utils;

import android.text.TextUtils;

import com.j.mediaview.beans.Media;
import com.j.mediaview.beans.MediaType;
import com.j.mediaview.beans.Source;

import java.util.ArrayList;
import java.util.List;

public class MediaUtil {
    public static List<Media> asMedia(String description, String urls){
        List<Media> medias = new ArrayList<>();
        if (urls != null) {
            for (String mediaUrl : urls.split(";")) {
                if (!TextUtils.isEmpty(mediaUrl)) {
                    String fileExtension = FileUtil.getFileExtension(mediaUrl);
                    if (fileExtension.equals(".jpg")) {
                        Media media = new Media(
                                MediaType.PICTURE,
                                Source.NETWORK,
                                WebServiceUtil.PICTURE_PREFIX_URL + mediaUrl,
                                description
                        );
                        medias.add(media);
                    }
                    if (fileExtension.equals(".mp4")) {
                        Media media = new Media(
                                MediaType.VIDEO,
                                Source.NETWORK,
                                WebServiceUtil.VIDEO_PREFIX_URL + mediaUrl,
                                description
                        );
                        medias.add(media);
                    }
                }
            }
        }
        return medias;
    }
}