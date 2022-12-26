package Controller;

import Dao.PersonDao;
import Entity.Gender;
import Entity.Person;

import java.util.Hashtable;
import java.util.List;

public class PersonController {

    public static List<Person> get(Hashtable<String, Object> ht){
        List<Person> persons = null;
        if(ht == null){
            persons = PersonDao.getAll();
        }else {
            String name = (String) ht.get("name");

            Gender gender = (Gender) ht.get("gender");
            if(gender == null){
                persons = PersonDao.getAllByName(name);
            } else if (name == null) {
                persons = PersonDao.getAllByGender(gender);

            }else {
                persons = PersonDao.getAllByNameAndGender(name,gender);
            }

        }
        return persons;
    }

    public static String post(Person person) {

        String msg = "";
        String err = "";

        if (err.isEmpty()) {

            String dbmsg = PersonDao.save(person);
            if (dbmsg.equals("1"))
                msg = "1";
            else
                msg = "DB error : \n" + dbmsg;

        } else {
            msg = "Data Errors : \n" + err;
        }

        return msg;

    }

    public static String put(Person person) {
        String msg = "";
        String err = "";

        if (err.isEmpty()) {

            String dbmsg = PersonDao.update(person);
            if (dbmsg.equals("1"))
                msg = "1";
            else
                msg = "DB error : \n" + dbmsg;

        } else {
            msg = "Data Errors : \n" + err;
        }

        return msg;

    }
    public static String delete(Person person){

        String msg="";

        String dberr = PersonDao.delete(person);
        if(dberr.equals("1"))
            msg="1";
        else msg="DB error as : " + dberr;

        return msg;

    }
    
}
