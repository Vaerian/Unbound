package com.cumulusmc.unbound.block;

import com.cumulusmc.unbound.init.UnboundBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class EphemeralGlass extends AbstractGlassBlock {
    public EphemeralGlass() {
        super(FabricBlockSettings.of(Material.GLASS, MapColor.BRIGHT_TEAL).nonOpaque().dynamicBounds().allowsSpawning(UnboundBlocks::never).solidBlock(UnboundBlocks::never).suffocates(UnboundBlocks::never).blockVision(UnboundBlocks::never));
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity) || entity.getBlockStateAtPos().isOf(this) || world.getBlockState(entity.getBlockPos().up()).isOf(this)) {
            entity.slowMovement(state, new Vec3d(0.8999999761581421D, 1.5D, 0.8999999761581421D));
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext) {
            EntityShapeContext entityShapeContext = (EntityShapeContext) context;
            Optional<Entity> optional = entityShapeContext.getEntity();

            if (optional.isPresent()) {
                Entity entity = optional.get();

                boolean inAlready = blocksWithin(entity.getBoundingBox()).stream().anyMatch((ipos) -> world.getBlockState(ipos).isOf(this));

                if (!inAlready) {
                    if (entity.getBlockPos().getY() > pos.getY() || entity.getBlockPos().getY() - pos.getY() <= -2) {
                        double velocity = Math.abs(entity.getVelocity().getY());
                        if (velocity <= 0.4448259643949201 /*The maximum speed downwards at which a player can travel after jumping from ground height*/) {
                            return VoxelShapes.fullCube();
                        }
                    } else {
                        double horizonal = entity.getVelocity().horizontalLengthSquared();
                        if (horizonal > 0 && horizonal <= 0.0466 /*The maximum horizonal speed a player can travel from walking*/) {
                            return VoxelShapes.fullCube();
                        }
                    }
                }
            }
        }

        return VoxelShapes.empty();
    }

    private static Collection<BlockPos> blocksWithin(Box box) {
        Collection<BlockPos> pos = new ArrayList<>();
        for (int x = (int) Math.floor(box.minX); x < Math.ceil(box.maxX); x++) {
            for (int y = (int) Math.floor(box.minY); y < Math.ceil(box.maxY); y++) {
                for (int z = (int) Math.floor(box.minZ); z < Math.ceil(box.maxZ); z++) {
                    pos.add(new BlockPos(x, y, z));
                }
            }
        }
        return pos;
    }
}
