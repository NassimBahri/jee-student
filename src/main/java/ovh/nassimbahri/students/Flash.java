package ovh.nassimbahri.students;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Flash {


    /**
     * Méthode qui permet de créer un message flash
     * @param request Objet représentant la requête utilisateur
     * @param type type du message (echec / success)
     * @param message contenu du message
     */
    public static void create(HttpServletRequest request, String type, String message){
        HttpSession session = request.getSession();
        String content = "";
        if(type.equals("echec")){
            content = "<div style=\"color:red;border: solid 1px red; padding: 10px;\">" + message + "</div>";
        }
        else{
            content = "<div style=\"color:green;border: solid 1px green; padding: 10px;\">" + message + "</div>";
        }
        session.setAttribute("message", content);
    }

    /**
     * Afficher le contenu du message flash
     * @param session Session
     * @return contenu du message
     */
    public static String show(HttpSession session){
        String message = "";
        if(session.getAttribute("message") != null){
            message = (String)session.getAttribute("message");
            session.removeAttribute("message");
        }
        return message;
    }

}
