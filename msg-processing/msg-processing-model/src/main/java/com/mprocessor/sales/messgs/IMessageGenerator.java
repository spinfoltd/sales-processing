package com.mprocessor.sales.messgs;

import com.mprocessor.sales.model.MBaseMessageType;


public interface IMessageGenerator {
    public MBaseMessageType generateMessage();
}
