package pub.pigeon.yggdyy.cbcps;

import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_he_shell.PhantomHEShellProjectile;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_sonic_shell.PhantomSonicShellProjectile;
import pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_teleport_shell.PhantomTeleportShellProjectile;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.multiloader.EntityTypeConfigurator;
import rbasamoyai.createbigcannons.munitions.big_cannon.AbstractBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.BigCannonProjectileRenderer;
import rbasamoyai.createbigcannons.munitions.config.MunitionPropertiesHandler;
import rbasamoyai.createbigcannons.munitions.config.PropertiesTypeHandler;
import rbasamoyai.ritchiesprojectilelib.RPLTags;

import java.util.function.Consumer;

import static pub.pigeon.yggdyy.cbcps.CBCPSMain.REGISTRATE;
public class CBCPSEntityTypes {
    public static final EntityEntry<PhantomHEShellProjectile> PHANTOM_HE_SHELL = cannonProjectile("phantom_he_shell", PhantomHEShellProjectile::new, "Phantom High Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<PhantomSonicShellProjectile> PHANTOM_SONIC_SHELL = cannonProjectile("phantom_sonic_shell",PhantomSonicShellProjectile::new, "Phantom Sonic Boom Shell",CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<PhantomTeleportShellProjectile> PHANTOM_TELEPORT_SHELL = cannonProjectile("phantom_teleport_shell", PhantomTeleportShellProjectile::new, "Phantom Teleport Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);

    /* following are some code from CBCPS (by Cainiao1053) [https://github.com/Cainiao1053/cbcmoreshells/blob/main/src/main/java/com/cainiao1053/cbcmoreshells/CBCMSEntityTypes.java] */
    // The great Cainiao1053 will MVGA!!!
    private static <T extends AbstractBigCannonProjectile> EntityEntry<T>
    cannonProjectile(String id, EntityType.EntityFactory<T> factory, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(cannonProperties())
                .renderer(() -> BigCannonProjectileRenderer::new)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }
    private static <T extends AbstractBigCannonProjectile> EntityEntry<T>
    cannonProjectile(String id, EntityType.EntityFactory<T> factory, String enUSdiffLang, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(cannonProperties())
                .renderer(() -> BigCannonProjectileRenderer::new)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }
    private static <T> NonNullConsumer<T> configure(Consumer<EntityTypeConfigurator> cons) {
        return b -> cons.accept(EntityTypeConfigurator.of(b));
    }
    private static <T> NonNullConsumer<T> autocannonProperties() {
        return configure(c -> c.size(0.2f, 0.2f)
                .fireImmune()
                .updateInterval(1)
                .updateVelocity(false) // Mixin ServerEntity to not track motion
                .trackingRange(16));
    }
    private static <T> NonNullConsumer<T> cannonProperties() {
        return configure(c -> c.size(0.8f, 0.8f)
                .fireImmune()
                .updateInterval(1)
                .updateVelocity(false) // Ditto
                .trackingRange(16));
    }
    private static <T> NonNullConsumer<T> shrapnel() {
        return configure(c -> c.size(0.8f, 0.8f)
                .fireImmune()
                .updateInterval(1)
                .updateVelocity(true)
                .trackingRange(16));
    }

    public static void init() {}
}
