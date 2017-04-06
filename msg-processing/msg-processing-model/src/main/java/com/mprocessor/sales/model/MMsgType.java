package com.mprocessor.sales.model;

import java.util.Arrays;
import java.util.Optional;

public enum MMsgType {
    MSG_TYPE_1(MMessageType1.class),
    MSG_TYPE_2(MMessageType2.class),
    MSG_TYPE_3(MMessageType3.class);
    private Class<?> typeClass;

    public Class<?> getTypeClass() {
        return typeClass;
    }

    MMsgType(Class<?> typeClass) {
        this.typeClass = typeClass;
    }
    public static MMsgType getMsgType(Class<?> tClass){
        Optional<MMsgType> optMsgType =
                Arrays.stream(MMsgType.values()).filter(
                        t -> (t.getTypeClass() == tClass)
                )
                .findAny()
                ;
        // throw an exception here
        return optMsgType.orElse(null);
    }
}
