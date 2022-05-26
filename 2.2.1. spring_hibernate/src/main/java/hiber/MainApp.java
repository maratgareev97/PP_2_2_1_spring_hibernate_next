package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.addCar(new Car("ваз", 2101));
        userService.addCar(new Car("газ", 24));
        userService.addCar(new Car("уаз", 469));
        userService.addCar(new Car("маз", 5551));

        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        List<Car> cars = userService.listCar();
        for (Car car : cars) {
            System.out.println("Id = " + car.getId());
            System.out.println("First Name = " + car.getModel());
            System.out.println("Last Name = " + car.getSeries());
            System.out.println();
        }

        userService.addCarUsers("ваз", 2101, 1L);
        userService.addCarUsers("газ", 24, 2L);
        userService.addCarUsers("уаз", 469, 3L);
        userService.addCarUsers("маз", 5551, 4L);

        System.out.println(userService.searchUserCar("уаз",469));

        context.close();
    }
}
