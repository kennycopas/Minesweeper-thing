import java.lang.Math;
import java.util.Scanner;
public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean endInput = false;
    public static Clear clear;
    public static Clear clear2;
    public static Bomb bomb;
    public static int size = 10;
    public static Block[][] map = new Block[size][size];
    public static int mapArea = size*size;
    public static void populateMap(int bombCount) {
        for(int i = 0; i < mapArea-bombCount; i++) {
            int ranX = (int)Math.floor(Math.random()*size);
            int ranY = (int)Math.floor(Math.random()*size);
            if(map[ranX][ranY] == null) {
                map[ranX][ranY] = new Clear(ranX, ranY, map);
            } else {
                i--;
            }
        }
        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[x].length; y++) {
                if(map[x][y] == null) {
                    map[x][y] = new Bomb(x, y, map);
                }
            }
        }
    }
    public static boolean isClear(Block temp, int inst) {
        if(inst == 0 && temp instanceof Clear) {
            clear = (Clear)temp;
            return true;
        } else if(inst == 1 && temp instanceof Clear) {
            clear2 = (Clear)temp;
            return true;
        } else {
            bomb = (Bomb)temp;
            return false;
        }
    }
    public static boolean inBounds(int x, int y) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
    }
    public static void updateMap() {
        for(int i = 0; i < 5; i++) {
            for(int x = 0; x < map.length; x++) {
                for(int y = 0; y < map[x].length; y++) {
                    if(isClear(map[x][y], 0) && !clear.visible) {
                        for (int k = x - 1; k <= x + 1; k++) {
                            for (int l = y - 1; l <= y + 1; l++) {
                                if (inBounds(k, l) && isClear(map[k][l], 1) && map[k][l] != null && map[k][l] != map[x][y] && clear2.count == 0 && clear2.visible) {
                                    clear.visible = true;
                                    l = 100;
                                    k = 100;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void printMap() {
        System.out.println("   0  1  2  3  4  5  6  7  8  9");
        for(int x = 0; x < map.length; x++) {
            System.out.print(x + "  ");
            for(int y = 0; y < map[x].length; y++) {
                System.out.print(map[x][y].getStats() + "  ");
            }
            System.out.println();
        }
    }
    public static void allVisible() {
        for(int x = 0; x < map.length; x++) {
            for(int y = 0; y < map[x].length; y++) {
                if(isClear(map[x][y], 0)) {
                    clear.visible = true;
                } else {
                    bomb.visible = true;
                }
            }
        }

    }
    public static void userClick(int x, int y) {
        if(isClear(map[x][y], 0)) {
            clear.click();
        } else {
            bomb.click();
            endInput = true;
            scanner.close();
            allVisible();
        }
    }
    public static void processInput(String input, Scanner scanner) {
        String[] splitCoords = input.split(",");
        int y = Integer.parseInt(splitCoords[0]);
        int x = Integer.parseInt(splitCoords[1]);
        userClick(x, y);
    }
    public static void main(String[] args) {
        populateMap(10);

        //LOOK HERE -------------------------------------------------------------------------------------------------------------------------------------
        //I'm passing map through as a parameter so that the Clear class can reference it, but that isn't relevant to this
        map[1][2] = new Clear(1, 2, map);
        map[1][2].incCounter();
        System.out.println(map[1][2]);
        //If the contents of the incCounter method are: this.count++;
        //This print statement will print 0, meaning the count was not incremented
        //If the contents of the incCounter method are: super.count++;
        //This print statement will print 1, meaning the count was incremented

        //The solution I used uses the public static Clear clear; variable defined at the top of the Main class
        map[1][2] = new Clear(1, 2, map);
        clear = (Clear)map[1][2];
        clear.incCounter();
        System.out.println(clear.count);
        //Leaving the contents of the incCounter method as: this.count++;
        //And casting the array reference to the new Clear object as type (Clear)
        //This print statement will print 1, meaning the count was incremented

        while (!endInput) {
            updateMap();
            printMap();
            String input = scanner.nextLine();
            processInput(input, scanner);
        }
        scanner.close();
        printMap();
    }
}