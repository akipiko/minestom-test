package com.github.akipiko3150;

import com.github.akipiko3150.command.GameModeCommand;
import com.github.akipiko3150.command.SkinCommand;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        MinecraftServer server = MinecraftServer.init();

        // create Instance
        InstanceManager manager = MinecraftServer.getInstanceManager();
        InstanceContainer container = manager.createInstanceContainer();

        // generate Instance
        container.setGenerator(unit -> {
            unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK);
        });

        // add command
        MinecraftServer.getCommandManager().register(new GameModeCommand());
        MinecraftServer.getCommandManager().register(new SkinCommand());

        // add lighting
        container.setChunkSupplier(LightingChunk::new);

        MojangAuth.init();

        // add an event handler to players spawning
        GlobalEventHandler handler = MinecraftServer.getGlobalEventHandler();
        handler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(container);
            player.setRespawnPoint(new Pos(0, 42, 0));
            if (player.getUuid().equals(UUID.fromString("b18e471d-8fb4-4741-8778-91a4fbfcd1c9"))) player.setPermissionLevel(4);
        });

        server.start("0.0.0.0", 25565);


    }
}