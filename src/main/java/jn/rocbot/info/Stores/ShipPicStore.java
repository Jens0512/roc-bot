package jn.rocbot.info.stores;

import jn.rocbot.ships.Ship;
import jn.rocbot.utils.Log;

import java.io.File;
import java.util.ArrayList;

public class ShipPicStore {
    private static ArrayList<String> pics;

    public static void init(){
        pics = new ArrayList<>();
        File folder = new File("pics/ships");
        for(File file : folder.listFiles()) {
            pics.add(file.getName().replace(".png", ""));
        }
    }

    public static boolean hasPic(Ship ship){
        return pics.contains(ship.name.toLowerCase().replace(" ", ""));
    }

    public static String getPicURL(Ship ship){
        return "https://raw.githubusercontent.com/Jens0512/roc-bot/master/pics/ships/"
                + ship.name.toLowerCase().replace(" ","") + ".png";
    }
}
