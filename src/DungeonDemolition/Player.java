package DungeonDemolition;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public Point position;
    public Point rotation;

    public Player(Point position, Point rotation) {

        this.position = position;
        this.rotation = rotation;

    }

    public void update() {

        if (Input.isKeyPressed(KeyEvent.VK_W)) position.y -= 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_S)) position.y += 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_A)) position.x -= 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_D)) position.x += 50 * TimeHelper.deltaTime;

        if (position.x < 0 ) position.x = 0;
        else if (position.x > ObjectController.display.size.x) position.x = ObjectController.display.size.x;

        if (position.y < 0 ) position.y = 0;
        else if (position.y > ObjectController.display.size.y) position.y = ObjectController.display.size.y;

        System.out.println(position);

    }

}
