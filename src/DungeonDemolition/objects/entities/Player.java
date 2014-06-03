package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.objects.weapons.RocketLauncher;
import dungeonDemolition.objects.weapons.Weapon;
import dungeonDemolition.objects.weapons.WeaponContainer;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.Vector2i;
import dungeonDemolition.util.VectorHelper;
import dungeonDemolition.util.InputInformation;
import dungeonDemolition.util.InputListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public WeaponContainer weaponContainer;
    public GUIRectangle[] heartBar;

    public Player() {

        super("playerMovement", "explosion", 100);
        maxHealth = 100;
        health = 100;
        weaponContainer = new WeaponContainer(6);

        heartBar = new GUIRectangle[10];

        for (int i = 0; i < heartBar.length; i++) {
            heartBar[i] = new GUIRectangle(new Vector2i(
                    (int)(ObjectController.display.size.x / 2 - 5 * 27 - 4.5 * 6 + i * 27 + i * 6),
                    ObjectController.display.size.y - 27 - 20),
                    "heart");
            ObjectController.guiPanels.get("inGame").guiElements.add(heartBar[i]);
        }

    }

    public void update() {

        super.update();

        if (health > 0) {

            for (int count = 0; count < weaponContainer.weapons.size(); count++)
                if (InputInformation.isKeyPressed(InputListener.getKeyCode(String.valueOf(count + 1)))) weaponContainer.currentWeapon = count;

            weaponContainer.update();

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

        }

    }

    public void render(Graphics graphics) {

        AffineTransform transform = new AffineTransform();
        transform.translate(ObjectController.display.size.x / 2, ObjectController.display.size.y / 2);
        transform.rotate(rotation);
        transform.translate(-20, -20);

        Graphics2D graphics2D = (Graphics2D) graphics;

        Weapon currentWeapon = weaponContainer.getCurrentWeapon();

        if (currentWeapon != null && health > 0) {

            if (!(currentWeapon instanceof RocketLauncher)) graphics2D.drawImage(currentWeapon.texture, transform, null);

            if (currentWeapon.neededToBeReloaded)
                new GUIText(new Vector2i(ObjectController.display.size.x / 2 - 50, ObjectController.display.size.y - 100),
                        Color.blue, 15,
                        "R: reload").render(graphics);

            new GUIText(new Vector2i(ObjectController.display.size.x - 100, ObjectController.display.size.y - 100),
                    Color.blue, 15,
                    currentWeapon.currentAmmoCount + " / " + currentWeapon.maxCurrentAmmoCount).render(graphics);

            new GUIText(new Vector2i(ObjectController.display.size.x - 100, ObjectController.display.size.y - 80),
                    Color.blue, 15,
                    currentWeapon.remainingAmmoCount + " / " + currentWeapon.maxRemainingAmmoCount).render(graphics);

        }

        BufferedImage texture = null;

        if (health > 0) texture = movementAnimation.getCurrentFrame();

        else if (!deathAnimation.oneLoopPassed) texture = deathAnimation.getCurrentFrame();

        if (texture != null) graphics2D.drawImage(texture, transform, null);

        if (currentWeapon != null && health > 0)
            if (currentWeapon instanceof RocketLauncher) graphics2D.drawImage(currentWeapon.texture, transform, null);


        for (int i = heartBar.length - 1; i >= 0; i--) {

            if(i * 10 > health) ObjectController.guiPanels.get("inGame").guiElements.remove(heartBar[i]);
            else if(!ObjectController.guiPanels.get("inGame").guiElements.contains(heartBar[i]))
                ObjectController.guiPanels.get("inGame").guiElements.add(heartBar[i]);

        }

    }

}