package net.leo.herotech.blockentity;

import net.leo.herotech.blockentity.screen.CompressorMenu;
import net.leo.herotech.recipe.CompressorRecipe;
import net.leo.herotech.util.ElectricTier;
import net.leo.herotech.util.IElectricBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CompressorBlockEntity extends BlockEntity implements MenuProvider, IElectricBlock {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(2);
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private int storedEnergy = 300;
    private final int maxEnergy = 300;
    private ElectricTier tier = ElectricTier.LOW;

    protected final ContainerData data;
    private int progress;
    private int maxProgress = 72;


    public CompressorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.COMPRESSOR.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                switch (pIndex){
                    case 0: return CompressorBlockEntity.this.progress;
                    case 1: return CompressorBlockEntity.this.maxProgress;
                    case 2: return CompressorBlockEntity.this.storedEnergy;
                    case 3: return CompressorBlockEntity.this.maxEnergy;
                    default: return 0;
                }
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0: CompressorBlockEntity.this.progress = pValue; break;
                    case 1: CompressorBlockEntity.this.maxProgress = pValue; break;
                    case 2: CompressorBlockEntity.this.storedEnergy = pValue; break;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Compressor");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new CompressorMenu(pContainerId, pInventory, this, this.data);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemStackHandler.serializeNBT());
        tag.putInt("compressor.progress", progress);
        tag.putInt("compressor.storedEnergy", storedEnergy);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemStackHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("compressor.progress");
        storedEnergy = nbt.getInt("compressor.storedEnergy");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, CompressorBlockEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity)) {
            System.out.println("has recipe");
            if (pBlockEntity.storedEnergy > 0) {
                System.out.println("has energy");
                pBlockEntity.progress++;
                pBlockEntity.charge(-1);
                setChanged(pLevel, pPos, pState);
                if (pBlockEntity.progress >= pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity);
                }
            }
        }
    }

    @Override
    public ElectricTier getTier() {
        return this.tier;
    }

    @Override
    public boolean isGenerator() {
        return false;
    }

    @Override
    public int getStoredEnergy() {
        return this.storedEnergy;
    }

    @Override
    public void charge(int amount) {
        this.storedEnergy += amount;
    }

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack out) {
        return inventory.getItem(1).getItem() == out.getItem() || inventory.getItem(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(1).getMaxStackSize() > inventory.getItem(1).getCount();
    }

    private static boolean hasRecipe(CompressorBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());
        for (int i = 0; i < entity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }

        Optional<CompressorRecipe> match = level.getRecipeManager()
                .getRecipeFor(CompressorRecipe.Type.INSTANCE, inventory, level);

        System.out.println("match"+match.isPresent());
        //System.out.println("item"+canInsertItemIntoOutputSlot(inventory, match.get().getResultItem()));
        System.out.println("amount"+canInsertAmountIntoOutputSlot(inventory));

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());
    }

    private static void craftItem(CompressorBlockEntity entity){
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemStackHandler.getSlots());

        for (int i = 0; i < entity.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemStackHandler.getStackInSlot(i));
        }

        Optional<CompressorRecipe> match = level.getRecipeManager().getRecipeFor(
                CompressorRecipe.Type.INSTANCE, inventory, level);

        if (match.isPresent()){
            if (entity.itemStackHandler.getStackInSlot(0).getCount() >= 9) {
                entity.itemStackHandler.extractItem(0, 9, false);

                entity.itemStackHandler.setStackInSlot(1, new ItemStack(match.get().getResultItem().getItem(),
                        entity.itemStackHandler.getStackInSlot(1).getCount() + 1));
                entity.resetProgress();
            }
        }
    }
}
