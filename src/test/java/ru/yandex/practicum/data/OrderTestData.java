package ru.yandex.practicum.data;
import ru.yandex.practicum.model.OrderModel;

public class OrderTestData extends OrderModel {
    private static String firstName = "Иван";
    private static String lastName = "Иванов";
    private static String address = "Беговая 32-61";
    private static Integer metroStation = 4;
    private static String phone = "+7 800 355 35 35";
    private static Integer rentTime = 5;
    private static String deliveryDate = "2025-12-12";
    private static String comment = "Saske, come back to Konoha";

    public OrderTestData(){
        super(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment);
    }
}
