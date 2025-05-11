package ru.nsu.fit.spring_business_process.entity;

import jakarta.annotation.Nonnull;
import lombok.Value;

@Value
public class BusinessProcessResultData {
    BusinessProcessResult result;
    Object nextPayload;

    @Nonnull
    public static BusinessProcessResultData success(Object payload) {
        return new BusinessProcessResultData(BusinessProcessResult.SUCCESS, payload);
    }

    @Nonnull
    public static BusinessProcessResultData error(Object payload) {
        return new BusinessProcessResultData(BusinessProcessResult.ERROR, payload);
    }
}
