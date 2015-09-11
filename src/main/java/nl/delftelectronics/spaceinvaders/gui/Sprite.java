package nl.delftelectronics.spaceinvaders.gui;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import nl.delftelectronics.spaceinvaders.core.Entity;

public class Sprite {
    private Entity entity;
    private Image image;
    private Double width;
    private Double height;

    public Sprite(Entity entity, Image image) {
        this.entity = entity;
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public void render(GraphicsContext gc) {
        Integer positionX = entity.getPositionX();
        Integer positionY = entity.getPositionY();
        gc.drawImage(image, positionX, positionY);
    }

    public Rectangle2D getBoundingBox() {
        Integer positionX = entity.getPositionX();
        Integer positionY = entity.getPositionY();
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(Sprite sprite) {
        return sprite.getBoundingBox().intersects(getBoundingBox());
    }

    public Entity getEntity() {
        return entity;
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
