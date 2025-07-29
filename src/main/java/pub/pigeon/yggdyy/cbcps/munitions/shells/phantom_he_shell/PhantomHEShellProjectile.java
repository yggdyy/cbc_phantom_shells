package pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_he_shell;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import pub.pigeon.yggdyy.cbcps.CBCPSBlocks;
import pub.pigeon.yggdyy.cbcps.munitions.shells.AbstractPhantomShellProjectile;
import rbasamoyai.createbigcannons.CreateBigCannons;
import rbasamoyai.createbigcannons.config.CBCCfgMunitions;
import rbasamoyai.createbigcannons.config.CBCConfigs;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.ShellExplosion;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonCommonShellProperties;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonFuzePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;

public class PhantomHEShellProjectile extends AbstractPhantomShellProjectile {
    public PhantomHEShellProjectile(EntityType<? extends PhantomHEShellProjectile> type, Level level) {
        super(type, level);
    }
    @NotNull
    @Override
    protected BigCannonFuzePropertiesComponent getFuzeProperties() {
        return this.getAllProperties().fuze();
    }
    @Override
    protected void detonate(Position position) {
        ShellExplosion exp = new ShellExplosion(this.level(), this, this.indirectArtilleryFire(false), position.x(), position.y(), position.z(), this.getAllProperties().explosion().explosivePower(), false, ((CBCCfgMunitions.GriefState) CBCConfigs.SERVER.munitions.damageRestriction.get()).explosiveInteraction());
        CreateBigCannons.handleCustomExplosion(this.level(), exp);
        //this.level().players().forEach(player -> {player.sendSystemMessage(Component.literal(this.position().toString()));});
    }
    @Override
    public BlockState getRenderedBlockState() {
        return CBCPSBlocks.PHANTOM_HE_SHELL.getDefaultState().setValue(BlockStateProperties.FACING, Direction.NORTH);
    }
    @NotNull
    @Override
    protected BigCannonProjectilePropertiesComponent getBigCannonProjectileProperties() {
        return this.getAllProperties().bigCannonProperties();
    }
    @NotNull
    @Override
    public EntityDamagePropertiesComponent getDamageProperties() {
        return this.getAllProperties().damage();
    }
    @NotNull
    @Override
    protected BallisticPropertiesComponent getBallisticProperties() {
        return this.getAllProperties().ballistics();
    }
    protected BigCannonCommonShellProperties getAllProperties() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this);
    }
}
