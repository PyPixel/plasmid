package xyz.nucleoid.plasmid.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class FriendCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register( literal("friend")
                .then(literal("accept").executes(FriendCommand::acceptFriend))
                .then(literal("add").then(argument("playerName", GameProfileArgumentType.gameProfile())).executes(FriendCommand::registerFriendAdd)));
    }

    private static int acceptFriend(CommandContext<ServerCommandSource> context) {
        System.out.println("accepting");
        return 1;
    }

    private static int registerFriendAdd(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        GameProfile player = (GameProfile) GameProfileArgumentType.getProfileArgument(context, "playerName");
        System.out.println(player.getName());
        return 1;
    }

}
