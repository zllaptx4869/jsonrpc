/*
 * Copyright 2018 xincao9@gmail.com.
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
package com.github.xincao9.jsonrpc.core.constant;

/**
 * 服务端常量
 * 
 * @author xincao9@gmail.com
 */
public class ServerConsts {

    public static final String DEFAULT_CONFIG_FILENAME = "/config.properties";
    public static final String PORT = "jsonrpc.server.port";
    public static final String DEFAULT_PORT = "12306";
    public static final String IO_THREAD_BOSS = "jsonrpc.server.ioThreadBoss";
    public static final String DEFAULT_IO_THREAD_BOSS = "1";
    public static final String IO_THREAD_WORKER = "jsonrpc.server.ioThreadWorker";
    public static final Integer DEFAULT_IO_THREAD_WORKER = Runtime.getRuntime().availableProcessors();

}