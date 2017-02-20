package cn.qiditu.guet.android.openglesexample;


import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import cn.qiditu.guet.android.openglesexample.item.Cube;

class CubeRenderer implements GLSurfaceView.Renderer {

    private Cube cube = new Cube();

    @SuppressWarnings("FieldCanBeLocal")
    boolean canRotation = false;
    private float rotation = -54;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_MODELVIEW);     //紧接着设置模型视图矩阵
        gl.glLoadIdentity();                    //清空矩阵
        //视点变换，将相机位置设置为(0, 0, 3)，同时指向(0, 0, 0)点
        GLU.gluLookAt(gl, 0, 0, 3, 0, 0, 0, 0, 1, 0);

        //设置模型位置旋转及缩放信息
        gl.glTranslatef(0.0f, 0.0f, -5.0f);     //将模型位置设置为(0, 0, -5)
        if(canRotation) {
            //旋转
            rotation -= 0.15f;
        }
            gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
//        }
//        else {
//            gl.glRotatef(30.0f, 0, 1, 0);       //绕模型自身Y轴旋转30度
//            gl.glRotatef(30.0f, 1, 0, 0);       //绕模型自身X轴旋转30度
//        }

        cube.drawSelf(gl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height);
        // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // Reset the projection matrix
        gl.glLoadIdentity();
        // Calculate the aspect ratio of the window
        GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
        // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        // Reset the modelview matrix
        gl.glLoadIdentity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        // Depth buffer setup.
        gl.glClearDepthf(1.0f);
        // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST);
        // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL);
        // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

}