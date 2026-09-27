package com.alyson.apiestetica.execption;

import java.time.LocalDateTime;

public record Erro(String mensagem, LocalDateTime hora) {
}
