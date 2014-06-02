package dungeonDemolition.objects.gui;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.util.MathHelper;
import dungeonDemolition.util.Vector2i;

import java.awt.*;

public class GUIHealthBar extends GUIElement {

    private GUIRectangle maxHealthBar;
    private GUIRectangle healthBar;
    public Entity entity;

    public GUIHealthBar(Entity entity) {
        super(new Vector2i((int)entity.position.x, (int)entity.position.y));
        maxHealthBar = new GUIRectangle(position, new Vector2i(40, 4), Color.darkGray, true);
        healthBar = new GUIRectangle(position, new Vector2i((int)(entity.health / entity.maxHealth * 40), 4), Color.green, true);
        this.entity = entity;
    }

    @Override
    public void render(Graphics graphics) {

        maxHealthBar.position = new Vector2i((int) (entity.position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20,
                (int) (entity.position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 24);
        healthBar.position = new Vector2i((int) (entity.position.x - ObjectController.entities.get("player").position.x) + ObjectController.display.size.x / 2 - 20,
                (int) (entity.position.y - ObjectController.entities.get("player").position.y) + ObjectController.display.size.y / 2 - 24);

        healthBar.size.x = MathHelper.clamp((int) (entity.health / entity.maxHealth * 40), 0, 40);

        if(entity.health / entity.maxHealth > 0.75f) healthBar.color = Color.green;
        else if(entity.health / entity.maxHealth > 0.5f) healthBar.color = Color.yellow;
        else if (entity.health / entity.maxHealth > 0.25f) healthBar.color = Color.orange;
        else healthBar.color = Color.red;

        maxHealthBar.render(graphics);
        healthBar.render(graphics);

    }

}
