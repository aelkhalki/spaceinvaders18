package nl.delftelectronics.spaceinvaders.gui;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import nl.delftelectronics.spaceinvaders.core.Actor;

public class Sprite {
    private Actor actor;
    private Image image;
    private Double width;
    private Double height;

    public Sprite(Actor actor, Image image) {
        this.actor = actor;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void render(GraphicsContext gc) {
        Integer positionX = actor.getPositionX();
        Integer positionY = actor.getPositionY();
        gc.drawImage(image, positionX, positionY);
    }
    
    public Rectangle2D getBoundingBox() {
        Integer positionX = actor.getPositionX();
        Integer positionY = actor.getPositionY();
        return new Rectangle2D(positionX,positionY,width,height);
    }
    
    public boolean intersects(Sprite sprite) {
        return sprite.getBoundingBox().intersects(getBoundingBox());
    }

    public Actor getActor() {
        return actor;
    }

    public Image getImage() {
        return image;
    }

    public Double getWidth() {
        return width;
    }

    public Double getHeight() {
        return height;
    }
}
