package thedarkdnktv.studentdb.common;

public enum Operation {
    ADD     ("Добавить студента"),
    REMOVE  ("Удалить студента"),
    VIEW    ("Просмотр базы"),
    EXIT    ("Выход"),
    INVALID (null);

    public static final Operation[] VALID_VALUES = {
        ADD, REMOVE, VIEW, EXIT
    };

    public static Operation getById(int id) {
        if (id >= 0 && id < values().length) {
            return values()[id];
        }

        return INVALID;
    }

    public final String label;

    Operation(String label) {
        this.label = label;
    }

    public boolean isInvalid() {
        return this == INVALID;
    }
}
