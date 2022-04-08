package com.gwu.backend.Service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.gwu.backend.Model.Health;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


@Service
public class HealthService {

    public String getFromGoogle(Health health) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("https://www.googleapis.com/fitness/v1/users/me/dataset:aggregate");
        httppost.addHeader("Content-Type", "application/json");
        httppost.addHeader("Authorization",
                "Bearer " + health.getToken());

        // GET Today's date
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        Calendar endcal = Calendar.getInstance();
        endcal.set(Calendar.HOUR, 0);
        endcal.set(Calendar.MINUTE, 0);
        endcal.set(Calendar.SECOND, 0);
        // System.out.println("Format Time Now: "+simpleformat.format(now.getTime()));
        endcal.set(Calendar.HOUR_OF_DAY, 0);
        long todayMidNightTime = endcal.getTimeInMillis();
        Date startdate = new Date(todayMidNightTime - (1 * DAY_IN_MS));
        long starttime = startdate.getTime();
        long endtime = System.currentTimeMillis();
        System.out.println("Start time : "+starttime);
        System.out.println("End time : "+endtime);

        // Request parameters and other properties.
        httppost.setEntity(new StringEntity(" {\r\n" + "  \"aggregateBy\": [{\r\n" + "    \"dataSourceId\":\r\n"
                + "      \"derived:com.google.step_count.delta:com.google.android.gms:estimated_steps\"\r\n"
                + "  }],\r\n" + "  \"bucketByTime\": { \"durationMillis\": 86400000 },\r\n" + "  \"startTimeMillis\": "
                + starttime + ",\r\n" + "  \"endTimeMillis\": " + endtime + "\r\n" + "}"));

        // Execute and get the response.
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null) {
            try (InputStream instream = entity.getContent()) {
                // do something useful
                StringWriter writer = new StringWriter();
                Scanner s = new Scanner(instream).useDelimiter("\\A");
                result = s.hasNext() ? s.next() : "";
            }
            System.out.println(result);
            FileWriter fw = new FileWriter(
                    "jsonToday.json");
            fw.write(result);
            System.out.println("Written json result to file");
            fw.close();
        }

        return result;

    }
}
