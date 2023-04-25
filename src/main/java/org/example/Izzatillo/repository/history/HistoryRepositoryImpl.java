package org.example.Izzatillo.repository.history;

import com.google.gson.reflect.TypeToken;
import org.example.Izzatillo.domain.model.history.History;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static org.example.Izzatillo.util.BeanUtils.gson;

public class HistoryRepositoryImpl implements HistoryRepository {
    private final static HistoryRepositoryImpl instance = new HistoryRepositoryImpl();

    private HistoryRepositoryImpl() {

    }

    public static HistoryRepositoryImpl getInstance() {
        return instance;
    }

    String path = "src/main/resources/Histories.json";

    @Override
    public void save(History history) {
        ArrayList<History> histories = getAll();
        histories.add(history);
        update(histories);
    }

    @Override
    public ArrayList<History> getAll() {
        return readFromFile(path);
    }

    @Override
    public ArrayList<History> readFromFile(String path) {
        try {
            FileReader reader = new FileReader(path);
            Type type = new TypeToken<ArrayList<History>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public void remove(History history) {

    }

    @Override
    public ArrayList<History> getUserHistories(UUID userId) {
        ArrayList<History> userHistories = new ArrayList<>();
        for (History history : getAll()) {
            if (Objects.equals(history.getId(), userId) ||
                    Objects.equals(history.getReceiverId(), userId)) {
                userHistories.add(history);
            }
        }
        return userHistories;
    }

    @Override
    public ArrayList<History> userOutcomeHistories(UUID userId) {
        System.out.println("5555");
        ArrayList<History> outcomeHistories = new ArrayList<>();
        for (History history : getAll()) {
            if (Objects.equals(history.getId(), userId)) {
                outcomeHistories.add(history);
            }
        }
        return outcomeHistories;
    }

    @Override
    public ArrayList<History> userIncomeHistories(UUID userId) {
        ArrayList<History> incomeHistories = new ArrayList<>();
        for (History history : getAll()) {
            if (Objects.equals(history.getReceiverId(), userId)) {
                incomeHistories.add(history);
            }
        }
        return incomeHistories;
    }

    @Override
    public ArrayList<History> historiesByPeriod(String firsDate, String lastDate) {
        ArrayList<History> historiesByPeriod = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
        try {
            LocalDate date1 = LocalDate.parse(firsDate, formatter);
            LocalDate date2 = LocalDate.parse(lastDate, formatter);
            for (History history : getAll()) {
                LocalDate date = LocalDate.from(LocalDateTime.parse(history.getTransactionDate(), format));
                if (date.isAfter(date1) && date.isBefore(date2) || date.equals(date1) || date.equals(date2)) {
                    historiesByPeriod.add(history);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return historiesByPeriod;
    }

    @Override
    public ArrayList<History> historiesWeek() {
        ArrayList<History> histories = new ArrayList<>();

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
        for (History history : getAll()) {
            LocalDate date = LocalDate.from(LocalDateTime.parse(history.getTransactionDate(), format));
            if (date.isAfter(localDate.minusWeeks(1)) ||
                    date.equals(localDate.minusWeeks(1))) {
                histories.add(history);
            }
        }
        return histories;
    }

    @Override
    public ArrayList<History> historiesMonth() {
        ArrayList<History> histories = new ArrayList<>();

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
        for (History history : getAll()) {
            LocalDate date = LocalDate.from(LocalDateTime.parse(history.getTransactionDate(), format));
            if (date.isAfter(localDate.minusMonths(1)) ||
                    date.equals(localDate.minusMonths(1))) {
                histories.add(history);
            }
        }
        return histories;
    }

    @Override
    public void update(ArrayList<History> histories) {
        try {
            PrintWriter writer = new PrintWriter(path);
            gson.toJson(histories, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
