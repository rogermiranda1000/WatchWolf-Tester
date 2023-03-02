package dev.watchwolf.entities.entities;

import dev.watchwolf.entities.Position;
import dev.watchwolf.entities.SocketData;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.items.Item;

/* THIS CLASS WAS AUTOMATICALLY GENERATED; DO NOT MODIFY */
public enum EntityType {
	DROPPED_ITEM,
	EXPERIENCE_ORB,
	AREA_EFFECT_CLOUD,
	ELDER_GUARDIAN,
	WITHER_SKELETON,
	STRAY,
	EGG,
	LEASH_HITCH,
	PAINTING,
	ARROW,
	SNOWBALL,
	FIREBALL,
	SMALL_FIREBALL,
	ENDER_PEARL,
	ENDER_SIGNAL,
	SPLASH_POTION,
	THROWN_EXP_BOTTLE,
	ITEM_FRAME,
	WITHER_SKULL,
	PRIMED_TNT,
	FALLING_BLOCK,
	FIREWORK,
	HUSK,
	SPECTRAL_ARROW,
	SHULKER_BULLET,
	DRAGON_FIREBALL,
	ZOMBIE_VILLAGER,
	SKELETON_HORSE,
	ZOMBIE_HORSE,
	ARMOR_STAND,
	DONKEY,
	MULE,
	EVOKER_FANGS,
	EVOKER,
	VEX,
	VINDICATOR,
	ILLUSIONER,
	MINECART_COMMAND,
	BOAT,
	MINECART,
	MINECART_CHEST,
	MINECART_FURNACE,
	MINECART_TNT,
	MINECART_HOPPER,
	MINECART_MOB_SPAWNER,
	CREEPER,
	SKELETON,
	SPIDER,
	GIANT,
	ZOMBIE,
	SLIME,
	GHAST,
	ZOMBIFIED_PIGLIN,
	ENDERMAN,
	CAVE_SPIDER,
	SILVERFISH,
	BLAZE,
	MAGMA_CUBE,
	ENDER_DRAGON,
	WITHER,
	BAT,
	WITCH,
	ENDERMITE,
	GUARDIAN,
	SHULKER,
	PIG,
	SHEEP,
	COW,
	CHICKEN,
	SQUID,
	WOLF,
	MUSHROOM_COW,
	SNOWMAN,
	OCELOT,
	IRON_GOLEM,
	HORSE,
	RABBIT,
	POLAR_BEAR,
	LLAMA,
	LLAMA_SPIT,
	PARROT,
	VILLAGER,
	ENDER_CRYSTAL,
	TURTLE,
	PHANTOM,
	TRIDENT,
	COD,
	SALMON,
	PUFFERFISH,
	TROPICAL_FISH,
	DROWNED,
	DOLPHIN,
	CAT,
	PANDA,
	PILLAGER,
	RAVAGER,
	TRADER_LLAMA,
	WANDERING_TRADER,
	FOX,
	BEE,
	HOGLIN,
	PIGLIN,
	STRIDER,
	ZOGLIN,
	PIGLIN_BRUTE,
	AXOLOTL,
	GLOW_ITEM_FRAME,
	GLOW_SQUID,
	GOAT,
	MARKER,
	ALLAY,
	CHEST_BOAT,
	FROG,
	TADPOLE,
	WARDEN,
	FISHING_HOOK,
	LIGHTNING,
	PLAYER;

	/**
     * Get the EntityType using a class
     * @param cls Entity subclass
     * @return EntityType of the class
     * @throws IllegalArgumentException shouldn't happen; the specified class doesn't exist as EntityType
     */
    public static EntityType getType(Class<? extends Entity> cls) throws IllegalArgumentException {
        return EntityType.valueOf(EntityType.classNameToEnumName(cls.getSimpleName()));
    }

    private static String classNameToEnumName(String className) {
        StringBuilder sb = new StringBuilder(className);
        for (int i = 1; i < sb.length() - 1; i++) {
            if (sb.charAt(i) >= 'A' && sb.charAt(i) <= 'Z') {
                sb.insert(i, '_');
                i++; // now the index 'i' is at 'i+1'
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * Get the EntityType using an instance
     * @param e Entity instance
     * @return EntityType of the instance
     * @throws IllegalArgumentException shouldn't happen; the specified class doesn't exist as EntityType
     */
    public static EntityType getType(Entity e) throws IllegalArgumentException {
        return getType(e.getClass());
    }

    static {
        SocketData.setReaderFunction(Entity.class, (dis) -> {
            EntityType type = EntityType.values()[SocketHelper.readShort(dis)];
            Position pos = (Position) SocketData.readSocketData(dis, Position.class);
            String uuid = SocketHelper.readString(dis);

            if (type.equals(EntityType.DROPPED_ITEM)) {
                Item droppedItem = (Item) SocketData.readSocketData(dis, Item.class);
                return new DroppedItem(uuid, pos, droppedItem);
            }
			if (type.equals(EntityType.EXPERIENCE_ORB)) return new ExperienceOrb(uuid, pos);
			if (type.equals(EntityType.AREA_EFFECT_CLOUD)) return new AreaEffectCloud(uuid, pos);
			if (type.equals(EntityType.ELDER_GUARDIAN)) return new ElderGuardian(uuid, pos);
			if (type.equals(EntityType.WITHER_SKELETON)) return new WitherSkeleton(uuid, pos);
			if (type.equals(EntityType.STRAY)) return new Stray(uuid, pos);
			if (type.equals(EntityType.EGG)) return new Egg(uuid, pos);
			if (type.equals(EntityType.LEASH_HITCH)) return new LeashHitch(uuid, pos);
			if (type.equals(EntityType.PAINTING)) return new Painting(uuid, pos);
			if (type.equals(EntityType.ARROW)) return new Arrow(uuid, pos);
			if (type.equals(EntityType.SNOWBALL)) return new Snowball(uuid, pos);
			if (type.equals(EntityType.FIREBALL)) return new Fireball(uuid, pos);
			if (type.equals(EntityType.SMALL_FIREBALL)) return new SmallFireball(uuid, pos);
			if (type.equals(EntityType.ENDER_PEARL)) return new EnderPearl(uuid, pos);
			if (type.equals(EntityType.ENDER_SIGNAL)) return new EnderSignal(uuid, pos);
			if (type.equals(EntityType.SPLASH_POTION)) return new SplashPotion(uuid, pos);
			if (type.equals(EntityType.THROWN_EXP_BOTTLE)) return new ThrownExpBottle(uuid, pos);
			if (type.equals(EntityType.ITEM_FRAME)) return new ItemFrame(uuid, pos);
			if (type.equals(EntityType.WITHER_SKULL)) return new WitherSkull(uuid, pos);
			if (type.equals(EntityType.PRIMED_TNT)) return new PrimedTnt(uuid, pos);
			if (type.equals(EntityType.FALLING_BLOCK)) return new FallingBlock(uuid, pos);
			if (type.equals(EntityType.FIREWORK)) return new Firework(uuid, pos);
			if (type.equals(EntityType.HUSK)) return new Husk(uuid, pos);
			if (type.equals(EntityType.SPECTRAL_ARROW)) return new SpectralArrow(uuid, pos);
			if (type.equals(EntityType.SHULKER_BULLET)) return new ShulkerBullet(uuid, pos);
			if (type.equals(EntityType.DRAGON_FIREBALL)) return new DragonFireball(uuid, pos);
			if (type.equals(EntityType.ZOMBIE_VILLAGER)) return new ZombieVillager(uuid, pos);
			if (type.equals(EntityType.SKELETON_HORSE)) return new SkeletonHorse(uuid, pos);
			if (type.equals(EntityType.ZOMBIE_HORSE)) return new ZombieHorse(uuid, pos);
			if (type.equals(EntityType.ARMOR_STAND)) return new ArmorStand(uuid, pos);
			if (type.equals(EntityType.DONKEY)) return new Donkey(uuid, pos);
			if (type.equals(EntityType.MULE)) return new Mule(uuid, pos);
			if (type.equals(EntityType.EVOKER_FANGS)) return new EvokerFangs(uuid, pos);
			if (type.equals(EntityType.EVOKER)) return new Evoker(uuid, pos);
			if (type.equals(EntityType.VEX)) return new Vex(uuid, pos);
			if (type.equals(EntityType.VINDICATOR)) return new Vindicator(uuid, pos);
			if (type.equals(EntityType.ILLUSIONER)) return new Illusioner(uuid, pos);
			if (type.equals(EntityType.MINECART_COMMAND)) return new MinecartCommand(uuid, pos);
			if (type.equals(EntityType.BOAT)) return new Boat(uuid, pos);
			if (type.equals(EntityType.MINECART)) return new Minecart(uuid, pos);
			if (type.equals(EntityType.MINECART_CHEST)) return new MinecartChest(uuid, pos);
			if (type.equals(EntityType.MINECART_FURNACE)) return new MinecartFurnace(uuid, pos);
			if (type.equals(EntityType.MINECART_TNT)) return new MinecartTnt(uuid, pos);
			if (type.equals(EntityType.MINECART_HOPPER)) return new MinecartHopper(uuid, pos);
			if (type.equals(EntityType.MINECART_MOB_SPAWNER)) return new MinecartMobSpawner(uuid, pos);
			if (type.equals(EntityType.CREEPER)) return new Creeper(uuid, pos);
			if (type.equals(EntityType.SKELETON)) return new Skeleton(uuid, pos);
			if (type.equals(EntityType.SPIDER)) return new Spider(uuid, pos);
			if (type.equals(EntityType.GIANT)) return new Giant(uuid, pos);
			if (type.equals(EntityType.ZOMBIE)) return new Zombie(uuid, pos);
			if (type.equals(EntityType.SLIME)) return new Slime(uuid, pos);
			if (type.equals(EntityType.GHAST)) return new Ghast(uuid, pos);
			if (type.equals(EntityType.ZOMBIFIED_PIGLIN)) return new ZombifiedPiglin(uuid, pos);
			if (type.equals(EntityType.ENDERMAN)) return new Enderman(uuid, pos);
			if (type.equals(EntityType.CAVE_SPIDER)) return new CaveSpider(uuid, pos);
			if (type.equals(EntityType.SILVERFISH)) return new Silverfish(uuid, pos);
			if (type.equals(EntityType.BLAZE)) return new Blaze(uuid, pos);
			if (type.equals(EntityType.MAGMA_CUBE)) return new MagmaCube(uuid, pos);
			if (type.equals(EntityType.ENDER_DRAGON)) return new EnderDragon(uuid, pos);
			if (type.equals(EntityType.WITHER)) return new Wither(uuid, pos);
			if (type.equals(EntityType.BAT)) return new Bat(uuid, pos);
			if (type.equals(EntityType.WITCH)) return new Witch(uuid, pos);
			if (type.equals(EntityType.ENDERMITE)) return new Endermite(uuid, pos);
			if (type.equals(EntityType.GUARDIAN)) return new Guardian(uuid, pos);
			if (type.equals(EntityType.SHULKER)) return new Shulker(uuid, pos);
			if (type.equals(EntityType.PIG)) return new Pig(uuid, pos);
			if (type.equals(EntityType.SHEEP)) return new Sheep(uuid, pos);
			if (type.equals(EntityType.COW)) return new Cow(uuid, pos);
			if (type.equals(EntityType.CHICKEN)) return new Chicken(uuid, pos);
			if (type.equals(EntityType.SQUID)) return new Squid(uuid, pos);
			if (type.equals(EntityType.WOLF)) return new Wolf(uuid, pos);
			if (type.equals(EntityType.MUSHROOM_COW)) return new MushroomCow(uuid, pos);
			if (type.equals(EntityType.SNOWMAN)) return new Snowman(uuid, pos);
			if (type.equals(EntityType.OCELOT)) return new Ocelot(uuid, pos);
			if (type.equals(EntityType.IRON_GOLEM)) return new IronGolem(uuid, pos);
			if (type.equals(EntityType.HORSE)) return new Horse(uuid, pos);
			if (type.equals(EntityType.RABBIT)) return new Rabbit(uuid, pos);
			if (type.equals(EntityType.POLAR_BEAR)) return new PolarBear(uuid, pos);
			if (type.equals(EntityType.LLAMA)) return new Llama(uuid, pos);
			if (type.equals(EntityType.LLAMA_SPIT)) return new LlamaSpit(uuid, pos);
			if (type.equals(EntityType.PARROT)) return new Parrot(uuid, pos);
			if (type.equals(EntityType.VILLAGER)) return new Villager(uuid, pos);
			if (type.equals(EntityType.ENDER_CRYSTAL)) return new EnderCrystal(uuid, pos);
			if (type.equals(EntityType.TURTLE)) return new Turtle(uuid, pos);
			if (type.equals(EntityType.PHANTOM)) return new Phantom(uuid, pos);
			if (type.equals(EntityType.TRIDENT)) return new Trident(uuid, pos);
			if (type.equals(EntityType.COD)) return new Cod(uuid, pos);
			if (type.equals(EntityType.SALMON)) return new Salmon(uuid, pos);
			if (type.equals(EntityType.PUFFERFISH)) return new Pufferfish(uuid, pos);
			if (type.equals(EntityType.TROPICAL_FISH)) return new TropicalFish(uuid, pos);
			if (type.equals(EntityType.DROWNED)) return new Drowned(uuid, pos);
			if (type.equals(EntityType.DOLPHIN)) return new Dolphin(uuid, pos);
			if (type.equals(EntityType.CAT)) return new Cat(uuid, pos);
			if (type.equals(EntityType.PANDA)) return new Panda(uuid, pos);
			if (type.equals(EntityType.PILLAGER)) return new Pillager(uuid, pos);
			if (type.equals(EntityType.RAVAGER)) return new Ravager(uuid, pos);
			if (type.equals(EntityType.TRADER_LLAMA)) return new TraderLlama(uuid, pos);
			if (type.equals(EntityType.WANDERING_TRADER)) return new WanderingTrader(uuid, pos);
			if (type.equals(EntityType.FOX)) return new Fox(uuid, pos);
			if (type.equals(EntityType.BEE)) return new Bee(uuid, pos);
			if (type.equals(EntityType.HOGLIN)) return new Hoglin(uuid, pos);
			if (type.equals(EntityType.PIGLIN)) return new Piglin(uuid, pos);
			if (type.equals(EntityType.STRIDER)) return new Strider(uuid, pos);
			if (type.equals(EntityType.ZOGLIN)) return new Zoglin(uuid, pos);
			if (type.equals(EntityType.PIGLIN_BRUTE)) return new PiglinBrute(uuid, pos);
			if (type.equals(EntityType.AXOLOTL)) return new Axolotl(uuid, pos);
			if (type.equals(EntityType.GLOW_ITEM_FRAME)) return new GlowItemFrame(uuid, pos);
			if (type.equals(EntityType.GLOW_SQUID)) return new GlowSquid(uuid, pos);
			if (type.equals(EntityType.GOAT)) return new Goat(uuid, pos);
			if (type.equals(EntityType.MARKER)) return new Marker(uuid, pos);
			if (type.equals(EntityType.ALLAY)) return new Allay(uuid, pos);
			if (type.equals(EntityType.CHEST_BOAT)) return new ChestBoat(uuid, pos);
			if (type.equals(EntityType.FROG)) return new Frog(uuid, pos);
			if (type.equals(EntityType.TADPOLE)) return new Tadpole(uuid, pos);
			if (type.equals(EntityType.WARDEN)) return new Warden(uuid, pos);
			if (type.equals(EntityType.FISHING_HOOK)) return new FishingHook(uuid, pos);
			if (type.equals(EntityType.LIGHTNING)) return new Lightning(uuid, pos);
			if (type.equals(EntityType.PLAYER)) return new Player(uuid, pos);
            return null;
        });
    }
}