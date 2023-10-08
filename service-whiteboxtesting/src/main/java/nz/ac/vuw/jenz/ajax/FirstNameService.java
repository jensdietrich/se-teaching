package nz.ac.vuw.jenz.ajax;

import java.io.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Servlet providing the firstname service used by ajax.
 * @author  Jens Dietrich
 */
public class FirstNameService extends HttpServlet {

    public static final String[] FIRST_NAMES_DB = {"Joel","Michael","Rachel","Joshua","Joseph","David","Jeremy","Rebecca","Adam","Elizabeth","James","Anne","Mary","Sarah","Daniel","John","Susan","Albert","Norbert","Robert","Alfred","Adelaide","Edward","Ashley","Aengus","Alan","Brian","Brigid","Br�dorBride","M�rag","Niamh","Conor","Guinevere","Fionnghuala","Donovan","Malcolm","Medbh","Maeve","Eoghan","Gr�inne","Ian","Eoin","Owen","Rowan","Kaylyn","Bronwen","Gwendolen","Ryan","Se�n","StephenStephanos","AlexanderAlexandros","AndrewAndreas","Peter","Petros","George","Ge�rgios","Christopher","Christophoros","Melissa","Margaret","Catherine","Jason","Iason","Laura","Victoria","Mark","Marcus","Diana","Paul","Paulus","Lily","Mavis","Amber","Serenity","Sky","Skye","Sienna","Sierra","Apple","Ember","Jewel","Jasmine","Coral","Laurel","Lotus","Lavendar","Lilac","Blaze","Ridge","Jasper","Jade","Sunny","Autumn","Blue","Bleu","Brooke","Fawn","Dawn","Daisy","Rose","Hyacinth","Iris","Ivy","Harmony","Charisma","Faith","Grace","Hope","Breezy","Rainy","tormy","Lukas","Sarah","Florian","Anna","Tobias","Julia","David","Laura","Alexander","Lena","Fabian","Hannah","Michael","Lisa","Julian","Katharina","Daniel","Leonie","Simon","Vanessa"};

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String n = request.getParameter("name");
        if (n==null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        String names = Arrays.stream(FIRST_NAMES_DB)
            .filter(name -> name.startsWith(n))
            .collect(Collectors.joining(" "));
        out.println(names);
        out.close();
    }

    
}
