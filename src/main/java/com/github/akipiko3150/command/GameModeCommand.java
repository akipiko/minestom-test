package com.github.akipiko3150.command;

import net.kyori.adventure.text.Component;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;

public class GameModeCommand extends Command {

    public GameModeCommand() {
        super("gamemode");

        var gameModeArgument = ArgumentType.Enum("gameMode", GameMode.class);

        addSyntax((sender, context) -> {
            if (sender instanceof Player player) {
                GameMode gameMode = context.get(gameModeArgument);
                player.setGameMode(gameMode);
                String gameModeName = switch (gameMode){
                    case SURVIVAL -> "サバイバル";
                    case CREATIVE -> "クリエイティブ";
                    case ADVENTURE -> "アドベンチャー";
                    case SPECTATOR -> "スペクテイター";
                };
                player.sendMessage(Component.text("ゲームモードを" + gameModeName + "に変更しました。"));
            } else {
                System.out.println("プレイヤーのみ実行できます。");
            }

        }, gameModeArgument);
    }
}