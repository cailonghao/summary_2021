package com.im.server.util;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelUtil {


    public static Map<String, Channel> channelMap = new ConcurrentHashMap<>();
    public static Map<String, String> channelIndex = new ConcurrentHashMap<>();

    public static void setChannelMap(String key, Channel channel) {
        channelMap.put(key, channel);
    }

    public static void removeChannelMap(String key) {
        channelMap.remove(key);
    }

    public static Channel getChannelMap(String key) {
        return channelMap.get(key);
    }
}
