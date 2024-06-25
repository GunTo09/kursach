package ru.uvarov.ystu.kursach.exceptions;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {

    private final Long CodeID;

    private final String message;
}
