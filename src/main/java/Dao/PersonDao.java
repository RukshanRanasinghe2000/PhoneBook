package Dao;

import Entity.Gender;
import Entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    public static List<Person>get(String qry){
        List<Person> persons = new ArrayList<>();
        try{
            ResultSet rslt = CommonDao.get(qry);
            while (rslt.next()){

                Person person = new Person();
//                main.java.Entity.Gender gender = new main.java.Entity.Gender();
                person.setId(rslt.getInt("id"));
                person.setName(rslt.getString("name"));
                person.setMobile((rslt.getString("mobile")));
                person.setAddress(rslt.getString("address"));
                person.setEmail(rslt.getString("email"));
                person.setGender(GenderDao.getById(rslt.getInt("gender_id")));
                persons.add(person);
            }
        }catch (SQLException e){
            System.out.println("Can't get result as"+e.getMessage());
        }
        return persons;
    }

    public static String save(Person person) {
        String msg = "1";

        String qry = "insert into person(name,mobile,address,email,gender_id) values( '" + person.getName() + "','" + person.getMobile() + "','" + person.getAddress() + "','" + person.getEmail() + "'," + person.getGender().getId()  + ");";
        msg = CommonDao.modify(qry);
        return msg;
    }


    public static String update(Person person) {
        String msg ="1";
        String sql = "UPDATE person set name='"+person.getName()+"', mobile ='"+
                person.getMobile()+"', address='"
                +person.getAddress()+"',email='"
                +person.getEmail()+"',gender_id="
                +person.getGender().getId()+" WHERE id="
                +person.getId();

        msg = CommonDao.modify(sql);
        return msg;


    }

    public static String delete(Person person) {
        String msg ="1";
        String sql = "Delete From person WHERE id=" +person.getId();

        msg = CommonDao.modify(sql);
        return msg;


    }






    public static List<Person> getAll() {
        String qry = "select*from person";
        List list = get(qry);
        return list;
    }
    public static List<Person> getAllByName(String name){
        String qry =  "select*from person where name like '" + name + "%'";
        List list = get(qry);
        return list;
    }
    public static List<Person> getAllByGender(Gender gender){
        String qry = "select*from person where gender_id =" + gender.getId();
        List list = get(qry);
        return  list;
    }
    public static List<Person> getAllByNameAndGender(String name, Gender gender) {
        String qry = "select*from person where name like '" + name + "%' and gender_id =" + gender.getId();
        List list = get(qry);
        return list;
    }
}
