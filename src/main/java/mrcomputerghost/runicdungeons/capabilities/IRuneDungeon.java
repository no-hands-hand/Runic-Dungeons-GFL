package mrcomputerghost.runicdungeons.capabilities;

import net.minecraft.util.math.BlockPos;

/**
 * @author jiGGO
 * @date 2020/8/4
 */
public interface IRuneDungeon {

    int getDimension();

    void setDimension(int id);

    BlockPos getPosValue();

    void setPosValue(BlockPos pos);

    int getScore();

    void setScore(int score);

}