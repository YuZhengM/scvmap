package com.spring.boot.util.util.math;

import lombok.Data;

/**
 * Snowflake algorithm, an open-source distributed ID generation algorithm of Twitter.
 *
 * @author Zhengmin Yu
 */
@Data
public class SnowflakeAlgorithm {


    /* If the first bit in binary is 1, the number is negative. Since the IDs we generate are all positive, the first bit is set to 0. */

    /**
     * 5-bit binary machine ID, allowing a maximum of 31 unique IDs (32 values minus 1).
     */
    private long workerId;
    /**
     * 5-bit binary data center ID, allowing a maximum of 31 unique IDs (32 values minus 1).
     */
    private long dataCenterId;
    /**
     * Represents the latest sequence number of multiple IDs generated within one millisecond. 12 bits allow for 4096 - 1 = 4095 unique numbers.
     */
    private long sequence;
    /**
     * Sets an initial timestamp value. With 2^41 - 1 milliseconds, it can be used for approximately 69 years.
     */
    private long timeInit = 1585644268888L;
    /**
     * Number of bits allocated for the machine ID (5 bits).
     */
    private long workerIdBits = 5L;
    /**
     * Number of bits allocated for the data center ID (5 bits).
     */
    private long dataCenterIdBits = 5L;
    /**
     * Number of IDs generated per millisecond (2 to the power of 12).
     */
    private long sequenceBits = 12L;
    /**
     * This is a binary operation. 5 bits can represent a maximum of 31 numbers, meaning the machine ID can be at most 31.
     */
    private long maxWorkerId = ~(-1L << workerIdBits);
    /**
     * Similarly, 5 bits can represent a maximum of 31 numbers, meaning the data center ID can be at most 31.
     */
    private long maxDataCenterId = ~(-1L << dataCenterIdBits);

    private long workerIdShift = sequenceBits;
    private long dataCenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private long sequenceMask = ~(-1L << sequenceBits);
    /**
     * Records the timestamp of the last ID generation to determine if it's within the same millisecond.
     */
    private long lastTimestamp = -1L;

    public SnowflakeAlgorithm(long workerId, long dataCenterId, long sequence) {
        // Check if the data center ID and machine ID exceed 31 or are less than 0.
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId)
            );
        }

        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId)
            );
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    /**
     * This is the core method. By calling the nextId() method, the Snowflake algorithm on the current machine generates a globally unique ID.
     *
     * @return A globally unique long-type ID.
     */
    public synchronized long nextId() {
        // Get the current timestamp in milliseconds.
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp)
            );
        }

        // If multiple ID generation requests are made within the same millisecond,
        // increment the sequence number, with a maximum of 4096.
        if (lastTimestamp == timestamp) {
            // This means that a maximum of 4096 numbers can be generated per millisecond.
            // The bitwise operation ensures the sequence number stays within 4096,
            // preventing it from exceeding this range.
            sequence = (sequence + 1) & sequenceMask;
            // If the number of IDs generated in a millisecond exceeds 4095,
            // the system waits until the next millisecond to continue generating IDs.
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // Record the timestamp of the most recent ID generation in milliseconds.
        lastTimestamp = timestamp;
        // This is the core bitwise operation to generate a 64-bit ID.
        // First, shift the current timestamp left to the 41-bit position;
        // shift the data center ID left to the 5-bit position;
        // shift the machine ID left to the 5-bit position;
        // place the sequence number in the last 12 bits.
        // Finally, combine them into a 64-bit binary number and convert it to a long type.
        return ((timestamp - timeInit) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    /**
     * When the number of IDs generated in a millisecond exceeds 4095,
     * the system waits until the next millisecond to continue generating IDs.
     *
     * @param lastTimestamp The timestamp of the last ID generation.
     * @return The timestamp of the next millisecond.
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * Gets the current timestamp in milliseconds.
     *
     * @return The current timestamp in milliseconds.
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

}
