package nl.delftelectronics.spaceinvaders.core;

public class Ship extends Actor {
    private static final String FILENAME = "/ship.png";
    private static int SHIELD = 0;
    private static int BOMB = 0;
    private static int LIFE = 0;
    private static int VERTICAL = 0;
    public Ship(Integer positionX, Integer positionY, Integer width, Integer height, int bomb, int shield, int vertical) {
        super(positionX, positionY, width, height);
        Ship.setSHIELD(shield);
        Ship.setBOMB(bomb);
        Ship.setVERTICAL(vertical);
    }

    public Bullet shoot() {
        return new Bullet(getPositionX() + getWidth()/2, getPositionY(), 3, 10, Direction.NORTH);
    }

    public String getSpriteFilename() {
        return FILENAME;
    }

	public static int getSHIELD() {
		return SHIELD;
	}

	public static void setSHIELD(int sHIELD) {
		SHIELD = sHIELD;
	}

	public static int getBOMB() {
		return BOMB;
	}

	public static void setBOMB(int bOMB) {
		BOMB = bOMB;
	}

	public static int getLIFE() {
		return LIFE;
	}

	public static void setLIFE(int lIFE) {
		LIFE = lIFE;
	}

	public static int getVERTICAL() {
		return VERTICAL;
	}

	public static void setVERTICAL(int vERTICAL) {
		VERTICAL = vERTICAL;
	}
}
