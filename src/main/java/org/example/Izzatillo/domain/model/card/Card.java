package org.example.Izzatillo.domain.model.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.Izzatillo.domain.model.BaseModel;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Card extends BaseModel {
    private CardStatus type;
    private String cardNumber;
    private Double balance;
    private boolean active;

    @Override
    public String toString() {
        return  "Card ->     " + type + '\n' +
                "Number ->   " + cardNumber + '\n' +
                "Balance ->  " + balance + '$' + '\n' +
                "Active ->   " + active;
    }
}

