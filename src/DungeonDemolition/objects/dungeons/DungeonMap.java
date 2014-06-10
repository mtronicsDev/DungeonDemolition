package dungeonDemolition.objects.dungeons;

import dungeonDemolition.util.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DungeonMap {

    public List<DungeonTile> dungeonTiles;

    public DungeonMap(byte[][] map) {

        dungeonTiles = new ArrayList<DungeonTile>();
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++) {
                dungeonTiles.add(new DungeonTile(map[i][j], new Vector2f(40 * i, 40 * j)));
            }

    }

    public void render(Graphics graphics) {

        for (DungeonTile tile : dungeonTiles) tile.render(graphics);

    }

}
