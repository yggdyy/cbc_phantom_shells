package pub.pigeon.yggdyy.cbcps.munitions.shells;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import rbasamoyai.createbigcannons.munitions.ProjectileContext;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public abstract class AbstractPhantomShellProjectile extends FuzedBigCannonProjectile {
    protected AbstractPhantomShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }
    @Override
    protected ImpactResult calculateBlockPenetration(ProjectileContext projectileContext, BlockState state, BlockHitResult blockHitResult) {
        return new ImpactResult(ImpactResult.KinematicOutcome.PENETRATE, false);
    }
}
