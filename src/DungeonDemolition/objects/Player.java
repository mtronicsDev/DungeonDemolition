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

        if (Input.isKeyPressed(KeyEvent.VK_W)) position.y -= 100 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_S)) position.y += 100 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_A)) position.x -= 100 * TimeHelper.deltaTime;
        if (Input.isKeyPressed(KeyEvent.VK_D)) position.x += 100 * TimeHelper.deltaTime;

    }

}
