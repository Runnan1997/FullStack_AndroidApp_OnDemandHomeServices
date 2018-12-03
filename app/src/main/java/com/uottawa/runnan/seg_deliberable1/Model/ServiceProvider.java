package com.uottawa.runnan.seg_deliberable1.Model;

public class ServiceProvider{

    private String _address;

    public ServiceProvider(String _address, String _phoneNumber, String _generalInfo, String _company) {
        this._address = _address;
        this._phoneNumber = _phoneNumber;
        this._generalInfo = _generalInfo;
        this._company = _company;
    }

    public ServiceProvider(String _username, String _address, String _phoneNumber, String _generalInfo, String _company) {
        this._address = _address;
        this._phoneNumber = _phoneNumber;
        this._generalInfo = _generalInfo;
        this._company = _company;
        this._username = _username;
    }

    public ServiceProvider(){}

    public String get_address() {
        return _address;
    }
    public void set_address(String _address) {
        this._address = _address;
    }


    private String _phoneNumber;
    public String get_phoneNumber() {
        return _phoneNumber;
    }
    public void set_phoneNumber(String _phoneNumber) {
        this._phoneNumber = _phoneNumber;
    }


    private String _generalInfo;
    public String get_generalInfo() {
        return _generalInfo;
    }
    public void set_generalInfo(String _generalInfo) {
        this._generalInfo = _generalInfo;
    }

    private String  _username;
    public String get_username() {
        return _username;
    }
    public void set_username(String _username) {
        this._username = _username;
    }

    private String _company;
    public String get_company() {
        return _company;
    }
    public void set_company(String _company) {
        this._company = _company;
    }

 /*   private String dates;
    public String getDates() {
        return dates;
    }
    public void setDates(String dates) {
        this.dates = dates;
    }


    private String time;
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
*/
    private String _service;
    public String get_service() {
        return _service;
    }
    public void set_service(String _service) {
        this._service = _service;
    }



}
