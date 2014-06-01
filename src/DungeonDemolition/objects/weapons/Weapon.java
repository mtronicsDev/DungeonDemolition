package dungeonDemolition.objects.weapons;

import dungeonDemolition.util.Input;
import dungeonDemolition.util.TextureHelper;
import dungeonDemolition.util.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class Weapon {

    public BufferedImage texture;
    public int maxRemainingAmmoCount;
    public int remainingAmmoCount;
    public int maxCurrentAmmoCount;
    public int currentAmmoCount;
    public Timer shootBreakTimer;
    public Timer reloadTimer;
    public boolean reloading = false;
    public boolean neededToBeReloaded = false;

    public Weapon(String textureName, float shootBreakTime, float reloadTime) {

        texture = TextureHelper.loadImage(textureName);

        currentAmmoCount = 0;

        shootBreakTimer = new Timer(shootBreakTime);
        reloadTimer = new Timer(reloadTime);

    }

    public void update() {

        if (reloading) {

            reloadTimer.update();

            if (reloadTimer.hasFinished()) reloading = false;

        } else {

            if (currentAmmoCount == 0) reload();

            if (currentAmmoCount <= (float) (maxCurrentAmmoCount / 4)) {

                neededToBeReloaded = true;

                if (Input.isKeyPressed(KeyEvent.VK_R)) reload();

            }

            else neededToBeReloaded = false;

            shootBreakTimer.update();

            if (shootBreakTimer.hasFinished()) {



                if (Input.isButtonPressed(MouseEvent.BUTTON1)) {

                    shoot();
                    shootBreakTimer.restart();

                }

            }

        }

    }

    public abstract void shoot();

    public void reload() {

        reloading = true;
        reloadTimer.restart();



    }

}
