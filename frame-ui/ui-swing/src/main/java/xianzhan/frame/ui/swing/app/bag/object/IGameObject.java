package xianzhan.frame.ui.swing.app.bag.object;

import java.awt.Graphics;

/**
 * 定义游戏对象行为
 *
 * @author xianzhan
 * @since 2023-10-30
 */
public interface IGameObject {

    /**
     * 每隔一段时间，游戏对象要做的计算
     */
    void tick();

    /**
     * 游戏对象计算完后进行渲染
     *
     * @param g 进行渲染
     */
    void render(Graphics g);
}
