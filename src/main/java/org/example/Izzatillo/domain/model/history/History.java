package org.example.Izzatillo.domain.model.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Izzatillo.domain.model.BaseModel;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class History extends BaseModel {
    private HistoryStatus historyStatus;
    private UUID ReceiverId;
    private String sender;
    private String receiver;
    private Double commission;
    private Double amount;
    private String transactionDate;

    @Override
    public String toString() {
        return "Status -> " + historyStatus + '\n' +
               "Sender -> " + sender + '\n' +
               "Receiver -> " + receiver + '\n' +
               "Commission -> " + commission + '\n' +
               "Amount -> " + amount + '$' + '\n' +
               "Transaction date -> " + transactionDate + '\n' +
               "Transaction ID -> " + getId() + '\n';
    }
}
