package com.igrejasobrenatural.sistemas.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PatrimonioEnum {
    ATIVO("Ativo"),
    EM_MANUTENCAO("Em Manutenção");

    private final String descricao;

    PatrimonioEnum(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static PatrimonioEnum fromValue(String value) {
        for (PatrimonioEnum situacao : PatrimonioEnum.values()) {
            if (situacao.descricao.equalsIgnoreCase(value)) {
                return situacao;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value);
    }
}