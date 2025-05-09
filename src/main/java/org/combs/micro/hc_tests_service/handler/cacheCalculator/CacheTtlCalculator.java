package org.combs.micro.hc_tests_service.handler.cacheCalculator;

import java.time.Duration;

public interface CacheTtlCalculator<E> {

    Duration calculateTtl(E entity );
}
