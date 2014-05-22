package dungeonDemolition.util;

public class Timer {

    public float endTime;
    public boolean running;
    private float currentTime = 0;

    public Timer(float endTime) {

        this.endTime = endTime;
        running = true;

    }

    public boolean hasFinished() {

        return currentTime >= endTime;

    }

    public void update() {

        if (running) currentTime += TimeHelper.deltaTime;

    }

    public void restart() {

        currentTime = 0;
        running = true;

    }

}
