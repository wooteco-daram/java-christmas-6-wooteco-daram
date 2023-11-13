package event.badge;

public enum EventBadgeType {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NONE("없음");

    private final String name;

    EventBadgeType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
