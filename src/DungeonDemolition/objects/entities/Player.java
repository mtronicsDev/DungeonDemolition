package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.objects.weapons.Inventory;
import dungeonDemolition.objects.weapons.RocketLauncher;
import dungeonDemolition.objects.weapons.Weapon;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    public Inventory inventory;

    public GUIRectangle[] heartBar;
    public GUIRectangle[] inventoryBar;
    public List<GUIText> informationTexts = new ArrayList<GUIText>();
    private int largeMargin = 14;
    private int smallMargin = 7;

    public Player() {

        super("playerMovement", "explosion", 100, 0);
        maxHealth = 100;
        health = 100;
        inventory = new Inventory(6);

        heartBar = new GUIRectangle[10];
        inventoryBar = new GUIRectangle[6];

        for (int i = 0; i < heartBar.length; i++) {
            if (i < heartBar.length / 2)
                heartBar[i] = new GUIRectangle(new Vector2i(
                        largeMargin + i * 27 + i * 6,
                        ObjectController.display.size.y - 27 - 20),
                        "inventory/heart"
                );
            else
                heartBar[i] = new GUIRectangle(new Vector2i(
                        largeMargin + (i - heartBar.length / 2) * 27 + (i - heartBar.length / 2) * 6,
                        ObjectController.display.size.y - 54 - largeMargin - 20),
                        "inventory/heart"
                );

            ObjectController.guiPanels.get("inGame").guiElements.add(heartBar[i]);
        }

        for (int i = 0; i < inventoryBar.length; i++) {
            inventoryBar[i] = new GUIRectangle(new Vector2i(
                    (int) (ObjectController.display.size.x / 2 - 3 * 70 - 2.5 * smallMargin + i * 64 + i * smallMargin),
                    ObjectController.display.size.y - 64 - 20),
                    "inventory/slot"
            );

            ObjectController.guiPanels.get("inGame").guiElements.add(inventoryBar[i]);
        }

    }

    public void update() {

        super.update();

        informationTexts.clear();

        if (health > 0) {

            inventory.update();

            Weapon currentWeapon = inventory.getCurrentWeapon();

            if (currentWeapon != null) {

                if (currentWeapon.neededToBeReloaded)
                    informationTexts.add(new GUIText(new Vector2i(ObjectController.display.size.x / 2 - 50, ObjectController.display.size.y - 100),
                            Color.blue, 20,
                            "R: reload"));

                informationTexts.add(new GUIText(new Vector2i(ObjectController.display.size.x - 100, ObjectController.display.size.y - 100),
                        Color.blue, 20,
                        currentWeapon.currentAmmoCount + " / " + currentWeapon.maxCurrentAmmoCount));

                informationTexts.add(new GUIText(new Vector2i(ObjectController.display.size.x - 100, ObjectController.display.size.y - 80),
                        Color.blue, 20,
                        currentWeapon.remainingAmmoCount + " / " + currentWeapon.maxRemainingAmmoCount));

            }

            float speed = super.speed;

            if (InputInformation.isKeyPressed(KeyEvent.VK_SHIFT)) {

                speed *= 2;
                movementAnimation.frameTimeModifier = 0.5f;

            } else movementAnimation.frameTimeModifier = 1;

            Vector2f movedSpace = new Vector2f();

            if (InputInformation.isKeyPressed(KeyEvent.VK_W)) movedSpace.y -= speed * TimeHelper.deltaTime;

            if (InputInformation.isKeyPressed(KeyEvent.VK_S)) movedSpace.y += speed * TimeHelper.deltaTime;

            if (InputInformation.isKeyPressed(KeyEvent.VK_A)) movedSpace.x -= speed * TimeHelper.deltaTime;

            if (InputInformation.isKeyPressed(KeyEvent.VK_D)) movedSpace.x += speed * TimeHelper.deltaTime;


            if (!VectorHelper.areEqual(movedSpace, new Vector2f()))
                movedSpace = Collider.getMovedSpace(this, movedSpace);

            movementAnimation.update(!VectorHelper.areEqual(movedSpace, new Vector2f()));

            position = VectorHelper.sumVectors(new Vector2f[]{position, movedSpace});

            if (!VectorHelper.areEqual(VectorHelper.subtractVectors(InputInformation.mousePosition, VectorHelper.divideVectorByFloat(new Vector2f(ObjectController.display.size), 2)),
                    new Vector2f())) {

                percentRotation = VectorHelper.negateVector(VectorHelper.normalizeVector(VectorHelper.subtractVectors(InputInformation.mousePosition,
                        VectorHelper.divideVectorByFloat(new Vector2f(ObjectController.display.size), 2))));

                if (VectorHelper.getScalarProduct(percentRotation, new Vector2f(1, 0)) <= 0)
                    rotation = VectorHelper.getAngle(percentRotation, new Vector2f(0, 1));

                else rotation = -VectorHelper.getAngle(percentRotation, new Vector2f(0, 1));

            }

            for (DungeonTile dungeonTile : ObjectController.dungeonMaps.get(ObjectController.currentDungeonMap).dungeonTiles)
                if (dungeonTile.interactable)
                    if (VectorHelper.getLength(VectorHelper.subtractVectors(position, dungeonTile.position)) <= 70) {

                        informationTexts.add(new GUIText(new Vector2i(ObjectController.display.size.x / 2 - 50, ObjectController.display.size.y - 150),
                                Color.blue, 20,
                                "E: interact"));

                        if (InputInformation.isKeyDown(KeyEvent.VK_E))
                            dungeonTile.interactionMethod.interact(this);

                    }

        }

    }

    public void render(Graphics graphics) {

        AffineTransform transform = new AffineTransform();
        transform.translate(ObjectController.display.size.x / 2, ObjectController.display.size.y / 2);
        transform.rotate(rotation);
        transform.translate(-20, -20);

        Graphics2D graphics2D = (Graphics2D) graphics;

        Weapon currentWeapon = inventory.getCurrentWeapon();

        if (currentWeapon != null && health > 0) {

            if (!(currentWeapon instanceof RocketLauncher))
                graphics2D.drawImage(currentWeapon.texture, transform, null);

        }

        BufferedImage texture = null;

        if (health > 0) texture = movementAnimation.getCurrentFrame();

        else if (!deathAnimation.oneLoopPassed) texture = deathAnimation.getCurrentFrame();

        if (texture != null) graphics2D.drawImage(texture, transform, null);

        if (currentWeapon != null && health > 0)
            if (currentWeapon instanceof RocketLauncher) graphics2D.drawImage(currentWeapon.texture, transform, null);

        for (int i = heartBar.length - 1; i >= 0; i--) {

            if (i * 10 >= health) ObjectController.guiPanels.get("inGame").guiElements.remove(heartBar[i]);
            else if (!ObjectController.guiPanels.get("inGame").guiElements.contains(heartBar[i]))
                ObjectController.guiPanels.get("inGame").guiElements.add(heartBar[i]);

        }

        for (GUIText guiText : informationTexts)
            guiText.render(graphics);

    }

}