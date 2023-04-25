package org.example.Izzatillo.service.service;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.service.Service;
import org.example.Izzatillo.domain.model.service.ServiceStatus;
import org.example.Izzatillo.repository.service.ServiceRepository;
import org.example.Izzatillo.repository.service.ServiceRepositoryImpl;

import java.util.ArrayList;
import java.util.Objects;

public class ServiceServiceImpl implements ServiceService {
    ServiceRepository serviceRepository = ServiceRepositoryImpl.getInstance();
    @Override
    public Response add(Service service) {
        if (doesServiceExists(service.getName())) return new Response("This service already exists", 400);
        serviceRepository.save(service);
        return new Response<>("Success", 200);
    }

    private boolean doesServiceExists(String name) {
        for (Service service : serviceRepository.getAll()) {
            if (Objects.equals(service.getName(), name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Service> getServicesByStatus(ServiceStatus serviceStatus) {
        return serviceRepository.getServicesByStatus(serviceStatus);
    }

    @Override
    public Response changeCommission(Service service, Float commission) {
        return serviceRepository.changeCommission(service, commission);
    }

    @Override
    public Response changeActivation(Service service, Boolean activation) {
        return serviceRepository.changeActivation(service, activation);
    }
}
