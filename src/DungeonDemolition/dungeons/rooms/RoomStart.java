package dungeonDemolition.dungeons.rooms;

import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.Vector2i;

import java.awt.*;

public class RoomStart extends Room {

    @Override
    public void generate(Vector2i position, Vector2i direction, byte[][] map) {

        tiles = new byte[Randomizer.getRandomInt(12, 34)][Randomizer.getRandomInt(12, 34)];

        size.x = tiles.length;
        size.y = tiles[0].length;

        this.position = new Vector2i(
                Randomizer.getRandomInt(0, map.length - 1 - size.x),
                Randomizer.getRandomInt(0, map[0].length - 1 - size.y)
        );

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {

                if (x == 0 && y == 0) tiles[x][y] = 6;
                else if (x == 0 && y == size.y - 1) tiles[x][y] = 9;
                else if (x == size.x - 1 && y == 0) tiles[x][y] = 7;
                else if (x == size.x - 1 && y == size.y - 1) tiles[x][y] = 8;
                else if (x == 0) tiles[x][y] = 2;
                else if (y == 0) tiles[x][y] = 5;
                else if (x == size.x - 1) tiles[x][y] = 4;
                else if (y == size.y - 1) tiles[x][y] = 3;
                else tiles[x][y] = 1;

            }
        }

        int spawnX = Randomizer.getRandomInt(1, size.x - 2);
        int spawnY = Randomizer.getRandomInt(1, size.y - 2);

        tiles[spawnX][spawnY] = 10; //Player spawn

    }

}
