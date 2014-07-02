package dungeonDemolition.objects.weapons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.GUIRectangle;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.util.InputInformation;
import dungeonDemolition.util.InputListener;
import dungeonDemolition.util.Vector2i;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public List<Weapon> weapons = new ArrayList<Weapon>();
    public int currentWeapon;
    public int weaponCount = 0;
    public int maxWeaponCount = 6;
    public GUIRectangle[] reloadProgress;
    public GUIRectangle currentWeaponMarker;

    public GUIText healthKitCount;
    private GUIRectangle healthKitSlot;
    private GUIRectangle healthKitSymbol;

    public Inventory() {

        reloadProgress = new GUIRectangle[2];

        reloadProgress[0] = new GUIRectangle(new Vector2i(ObjectController.display.size.x - 114, ObjectController.display.size.y - 40), new Vector2i(60, 20), Color.lightGray, true);
        reloadProgress[1] = new GUIRectangle(new Vector2i(ObjectController.display.size.x - 114, ObjectController.display.size.y - 40), new Vector2i(60, 20), Color.darkGray, true);

        healthKitSlot = new GUIRectangle(new Vector2i(210, ObjectController.display.size.y - 20 - 70), new Vector2i(70, 70), "inventory/slot");
        healthKitSymbol = new GUIRectangle(new Vector2i(210, ObjectController.display.size.y - 73), new Vector2i(64, 64), "inventory/healthKit");
        healthKitCount = new GUIText(new Vector2i(250, ObjectController.display.size.y - 50), Color.lightGray, 20, "");

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
            if (InputInformation.isKeyPressed(InputListener.getKeyCode(String.valueOf(count + 1))) && currentWeapon != count) {

                currentWeapon = count;

                Weapon currentWeapon = getCurrentWeapon();

                if (!(currentWeapon instanceof Shotgun)) currentWeapon.reloadTimer.restart();

            }

        Weapon currentWeapon = getCurrentWeapon();

        if (currentWeapon != null) {

            if (currentWeaponMarker == null) {

                currentWeaponMarker = new GUIRectangle(((Player) ObjectController.entities.get("player")).inventoryBar[this.currentWeapon].position,
                        new Vector2i(70, 70),
                        "inventory/slotSelected");

                ObjectController.guiPanels.get("inGame").guiElements.add(currentWeaponMarker);

            } else
                currentWeaponMarker.position = ((Player) ObjectController.entities.get("player")).inventoryBar[this.currentWeapon].position;

            currentWeapon.update();

            if (currentWeapon.reloading)
                reloadProgress[0].size.x = (int) (currentWeapon.reloadTimer.currentTime / currentWeapon.reloadTimer.endTime * 60);

            else reloadProgress[0].size.x = 60;

        }

    }

    public void render(Graphics graphics) {

        Weapon weapon = getCurrentWeapon();

        if (weapon != null) {

            if (!(weapon instanceof Knife)) {

                reloadProgress[1].position = new Vector2i(ObjectController.display.size.x - 114, ObjectController.display.size.y - 40);
                reloadProgress[0].position = new Vector2i(ObjectController.display.size.x - 114, ObjectController.display.size.y - 40);

                reloadProgress[1].render(graphics);
                reloadProgress[0].render(graphics);

            }

        }

        int xPos = ObjectController.getPlayer().largeMargin + (10 - ObjectController.getPlayer().heartBar.length / 2) * 27 +
                (10 - ObjectController.getPlayer().heartBar.length / 2) *
                        6 + ObjectController.getPlayer().largeMargin;

        int yPos = ObjectController.display.size.y - 70 - 20;

        healthKitSlot.position = new Vector2i(xPos, yPos);
        healthKitSymbol.position = new Vector2i(xPos + 3, yPos + 3);
        healthKitCount.position = new Vector2i(xPos + 70 + ObjectController.getPlayer().smallMargin, yPos + 43);

        healthKitSlot.render(graphics);
        healthKitSymbol.render(graphics);
        healthKitCount.render(graphics);

    }

    public Weapon getCurrentWeapon() {

        if (weapons.size() == 0) return null;
        else return weapons.get(currentWeapon);

    }

}
