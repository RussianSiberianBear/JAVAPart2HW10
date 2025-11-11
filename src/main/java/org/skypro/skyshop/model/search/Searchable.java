package org.skypro.skyshop.model.search;

import java.util.Comparator;
import java.util.UUID;

public interface Searchable {

    UUID getId();
    String getName();

    String searchTerm();

    String getContentType();

    int getProductPrice();

    boolean isSpecial();

    default String getStringRepresentation() {
        return "Имя объекта \"" + this.getName() + "\" Тип \"" + this.getContentType() + "\"";
    }

    boolean equals(Searchable o);

    static Comparator<Searchable> getInverseComparator() {

        return  (o1, o2) -> {
            if (o2.getName().length() < o1.getName().length()) {
                return -1;
            } else if (o2.getName().length() > o1.getName().length()) {
                return 1;
            } else return o2.getName().compareTo(o1.getName());
        };
    }
}