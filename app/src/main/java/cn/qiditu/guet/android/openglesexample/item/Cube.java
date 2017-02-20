package cn.qiditu.guet.android.openglesexample.item;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Cube {

    //每一个顶点都是由x/y/z三个坐标表示的。对于一个立方体，有八个顶点。
    //每个顶点的位置是先下到上，在每一层是由里到外逆时针方向标识
    @SuppressWarnings("FieldCanBeLocal")
    private float[] vertices = {
            -1.0f,  -1.0f,  -1.0f,
             1.0f,  -1.0f,  -1.0f,
             1.0f,   1.0f,  -1.0f,
            -1.0f,   1.0f,  -1.0f,
            -1.0f,  -1.0f,   1.0f,
             1.0f,  -1.0f,   1.0f,
             1.0f,   1.0f,   1.0f,
            -1.0f,   1.0f,   1.0f
    };
    //每个顶点的颜色由四个数字表示：RED/GREEN/BLUE/ALPHA,最后一个透明度是可选的
    @SuppressWarnings("FieldCanBeLocal")
    private float[] colors = {
            0.0f,  1.0f,  0.0f,  1.0f,
            0.0f,  1.0f,  0.0f,  1.0f,
            1.0f,  0.5f,  0.0f,  1.0f,
            1.0f,  0.5f,  0.0f,  1.0f,
            1.0f,  0.0f,  0.0f,  1.0f,
            1.0f,  0.0f,  0.0f,  1.0f,
            0.0f,  0.0f,  1.0f,  1.0f,
            1.0f,  0.0f,  1.0f,  1.0f
    };
    //这里有点复杂，每个数字对应于正方体中的每个特定的点。比如0代表立方体原点，就是立方体下面一层中最后面的那个点。
    //那么0,4,5对应的就是立方体侧面上的一个三角，每个面都有两个三角组成。
    //这样，整个立方体有六个面，就有12个三角面了。
    //注意：点的排列顺序对于显示效果有很大的影响。比如：0,4,5和0,5,4就不一样。
    //之所以会这样的原因是因为，调用了gl.glFrontFace(gl.GL_CW).
    //这样我们需要以顺时针的顺序来指定可视的三角面。
    @SuppressWarnings("FieldCanBeLocal")
    private byte[] indices = {
            0, 4, 5,    0, 5, 1,
            1, 5, 6,    1, 6, 2,
            2, 6, 7,    2, 7, 3,
            3, 7, 4,    3, 4, 0,
            4, 7, 6,    4, 6, 5,
            3, 0, 1,    3, 1, 2
    };

    private FloatBuffer vertexBuffer;
    private FloatBuffer colorBuffer;
    private ByteBuffer indexBuffer;

    public Cube() {
        //这里使用ByteBuffer来做缓冲处理。必须通过ByteBuffer.allocateDirect()方法来实例化ByteBuffer对象
        //这些buffer必须放到本地堆栈中，以使垃圾回收器不能移除它们
        //这些缓冲将作为参数传入到gl*Pointer()方法中
        //对于不同原数据类型的Buffers需要将byte设置成对应的顺序
        vertexBuffer = ByteBuffer.allocateDirect(vertices.length * 4)
                                    .order(ByteOrder.nativeOrder())
                                    .asFloatBuffer();
        vertexBuffer.put(vertices).position(0);

        colorBuffer = ByteBuffer.allocateDirect(colors.length * 4)
                                .order(ByteOrder.nativeOrder())
                                .asFloatBuffer();
        colorBuffer.put(colors).position(0);

        indexBuffer = ByteBuffer.allocateDirect(indices.length * 4);
        indexBuffer.put(indices).position(0);
    }

    //让Cube来绘制自己，传入GL10g
    public void drawSelf(GL10 gl)
    {
        gl.glFrontFace(GL10.GL_CW);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl.glDrawElements(GL10.GL_TRIANGLES, 36, GL10.GL_UNSIGNED_BYTE, indexBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }

}

