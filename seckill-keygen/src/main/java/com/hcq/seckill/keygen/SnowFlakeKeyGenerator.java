package com.hcq.seckill.keygen;


import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
public class SnowFlakeKeyGenerator implements KeyGenerator {
    @Resource
    private WorkerIDSenquence workerIDSenquence;

    public static final long EPOCH;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_BITS = 10L;
    private static final long SEQUENCE_MASK = 4095L;
    private static final long WORKER_ID_LEFT_SHIFT_BITS = 12L;
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = 22L;
    private static final long WORKER_ID_MAX_VALUE = 1024L;
    private static long workerId;
    private long sequence;
    private long lastTime;

    public SnowFlakeKeyGenerator() {
    }

    /**
     * 初始化workerID 从ZK获取序列
     */
    @PostConstruct
    public  void initWorkerId() throws Exception {
        long workerID = workerIDSenquence.getSequence(null);
        Preconditions.checkArgument(workerID >= 0L && workerID < 1024L);
        workerId = workerID;
    }

    public synchronized Number generateKey() {
        long currentMillis = System.currentTimeMillis();
        Preconditions.checkState(this.lastTime <= currentMillis, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", Long.valueOf(this.lastTime), Long.valueOf(currentMillis));
        if(this.lastTime == currentMillis) {
            if(0L == (this.sequence = ++this.sequence & 4095L)) {
                currentMillis = this.waitUntilNextTime(currentMillis);
            }
        } else {
            this.sequence = 0L;
        }

        this.lastTime = currentMillis;
        if(log.isDebugEnabled()) {
            log.debug("{}-{}-{}", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(new Date(this.lastTime)), Long.valueOf(workerId), Long.valueOf(this.sequence));
        }

        return Long.valueOf(currentMillis - EPOCH << 22 | workerId << 12 | this.sequence);
    }

    private long waitUntilNextTime(long lastTime) {
        long time;
        for(time = System.currentTimeMillis(); time <= lastTime; time = System.currentTimeMillis()) {
        }

        return time;
    }


    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 1, 1);
        EPOCH = calendar.getTimeInMillis();
    }
}
