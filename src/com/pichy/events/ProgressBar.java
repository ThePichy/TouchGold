package com.pichy.events;

import com.pichy.TouchGold;
import com.pichy.utils.CacheConfig;
import com.pichy.utils.MathUtil;
import com.pichy.utils.RandomEnum;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ProgressBar extends BukkitRunnable implements org.bukkit.event.Listener {

    @Getter private static int max = 0, refresh = 20;
    @Getter public static BossBar bossBar;
    String title;

    boolean mob = false;
    @Getter public EntityType[] mobs = new EntityType[]
            {
                    EntityType.ZOMBIE,EntityType.ZOGLIN,EntityType.ZOMBIE_HORSE,EntityType.ZOMBIE_VILLAGER,EntityType.ZOMBIFIED_PIGLIN,
                    EntityType.CREEPER, EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.BLAZE, EntityType.VEX, EntityType.GUARDIAN,
                    EntityType.DROWNED, EntityType.CAVE_SPIDER, EntityType.BEE, EntityType.WITHER, EntityType.WITHER_SKELETON, EntityType.ELDER_GUARDIAN,
                    EntityType.EVOKER, EntityType.GHAST, EntityType.LLAMA
            };

    RandomEnum randomEntityEnum;

    public ProgressBar(){
        TouchGold.setProgressBar(this);
        load();
        runTaskTimer(TouchGold.getInstance(),refresh,refresh);
    }
    public void load(){
        remove();

        CacheConfig config = new CacheConfig(TouchGold.getInstance().getConfig());
        max = (int) config.get("MAX");
        title = config.get("TITLE_BOSSBAR").toString();
        BarColor color = BarColor.valueOf(config.get("COLOR_BOSSBAR").toString().toUpperCase());
        refresh = (int) config.get("REFRESH_THREAD");

        randomEntityEnum = new RandomEnum(mobs);

        bossBar = Bukkit.createBossBar("Gold...", color, BarStyle.SEGMENTED_6);
        bossBar.setProgress(0.0);

        for(Player p : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(p);
        }
    }
    public static void remove(){
        if(bossBar != null) {
            bossBar.removeAll();
            CacheConfig.clear();
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){ bossBar.addPlayer(e.getPlayer()); }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){ bossBar.removePlayer(e.getPlayer()); }
    @Override
    public void run() {
        if(!mob && Walk.getExchanged() >= max){
            mob = true;
            spawnRandomMob();
        }
        int progress = Walk.getExchanged();
        double marg = MathUtil.getPercentage((double) max,(double) progress)/max;
        bossBar.setProgress(java.lang.Math.min(1.0, marg));
        refreshBossBar();
    }
    public void spawnRandomMob(){
        for(Player p : Bukkit.getOnlinePlayers()) {
            EntityType entityType = (EntityType) randomEntityEnum.get();
            if(entityType == null)
                entityType = EntityType.PIGLIN;
            p.getWorld().spawnEntity(p.getLocation(), entityType);
        }
    }
    public void refreshBossBar(){
        bossBar.setTitle(title
                .replaceAll("@now", String.valueOf(Walk.getExchanged()))
                .replaceAll("@max", String.valueOf(max)
                ));
    }
}
