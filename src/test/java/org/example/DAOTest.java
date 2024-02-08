package org.example;

import junit.framework.TestCase;
import org.example.Connection.MySessionFactory;
import org.example.DAO.*;
import org.example.Entity.*;
import org.example.Entity.Enums.FilmRating;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class DAOTest extends TestCase {
    static Session session;
    static ActorDAO actorDAO;
    static AddressDAO addressDAO;
    static CategoryDAO categoryDAO;
    static CityDAO cityDAO;
    static CountryDAO countryDAO;
    static CustomerDAO customerDAO;
    static FilmDAO filmDAO;
    static FilmTextDAO filmTextDAO;
    static InventoryDAO inventoryDAO;
    static LanguageDAO languageDAO;
    static PaymentDAO paymentDAO;
    static RentalDAO rentalDAO;
    static StaffDAO staffDAO;
    static StoreDAO storeDAO;

    @BeforeAll
    public static void makeSession() {
        session = MySessionFactory.getSessionFactory().openSession();
        actorDAO = new ActorDAO(session);
        addressDAO = new AddressDAO(session);
        categoryDAO = new CategoryDAO(session);
        cityDAO = new CityDAO(session);
        countryDAO = new CountryDAO(session);
        customerDAO = new CustomerDAO(session);
        filmDAO = new FilmDAO(session);
        filmTextDAO = new FilmTextDAO(session);
        inventoryDAO = new InventoryDAO(session);
        languageDAO = new LanguageDAO(session);
        paymentDAO = new PaymentDAO(session);
        rentalDAO = new RentalDAO(session);
        staffDAO = new StaffDAO(session);
        storeDAO = new StoreDAO(session);
    }

    @Test
    public void createAndSaveCustomer() {
        Optional<Store> store = storeDAO.getById(Byte.class, (byte) 1);
        Optional<Address> address = addressDAO.getById(Short.class, (short) 1);

        Customer customer = new Customer();

        store.ifPresent(customer::setStore);
        store.orElseThrow(RuntimeException::new);

        address.ifPresent(customer::setAddress);
        address.orElseThrow(RuntimeException::new);

        customer.setFirstName("Богдан");
        customer.setLastName("Васильєв");
        customer.setEmail("byVasyliev@gmail.com");
        customer.setIsActive(true);
        customer.setCreateDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());

        customerDAO.save(customer);
    }

    @Test
    public void returnInventoryInStore() {
        Optional<Rental> unreturnedRental = rentalDAO.getUnreturnedRental();
        unreturnedRental.ifPresent(e -> e.setReturnDate(LocalDateTime.now()));
        unreturnedRental.orElseThrow(RuntimeException::new);
        rentalDAO.update(unreturnedRental.get());
    }

    @Test
    public void rentInventory() {
        Optional<Film> availableFilmForRent = filmDAO.getAvailableFilmForRent();
        Optional<Store> store = storeDAO.getById(Byte.class, (byte) 2);
        Optional<Customer> customer = customerDAO.getById(Short.class, (short) 2);

        Inventory inventory = new Inventory();
        store.ifPresent(inventory::setStore);
        store.orElseThrow(RuntimeException::new);


        availableFilmForRent.ifPresent(inventory::setFilm);
        availableFilmForRent.orElseThrow(RuntimeException::new);

        inventory.setLastUpdate(LocalDateTime.now());

        inventoryDAO.save(inventory);

        Staff staff = store.get().getStaff();

        Rental rental = new Rental();
        rental.setLastUpdate(LocalDateTime.now());
        rental.setStaff(staff);

        customer.ifPresent(rental::setCustomer);
        customer.orElseThrow(RuntimeException::new);

        rental.setInventory(inventory);
        rental.setRentalDate(LocalDateTime.now());

        rentalDAO.save(rental);

        Payment payment = new Payment();
        payment.setCustomer(customer.get());
        payment.setRental(rental);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setAmount(BigDecimal.valueOf(32.23));
        payment.setStaff(staff);
        paymentDAO.save(payment);
    }

    @Test
    public void MakeNewFilm() {
        Film film = new Film();

        Optional<Language> languageOp = languageDAO.getById(Byte.class, (byte) 5);
        Optional<List<Actor>> actorsOp = actorDAO.getItems(4, 5);
        Optional<List<Category>> categoriesOp = categoryDAO.getItems(2, 3);

        languageOp.ifPresent(film::setLanguage);
        actorsOp.ifPresent(film::setActors);
        categoriesOp.ifPresent(film::setCategories);

        languageOp.orElseThrow(RuntimeException::new);
        actorsOp.orElseThrow(RuntimeException::new);
        categoriesOp.orElseThrow(RuntimeException::new);

        film.setTitle("Sherlock Holmes Crimes and Criminals");
        film.setRentalRate(BigDecimal.valueOf(3.9));
        film.setRentalDuration((byte) 3);
        film.setRating(FilmRating.G);
        film.setReplacementCost(BigDecimal.valueOf(23.99));
        film.setLastUpdate(LocalDateTime.now());

        filmDAO.save(film);
    }

    @AfterAll
    public static void CloseSession() {
        session.close();
    }
}
