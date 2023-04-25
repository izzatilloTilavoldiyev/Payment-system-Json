package org.example.Izzatillo.controller.admin;

import org.example.Izzatillo.domain.model.service.Service;
import org.example.Izzatillo.domain.model.service.ServiceStatus;

import java.util.ArrayList;

import static org.example.Izzatillo.util.BeanUtils.*;

public class ServiceStage {
    public void service() {
        while (true) {
            System.out.println("1. Add service\n2. Update service\n3. Commission\n0. Back");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> addService();
                case 2 -> updateService();
                case 3 -> commission();
                case 0 -> {
                    System.out.println("Thank you!"); return;
                }
            }
        }
    }

    private void addService() {
        while (true) {
            System.out.println("1. Utility\n2. Education\n0. Back");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> {
                    Service service = getServiceInfo(ServiceStatus.Utilities);
                    System.out.println(serviceService.add(service));
                }
                case 2 -> {
                    Service service = getServiceInfo(ServiceStatus.Education);
                    System.out.println(serviceService.add(service));
                }
                case 0 -> {
                    System.out.println("Thank you!"); return;
                }
            }
        }
    }

    private Service getServiceInfo(ServiceStatus serviceStatus) {
        System.out.print("Name: ");
        String name = scanStr.nextLine();

        return new Service(true, name, serviceStatus, 0F);
    }

    public void updateService() {
        while (true) {
            System.out.println("1. Utility\n2. Education\n0. Back");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> displayServiceByServiceStatus(ServiceStatus.Utilities);
                case 2 -> displayServiceByServiceStatus(ServiceStatus.Education);
                case 0 -> {
                    System.out.println("Thank you!"); return;
                }
            }
        }
    }

    private void displayServiceByServiceStatus(ServiceStatus serviceStatus) {
        ArrayList<Service> services = serviceService.getServicesByStatus(serviceStatus);
        int i = 1;
        for (Service service : services) {
            System.out.println(i++ + ". " + service.getName());
        }
        int choose = scanNum.nextInt();
        try {
            Service service = services.get(choose - 1);
            System.out.println(service.getName());
            editService(service);
        }catch (Exception e) {
            System.out.println("Wrong input...");
        }
    }

    private void editService(Service service) {
        while (true) {
            System.out.println("1. Change commission     2. Change activation     0. Back");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> {
                    System.out.print("Enter new commission: ");
                    Float commission = scanNum.nextFloat();
                    System.out.println(serviceService.changeCommission(service, commission));
                }

                case 2 -> {
                    System.out.println("1. Activate     2. Deactivate     0. Back");
                    int act = scanNum.nextInt();
                    switch (act) {
                        case 1 -> System.out.println(serviceService.changeActivation(service, true));
                        case 2 -> System.out.println(serviceService.changeActivation(service, false));
                        case 0 -> {
                            System.out.println("Thank you!"); return;
                        }
                    }
                }
                case 0 -> {
                    System.out.println("Thank you!"); return;
                }
            }
        }
    }

    private void commission() {
        Commission commission = new Commission();
        commission.commission();
    }
}
