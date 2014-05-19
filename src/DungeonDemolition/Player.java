package DungeonDemolition;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public float[] position;
    public Point rotation;

    public Player(Point position, Point rotation) {

        this.position = new float[] {position.x, position.y};
        this.rotation = rotation;

    }

    public void update() {

        if (Input.isKeyPressed(KeyEvent.VK_W)) position[1] -= 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_S)) position[1] += 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_A)) position[0] -= 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_D)) position[0] += 50 * TimeHelper.deltaTime;

        if (position[0] < 0 ) position[0] = 0;
        else if (position[0] > ObjectController.display.size.x) position[0] = ObjectController.display.size.x;

        if (position[1] < 0 ) position[1] = 0;
        else if (position[1] > ObjectController.display.size.y) position[1] = ObjectController.display.size.y;

    }

}
