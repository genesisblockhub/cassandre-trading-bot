package tech.cassandre.trading.bot.batch;

import tech.cassandre.trading.bot.dto.position.PositionDTO;
import tech.cassandre.trading.bot.service.PositionService;
import tech.cassandre.trading.bot.util.base.BaseFlux;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Position flux - push {@link PositionDTO}.
 */
public class PositionFlux extends BaseFlux<PositionDTO> {

    /** Position service. */
    private final PositionService positionService;

    /** Previous values. */
    private final Map<Long, Long> previousValues = new LinkedHashMap<>();

    /**
     * Constructor.
     *
     * @param newPositionService position service
     */
    public PositionFlux(final PositionService newPositionService) {
        this.positionService = newPositionService;
    }

    @Override
    protected final Set<PositionDTO> getNewValues() {
        getLogger().debug("PositionFlux - Retrieving new values");
        Set<PositionDTO> newValues = new LinkedHashSet<>();

        // Finding which positions has been updated.
        positionService.getPositions().forEach(position -> {
            getLogger().debug("PositionFlux - Treating position : {}", position.getId());
            Long previousVersion = previousValues.get(position.getId());
            if (previousVersion == null || !previousVersion.equals(position.getVersion())) {
                getLogger().debug("PositionFlux - Flux {} has changed : {}", position.getId(), position);
                previousValues.put(position.getId(), position.getVersion());
                newValues.add(position);
            }
        });

        getLogger().debug("PositionFlux - {} position(s) updated", newValues.size());
        return newValues;
    }

    /**
     * Restore position.
     *
     * @param position position
     */
    public final void restorePosition(final PositionDTO position) {
        previousValues.put(position.getId(), position.getVersion());
    }

}
