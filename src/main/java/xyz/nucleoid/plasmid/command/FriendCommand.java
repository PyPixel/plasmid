package xyz.nucleoid.plasmid.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import xyz.nucleoid.plasmid.storage.FriendListManager;

import java.awt.print.PageFormat;
import java.util.ArrayList;
import java.util.UUID;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class FriendCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register( literal("friend")
                .then(literal("accept").executes(FriendCommand::acceptFriend))
                .then(literal("add").then(argument("playerName", GameProfileArgumentType.gameProfile()).executes(FriendCommand::registerFriendAdd)))
                .then(literal("list").executes(FriendCommand::ListFriends)));
    }

    private static int ListFriends(CommandContext<ServerCommandSource> context){
        try {
            if (FriendListManager.returnFlist(context.getSource().getPlayer().getUuid()).returnFlistIds().size() <= 0) {
                return 0;
            }
            System.out.println("Listing friends");
            ArrayList<UUID> tempids = FriendListManager.returnFlist(context.getSource().getPlayer().getUuid()).returnFlistIds();
            for (UUID ids : tempids) {
                System.out.println(ids.toString());
                System.out.println(context.getSource().getMinecraftServer().getUserCache().getByUuid(ids).getName());
            }
            return 1;
        }catch(Exception err) {
            err.printStackTrace();
        }
        return 1;
    }

    private static int acceptFriend(CommandContext<ServerCommandSource> context) {
        System.out.println("accepting");
        return 1;
    }

    private static int registerFriendAdd(CommandContext<ServerCommandSource> context) throws CommandSyntaxException{
        try {
            for (GameProfile Profile: GameProfileArgumentType.getProfileArgument(context, "playerName")) {
                System.out.println(Profile.getName());
                System.out.println(FriendListManager.returnFlist(context.getSource().getPlayer().getUuid()));

                FriendListManager.returnFlist(context.getSource().getPlayer().getUuid()).addFreind(Profile.getId());
                System.out.println("Added friend");
                break;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return 1;
    }

}
