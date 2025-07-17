package com.spring.boot.util.util.math;

import lombok.Data;

/**
 * Snowflake algorithm, an open-source distributed ID generation algorithm of Twitter.
 *
 * @author Zhengmin Yu
 */
@Data
public class SnowflakeAlgorithm {


    /* Because the first bit in binary is negative if it is 1, but the IDS we generate are all positive, so the first bit is 0. */

    /**
     * 机器 ID 2进制5位  32位减掉1位 31个
     */
    private long workerId;
    /**
     * 机房ID 2进制5位  32位减掉1位 31个
     */
    private long dataCenterId;
    /**
     * 代表一毫秒内生成的多个id的最新序号  12位 4096 -1 = 4095 个
     */
    private long sequence;
    /**
     * 设置一个时间初始值 2^41 - 1 差不多可以用 69 年
     */
    private long timeInit = 1585644268888L;
    /**
     * 5位的机器id
     */
    private long workerIdBits = 5L;
    /**
     * 5位的机房id
     */
    private long dataCenterIdBits = 5L;
    /**
     * 每毫秒内产生的id数 2 的 12次方
     */
    private long sequenceBits = 12L;
    /**
     * 这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
     */
    private long maxWorkerId = ~(-1L << workerIdBits);
    /**
     * 这个是一个意思，就是5 bit最多只能有31个数字，机房id最多只能是32以内
     */
    private long maxDataCenterId = ~(-1L << dataCenterIdBits);

    private long workerIdShift = sequenceBits;
    private long dataCenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private long sequenceMask = ~(-1L << sequenceBits);
    /**
     * 记录产生时间毫秒数，判断是否是同 1 毫秒
     */
    private long lastTimestamp = -1L;

    public SnowflakeAlgorithm(long workerId, long dataCenterId, long sequence) {
        // 检查机房 id 和机器 id 是否超过 31 不能小于 0
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
     * 这个是核心方法，通过调用 nextId() 方法，让当前这台机器上的 snowflake 算法程序生成一个全局唯一的 id
     *
     * @return long
     */
    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(
                    String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp)
            );
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个 id
        // 这个时候就得把 sequence 序号给递增 1，最多就是 4096
        if (lastTimestamp == timestamp) {
            // 这个意思是说一个毫秒内最多只能有 4096 个数字，无论你传递多少进来，
            // 这个位运算保证始终就是在 4096 这个范围内，避免你自己传递个 sequence 超过了 4096 这个范围
            sequence = (sequence + 1) & sequenceMask;
            // 当某一毫秒的时间，产生的 id 数 超过 4095，系统会进入等待，直到下一毫秒，系统继续产生 ID
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成 id 的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个 64bit 的 id
        // 先将当前时间戳左移，放到 41 bit 那儿；将机房 id 左移放到 5 bit 那儿；将机器 id 左移放到 5 bit 那儿；将序号放最后 12 bit
        // 最后拼接起来成一个 64 bit 的二进制数字，转换成 10 进制就是个 long 型
        return ((timestamp - timeInit) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    /**
     * 当某一毫秒的时间，产生的 id 数 超过 4095，系统会进入等待，直到下一毫秒，系统继续产生 ID
     *
     * @param lastTimestamp lastTimestamp
     * @return long
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间戳
     *
     * @return long
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }


}
