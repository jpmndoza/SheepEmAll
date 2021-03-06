package mx.itesm.sheep;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Muestra una pantalla inicial durante cierto tiempo.
 */

class SplashScreen extends ScreenTemplate
{
    private SheepEm sheepEm;
    private float tiempo;   // Tiempo transcurrido
    private Texture logo_itesm;


    public SplashScreen(SheepEm sheepEm) {
        this.sheepEm = sheepEm;
    }
    @Override
    public void show() {
        tiempo = 0;
        logo_itesm = new Texture("logo_itesm.png");
    }

    @Override
    public void render(float delta) {
        clearScreen(1, 1, 1);
        // Dibuja
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(logo_itesm,0,100);

        batch.end();
        // Actualiza
        tiempo += Gdx.graphics.getDeltaTime();  // Acumula tiempo
        if (tiempo>=2.5) {
            sheepEm.setScreen(new LoadingScreen(sheepEm));
        }

        if (pref.getBoolean("playedLevel1")){
            pref.putBoolean("musicOn",pref.getBoolean("musicOn"));
            pref.flush();
        }else{
            pref.putBoolean("musicOn",true);
            pref.putBoolean("fxOn",true);
            pref.flush();
        }


    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
