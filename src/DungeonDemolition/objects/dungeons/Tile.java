package dungeonDemolition.objects.dungeons;

public enum Tile {
    VOID,
    FLOOR,

    WALL_NORTH,
    WALL_EAST,
    WALL_SOUTH,
    WALL_WEST,

    CORNER_NORTH_WEST,
    CORNER_NORTH_EAST,
    CORNER_SOUTH_EAST,
    CORNER_SOUTH_WEST,

    SPAWN_PLAYER,
    SPAWN_ENEMY,

    CHEST_FULL,
    CHEST_EMPTY,
    CHEST_GOLD,

    STAIRS_UP,
    STAIRS_DOWN;

    public byte id() {
        return (byte) this.ordinal();
    }
}
