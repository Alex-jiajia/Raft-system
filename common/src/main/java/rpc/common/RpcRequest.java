/*
 * RpcRequest.java

 */

package rpc.common;

import lombok.Data;

/**
 * @author jiaying
 * @date 2023/4/6
 */
@Data
public class RpcRequest {
    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
}
