package dungeonDemolition.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StopWatch {

    private long startTime;
    private long passedTime;
    private boolean running = false;

    public void start() {
        running = true;
        startTime = TimeHelper.getCurrentTime();
    }

    public void update() {
        if(running) passedTime = TimeHelper.getCurrentTime() - startTime;
    }

    public void stop() { running = false; }

    public long getPassedTime() {
        return passedTime;
    }

    public String getFormattedPassedTime() {
        DateFormat formatter = new SimpleDateFormat("mm:ss:S");
        Date date = new Date(passedTime);

        return formatter.format(date);
    }

}
