
package com.example.demo.enums;

public enum ProfileEnum {

    USER(1, "ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private final Integer codigo;
    private final String descricao;

    ProfileEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ProfileEnum toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (ProfileEnum perfil : ProfileEnum.values()) {
            if (perfil.getCodigo().equals(codigo)) {
                return perfil;
            }
        }

        throw new IllegalArgumentException(
            "Código de perfil inválido: " + codigo
        );
    }
}
