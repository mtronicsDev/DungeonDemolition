package dungeonDemolition.objects.dungeons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.weapons.*;
import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.TextureHelper;
import dungeonDemolition.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DungeonTile {

    private static List<BufferedImage> textures = new ArrayList<BufferedImage>();
    public Vector2f position;
    public List<Vector2f> normals = new ArrayList<Vector2f>();
    public byte id;
    public boolean passable = true;
    public boolean interactable = false;
    public TileInteractionMethod interactionMethod;

    public DungeonTile(byte tileId, Vector2f position) {

        if (textures.size() == 0)
            for (int i = 0; i < 14; i++)
                textures.add(TextureHelper.loadImage("tiles/" + String.valueOf(i)));

        this.position = position;
        id = tileId;

        switch (id) {

            case 2:
                passable = false;
                normals.add(new Vector2f(0, 1));
                break;

            case 3:
                passable = false;
                normals.add(new Vector2f(-1, 0));
                break;

            case 4:
                passable = false;
                normals.add(new Vector2f(0, -1));
                break;

            case 5:
                passable = false;
                normals.add(new Vector2f(1, 0));
                break;

            case 12:
                passable = false;
                normals.add(new Vector2f(0, 1));
                normals.add(new Vector2f(0, -1));
                normals.add(new Vector2f(1, 0));
                normals.add(new Vector2f(-1, 0));
                interactable = true;
                interactionMethod = new TileInteractionMethod() {
                    @Override
                    public void interact(Player player) {

                        LootType type = LootType.values()[Randomizer.getRandomInt(0, LootType.values().length - 1)];
                        boolean isWeaponUnassigned = true;
                        switch (type) {

                            case WEAPON_GUN:
                                for (Weapon weapon : player.inventory.weapons)
                                    if ((weapon instanceof Pistol)) isWeaponUnassigned = false;
                                if (player.inventory.weapons.size() == 0 || isWeaponUnassigned)
                                    player.inventory.addWeapon(new Pistol());
                                break;

                            case WEAPON_MACHINE_GUN:
                                for (Weapon weapon : player.inventory.weapons)
                                    if ((weapon instanceof MachineGun)) isWeaponUnassigned = false;
                                if (player.inventory.weapons.size() == 0 || isWeaponUnassigned)
                                    player.inventory.addWeapon(new MachineGun());
                                break;

                            case WEAPON_RPG:
                                for (Weapon weapon : player.inventory.weapons)
                                    if ((weapon instanceof RocketLauncher)) isWeaponUnassigned = false;
                                if (player.inventory.weapons.size() == 0 || isWeaponUnassigned)
                                    player.inventory.addWeapon(new RocketLauncher());
                                break;

                            case WEAPON_SHOTGUN:
                                for (Weapon weapon : player.inventory.weapons)
                                    if ((weapon instanceof Shotgun)) isWeaponUnassigned = false;
                                if (player.inventory.weapons.size() == 0 || isWeaponUnassigned)
                                    player.inventory.addWeapon(new Shotgun());
                                break;

                            case AMMO_GUN:
                                for (Weapon weapon : player.inventory.weapons)
                                    if ((weapon instanceof Pistol))
                                        weapon.remainingAmmoCount += Randomizer.getRandomInt(0, weapon.maxRemainingAmmoCount - weapon.remainingAmmoCount);
                                break;

                            case AMMO_MACHINE_GUN:
                                for (Weapon weapon : player.inventory.weapons)
                                    if ((weapon instanceof MachineGun))
                                        weapon.remainingAmmoCount += Randomizer.getRandomInt(0, weapon.maxRemainingAmmoCount - weapon.remainingAmmoCount);
                                break;

                            case AMMO_RPG:
                                for (Weapon weapon : player.inventory.weapons)
                                    if ((weapon instanceof RocketLauncher))
                                        weapon.remainingAmmoCount += Randomizer.getRandomInt(0, weapon.maxRemainingAmmoCount - weapon.remainingAmmoCount);
                                break;

                            case AMMO_SHOTGUN:
                                for (Weapon weapon : player.inventory.weapons)
                                    if ((weapon instanceof Shotgun))
                                        weapon.remainingAmmoCount += Randomizer.getRandomInt(0, weapon.maxRemainingAmmoCount - weapon.remainingAmmoCount);
                                break;

                        }

                        interactable = false;
                        id = 13;

                    }
                };
                break;

            case 14:
                passable = false;
                interactable = true;
                interactionMethod = new TileInteractionMethod() {
                    @Override
                    public void interact(Player player) {
                        passable = !passable;
                    }
                };
                break;

        }

    }

    public void render(Graphics graphics) {

        Entity player = ObjectController.entities.get("player");

        if (!(((position.x - player.position.x) + ObjectController.display.size.x / 2 - 20 < -40)
                || ((position.x - player.position.x) + ObjectController.display.size.x / 2 - 20) >= ObjectController.display.size.x
                || ((position.y - player.position.y) + ObjectController.display.size.y / 2 - 20) < -40
                || ((position.y - player.position.y) + ObjectController.display.size.y / 2 - 20) >= ObjectController.display.size.y))

            graphics.drawImage(textures.get(id),
                    (int) (position.x - player.position.x) + ObjectController.display.size.x / 2 - 20,
                    (int) (position.y - player.position.y) + ObjectController.display.size.y / 2 - 20,
                    40, 40, null);

    }

}
