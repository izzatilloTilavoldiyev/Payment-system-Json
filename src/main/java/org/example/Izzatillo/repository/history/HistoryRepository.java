package org.example.Izzatillo.repository.history;

import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.repository.BaseRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface HistoryRepository extends BaseRepository<History> {
    ArrayList<History> getUserHistories(UUID userId);
    ArrayList<History> userOutcomeHistories(UUID userId);
    ArrayList<History> userIncomeHistories(UUID userId);

    ArrayList<History> historiesByPeriod(String firsDate, String lastDate);

    ArrayList<History> historiesWeek();
    ArrayList<History> historiesMonth();
}
