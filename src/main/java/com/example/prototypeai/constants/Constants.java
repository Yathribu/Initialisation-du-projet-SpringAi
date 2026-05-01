package com.example.prototypeai.constants;

import com.example.prototypeai.role.roleenum.RoleType;

public class Constants {

    private Constants() {
        throw new AssertionError("Les classes utilitaires ne peuvent pas être instanciés.");
    }

    public static final RoleType ROLE_ADMIN = RoleType.ROLE_ADMIN;
    public static final RoleType ROLE_USER = RoleType.ROLE_USER;

}
