package nl.delftelectronics.spaceinvaders.gui;

import nl.delftelectronics.spaceinvaders.core.Engine;
import nl.delftelectronics.spaceinvaders.core.Ship;

public class Store {
	
    private static final int BOMB = 250;
    private static final int SHIELD = 400;
    private static final int LIFE = 800;
    private static final int VERTICAL = 3000;
	/**
	 * 
	 * @param engine The game engine
	 * @param choice 1 is bomb, 2 is shield, 3 is life, 4 is vertical
	 */
	public static void buy(Engine engine, int choice){
		/**
		 * choice 1 bomb
		 * choice 2 shield
		 * choice 3 life
		 * choice 4 vertical
		 */
		switch(choice){
		case 1:
			if(engine.getPoints()>= BOMB){
				engine.setPoints(engine.getPoints()-BOMB);
				Ship.setBOMB(Ship.getBOMB()+1);
			}
			break;
		case 2:
			if(engine.getPoints()>= SHIELD){
				engine.setPoints(engine.getPoints()-SHIELD);
				Ship.setSHIELD(Ship.getSHIELD()+20);
			}
			break;
		case 3:
			if(engine.getPoints()>= LIFE){
				engine.setPoints(engine.getPoints()-LIFE);
				engine.setLife(engine.getLives()+1);
			}
			break;
		case 4:
			if(engine.getPoints()>= VERTICAL){
				engine.setPoints(engine.getPoints()-VERTICAL);
				Ship.setLIFE(Ship.getVERTICAL()+1);
			}
			break;
			
		}
		
	}

}
