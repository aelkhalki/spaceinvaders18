package nl.delftelectronics.spaceinvaders.core.entities;

import java.util.EventListener;

public interface BarricadeDestroyedListener extends EventListener {
    void barricadeDestroyed(Barricade barricade);
}
