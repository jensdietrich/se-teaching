package nz.ac.vuw.jenz.easycrud.client;

import nz.ac.vuw.jenz.easycrud.JSONPersistencyService;
import nz.ac.vuw.jenz.easycrud.PersistencyService;

public class Main {

    public static void main (String[] args) {
        PersistencyService service = new JSONPersistencyService();
        f1(service);
    }

    public static void f1(PersistencyService service) {
        service.readAll();
    }
}
