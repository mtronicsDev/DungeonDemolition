package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public List<Weapon> weapons = new ArrayList<Weapon>();
    public int currentWeapon;
    public int weaponCount = 0;
    public int maxWeaponCount = 6;
    public GUIRectangle[] reloadProgress;
    public GUIRectangle healthKitSymbol;
    public GUIText healthKitCount;

    public Inventory(int size) {

        reloadProgress = new GUIRectangle[2];

        reloadProgress[0] = new GUIRectangle(new Vector2i(ObjectController.display.size.x - 100, ObjectController.display.size.y - 60), new Vector2i(60, 20), Color.blue, true);
        reloadProgress[1] = new GUIRectangle(new Vector2i(ObjectController.display.size.x - 100, ObjectController.display.size.y - 60), new Vector2i(60, 20), Color.black, false);

        healthKitSymbol = new GUIRectangle(new Vector2i(210, ObjectController.display.size.y - 73), new Vector2i(30, 30), "inventory/healthKit");
        healthKitCount = new GUIText(new Vector2i(250, ObjectController.display.size.y - 50), Color.red, 20, "");

    }

    public Inventory addWeapon(Weapon weapon) {

        if (weapons.size() < maxWeaponCount) {

            weapons.add(weapon);
            currentWeapon = weapons.size() - 1;

            weaponCount++;

        }

        return this;

    }

    public void update() {

        healthKitCount.text = String.valueOf(((Player) ObjectController.entities.get("player")).healthKits);

        for (int count = 0; count < weapons.size(); count++)
            if (InputInformation.isKeyPressed(InputListener.getKeyCode(String.valueOf(count + 1)))) {

                currentWeapon = count;

                Weapon currentWeapon = getCurrentWeapon();

                if (!(currentWeapon instanceof Shotgun)) currentWeapon.reloadTimer.restart();

            }

        Weapon currentWeapon = getCurrentWeapon();

        if (currentWeapon != null) {

            currentWeapon.update();

            if (currentWeapon.reloading) reloadProgress[0].size.x = (int) (currentWeapon.reloadTimer.currentTime / currentWeapon.reloadTimer.endTime * 60);

            else reloadProgress[0].size.x = 60;

        }

    }

    public void render(Graphics graphics) {

        if (getCurrentWeapon() != null) {

            reloadProgress[0].render(graphics);
            reloadProgress[1].render(graphics);

        }

        healthKitSymbol.render(graphics);
        healthKitCount.render(graphics);

    }

    public Weapon getCurrentWeapon() {

        if (weapons.size() == 0) return null;
        else return weapons.get(currentWeapon);

    }

}
