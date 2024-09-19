public class Block {
    public Block block;
    public int x;
    public int y;
    public boolean visible = false;
    public int count = 0;
    public String status = "Block";
    public Block(int x, int y, Block[][] map, String status, int count) {
        this.x = x;
        this.y = y;
        this.status = status;
        this.count = count;
    }
    public String getStats() {
        return "?";
    }
    public void incCounter() {
        //Bomb - do nothing
        //Clear - counter++;
    }
    public void click() {
        //Bomb - Game Over
        //Clear - Reveal
    }
    public void reveal() {
        visible = true;
    }
}
