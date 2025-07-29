package pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_sonic_shell;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import pub.pigeon.yggdyy.cbcps.CBCPSBlocks;
import pub.pigeon.yggdyy.cbcps.CBCPSConfig;
import pub.pigeon.yggdyy.cbcps.munitions.shells.AbstractPhantomShellProjectile;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonCommonShellProperties;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonFuzePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;

public class PhantomSonicShellProjectile extends AbstractPhantomShellProjectile {
    public PhantomSonicShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }
    @NotNull
    @Override
    protected BigCannonFuzePropertiesComponent getFuzeProperties() {
        return getAllProperties().fuze();
    }
    @Override
    protected void detonate(Position position) {
        if(level().isClientSide) return;
        ServerLevel level = (ServerLevel) level();
        Vec3 center = new Vec3(position.x(), position.y(), position.z());
        double radius = getBoomRadius();
        float baseDamage = getBaseBoomDamage();
        double baseKnock = getBaseKnockBack();
        int n = 12;
        for(int i = 0; i < n; ++i) {
            for(int j = (-n / 4) + 1; j < n / 4; ++j) {
                double a = Math.PI * 2 / n * i, b = Math.PI * 2 / n * j;
                Vec3 _delta = new Vec3(radius * Math.cos(b) * Math.cos(a), radius * Math.sin(b), radius * Math.cos(b) * Math.sin(a));
                level.sendParticles(
                        ParticleTypes.SONIC_BOOM,
                        center.x, center.y, center.z,
                        (int) (radius * 4 + 1),
                        _delta.x / 2.0, _delta.y / 2.0, _delta.z / 2.0,
                        radius
                );
            }
        }
        playSound(SoundEvents.WARDEN_SONIC_BOOM, 3.0f, 1.0f);
        level.getEntities(this, new AABB(center.add(new Vec3(-radius, -radius, -radius)), center.add(new Vec3(radius, radius, radius)))).stream()
                .filter(entity -> entity instanceof LivingEntity && entity.position().distanceToSqr(center) <= radius * radius)
                .map(entity -> (LivingEntity)entity)
                .forEach(victim -> {
                    Vec3 delta = victim.position().add(center.scale(-1));
                    /*level.sendParticles(
                            ParticleTypes.SONIC_BOOM,
                            center.x, center.y, center.z,
                            (int) (delta.length() * 4 + 1),
                            delta.x, delta.y, delta.z,
                            radius * 2
                    );*/
                    double fac = 1 - 0.5 * delta.length() / radius;
                    victim.hurt(this.damageSources().sonicBoom(this), (float) (baseDamage * fac));
                    Vec3 push = delta.normalize().scale(baseKnock);
                    victim.push(push.x * fac, push.y * fac, push.z * fac);
                });
    }
    @Override
    public BlockState getRenderedBlockState() {
        return CBCPSBlocks.PHANTOM_SONIC_SHELL.getDefaultState().setValue(BlockStateProperties.FACING, Direction.NORTH);
    }
    @NotNull
    @Override
    protected BigCannonProjectilePropertiesComponent getBigCannonProjectileProperties() {
        return getAllProperties().bigCannonProperties();
    }
    @NotNull
    @Override
    public EntityDamagePropertiesComponent getDamageProperties() {
        return getAllProperties().damage();
    }
    @NotNull
    @Override
    protected BallisticPropertiesComponent getBallisticProperties() {
        return getAllProperties().ballistics();
    }
    protected BigCannonCommonShellProperties getAllProperties() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this);
    }
    public static double getBoomRadius() {return CBCPSConfig.PHANTOM_SONIC_SHELL_DAMAGE_RADIUS.get();}
    public static float getBaseBoomDamage() {return (float) (1f * CBCPSConfig.PHANTOM_SONIC_SHELL_BASE_DAMAGE.get());}
    public static double getBaseKnockBack() {return CBCPSConfig.PHANTOM_SONIC_SHELL_BASE_KNOCK_BACK.get();}
}
