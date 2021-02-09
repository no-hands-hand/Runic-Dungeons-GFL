package mrcomputerghost.runicdungeons.recipes;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

/**
 * @author ji_GGO
 * @date 16/1/2021
 */
public class BaubleRecipe extends ShapedRecipes {

    public BaubleRecipe(String group, int width, int height, NonNullList<Ingredient> ingredients, ItemStack result) {
        super(group, width, height, ingredients, result);
    }

    public boolean matches(InventoryCrafting inv, World worldIn) {
        for (int i = 0; i <= inv.getWidth() - this.recipeWidth; ++i) {
            for (int j = 0; j <= inv.getHeight() - this.recipeHeight; ++j) {
                if (this.checkMatch(inv, i, j, true)) {
                    return true;
                }

                if (this.checkMatch(inv, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkMatch(InventoryCrafting crafting, int p_77573_2_, int p_77573_3_, boolean flag) {
        for (int i = 0; i < crafting.getWidth(); ++i) {
            for (int j = 0; j < crafting.getHeight(); ++j) {
                int k = i - p_77573_2_;
                int l = j - p_77573_3_;
                Ingredient ingredient = Ingredient.EMPTY;

                if (k >= 0 && l >= 0 && k < this.recipeWidth && l < this.recipeHeight) {
                    if (flag) {
                        ingredient = this.recipeItems.get(this.recipeWidth - k - 1 + l * this.recipeWidth);
                    } else {
                        ingredient = this.recipeItems.get(k + l * this.recipeWidth);
                    }
                }

                ItemStack itemStack = crafting.getStackInRowAndColumn(i, j);
                if (!ingredient.apply(itemStack)) {
                    return false;
                }

                if (itemStack.getItem() instanceof ItemPotion) {
                    if (!apply(ingredient, itemStack)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean apply(Ingredient ingredient, @Nullable ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        else {
            Field field = ReflectionHelper.findField(Ingredient.class, "matchingStacks",
                    "field_193371_b");
            field.setAccessible(true);
            try {
                ItemStack[] matchingStacks = (ItemStack[]) field.get(ingredient);
                for (ItemStack itemstack : matchingStacks) {
                    if (itemstack.getItem() == itemStack.getItem()) {
                        int i = itemstack.getMetadata();

                        if (i == 32767 || i == itemStack.getMetadata()) {
                            if (itemStack.getTagCompound().equals(itemstack.getTagCompound())) {
                                return true;
                            }
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                RunicDungeons.LOGGER.error(e.getMessage());
            }

            return false;
        }
    }

}