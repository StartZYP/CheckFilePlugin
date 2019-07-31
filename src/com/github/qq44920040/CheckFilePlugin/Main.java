package com.github.qq44920040.CheckFilePlugin;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JavaPlugin implements PluginMessageListener {
    private Logger logger = Logger.getLogger(Main.class);
    @Override
    public void onEnable() {
        getServer().getMessenger().registerIncomingPluginChannel(this,"checkfile", this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "checkfile");
        SimpleLayout layout = new SimpleLayout();
        FileAppender appender = null;
        File logdir = new File("."+File.separator+"CheckFileLog");
        if (!logdir.exists()){
            logdir.mkdir();
        }
        try {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            appender = new FileAppender(layout, "."+File.separator+"CheckFileLog"+File.separator+"[CheckFile]"+sdf.format(d)+".log", false);
        } catch (Exception e) {
        }
        logger.addAppender(appender);
        logger.info("【CheckFile】启动成功");
        super.onEnable();
    }

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        String recive=new String(bytes, StandardCharsets.UTF_8);
        System.out.println(recive);
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        logger.info("DataTime:"+sdf.format(d)+";"+"PlayerNmae:"+player.getName()+";"+"PlayerIp;"+player.getAddress().getHostName()+";"+"InfoBody"+recive);
    }
}
