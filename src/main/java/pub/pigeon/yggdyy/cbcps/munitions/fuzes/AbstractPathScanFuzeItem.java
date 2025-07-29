package pub.pigeon.yggdyy.cbcps.munitions.fuzes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.munitions.AbstractCannonProjectile;
import rbasamoyai.createbigcannons.munitions.fuzes.FuzeItem;

public abstract class AbstractPathScanFuzeItem extends FuzeItem {
    public AbstractPathScanFuzeItem(Properties properties) {
        super(properties);
    }
    public abstract boolean detonateCheck(Level level, Vec3 position, ItemStack stack, AbstractCannonProjectile projectile);
    public double scanStepLength(ItemStack stack, AbstractCannonProjectile projectile) {return 0.5;}
    @Override
    public boolean onProjectileTick(ItemStack stack, AbstractCannonProjectile projectile) {
        Level level = projectile.level();
        Vec3 pre = new Vec3(projectile.xOld, projectile.yOld, projectile.zOld),
                now = projectile.position();
        Vec3 step = now.add(pre.scale(-1)).normalize().scale(scanStepLength(stack, projectile));
        if(step.equals(Vec3.ZERO)) {
            return detonateCheck(level, now, stack, projectile);
        } else {
            Vec3 p = pre;
            while(true) {
                if(detonateCheck(level, p, stack, projectile)) {
                    projectile.setPos(p);
                    return true;
                }
                p = p.add(step);
                if(p.distanceToSqr(pre) > now.distanceToSqr(pre)) return false;
            }
        }
    }
}
