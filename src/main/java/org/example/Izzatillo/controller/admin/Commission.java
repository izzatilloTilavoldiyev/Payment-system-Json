package org.example.Izzatillo.controller.admin;

import static org.example.Izzatillo.util.BeanUtils.*;

public class Commission {
    public void commission() {
        while (true) {
            System.out.println("""
                    1. Change commission for transaction
                    2. Apply commission to transactions
                    0. Back""");
            int action = scanNum.nextInt();
            switch (action) {
                case 1 -> changeCommission();
                case 2 -> applyCommission();
                case 0 -> {
                    System.out.println("Thank you!"); return;
                }
            }
        }
    }

    private void changeCommission() {

    }

    private void applyCommission() {
        ServiceStage serviceStage = new ServiceStage();
        serviceStage.updateService();
    }
}
