public class Bomb extends Block {
    public Bomb(int x, int y, Block[][] map) {
        super(x, y, map, "bomb", 100);
        this.bombCounter(x, y, map);
    }
    public String getStats() {
        if(visible) {
            return "X";
        } else {
            return "□";
        }
    }
    public void click() {
        System.out.println("Game Over!");
    }
    public void bombCounter(int x, int y, Block[][] map) {
        for(int i = x-1; i <= x+1; i++) {
            for(int j = y-1; j <= y+1; j++) {
                if(i >= 0 && i < map.length && j >= 0 && j < map[0].length && map[i][j] != null && map[i][j] != this) {
                    map[i][j].incCounter();
                }
            }
        }
    }
}
