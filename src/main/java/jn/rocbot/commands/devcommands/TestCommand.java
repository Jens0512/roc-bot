package jn.rocbot.commands.devcommands;

import jn.rocbot.commands.common.Command;
import jn.rocbot.commands.common.CommandConfig;
import jn.rocbot.commands.common.CommandType;
import jn.rocbot.info.Stores.ShipStore;
import jn.rocbot.info.Stores.ZenStore;
import jn.rocbot.ships.Ship;
import jn.rocbot.ships.Zen;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.StringJoiner;

public class TestCommand implements Command{
    private CommandConfig config = new CommandConfig(CommandType.DEV, false);

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if(args[0].equals("isShip") && args.length > 1){
            StringJoiner ship = new StringJoiner(" ");
            for(String arg : args) if(!args.equals("extra")) ship.add(arg);
            sendMessage(ship.toString() + " isShip: " + Ship.isShip(ship.toString()), event);

        } else if(args[0].equals("zen") && args.length > 1) {
            StringJoiner zenName = new StringJoiner(" ");
            for (int i = 1; i < args.length; i++) {
                zenName.add(args[i]);
            } if(ZenStore.isZen(zenName.toString())) {
                try {
                    Zen zen = ZenStore.fromName(zenName.toString());
                    sendMessage(zen.simpleDesc(true), event);
                } catch (ZenStore.ZenNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }else if(args.length > 1 && args[0].toLowerCase().equals("zen") && args[1].toLowerCase().equals("random")){
            Random r = new Random();
            sendMessage(ZenStore.ZENS.get(r.nextInt(ZenStore.ZENS.size() - 1)).simpleDesc(true), event);
        }else if(args.length > 1 &&  args[0].toLowerCase().equals("emb") && args[1].toLowerCase().equals("random")){
            Random r = new Random();
            event.getTextChannel().sendMessage(ShipStore.randomShip().desc()).complete();
        }
    }

    @Override
    public String help() {
        return "NO HELP YET";
    }

    @Override
    public boolean executed(boolean success, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public CommandConfig getConfig() {
        return config;
    }

    @Override
    public CommandType getType() {
        return config.type;
    }
}
