package dungeonDemolition.objects.entities;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.objects.weapons.RocketLauncher;
import dungeonDemolition.objects.weapons.Weapon;
import dungeonDemolition.objects.weapons.WeaponContainer;
import dungeonDemolition.physics.Collider;
import dungeonDemolition.util.TimeHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.Vector2i;
import dungeonDemolition.util.VectorHelper;
import dungeonDemolition.util.input.Input;
import dungeonDemolition.util.input.InputListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Player extends Entity {

    public WeaponContainer weaponContainer;

    public Player() {

        super("playerMovement", 100);
        maxHealth = 100;
        health = 100;
        weaponContainer = new WeaponContainer(6);

    }

    public void update() {

        super.update();

        if (health > 0) {

            for (int count = 0; count < weaponContainer.weapons.size(); count++)
                if (Input.isKeyPressed(InputListener.getKeyCode(String.valueOf(count + 1)))) weaponContainer.currentWeapon = count;

            weaponContainer.update();

            float speed = super.speed;

            if (Input.isKeyPressed(KeyEvent.VK_SHIFT)) {

                speed *= 2;
                movementAnimation.frameTimeModifier = 0.5f;

            } else movementAnimation.frameTimeModifier = 1;

            Vector2f movedSpace = new Vector2f();

            if (Input.isKeyPressed(KeyEvent.VK_W)) movedSpace.y -= speed * TimeHelper.deltaTime;

            if (Input.isKeyPressed(KeyEvent.VK_S)) movedSpace.y += speed * TimeHelper.deltaTime;

            if (Input.isKeyPressed(KeyEvent.VK_A)) movedSpace.x -= speed * TimeHelper.deltaTime;

            if (Input.isKeyPressed(KeyEvent.VK_D)) movedSpace.x += speed * TimeHelper.deltaTime;


            if (!VectorHelper.areEqual(movedSpace, new Vector2f()))
                movedSpace = Collider.getMovedSpace(this, movedSpace);

            if (VectorHelper.areEqual(movedSpace, new Vector2f())) movementAnimation.update(false);

            else  movementAnimation.update(true);

            position = VectorHelper.sumVectors(new Vector2f[]{position, movedSpace});

            if (!VectorHelper.areEqual(VectorHelper.subtractVectors(Input.mousePosition, VectorHelper.divideVectorByFloat(new Vector2f(ObjectController.display.size), 2)), new Vector2f())) {

                percentRotation = VectorHelper.negateVector(VectorHelper.normalizeVector(VectorHelper.subtractVectors(Input.mousePosition,
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

        if (currentWeapon != null) {

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

        graphics2D.drawImage(movementAnimation.getCurrentFrame(), transform, null);

        if (currentWeapon != null)
            if (currentWeapon instanceof RocketLauncher) graphics2D.drawImage(currentWeapon.texture, transform, null);

    }

}
