package me.cholesterinlord.coinsgame.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.UUID;

public class SkullCreator {

    /**
            * Creates a player skull based on a player's name.
            *
            * @param type Whether to make a block or item
     * @param name The Player's name
            * @return The head of the Player
     */
    public static ItemStack fromName(Type type, String name) {
        ItemStack item = new ItemStack(type.mat, 1, (short) 3);
        return withName(item, name);
    }

    /**
     * Creates a player skull based on a player's name.
     *
     * @param item The item to apply the name to
     * @param name The Player's name
     * @return The head of the Player
     */

    public static ItemStack withName(ItemStack item, String name) {
        notNull(item, "item");
        notNull(name, "name");
        return Bukkit.getUnsafe().modifyItemStack(item,
                "{SkullOwner:\"" + name + "\"}"
        );
    }

    /**
     * Creates a player skull based on a Mojang server URL.
     *
     * @param type  Whether to make a block or item
     * @param url   The URL of the Mojang skin
     * @return The head associated with the URL
     */
    public static ItemStack fromUrl(Type type, String url) {
        ItemStack item = new ItemStack(type.mat, 1, (short) 3);
        return withUrl(item, url);
    }


    /**
     * Creates a player skull based on a Mojang server URL.
     *
     * @param item  The item to apply the skin to
     * @param url   The URL of the Mojang skin
     * @return The head associated with the URL
     */
    public static ItemStack withUrl(ItemStack item, String url) {
        notNull(url, "url");
        URI actualUrl;
        try {
            actualUrl = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String base64 = "{\"textures\":{\"SKIN\":{\"url\":\"" + actualUrl.toString() + "\"}}}";
        base64 = Base64.getEncoder().encodeToString(base64.getBytes());
        return withBase64(item, base64);
    }

    /**
     * Creates a player skull based on a base64 string containing the link to the skin.
     *
     * @param type   Whether to make a block or item
     * @param base64 The base64 string containing the texture
     * @return The head with a custom texture
     */
    public static ItemStack fromBase64(Type type, String base64) {
        ItemStack item = new ItemStack(type.mat, 1, (short) 3);
        return withBase64(item, base64);
    }

    /**
     * Applies the base64 string to the ItemStack.
     *
     * @param item   The ItemStack to put the base64 onto
     * @param base64 The base64 string containing the texture
     * @return The head with a custom texture
     */
    public static ItemStack withBase64(ItemStack item, String base64) {
        notNull(item, "item");
        notNull(base64, "base64");
        UUID hashAsId = new UUID(base64.hashCode(), base64.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(item,
                "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + base64 + "\"}]}}}"
        );
    }

    public enum Type {
        BLOCK(Material.SKELETON_SKULL),
        ITEM(Material.SKELETON_SKULL);

        private Material mat;

        Type(Material mat) {

            this.mat = mat;
        }
    }

    private static void notNull(Object o, String name) {
        if (o == null) {
            throw new NullPointerException(name + " should not be null!");
        }
    }
}
