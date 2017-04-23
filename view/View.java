package fr.game.epis.engine.core.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import fr.game.epis.engine.R;
import fr.game.epis.engine.core.Renderer;

/**
 * Created by thomas on 22/04/2017.
 */

public class View{
    private Bitmap bitmapBob;

    public View(Context context){
        bitmapBob = BitmapFactory.decodeResource(context.getResources(), R.drawable.scifiunit_11);
    }

    public void update(){

    }

    public void draw(Renderer renderer){
        renderer.getCanvas().drawColor(Color.argb(255,  26, 128, 182));
        renderer.getPaint().setColor(Color.argb(255,  249, 129, 0));
        renderer.getPaint().setTextSize(45);
        for (int i = 0; i<500; i++){
            renderer.getCanvas().drawBitmap(this.bitmapBob, 5*i, 200, renderer.getPaint());
        }
    }
}


