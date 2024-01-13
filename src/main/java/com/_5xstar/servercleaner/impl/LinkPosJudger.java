package com._5xstar.servercleaner.impl;

import java.io.IOException;

/**
 * 通过数组关联文件位置，实现双级查找：第一级：内存里；第二级：文件中
 * 庞海文  2024-1-9 更新
 **/
public interface LinkPosJudger {
    //检查是否包含
    boolean checkIn(final byte[] bytKey)throws IOException;
}
