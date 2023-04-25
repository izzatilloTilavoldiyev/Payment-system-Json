package org.example.Izzatillo.repository.service;

import com.google.gson.reflect.TypeToken;
import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.service.Service;
import org.example.Izzatillo.domain.model.service.ServiceStatus;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import static org.example.Izzatillo.util.BeanUtils.gson;

public class ServiceRepositoryImpl implements ServiceRepository {
    private final static ServiceRepositoryImpl instance = new ServiceRepositoryImpl();

    private ServiceRepositoryImpl() {

    }

    public static ServiceRepositoryImpl getInstance() {
        return instance;
    }

    String path = "src/main/resources/Services.json";
    @Override
    public void save(Service service) {
        ArrayList<Service> services = getAll();
        services.add(service);
        update(services);
    }

    @Override
    public ArrayList<Service> getAll() {
        return readFromFile(path);
    }

    @Override
    public ArrayList<Service> readFromFile(String path) {
        try {
            FileReader reader = new FileReader(path);
            Type type = new TypeToken<ArrayList<Service>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public void remove(Service service) {

    }

    @Override
    public ArrayList<Service> getServicesByStatus(ServiceStatus serviceStatus) {
        ArrayList<Service> servicesByStatus = new ArrayList<>();
        for (Service service : getAll()) {
            if (Objects.equals(service.getType(), serviceStatus)) {
                servicesByStatus.add(service);
            }
        }
        return servicesByStatus;
    }

    @Override
    public Response changeCommission(Service service, Float commission) {
        ArrayList<Service> services = getAll();
        for (Service service1 : services) {
            if (Objects.equals(service1.getName(), service.getName())) {
                service1.setCommission(commission);
                update(services);
                return new Response<>("Success", 200);
            }
        }
        return new Response<>("Service not found");
    }

    @Override
    public Response changeActivation(Service service, Boolean activation) {
        String activate;
        if (activation) activate = "Activate";
        else activate = "Deactivate";
        ArrayList<Service> services = getAll();
        for (Service service1 : services) {
            if (Objects.equals(service1.getName(), service.getName())) {
                if (!Objects.equals(service1.getActivate(), activation)) {
                    service1.setActivate(activation);
                    update(services);
                    return new Response("Success", 200);
                }
                return new Response("This service already " + activate, 401);
            }
        }
        return new Response<>("Service not found", 400);
    }

    @Override
    public void update(ArrayList<Service> services) {
        try {
            PrintWriter writer = new PrintWriter(path);
            gson.toJson(services, writer);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
