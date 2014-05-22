package dungeonDemolition.dungeons.rooms;

import dungeonDemolition.util.MathHelper;
import dungeonDemolition.util.Randomizer;
import dungeonDemolition.util.Vector2i;

public class RoomGeneric extends Room {

    @Override
    public void generate(Vector2i position, Vector2i direction, byte[][] map) {

        tiles = new byte[Randomizer.getRandomInt(24, 54)][Randomizer.getRandomInt(24, 54)];

        size.x = tiles.length;
        size.y = tiles[0].length;

        if (direction.x == 1) {

            int randomXOffset = -1 * Randomizer.getRandomInt(1, size.x - 1);
            this.position = new Vector2i(
                    MathHelper.clamp(position.x + randomXOffset, 0, map[0].length - size.y),
                    position.y
            );

        }

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {

                if (x == 0 && y == 0) tiles[x][y] = 6;
                else if (x == 0 && y == size.y - 1) tiles[x][y] = 9;
                else if (x == size.x - 1 && y == 0) tiles[x][y] = 7;
                else if (x == size.x - 1 && y == size.y - 1) tiles[x][y] = 8;
                else if (x == 0) tiles[x][y] = 5;
                else if (y == 0) tiles[x][y] = 2;
                else if (x == size.x - 1) tiles[x][y] = 3;
                else if (y == size.y - 1) tiles[x][y] = 4;
                else tiles[x][y] = 1;

            }
        }

    }

    @Override
    public Vector2i[] getAvailableSpace(int direction, Vector2i start, byte[][] map) {

        switch (direction) {

            case 0: //North
                //(new Vector2i(0, 0), new Vector2i(map.length - 1, start.y), map);
            case 1: //East
                //(new Vector2i(start.x, 0), new Vector2i(0, start.y), map);
            case 2: //South
                //(new Vector2i(0, start.y), new Vector2i(map.length - 1, map[0].length - 1), map);
            case 3: //West
                //(new Vector2i(0, 0), new Vector2i(start.x, map[0].length - 1), map);

        }

        return null;

    }

}
