package dungeonDemolition.objects.weapons;

import dungeonDemolition.util.InputInformation;
import dungeonDemolition.util.InputListener;
import dungeonDemolition.util.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    public List<Weapon> weapons = new ArrayList<Weapon>();
    public int currentWeapon;
    public int maxWeaponCount;

    public Inventory(int maxWeaponCount) {

        this.maxWeaponCount = MathHelper.clamp(maxWeaponCount, 0, 9);

    }

    public Inventory addWeapon(Weapon weapon) {

        if (weapons.size() <= maxWeaponCount - 1) {

            weapons.add(weapon);
            currentWeapon = weapons.size() - 1;

        }

        return this;

    }

    public void update() {

        for (int count = 0; count < weapons.size(); count++)
            if (InputInformation.isKeyPressed(InputListener.getKeyCode(String.valueOf(count + 1))))
                currentWeapon = count;

        if (getCurrentWeapon() != null) getCurrentWeapon().update();

    }

    public Weapon getCurrentWeapon() {

        if (weapons.size() == 0) return null;
        else return weapons.get(currentWeapon);

    }

}
