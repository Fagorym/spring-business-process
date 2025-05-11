package ru.nsu.fit.spring_business_process.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessProcessResult {
    SUCCESS,
    ERROR,
    ;
}
