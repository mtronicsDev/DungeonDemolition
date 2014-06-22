package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.dungeons.DungeonTile;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.objects.gui.GUITitle;
import dungeonDemolition.objects.weapons.RocketLauncher;
import dungeonDemolition.objects.weapons.Weapon;
import dungeonDemolition.objects.weapons.*;
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
    public GUIRectangle[] inventoryIconBar;
    public List<GUIText> informationTexts = new ArrayList<GUIText>();
    public List<GUITitle> lootSlots = new ArrayList<GUITitle>();
    public int smallMargin = 7;
    public int largeMargin = 14;
    public int healthKits = 0;

    public Player() {

        super("playerMovement", "explosion", 100, 0);
        maxHealth = 100;
        health = 100;
        inventory = new Inventory();

        heartBar = new GUIRectangle[10];

        for (int count = 0; count < 20; count++)
            lootSlots.add(null);
        inventoryBar = new GUIRectangle[6];
        inventoryIconBar = new GUIRectangle[6];

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
                    (int) (ObjectController.display.size.x / 2 - 3 * 70 - 2.5 * smallMargin + i * 70 + i * smallMargin),
                    ObjectController.display.size.y - 70 - 20),
                    "inventory/slot"
            );

            ObjectController.guiPanels.get("inGame").guiElements.add(inventoryBar[i]);
        }

        for (int i = 0; i < inventoryIconBar.length; i++) {
            inventoryIconBar[i] = new GUIRectangle(new Vector2i(
                    (int) (ObjectController.display.size.x / 2 - 3 * 70 - 2.5 * smallMargin + i * 70 + i * smallMargin) + 3,
                    ObjectController.display.size.y - 67 - 20),
                    "inventory/empty"
            );

            ObjectController.guiPanels.get("inGame").guiElements.add(inventoryIconBar[i]);
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

            if (healthKits > 0) {

                if (health <= 30) informationTexts.add(new GUIText(new Vector2i(ObjectController.display.size.x / 2 - 35, ObjectController.display.size.y - 200), Color.blue, 20, "Q: Heal"));

                if (health < maxHealth && InputInformation.isKeyDown(KeyEvent.VK_Q)) {

                    if (health + 50 <= maxHealth) health += 50;

                    else health = maxHealth;

                    healthKits--;

                }

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
        else {
            if (!deathAnimation.oneLoopPassed) texture = deathAnimation.getCurrentFrame();
            ObjectController.guiPanels.get("inGame").guiElements.add(new GUITitle(
                    Color.red,
                    "Game Over",
                    5
            ));
        }

        if (texture != null) graphics2D.drawImage(texture, transform, null);

        if (currentWeapon != null && health > 0)
            if (currentWeapon instanceof RocketLauncher) graphics2D.drawImage(currentWeapon.texture, transform, null);

        for (int i = heartBar.length - 1; i >= 0; i--) {

            if (i * 10 >= health) ObjectController.guiPanels.get("inGame").guiElements.remove(heartBar[i]);
            else if (!ObjectController.guiPanels.get("inGame").guiElements.contains(heartBar[i]))
                ObjectController.guiPanels.get("inGame").guiElements.add(heartBar[i]);

        }

        for (int i = 0; i < heartBar.length; i++) {
            if (i < heartBar.length / 2)
                heartBar[i].position = new Vector2i(
                        largeMargin + i * 27 + i * 6,
                        ObjectController.display.size.y - 54 - largeMargin - 20);
            else
                heartBar[i].position = new Vector2i(
                        largeMargin + (i - heartBar.length / 2) * 27 + (i - heartBar.length / 2) * 6,
                        ObjectController.display.size.y - 27 - 20);
        }

        for (int i = 0; i < inventoryBar.length; i++) {
            inventoryBar[i].position = new Vector2i(
                    (int) (ObjectController.display.size.x / 2 - 3 * 70 - 2.5 * smallMargin + i * 70 + i * smallMargin),
                    ObjectController.display.size.y - 70 - 20);
        }

        for (GUITitle lootSlot : lootSlots) if(lootSlot != null) lootSlot.render(graphics);

        for (int i = 0; i < inventory.maxWeaponCount; i++) {

            String iconName = "inventory/empty";
            if(inventory.weapons.size() > i) {
                if (inventory.weapons.get(i) instanceof Pistol) iconName = "inventory/gun";
                else if (inventory.weapons.get(i) instanceof Shotgun) iconName = "inventory/shotgun";
                else if (inventory.weapons.get(i) instanceof MachineGun) iconName = "inventory/mg";
                else if (inventory.weapons.get(i) instanceof RocketLauncher) iconName = "inventory/rpg";
            }

            inventoryIconBar[i].position = new Vector2i(
                    (int) (ObjectController.display.size.x / 2 - 3 * 70 - 2.5 * smallMargin + i * 70 + i * smallMargin) + 3,
                    ObjectController.display.size.y - 87);

            inventoryIconBar[i].texture = TextureHelper.loadImage(iconName);

        }

        inventory.render(graphics);

        for (GUIText guiText : informationTexts)
            guiText.render(graphics);

    }

    public void addTitle(String message) {

        for (int i = 0; i < lootSlots.size(); i++)
            if (lootSlots.get(i) == null) {

                lootSlots.set(i, new GUITitle(
                        new Vector2i(ObjectController.display.size.x / 2 - message.toCharArray().length * 5, ObjectController.display.size.y / 2 + i * 20),
                        Color.blue, 20, message, 3)
                );

                break;

            }

    }

    public void deleteTitle(GUITitle title) {

        lootSlots.set(lootSlots.indexOf(title), null);

    }

}