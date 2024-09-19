public class Clear extends Block {
    public static boolean running = false;
    public Clear(int x, int y, Block[][] map) {
        super(x, y, map, "clear", 0);
    }
    public String getStats() {
        if(visible && count == 0) {
            return " ";
        } else if(visible && count > 0) {
            return count + "";
        } else {
            return "□";
        }
    }
    public void click() {
        visible = true;
    }
    //LOOK HERE
    public void incCounter() {
        //Option 1: this.count++;
        //Option 2: super.count++;
    }
}
