package dungeonDemolition.dungeons.rooms;

import dungeonDemolition.util.Randomizer;

import java.awt.*;

public class RoomStart extends Room {

    @Override
    public void generate(Point position, Point direction, byte[][] map) {

        tiles = new byte[Randomizer.getRandomInt(12, 34)][Randomizer.getRandomInt(12, 34)];

        size.x = tiles.length;
        size.y = tiles[0].length;

        this.position = new Point(
                Randomizer.getRandomInt(0, map.length - 1 - size.x),
                Randomizer.getRandomInt(0, map[0].length - 1 - size.y)
        );

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {

                if(x == 0 && y == 0) tiles[x][y] = 6;
                else if(x == 0 && y == size.y - 1) tiles[x][y] = 9;
                else if(x == size.x - 1 && y == 0) tiles[x][y] = 7;
                else if(x == size.x - 1 && y == size.y - 1) tiles[x][y] = 8;
                else tiles[x][y] = 1;

            }
        }
        
        int spawnX = Randomizer.getRandomInt(1, size.x - 1);
        int spawnY = Randomizer.getRandomInt(1, size.y - 1);

        tiles[spawnX][spawnY] = 10; //Player spawn

    }
    
}
