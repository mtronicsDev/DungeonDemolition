package dungeonDemolition.objects;

import dungeonDemolition.util.Input;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;

import java.awt.event.KeyEvent;

public class Player {

    public Vector2f position;
    public Vector2f rotation;

    public Player(Vector2f position, Vector2f rotation) {

        this.position = position;
        this.rotation = rotation;

    }

    public void update() {

        if (Input.isKeyPressed(KeyEvent.VK_W)) position.y -= 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_S)) position.y += 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_A)) position.x -= 50 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_D)) position.x += 50 * TimeHelper.deltaTime;

        if (position.x < 0) position.x = 0;
        else if (position.x > ObjectController.display.size.x) position.x = ObjectController.display.size.x;

        if (position.y < 0) position.y = 0;
        else if (position.y > ObjectController.display.size.y) position.y = ObjectController.display.size.y;

    }

}
