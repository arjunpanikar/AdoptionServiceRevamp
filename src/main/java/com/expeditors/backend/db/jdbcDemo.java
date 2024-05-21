package com.expeditors.backend.db;

import com.expeditors.backend.domain.Adopter;
import com.expeditors.backend.domain.Pet;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class jdbcDemo {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5433/adoptapp";
        String user = "larku";
        String pw = "larku";

        try(Connection connection = DriverManager.getConnection(url, user, pw);) {
            //addAdopters(connection);
            //addComplexAdopters(connection);
            dumpAdopters(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addAdopters(Connection connection) {

        String sql = "insert into adopter  (name, phone, adoption_date) values (?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, "Romeo");
            ps.setString(2, "901 922 5342");
            ps.setDate(3, java.sql.Date.valueOf("2024-03-10"));
            int numRows = ps.executeUpdate();
            int newId = -1;

            try (ResultSet keys = ps.getGeneratedKeys();) {
                keys.next();
                newId = keys.getInt(1);
            }
            out.println("numRows: " + numRows + ", newId: " + newId);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addComplexAdopters(Connection connection) {

        String sql = "with first_insert as (insert into pet  (pet_type, pet_name, pet_breed) values (?, ?, ?) returning pet_id) insert into adopter (name, phone, adoption_date, pet_pet_id) values (?, ?, ?, (select pet_id from first_insert))";

        try(PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, "Dogs");
            ps.setString(2, "Mickey");
            ps.setString(3, "Dachshund");
            ps.setString(4, "Walker");
            ps.setString(5, "901-829-5342");
            ps.setDate(6, java.sql.Date.valueOf("2024-01-19"));

            int numRows = ps.executeUpdate();
            int newId = -1;

            try (ResultSet keys = ps.getGeneratedKeys();) {
                keys.next();
                newId = keys.getInt(1);
            }
            out.println("numRows: " + numRows + ", newId: " + newId);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dumpAdopters(Connection connection) {

        String sql = "select adopter_id, name, phone, adoption_date, pet_pet_id, pet_type, pet_name, pet_breed from adopter a inner join pet b on a.pet_pet_id = b.pet_id";

        try(PreparedStatement ps = connection.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();
            List<Adopter> adopters = new ArrayList<>();

            while(rs.next()) {
                int id = rs.getInt("adopter_id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                LocalDate date = rs.getDate("adoption_date").toLocalDate();
                int pet_id = rs.getInt("pet_pet_id");

                String petType = rs.getString("pet_type");
                String petName = rs.getString("pet_name");
                String petBreed = rs.getString("pet_breed");

                Pet pet = new Pet(petType, petName, petBreed);
                pet.setPetId(pet_id);
                Adopter adopter = new Adopter(name, phone, date, pet);
                adopter.setAdopterId(id);
                adopters.add(adopter);
            }

            adopters.forEach(out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
