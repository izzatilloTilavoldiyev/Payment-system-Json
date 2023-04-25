package org.example.Izzatillo.repository;

import java.util.ArrayList;
import java.util.UUID;

public interface BaseRepository<T> {
    void save(T t);
    ArrayList<T> getAll();

    ArrayList<T> readFromFile(String path);
    void remove(UUID id);
    void remove(T t);

    void update(ArrayList<T> ts, String path);
}
