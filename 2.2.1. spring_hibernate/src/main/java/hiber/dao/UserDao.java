package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.math.BigInteger;
import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();

   void addCar(Car car);
   List<Car> listCar();

   void addCarUsers(String model, Integer series, Long id);

   String searchUserCar(String model, Integer series);
}
