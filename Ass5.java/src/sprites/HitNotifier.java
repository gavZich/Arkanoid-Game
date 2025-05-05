package sprites;

import listeners.HitListener;

/**
 * @author Gavriel Zichron 206784407 Eliron Picard 322355678
 * @version 1
 * @since 31/07/2024
 */
public interface HitNotifier {
    /**
     * addHitListener.
     * Add hl as a listener to hit events.
     * @param hl hitListener
     */
     void addHitListener(HitListener hl);

    /**
     * removeHitListener.
     * Remove hl from the list of listeners to hit events.
     * @param hl the hit Listener.
     */
     void removeHitListener(HitListener hl);
}