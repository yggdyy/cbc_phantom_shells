package pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_teleport_shell;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class PhantomTeleportShellProjectile extends AbstractPhantomShellProjectile {
    public List<LivingEntity> divers = null;
    public PhantomTeleportShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }
    @NotNull
    @Override
    protected BigCannonFuzePropertiesComponent getFuzeProperties() {
        return getAllProperties().fuze();
    }
    @Override
    protected void detonate(Position pos) {
        if(level().isClientSide || divers == null) return;
        ServerLevel level = (ServerLevel) level();
        divers.stream().filter(e -> e.isAlive() && level.equals(e.level()))
                .forEach(e -> {
                    e.playSound(SoundEvents.SHULKER_TELEPORT, 1f, 1f);
                    e.teleportTo(pos.x(), pos.y(), pos.z());
                });
        playSound(SoundEvents.ENDERMAN_TELEPORT, 1f, 1f);
        level.sendParticles(ParticleTypes.REVERSE_PORTAL, pos.x(), pos.y(), pos.z(), 10, 0.5, 0.5, 0.5, 1);
    }
    @Override
    public BlockState getRenderedBlockState() {
        return CBCPSBlocks.PHANTOM_TELEPORT_SHELL.getDefaultState().setValue(BlockStateProperties.FACING, Direction.NORTH);
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
    public static double getScanRadius() {return CBCPSConfig.PHANTOM_TELEPORT_SHELL_SCAN_RADIUS.get();}
    public static int getMaxDiverAmount() {return CBCPSConfig.PHANTOM_TELEPORT_SHELL_MAX_DIVER_AMOUNT.get();}
    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if(level().isClientSide) return;
        if(divers == null) return;
        tag.putInt("divers_amount", divers.size());
        for(int i = 0; i < divers.size(); ++i)
            tag.putUUID("divers_" + i, divers.get(i).getUUID());
    }
    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if(level().isClientSide) return;
        ServerLevel level = (ServerLevel) level();
        if(tag.contains("divers_amount")) {
            int n = tag.getInt("divers_amount");
            divers = new ArrayList<>(List.of());
            for(int i = 0; i < n; ++i) {
                UUID id = tag.getUUID("divers_" + i);
                if(level.getEntity(id) != null && level.getEntity(id) instanceof LivingEntity diver)
                    divers.add(diver);
            }
        }
    }
    @Override
    public void tick() {
        super.tick();
        if(level().isClientSide) return;
        ServerLevel level = (ServerLevel) level();
        if(divers == null) {
            double radius = getScanRadius();
            int max = getMaxDiverAmount();
            Vec3 center = new Vec3(xOld, yOld, zOld);
            divers = level.getEntities(this, new AABB(center.add(-radius, -radius, -radius), center.add(radius, radius, radius))).stream()
                    .filter(e -> e instanceof LivingEntity && e.position().distanceToSqr(center) <= radius * radius)
                    .sorted(Comparator.comparingDouble(e -> e.position().distanceToSqr(center)))
                    .limit(max)
                    .map(e -> (LivingEntity) e)
                    .toList();
        } else {
            if(level.dayTime() % 4 != 0) return;
            divers.forEach(e -> {
                Vec3 dp = e.position().add(0, e.getEyeHeight() * (1.0 - 1.0 / (1.0 + e.position().distanceTo(position()) / 100.0)), 0);
                level.sendParticles(
                        ParticleTypes.END_ROD,
                        dp.x, dp.y, dp.z,
                        1,
                        0.1, 0, 0.1,
                        1
                );
            });
        }
    }
}
