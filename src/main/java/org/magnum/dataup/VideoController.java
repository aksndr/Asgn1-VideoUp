/*
 * 
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.magnum.dataup;

import org.magnum.dataup.model.Video;
import org.magnum.dataup.model.VideoStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class VideoController {

    private Collection<Video> videoList;
    private AtomicLong counter;
    private VideoFileManager videoFileManager;

    @PostConstruct
    public void init() throws IOException {
        videoList = new CopyOnWriteArrayList<Video>();
        counter = new AtomicLong(10);
        videoFileManager = VideoFileManager.get();
//        putTestVideo();
    }


    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.GET)
    @ResponseBody
    public Collection<Video> getVideoList(HttpServletResponse response) {
        // Maybe you want to set the status code with the response
        // or write some binary data to an OutputStream obtained from
        // the HttpServletResponse object
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        return videoList;
    }

    @RequestMapping(value = VideoSvcApi.VIDEO_SVC_PATH, method = RequestMethod.POST)
    @ResponseBody
    public Video addVideo(@RequestBody Video v) {
        Long id = counter.incrementAndGet();
        v.setId(id);
        v.setDataUrl(getVideoUrl(id));
        videoList.add(v);
        return v;
    }


    @Multipart
    @POST(VideoSvcApi.VIDEO_DATA_PATH)
    public VideoStatus setVideoData(@PathVariable("id") Long id,
                                    @Part(VideoSvcApi.DATA_PARAMETER) TypedFile videoData) {

//        videoList.contains()


        VideoStatus videoStatus = new VideoStatus(VideoStatus.VideoState.PROCESSING);


        return videoStatus;
    }


    private String getVideoUrl(Long id) {
        return String.format("http://localhost:8080/video/%s/data", id.toString());
    }

    private void saveSomeVideo(Video v, MultipartFile videoData) throws IOException {
        videoFileManager.saveVideoData(v, videoData.getInputStream());
    }

}
