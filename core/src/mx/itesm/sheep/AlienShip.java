package mx.itesm.sheep;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Andreu on 13/11/17.
 */

public class AlienShip {

    // Mover direcciones
    private float posicionX = -100;
    private float posicionY = -100;
    private float direccionX = 1;
    private float direccionY = 1;

    // Tamaño
    private int alto = 332;
    private int ancho = 542;

    // Animación
    private Animation animacion;
    private float timer;

    // Estado
    private Estado estado;
    private boolean yaSalio = false;

    public AlienShip(Texture textura, Estado estado){
        TextureRegion region = new TextureRegion(textura);
        TextureRegion[][]  frames = region.split(ancho,alto);
        animacion = new Animation(0.20f, frames[0][0],frames[0][1],frames[0][2]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timer = 0;

        this.estado = estado;
    }


    public void render (SpriteBatch batch) {
        timer += Gdx.graphics.getDeltaTime();
        TextureRegion region = (TextureRegion) animacion.getKeyFrame(timer);
        switch (estado){
            case MOVIENDO:
                batch.draw(region, posicionX, posicionY);
                break;
            case PAUSADO:
                timer = 0;
                break;
            case SALIENDOX:
                batch.draw(region, posicionX, posicionY);
                break;
            case SALIENDOY:
                batch.draw(region, posicionX, posicionY);
                break;
            case DERROTA:
                if (posicionX != 1080){
                    posicionX += 6.5f;
                    batch.draw(region, posicionX, posicionY);
                }else {
                    timer = 0;
                }
                break;
            case INICIO:
                break;
            case BOSS:
                batch.draw(region, posicionX,posicionY);
                break;
        }
    }

    // mueve la nave
    public void spaceShipMove(float xM, float yM){
        posicionX = xM;
        posicionY = yM;
    }

    // invierte la dirección en x
    public void cambiarDireccionX(){
        direccionX *= -1;
    }

    // invierte la dirección en y
    public void cambiarDireccionY(){
        direccionY *= -1;
    }

    public Estado saliendoPor() {
        if (this.posicionX >= 1080-this.ancho ){
            this.setEstado(Estado.SALIENDOX);
            this.posicionX = 1080-this.ancho;
            return this.getEstado();
        }
        if (this.posicionY >=1920-this.alto){
            this.setEstado(Estado.SALIENDOY);
            this.posicionY = 1920-this.alto;
            return this.getEstado();
        }
        if (this.posicionX <= 0 ){
            this.setEstado(Estado.SALIENDOX);
            this.posicionX = 0;
            return this.getEstado();
        }
        if (this.posicionY <=0 ){
            this.setEstado(Estado.SALIENDOY);
            this.posicionY = 0;
            return this.getEstado();
        }
        else {
            return Estado.PAUSADO;
        }
    }

    public float getDireccionX() {
        return direccionX;
    }

    public float getDireccionY() {
        return direccionY;
    }

    public enum Estado{
        MOVIENDO,
        PAUSADO,
        SALIENDOX,
        SALIENDOY,
        DERROTA,
        INICIO,
        BOSS
    }

    public float getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(float posicionX) {
        this.posicionX = posicionX;
    }

    public float getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(float posicionY) {
        this.posicionY = posicionY;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getAlto() { return alto; }

    public int getAncho() { return ancho; }

    public void hideShip(){
        this.setEstado(Estado.INICIO);
    }

    public void setYaSalio(boolean yaSalio) {
        this.yaSalio = yaSalio;
    }

    public boolean getYaSalio() {
        return yaSalio;
    }
}
