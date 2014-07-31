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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class VideoController {

	/**
	 * You will need to create one or more Spring controllers to fulfill the
	 * requirements of the assignment. If you use this file, please rename it
	 * to something other than "AnEmptyController"
	 * 
	 *
     *	 ________  ________  ________  ________          ___       ___  ___  ________  ___  __
     *	|\   ____\|\   __  \|\   __  \|\   ___ \        |\  \     |\  \|\  \|\   ____\|\  \|\  \
     *	\ \  \___|\ \  \|\  \ \  \|\  \ \  \_|\ \       \ \  \    \ \  \\\  \ \  \___|\ \  \/  /|_
     *	 \ \  \  __\ \  \\\  \ \  \\\  \ \  \ \\ \       \ \  \    \ \  \\\  \ \  \    \ \   ___  \
     *	  \ \  \|\  \ \  \\\  \ \  \\\  \ \  \_\\ \       \ \  \____\ \  \\\  \ \  \____\ \  \\ \  \
     *	   \ \_______\ \_______\ \_______\ \_______\       \ \_______\ \_______\ \_______\ \__\\ \__\
     *	    \|_______|\|_______|\|_______|\|_______|        \|_______|\|_______|\|_______|\|__| \|__|
     *
	 * 
	 */


    @RequestMapping(VideoSvcApi.VIDEO_SVC_PATH)
    @ResponseBody
    public  Collection<Video> getVideoList(
            HttpServletResponse response) {
        Collection<Video> videoList = new CopyOnWriteArrayList<Video>();

        Video video = Video.create().withContentType("video/mp4")
                .withDuration(123).withSubject(UUID.randomUUID().toString())
                .withTitle(UUID.randomUUID().toString()).build();
        video.setId(12341234L);

        Video video2 = Video.create().withContentType("video/mp4")
                .withDuration(1223).withSubject(UUID.randomUUID().toString())
                .withTitle(UUID.randomUUID().toString()).build();
        video.setId(123433334L);
        videoList.add(video);
        videoList.add(video2);
        // Maybe you want to set the status code with the response
        // or write some binary data to an OutputStream obtained from
        // the HttpServletResponse object
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        return videoList;
    }


}
