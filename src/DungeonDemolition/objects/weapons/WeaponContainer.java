package dungeonDemolition.objects.weapons;

import dungeonDemolition.util.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class WeaponContainer {

    public List<Weapon> weapons = new ArrayList<Weapon>();
    public int currentWeapon;
    public int maxWeaponCount;

    public WeaponContainer(int maxWeaponCount) {

        this.maxWeaponCount = MathHelper.clamp(maxWeaponCount, 0, 9);

    }

    public WeaponContainer addWeapon(Weapon weapon) {

        if (weapons.size() <= maxWeaponCount - 1) {

            weapons.add(weapon);
            currentWeapon = weapons.size() - 1;

        }

        return this;

    }

    public Weapon getCurrentWeapon() {

        if (weapons.size() == 0) return null;

        else return weapons.get(currentWeapon);

    }

}
