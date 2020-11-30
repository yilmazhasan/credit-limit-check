package com.example.springboot.model;

public class UserForm 
    {
        public String fullname = "";
        public int income = 0;
        public String tckn = "";
        public String phone = "";

        public UserForm(String fullname, int income, String tckn, String phone) {
            this.fullname = fullname;
            this.income = income;
            this.tckn = tckn;
            this.phone = phone;
            // TODO Auto-generated constructor stub
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public int getIncome() {
            return income;
        }

        public void setIncome(int income) {
            this.income = income;
        }

        public String getTckn() {
            return tckn;
        }

        public void setTckn(String tckn) {
            this.tckn = tckn;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

    }
