package com.github.akipiko3150.command;

import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;

public class SkinCommand extends Command {
    public SkinCommand(){
        super("skin");

        setDefaultExecutor(((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            PlayerSkin skin = PlayerSkin.fromUuid(player.getUuid().toString());
            player.setSkin(skin);
        }));

        var playerNameArg = ArgumentType.String("playerName");

        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            PlayerSkin skin = PlayerSkin.fromUsername(context.get(playerNameArg));
            player.setSkin(skin);
        }, playerNameArg);

        var playerUuidArg = ArgumentType.UUID("uuid");

        addSyntax((sender, context) -> {
            if (!(sender instanceof Player player)) return;
            PlayerSkin skin = PlayerSkin.fromUuid(context.get(playerUuidArg).toString());
            player.setSkin(skin);
        }, playerUuidArg);
    }
}
