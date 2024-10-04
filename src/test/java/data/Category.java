package data;

public enum Category {
    invest("Инвестиции"),
    company("Компании"),
    digest("Инвест-дайджесты"),
    economy("Экономика"),
    idea("Инвестидеи"),
    newinvest("Инвестиции для начинающих"),
    news("Новости для инвесторов"),
    where("Куда вложить"),
    analysis("Инвестразборы"),
    pro("Инвестиции для продвинутых"),
    how("А как инвестировать");

    public final String description;
    Category(String description) {
        this.description = description;
    }
}