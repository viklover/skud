package ru.team2.skud.card;

import org.springframework.data.relational.core.mapping.Table;
import ru.team2.skud.model.PersistableImpl;

@Table("card")
public class Card extends PersistableImpl<Long> {
}
