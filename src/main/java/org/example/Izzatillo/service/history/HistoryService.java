package org.example.Izzatillo.service.history;

import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.service.BaseService;

import java.util.ArrayList;
import java.util.UUID;

public interface HistoryService extends BaseService<History> {
    ArrayList<History> getAllHistories();
    ArrayList<History> getUserHistories(UUID userId);
    ArrayList<History> userOutcomeHistories(UUID userId);
    ArrayList<History> userIncomeHistories(UUID userId);

    ArrayList<History> historiesByPeriod(String firsDate, String lastDate);

    ArrayList<History> historiesWeek();
    ArrayList<History> historiesMonth();
}
