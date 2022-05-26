package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCar() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public void addCarUsers(String model, Integer series, Long id) {

        Query searchcar = sessionFactory.getCurrentSession().createQuery("Select id From Car where model=:mode and series=:serie");
        searchcar.setParameter("mode", model);
        searchcar.setParameter("serie", series);
        System.out.println("\n\n" + searchcar.list().get(0) + " Это id поиска");

        Query query = sessionFactory.getCurrentSession().createQuery("update User set cars_id = :cars_id where id = :id");
        query.setParameter("cars_id", searchcar.list().get(0));
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public String searchUserCar(String model, Integer series) {
        Query searchcar = sessionFactory.getCurrentSession().createQuery("Select id From Car where model=:mode and series=:serie");
        searchcar.setParameter("mode", model);
        searchcar.setParameter("serie", series);
        //System.out.println("\n\n" + searchcar.list().get(0) + " Это -------------");
        Long id_car = (Long) searchcar.list().get(0);

        Query searchcar1 = sessionFactory.getCurrentSession().createQuery("Select id From User where cars_id=:id");
        searchcar1.setParameter("id", id_car);
        id_car = (Long) searchcar1.list().get(0);
        //System.out.println("\n\n" + searchcar1.list().get(0) + " Это id user по машине");

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where id=:id");
        query.setParameter("id", id_car);
        List<User> info_user_list = query.getResultList();
        String info_user = info_user_list.get(0).getFirstName() + ", " + info_user_list.get(0).getLastName();
//        System.out.println(info_user);

        return info_user;
    }

}
