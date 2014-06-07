package dungeonDemolition.objects.weapons;

import dungeonDemolition.util.InputInformation;
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
    public boolean automaticallyShooting;
    public boolean reloading = false;
    public boolean neededToBeReloaded = false;

    public Weapon(String textureName, float shootBreakTime, float reloadTime) {

        texture = TextureHelper.loadImage(textureName);

        shootBreakTimer = new Timer(shootBreakTime);
        reloadTimer = new Timer(reloadTime);

    }

    public void update() {

        if (reloading) {

            reloadTimer.update();

            if (reloadTimer.hasFinished()) {

                int neededAmmoCount = maxCurrentAmmoCount - currentAmmoCount;

                if (remainingAmmoCount >= neededAmmoCount) {

                    currentAmmoCount += neededAmmoCount;
                    remainingAmmoCount -= neededAmmoCount;

                } else {

                    currentAmmoCount += remainingAmmoCount;
                    remainingAmmoCount = 0;

                }

                reloading = false;

            }

        } else {

            if (currentAmmoCount == 0) {

                reload();
                return;

            }

            if (currentAmmoCount <= (float) (maxCurrentAmmoCount / 4) && remainingAmmoCount != 0)
                neededToBeReloaded = true;

            else neededToBeReloaded = false;

            if (InputInformation.isKeyDown(KeyEvent.VK_R)) {

                reload();
                return;

            }

            shootBreakTimer.update();

            if (shootBreakTimer.hasFinished()) {

                if (automaticallyShooting) {

                    if (InputInformation.isButtonPressed(MouseEvent.BUTTON1)) {

                        shoot();
                        shootBreakTimer.restart();

                    }

                } else {

                    if (InputInformation.isButtonDown(MouseEvent.BUTTON1)) {

                        shoot();
                        shootBreakTimer.restart();

                    }

                }

            }

        }

    }

    public void shoot() {

        currentAmmoCount--;

    }

    public void reload() {

        neededToBeReloaded = false;
        reloading = true;
        reloadTimer.restart();

    }

}
