package cn.qiditu.guet.android.openglesexample;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GLSurfaceView glSurfaceView = (GLSurfaceView)this.findViewById(R.id.glSurfaceView);
        final CubeRenderer renderer = new CubeRenderer();
        glSurfaceView.setRenderer(renderer);
        glSurfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderer.canRotation = !renderer.canRotation;
            }
        });
    }
}
