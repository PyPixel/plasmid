package xyz.nucleoid.plasmid.storage;

import com.mojang.authlib.GameProfile;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;
import java.util.Map;

public class FriendListManager {
    private static Map fArrays = new HashMap<Integer,int[]>();
    public static void appendNewFreindList(Integer playerUUID, int[] fList) {
        if(fArrays.containsValue(playerUUID)){
            fArrays.computeIfPresent(playerUUID,(k,v) -> v = fList);

        }else{
            fArrays.put(playerUUID,fList);
        }
    }
}
