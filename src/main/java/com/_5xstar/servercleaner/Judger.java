package com._5xstar.servercleaner;

import java.io.Closeable;
import java.io.IOException;

/**
 * 使用准备好的主键集合，比较另一个集合中的元素，是否在集合中
 * 庞海文  2024-1-9
 */
public interface Judger extends Closeable {

    /**
     * 提供检查服务
     * @param strKey
     * @return
     * @throws IOException
     */
    boolean checkIn(final String strKey)throws IOException;
}
