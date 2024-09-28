package goorm.zzaturi.domain.board.entity;

public enum Category {
    GOAL_CERTIFICATION,
    INFORMATION;

    public String getName() {
        return this.name();
    }
}