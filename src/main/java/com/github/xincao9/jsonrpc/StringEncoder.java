/*
 * Copyright 2018 xingyunzhi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.xincao9.jsonrpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author xincao9@gmail.com
 */
public class StringEncoder extends MessageToByteEncoder<Object> {

    /**
     * 
     * @param chc
     * @param object
     * @param byteBuf
     * @throws Exception 
     */
    @Override
    protected void encode(ChannelHandlerContext chc, Object object, ByteBuf byteBuf) throws Exception {
        Objects.requireNonNull(object);
        if (!(object instanceof String)) {
            return;
        }
        String str = (String) object;
        if (StringUtils.isBlank(str)) {
            return;
        }
        byte[] data = str.getBytes("UTF-8");
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
    }

}