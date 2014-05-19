package dungeonDemolition.dungeons.rooms;

import dungeonDemolition.util.MathHelper;
import dungeonDemolition.util.Randomizer;

import java.awt.*;

public class RoomSmall extends Room {

    @Override
    public void generate(Point position, Point direction, byte[][] map) {

        tiles = new byte[Randomizer.getRandomInt(5, 14)][Randomizer.getRandomInt(5, 14)];

        size.x = tiles.length;
        size.y = tiles[0].length;

        if (direction.x == 1) {

            int randomXOffset = -1 * Randomizer.getRandomInt(1, size.x - 1);
            this.position = new Point(
                    MathHelper.clamp(position.x + randomXOffset, 0, map[0].length - size.y),
                    position.y
            );

        }

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {

                if(x == 0 && y == 0) tiles[x][y] = 6;
                else if(x == 0 && y == size.y - 1) tiles[x][y] = 9;
                else if(x == size.x - 1 && y == 0) tiles[x][y] = 7;
                else if(x == size.x - 1 && y == size.y - 1) tiles[x][y] = 8;
                else tiles[x][y] = 1;

            }
        }

    }

}
