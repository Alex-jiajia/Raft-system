/*
 * Serializer.java

 */

package rpc.common;

import java.io.IOException;

/**
 * @author jiaying
 * @date 2023/4/8
 */
public interface RpcSerializer {

    /**
     * @param object
     * @return
     * @throws IOException
     */
    byte[] serialize(Object object) throws IOException;


    /**
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException;
}
