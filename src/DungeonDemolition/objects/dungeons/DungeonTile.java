package dungeonDemolition.objects.dungeons;

import dungeonDemolition.objects.ObjectController;
import dungeonDemolition.objects.entities.Entity;
import dungeonDemolition.objects.entities.Player;
import dungeonDemolition.objects.gui.GUIText;
import dungeonDemolition.objects.weapons.*;
import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.TextureHelper;
import dungeonDemolition.util.Vector2f;
import dungeonDemolition.util.VectorHelper;

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

    public DungeonTile(byte id, Vector2f position) {

        if (textures.size() == 0)
            for (int i = 0; i < 13; i++)
                textures.add(TextureHelper.loadImage("tiles/" + String.valueOf(i)));

        this.position = position;
        this.id = id;

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
                    public void interact() {

                        Player player = (Player) ObjectController.entities.get("player");

                        int contents = Randomizer.getRandomInt(1, 5);

                        for (int count = 0; count < contents; count++) {

                            int type = Randomizer.getRandomInt(0, 4);

                            switch (type) {

                                case 0:
                                    int healthIncrease = Randomizer.getRandomInt(5, 80);

                                    if (player.health + healthIncrease <= player.maxHealth) player.health += healthIncrease;

                                    else player.health = player.maxHealth;

                                    break;

                                case 1:
                                    int rocketIncrease = Randomizer.getRandomInt(1, 3);

                                    for (Weapon weapon : player.weaponContainer.weapons)
                                        if (weapon instanceof RocketLauncher) {

                                            if (weapon.remainingAmmoCount + rocketIncrease <= weapon.maxRemainingAmmoCount) weapon.remainingAmmoCount += rocketIncrease;

                                            else weapon.remainingAmmoCount = weapon.maxRemainingAmmoCount;

                                        }

                                    break;

                                case 2:
                                    int mgBulletIncrease = Randomizer.getRandomInt(100, 400);

                                    for (Weapon weapon : player.weaponContainer.weapons)
                                        if (weapon instanceof MachineGun) {

                                            if (weapon.remainingAmmoCount + mgBulletIncrease <= weapon.maxRemainingAmmoCount) weapon.remainingAmmoCount += mgBulletIncrease;

                                            else weapon.remainingAmmoCount = weapon.maxRemainingAmmoCount;

                                        }

                                    break;

                                case 3:
                                    int shotIncrease = Randomizer.getRandomInt(7, 30);

                                    for (Weapon weapon : player.weaponContainer.weapons)
                                        if (weapon instanceof Shotgun) {

                                            if (weapon.remainingAmmoCount + shotIncrease <= weapon.maxRemainingAmmoCount) weapon.remainingAmmoCount += shotIncrease;

                                            else weapon.remainingAmmoCount = weapon.maxRemainingAmmoCount;

                                        }

                                    break;

                                case 4:
                                    int pistolBulletIncrease = Randomizer.getRandomInt(20, 70);

                                    for (Weapon weapon : player.weaponContainer.weapons)
                                        if (weapon instanceof Pistol) {

                                            if (weapon.remainingAmmoCount + pistolBulletIncrease <= weapon.maxRemainingAmmoCount) weapon.remainingAmmoCount += pistolBulletIncrease;

                                            else weapon.remainingAmmoCount = weapon.maxRemainingAmmoCount;

                                        }

                                    break;

                            }

                        }

                        interactable = false;

                    }
                };
                break;

            case 13:
                passable = false;
                interactable = true;
                interactionMethod = new TileInteractionMethod() {
                    @Override
                    public void interact() {

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
