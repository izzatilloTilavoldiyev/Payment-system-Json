package org.example.Izzatillo.repository.service;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.service.Service;
import org.example.Izzatillo.domain.model.service.ServiceStatus;
import org.example.Izzatillo.repository.BaseRepository;

import java.util.ArrayList;

public interface ServiceRepository extends BaseRepository<Service> {
    ArrayList<Service> getServicesByStatus(ServiceStatus serviceStatus);

    Response changeCommission(Service service, Float commission);
    Response changeActivation(Service service, Boolean activation);
}
