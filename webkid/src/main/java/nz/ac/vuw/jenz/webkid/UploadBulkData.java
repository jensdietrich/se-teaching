package nz.ac.vuw.jenz.webkid;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * Upload bulk data via serialization. Similar features were used in jenkins (remote CLI)
 * This illustrates a deserialization vulnerability.
 * @author  Jens Dietrich
 */
@WebServlet(name = "UploadBulkData", urlPatterns = "/admin/upload")
public class UploadBulkData extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (ObjectInputStream in = new ObjectInputStream(request.getInputStream())) {
            Object uploaded = in.readObject();
            if (uploaded instanceof List) {
                List<Client> clients = (List<Client>)uploaded;
                DataManager.importClients(clients);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


}