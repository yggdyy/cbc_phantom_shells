package pub.pigeon.yggdyy.cbcps.munitions.shells.phantom_sonic_shell;

import net.minecraft.world.entity.EntityType;
import pub.pigeon.yggdyy.cbcps.CBCPSEntityTypes;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.big_cannon.SimpleShellBlock;

public class PhantomSonicShellBlock extends SimpleShellBlock<PhantomSonicShellProjectile> {
    public PhantomSonicShellBlock(Properties properties) {
        super(properties);
    }
    @Override
    public boolean isBaseFuze() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
    }

    @Override
    public EntityType<? extends PhantomSonicShellProjectile> getAssociatedEntityType() {
        return CBCPSEntityTypes.PHANTOM_SONIC_SHELL.get();
    }
}
