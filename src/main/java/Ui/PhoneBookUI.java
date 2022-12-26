package Ui;

import Controller.PersonController;
import Controller.GenderController;
import Entity.Gender;
import Entity.Person;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class PhoneBookUI extends JFrame {

    Color valid;
    Color invalid;
    Color initial;
    Color updated;
    Vector data;

    JTextField txtName;
    JTextField txtMobile;
    JTextField txtEmail;
    JTextField txtAdderss;
    JTable tblPerson;

    JComboBox cmbGender;
    Vector titles;

    JTextField txtSearchName;
    JComboBox cmbSearchGender;
    JButton btnSearchClear;

    List<Person> personList;

    Person oldperson;

    DefaultTableModel dataModel;

    JButton btnAdd;
    JButton btnUpdate;
    JButton btnDelete;


    public PhoneBookUI() {

        this.setTitle("java Phone Book");
        this.setLocation(200, 200);
        this.setSize(510, 650);
        Container cont = this.getContentPane();
        FlowLayout lay = new FlowLayout(3);
        cont.setLayout(lay);

        JLabel lblname = new JLabel("Name   :");
        JLabel lblMobile = new JLabel("Mobile : ");
        JLabel lblEmail = new JLabel("Email   : ");
        JLabel lblGender = new JLabel("Gender          : ");
        JLabel lblAddress = new JLabel("Address   : ");
        JLabel lblBar1 = new JLabel("---------------------------------------------------------------------------------------------------------------------------------------");
        JLabel lblBar2 = new JLabel("----------------------------------------------------------------------------------------------------------------------------------------");
        JLabel lblBar3 = new JLabel("----------------------------------------------------------------------------------------------------------------------------------------");
        JLabel lblSearchName = new JLabel("Search by name :");
        JLabel lblSearchGender = new JLabel("Search by gender :");

        txtSearchName = new JTextField(10);
        cmbSearchGender = new JComboBox();
        btnSearchClear = new JButton("Clear");
        JButton btnSearch = new JButton("Search");

        cmbGender = new JComboBox();

        txtName = new JTextField(40);
        txtMobile = new JTextField(40);
        txtEmail = new JTextField(40);
        txtAdderss = new JTextField(40);

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        valid = new Color(200, 255, 200);
        invalid = Color.PINK;
        initial = Color.WHITE;
        updated = Color.YELLOW;


        titles = new Vector();
        titles.add("Name");
        titles.add("Mobile");
        titles.add("Email");
        titles.add("Address");
        titles.add("Gender");


        data = new Vector<>();

        dataModel = new DefaultTableModel(data, titles);
        tblPerson = new JTable();
        tblPerson.setModel(dataModel);

        JScrollPane jspTable = new JScrollPane();
        jspTable.setPreferredSize(new Dimension(490, 400));
        jspTable.setViewportView(tblPerson);


        cont.add(lblname);
        cont.add(txtName);
        cont.add(lblMobile);
        cont.add(txtMobile);
        cont.add(lblEmail);
        cont.add(txtEmail);
        cont.add(lblAddress);
        cont.add(txtAdderss);
        cont.add(lblGender);
        cont.add(cmbGender);
        cont.add(lblBar1);
        cont.add(btnAdd);
        cont.add(btnUpdate);
        cont.add(btnDelete);
        cont.add(btnClear);
        cont.add(lblBar3);
        cont.add(lblSearchName);
        cont.add(txtSearchName);
        cont.add(lblSearchGender);
        cont.add(cmbSearchGender);
        cont.add(btnSearch);
        cont.add(btnSearchClear);
        cont.add(lblBar2);
        cont.add(jspTable);

        btnSearch.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                btnSearchAp(e);
            }
        });
        initilize();
        btnAdd.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddAp(e);
            }
        }));

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnUpdateAp(e);
            }
        });

        tblPerson.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tblPersonVC(e);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteVC(e);
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearAP(e);
            }
        });

        btnSearchClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSearchClearAP(e);
            }
        });


    }

    public void initilize() {
        loadView();
        loadForm();

    }

    public void loadForm() {
        List<Gender> genders = GenderController.get();
        Vector gnd = new Vector();
        gnd.add("Select a Gender");
        for (Gender gen : genders) {
            gnd.add(gen);
        }
        DefaultComboBoxModel<Object> gndModel = new DefaultComboBoxModel(gnd);
        cmbGender.setModel(gndModel);

        enableButtons(true,false,false);

    }

    public void loadView() {
        personList = PersonController.get(null);
        fillTable(personList);

        List<Gender> gendList = GenderController.get();
        Vector<Object> genders = new Vector<>();
        genders.add("Select a Gender");

        for (Gender gen : gendList) {
            genders.add(gen);
        }

        DefaultComboBoxModel<Gender> gndModel = new DefaultComboBoxModel(genders);
        cmbSearchGender.setModel(gndModel);
    }

    public void fillTable(List<Person> persons) {

        DefaultTableModel model = (DefaultTableModel) tblPerson.getModel();

        model.setRowCount(0);

        for (Person per : persons) {
            Vector d = new Vector();
            d.add(per.getName().toString());
            d.add(per.getMobile().toString());
            d.add(per.getEmail().toString());
            d.add(per.getAddress().toString());
            d.add(per.getGender().getName());
            model.addRow(d);

//            data.add(d);

        }
//        dataModel = new DefaultTableModel(data, titles);
//        tblPerson.setModel(dataModel);

    }

    public void tblPersonVC(ListSelectionEvent e) {
        int row = tblPerson.getSelectedRow();
        System.out.println(row);
        if(row>-1){
            Person person = personList.get(row);
            fillForm(person);
        }
        enableButtons(true,true,true);
    }

    public void fillForm(Person person) {
        oldperson = person;
        txtName.setText(person.getName());
        txtMobile.setText(person.getMobile());
        txtEmail.setText(person.getEmail());
        txtAdderss.setText(person.getAddress());
        cmbGender.setSelectedIndex(person.getGender().getId());
        setStyle(valid);

    }

    public void enableButtons(boolean add, boolean upd, boolean del) {
        btnAdd.setEnabled(add);
        btnUpdate.setEnabled(upd);
        btnDelete.setEnabled(del);
    }

    public void setStyle(Color clr) {

        txtName.setBackground(clr);
        txtMobile.setBackground(clr);
        txtEmail.setBackground(clr);
        txtAdderss.setBackground(clr);
        txtName.setBackground(clr);
        cmbGender.setBackground(clr);

    }


    public void btnSearchAp(ActionEvent e) {
        String name = txtSearchName.getText();
        Object cmb = cmbSearchGender.getSelectedItem();
        Gender gender = null;
        Hashtable<String, Object> hash = new Hashtable<>();
//        hash.put("name", txt);

        if (!cmb.equals("Select a Gender")) {
            gender = (Gender) cmb;

        }
        if (!name.isEmpty()) {
            hash.put("name", name);
        } else if (gender != null) {
            hash.put("gender", gender);
        } else {
            hash = null;
        }

        personList = PersonController.get(hash);
        fillTable(personList);


    }

    public void btnAddAp(ActionEvent e) {
        Person person = new Person();
        String error = "";

        String name = txtName.getText();
        if (name.matches("^[A-Z][a-z]*$")) {
            person.setName(name);
            txtName.setBackground(valid);

        } else {
            txtName.setBackground(invalid);
            error = error + "\n Invalid Name";
        }

        String mobile = txtMobile.getText();
        if (mobile.matches("^07[0-9]{8}$")) {
            person.setMobile(mobile);
            txtMobile.setBackground(valid);

        } else {
            txtMobile.setBackground(invalid);
            error = error + "\n Invalid Mobile";
        }

        String address = txtAdderss.getText();
        if (!address.isEmpty()) {
            person.setAddress(address);
            txtAdderss.setBackground(valid);

        } else {
            txtAdderss.setBackground(invalid);
            error = error + "\n Invalid Address";
        }

        String email = txtEmail.getText();
        if (email.matches("^[a-z]+[@][a-z]+[.][a-z]+$")) {
            person.setEmail(email);
            txtEmail.setBackground(valid);

        } else {
            txtEmail.setBackground(invalid);
            error = error + "\n Invalid Email";
        }

        int genIndex = cmbGender.getSelectedIndex();

        if (genIndex != 0) {
            person.setGender((Gender) cmbGender.getSelectedItem());

            cmbGender.setBackground(valid);

        } else {
            cmbGender.setBackground(invalid);
            error = error + "\n Select A Gender";
        }

        if (error.isEmpty()) {

            String cnfmsg = "Are you sure to save following Details?\n\n";
            cnfmsg = cnfmsg + "\nName : " + person.getName();
            cnfmsg = cnfmsg + "\nMobile : " + person.getMobile();
            cnfmsg = cnfmsg + "\nEmail : " + person.getEmail();
            cnfmsg = cnfmsg + "\nAdderss : " + person.getAddress();
            cnfmsg = cnfmsg + "\nGender : " + person.getGender().getName();

            int conf = JOptionPane.showConfirmDialog(null,cnfmsg);
            System.out.println(conf);
            if(conf ==0){

                String st = PersonController.post(person);
                if (st.equals("1")) {
                    JOptionPane.showMessageDialog(null, "Successfully saved");
                    loadView();
                    loadForm();
                }
            }


        }

    }

    public void btnUpdateAp(ActionEvent e) {
        Person person = new Person();
        String error = "";
        String updates = "";
        person.setId(oldperson.getId());

        String name = txtName.getText();
        if (name.matches("^[A-Z][a-z]*$")) {
            if (name.equals(oldperson.getName())) {
                person.setName(oldperson.getName());
                txtName.setBackground(valid);

            } else {
                person.setName(name);
                txtName.setBackground(updated);

                updates = updates + "\n Name updated";

            }
        } else {
            txtName.setBackground(invalid);
            error = error + "\n Invalid name";
        }

        String email = txtEmail.getText();

        if (email.matches("^[a-z0-9]+[@][a-z]+[.][a-z]+$")) {

            if (email.equals(oldperson.getEmail())) {
                txtEmail.setBackground(valid);
                person.setEmail(oldperson.getEmail());
            } else {
                person.setEmail(email);
                txtEmail.setBackground(updated);
                updates = updates + "\n Email updated";
            }


        } else {
            txtEmail.setBackground(invalid);
            error = error + "\n Invalid email address";
        }

        String mobile = txtMobile.getText();

        if (mobile.matches("^[0][7][0-9][0-9]{7}$")) {
            if (mobile.equals(oldperson.getMobile())) {
                txtMobile.setBackground(valid);
                person.setMobile(oldperson.getMobile());
            } else {
                person.setMobile(mobile);
                txtMobile.setBackground(updated);
                updates = updates + "\n Mobile updated";
            }
        } else {
            txtMobile.setBackground(invalid);
            error = error + "\n Invalid Mobile number";
        }

        String address = txtAdderss.getText();

        if (!address.isEmpty()) {
            if (address.equals(oldperson.getAddress())) {
                txtAdderss.setBackground(valid);
                person.setAddress(oldperson.getAddress());
            } else {
                person.setAddress(address);
                txtAdderss.setBackground(updated);
                updates = updates + "\n Adderess updated";
            }
        } else {
            txtMobile.setBackground(invalid);
            error = error + "\n invalid adderess number";
        }

        int genindex = cmbGender.getSelectedIndex();

        if (genindex != 0) {
            Gender gender = (Gender) cmbGender.getSelectedItem();
            person.setGender(gender);

            if (gender.getId() == oldperson.getGender().getId()) {
                person.setGender(oldperson.getGender());
                cmbGender.setBackground(valid);


            } else {
                person.setGender(gender);
                cmbGender.setBackground(updated);
                updates = updates + "\n Gender Updated";
            }


        } else {
            cmbGender.setBackground(invalid);
            error = error + "\n Select a Gender";
        }

        if (error.isEmpty()) {

            if (!updates.isEmpty()) {
                int resp = JOptionPane.showConfirmDialog(null, "You have following updates\n\n" + updates);
                if (resp == 0) {

                    String status = PersonController.put(person);

                    if (status.equals("1")) {

                        int srow = tblPerson.getSelectedRow();
                        loadView();

                        tblPerson.setRowSelectionInterval(srow, srow);
                        loadForm();
                        JOptionPane.showMessageDialog(null, "Successfully  updated\n\n" + status);

                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update\n\n" + status);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Nothing to update");


            }

        } else {

            JOptionPane.showMessageDialog(null, "You have following data errors\n\n" + error);


        }


    }
    public void btnDeleteVC(ActionEvent e){

        int resp = JOptionPane.showConfirmDialog(null,"\n Are You Sure to delete following person ?\n"+oldperson.getName());
        if(resp==0){
                String status =PersonController.delete(oldperson);
            if(status.equals("1")){
                loadView();
                loadForm();
                JOptionPane.showMessageDialog(null, "Successfully Deleted");
                clearForm();

            }
            else{
                JOptionPane.showMessageDialog(null, "Faild to Deleted as\n\n"+status);
            }
        }

    }

    public void btnClearAP(ActionEvent e){

        int conf = JOptionPane.showConfirmDialog(null,"Are you sure to clear this form ?");
        if(conf ==0){
            loadForm();
            clearForm();
        }
    }

    public void btnSearchClearAP(ActionEvent e){

        int conf = JOptionPane.showConfirmDialog(null,"Are you sure to clear this form ?");
        if(conf ==0){
            loadView();
            clearSearch();
        }
    }

    public void clearForm(){
        JTextField [] arr = {txtName,txtAdderss,txtMobile,txtEmail,};
        for(JTextField x: arr){
            x.setText("");
            x.setBackground(initial);
        }
        cmbGender.setBackground(initial);

    }
    public void clearSearch(){
        txtSearchName.setText("");
    }

}
