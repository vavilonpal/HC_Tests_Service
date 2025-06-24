package org.combs.micro.hc_tests_service.service;


import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class TestTimeoutScheduler {
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    private final ResultService resultService;

    public TestTimeoutScheduler(ResultService resultService) {
        this.resultService = resultService;
    }

    public void scheduleTestTimeout(Long resultId, long delayInSeconds) {
        executorService.schedule(() -> {
            resultService.finishTestIfNotFinished(resultId);
        }, delayInSeconds, TimeUnit.SECONDS);
    }
}
