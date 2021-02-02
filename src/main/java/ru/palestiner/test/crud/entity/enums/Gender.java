package ru.palestiner.test.crud.entity.enums;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    NONE("NONE");

    private final String code;

    Gender(String gender) {
        this.code = gender;
    }

    public String getCode() {
        return code;
    }

    public static Gender getGender(String code) {
        for (Gender gender : Gender.values()) {
            if (gender.getCode().equals(code)) {
                return gender;
            }
        }
        return Gender.NONE;
    }
}
