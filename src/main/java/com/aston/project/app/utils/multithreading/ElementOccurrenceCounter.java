package com.aston.project.app.utils.multithreading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ElementOccurrenceCounter {

    private ElementOccurrenceCounter() {
    }

    public static <E> int countOccurrences(Collection<? extends E> collection,
                                           E searchedElement,
                                           int threadCount) {
        Objects.requireNonNull(collection, "collection must not be null");
        if (threadCount < 1) {
            throw new IllegalArgumentException("threadCount must be greater than zero");
        }

        Object[] elements = collection.toArray();
        if (elements.length == 0) {
            return 0;
        }

        int workerCount = Math.min(threadCount, elements.length);
        int chunkSize = (elements.length + workerCount - 1) / workerCount;
        List<Callable<Integer>> tasks = new ArrayList<>(workerCount);

        for (int start = 0; start < elements.length; start += chunkSize) {
            int from = start;
            int to = Math.min(start + chunkSize, elements.length);
            tasks.add(() -> countRange(elements, from, to, searchedElement));
        }

        ExecutorService executor = Executors.newFixedThreadPool(workerCount);
        try {
            int occurrences = 0;
            for (var result : executor.invokeAll(tasks)) {
                occurrences += result.get();
            }
            return occurrences;
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Counting was interrupted", interrupted);
        } catch (ExecutionException failedTask) {
            throw new IllegalStateException("Counting task failed", failedTask.getCause());
        } finally {
            executor.shutdown();
        }
    }

    private static int countRange(Object[] elements, int from, int to, Object searchedElement) {
        int occurrences = 0;
        for (int index = from; index < to; index++) {
            if (Objects.equals(elements[index], searchedElement)) {
                occurrences++;
            }
        }
        return occurrences;
    }
}
