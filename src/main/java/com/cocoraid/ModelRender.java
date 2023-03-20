package com.cocoraid;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ModelRender extends JavaPlugin {

    @Override
    public void onEnable() {


        // Render the model using particles
        new BukkitRunnable() {

            List<Vector> modelVertices = getVectors();

            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    Location playerLocation = player.getLocation();

                    for (Vector vertex : modelVertices) {
                        Location particleLocation = playerLocation.clone().add(vertex);
                        player.spawnParticle(Particle.END_ROD, particleLocation, 0, 0, 0, 0, 1);
                    }
                }
            }
        }.runTaskTimer(this, 0L, 20L); // Run every second (20 ticks)

    }


    private List<Vector> getVectors() {
        InputStream modelStream = getClass().getResourceAsStream("/model.obj");
        List<Vector> modelVertices = new ArrayList<>();
        try {
            modelVertices = ModelLoader.loadModel(modelStream);
        } catch (IOException e) {
            getLogger().severe("Failed to load the 3D model");
            e.printStackTrace();
        }
        return modelVertices;
    }
}
