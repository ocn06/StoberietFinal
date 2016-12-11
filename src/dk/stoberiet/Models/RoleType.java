package dk.stoberiet.Models;

public enum RoleType {
    USER(0), ADMIN(1);
    private final int value;

    private RoleType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RoleType getByValue(int value) {
        for (RoleType roleType : values()) {
            if (roleType.value == value) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("Invalid RoleType value: " + value);
    }
}