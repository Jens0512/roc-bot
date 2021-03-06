package jn.rocbot.commands.common;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface SubCommand{
    String invoke();
    String help();

    CommandConfig getConfig();

    void action(String[] args, MessageReceivedEvent event);

    default CommandType getType(){
        return getConfig().type;
    }

    default boolean isInvoke(String arg){
        if(getConfig().ignoreCase) {
            if (arg.equals(invoke())) return true;
            if (getConfig().OptionalInvokes.length != 0) {
                for (String opt : getConfig().OptionalInvokes) {
                    if (opt.equals(arg)) return true;
                }
            }
            return false;
        } else {
            if (arg.toLowerCase().equals(invoke().toLowerCase())) return true;
            if (getConfig().OptionalInvokes.length != 0) {
                for (String opt : getConfig().OptionalInvokes) {
                    if (opt.toLowerCase().equals(arg.toLowerCase())) return true;
                }
            }
            return false;
        }
    }
}
