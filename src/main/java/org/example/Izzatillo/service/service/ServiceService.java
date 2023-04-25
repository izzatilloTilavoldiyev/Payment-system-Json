package org.example.Izzatillo.service.service;

import org.example.Izzatillo.domain.DTO.Response;
import org.example.Izzatillo.domain.model.service.Service;
import org.example.Izzatillo.domain.model.service.ServiceStatus;
import org.example.Izzatillo.service.BaseService;

import java.util.ArrayList;

public interface ServiceService extends BaseService<Service> {
    ArrayList<Service> getServicesByStatus(ServiceStatus serviceStatus);
    Response changeCommission(Service service, Float commission);
    Response changeActivation(Service service, Boolean activation);
}
