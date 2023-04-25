package org.example.Izzatillo.service.history;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.history.History;
import org.example.Izzatillo.repository.history.HistoryRepository;
import org.example.Izzatillo.repository.history.HistoryRepositoryImpl;

import java.util.ArrayList;
import java.util.UUID;

public class HistoryServiceImpl implements HistoryService {
    HistoryRepository historyRepository = HistoryRepositoryImpl.getInstance();

    @Override
    public Response add(History history) {
        historyRepository.save(history);
        return new Response<>("Success", 200) ;
    }

    @Override
    public ArrayList<History> getAllHistories() {
        return historyRepository.getAll();
    }

    @Override
    public ArrayList<History> getUserHistories(UUID userId) {
        return historyRepository.getUserHistories(userId);
    }

    @Override
    public ArrayList<History> userOutcomeHistories(UUID userId) {
        return historyRepository.userOutcomeHistories(userId);
    }

    @Override
    public ArrayList<History> userIncomeHistories(UUID userId) {
        return historyRepository.userIncomeHistories(userId);
    }

    @Override
    public ArrayList<History> historiesByPeriod(String firsDate, String lastDate) {
        return historyRepository.historiesByPeriod(firsDate, lastDate);
    }

    @Override
    public ArrayList<History> historiesWeek() {
        return historyRepository.historiesWeek();
    }

    @Override
    public ArrayList<History> historiesMonth() {
        return historyRepository.historiesMonth();
    }
}
