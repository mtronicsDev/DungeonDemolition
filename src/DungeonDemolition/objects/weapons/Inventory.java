package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.util.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public List<Weapon> weapons = new ArrayList<Weapon>();
    public int currentWeapon;
    public int weaponCount = 0;
    public int maxWeaponCount = 6;
    public GUIRectangle[] inventoryBar;
    public List<GUIRectangle> renderWeapons = new ArrayList<GUIRectangle>();
    public GUIRectangle currentWeaponMarker;
    public GUIRectangle[] reloadProgress;

    public Inventory() {

        inventoryBar = new GUIRectangle[6];

        for (int i = 0; i < inventoryBar.length; i++)
            inventoryBar[i] = new GUIRectangle(new Vector2i(
                    (int) (ObjectController.display.size.x / 2 - 3 * 70 - 2.5 * 7 + i * 64 + i * 7),
                    ObjectController.display.size.y - 64 - 20),
                    "inventory/slot"
            );

        reloadProgress = new GUIRectangle[2];

        reloadProgress[0] = new GUIRectangle(new Vector2i(ObjectController.display.size.x - 100, ObjectController.display.size.y - 60), new Vector2i(60, 20), Color.blue, true);
        reloadProgress[1] = new GUIRectangle(new Vector2i(ObjectController.display.size.x - 100, ObjectController.display.size.y - 60), new Vector2i(60, 20), Color.black, false);

    }

    public Inventory addWeapon(Weapon weapon) {

        if (weapons.size() < maxWeaponCount) {

            weapons.add(weapon);
            currentWeapon = weapons.size() - 1;

            if (currentWeaponMarker == null) currentWeaponMarker = new GUIRectangle(inventoryBar[currentWeapon].position, new Vector2i(70, 70), Color.white, false);

            else currentWeaponMarker.position = inventoryBar[currentWeapon].position;



            String weaponName = "inventory/";

            if (weapon instanceof RocketLauncher) weaponName += "rpg";

            else if (weapon instanceof MachineGun) weaponName += "mg";

            else if (weapon instanceof Shotgun) weaponName += "shotgun";

            else if (weapon instanceof Pistol) weaponName += "gun";

            renderWeapons.add(new GUIRectangle(VectorHelper.sumVectors(new Vector2i[] {inventoryBar[currentWeapon].position, new Vector2i(2, 2)}), new Vector2i(64, 64), weaponName));

            weaponCount++;

        }

        return this;

    }

    public void update() {

        for (int count = 0; count < weapons.size(); count++)
            if (InputInformation.isKeyPressed(InputListener.getKeyCode(String.valueOf(count + 1)))) {

                currentWeapon = count;

                Weapon currentWeapon = getCurrentWeapon();

                if (!(currentWeapon instanceof Shotgun)) currentWeapon.reloadTimer.restart();

            }

        Weapon currentWeapon = getCurrentWeapon();

        if (currentWeapon != null) {

            currentWeapon.update();

            currentWeaponMarker.position = inventoryBar[this.currentWeapon].position;

        }

    }

    public void render(Graphics graphics) {

        for (GUIRectangle slot : inventoryBar)
            slot.render(graphics);

        for (GUIRectangle weapon : renderWeapons)
            weapon.render(graphics);

        if (currentWeaponMarker != null) currentWeaponMarker.render(graphics);

        Weapon currentWeapon = getCurrentWeapon();

        if (currentWeapon != null) {

            if (currentWeapon.reloading) reloadProgress[0].size.x = (int) (currentWeapon.reloadTimer.currentTime / currentWeapon.reloadTimer.endTime * 60);

            else reloadProgress[0].size.x = 60;

            reloadProgress[0].render(graphics);
            reloadProgress[1].render(graphics);

        }

    }

    public Weapon getCurrentWeapon() {

        if (weapons.size() == 0) return null;
        else return weapons.get(currentWeapon);

    }

}
