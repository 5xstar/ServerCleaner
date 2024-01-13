package com._5xstar.servercleaner;

import java.io.File;
import java.io.IOException;

/**
 * 处理器
 */
public interface Handler {
    /**
     * 无参数处理器
     */
    default boolean dirHandle(final File dir)throws IOException {return true;}

    default boolean fileHandle(String parentPath, String fileName, File file)throws IOException{return true;}

}
