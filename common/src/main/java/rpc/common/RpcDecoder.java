/*
 * RpcDecoder.java

 */

package rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> clazz;
    private RpcSerializer rpcSerializer;

    public RpcDecoder(Class<?> clazz, RpcSerializer rpcSerializer) {
        this.clazz = clazz;
        this.rpcSerializer = rpcSerializer;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf,
        List<Object> list) throws Exception {

        // data length
        int dataLength = byteBuf.readInt();
        // close connection
        if (dataLength < 0) {
            channelHandlerContext.close();
        }

        //read data
        byte[] bytes = new byte[dataLength];
        byteBuf.readBytes(bytes);
        //reform
        Object o = rpcSerializer.deserialize(clazz, bytes);
        list.add(o);
    }
}
