package dungeonDemolition.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StopWatch {

    private long passedTime = 0;
    private boolean running = false;

    public void start() {

        running = true;

    }

    public void update() {

        if (running) passedTime += (TimeHelper.deltaTime * 1000);

    }

    public void stop() { running = false; }

    public void restart() {

        passedTime = 0;

    }

    public long getPassedTime() {
        return passedTime;
    }

    public String getFormattedPassedTime() {

        DateFormat formatter = new SimpleDateFormat("mm:ss:S");
        Date date = new Date(passedTime);

        return formatter.format(date);

    }

}
